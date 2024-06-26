package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.entity.PictureEntity;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.entity.UserRoleEntity;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.model.service.GameEditServiceModel;
import com.example.GameImdb.model.service.RateGameServiceModel;
import com.example.GameImdb.model.view.GameDetailsViewModel;
import com.example.GameImdb.model.view.GameEditViewModel;
import com.example.GameImdb.model.view.GameViewModel;
import com.example.GameImdb.model.view.RateGameViewModel;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.*;
import com.example.GameImdb.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final GameCategoryService gameCategoryService;
    private final CloudinaryService cloudinaryService;

    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, UserService userService, PictureService pictureService, GameCategoryService gameCategoryService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.gameCategoryService = gameCategoryService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addNewGame(GameAddServiceModel gameAddServiceModel, String username) throws IOException {

        GameEntity newGame = modelMapper.map(gameAddServiceModel, GameEntity.class);
        UserEntity author = userService.findByUsername(username);
        PictureEntity picture = pictureService.createPictureEntity(gameAddServiceModel.getPicture());

        newGame.setAuthor(author)
                .setPicture(picture)
                .setRatingCount(1)
                .setCategories(gameAddServiceModel
                        .getCategories()
                        .stream()
                        .map(gameCategoryService::findCategoryByName)
                        .collect(Collectors.toList()));

        gameRepository.save(newGame);
    }

    @Transactional
    @Override
    public List<GameViewModel> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameEntity -> modelMapper.map(gameEntity, GameViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public GameDetailsViewModel getGameDetailsViewById(Long id, String name) {
        GameEntity gameEntity = findById(id);
        GameDetailsViewModel gameDetailsViewModel = modelMapper.map(gameEntity, GameDetailsViewModel.class);
        gameDetailsViewModel.setAuthor(gameEntity.getAuthor().getUsername());
        gameDetailsViewModel.setOwner(isOwner(id, name));
        return gameDetailsViewModel;
    }

    @Override
    public GameEntity findById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Game with id " + id + " is not found"));
    }

    @Override
    public boolean isOwner(Long id, String username) {
        GameEntity gameEntity = findById(id);
        UserEntity user = userService.findByUsername(username);



        return isAdmin(user) || user.getUsername().equals(gameEntity.getAuthor().getUsername());

    }

    @Override
    public GameEditViewModel getEditViewModel(Long id) {
        GameEntity game = findById(id);
        return modelMapper.map(game, GameEditViewModel.class);
    }

    @Override
    public void editGame(GameEditServiceModel gameEditServiceModel) {
        GameEntity game = findById(gameEditServiceModel.getId());

        game.setVideoUrl(gameEditServiceModel.getVideoUrl())
                .setAgeRestriction(gameEditServiceModel.getAgeRestriction())
                .setDescription(gameEditServiceModel.getDescription());

        gameRepository.save(game);

    }

    @Override
    public void deleteGame(Long id) {
        GameEntity game = findById(id);
        if(cloudinaryService.delete(game.getPicture().getPublicId())) {
            gameRepository.delete(game);
        }
    }

    @Override
    public void addRating(RateGameServiceModel rateGameServiceModel, String username) {
        UserEntity user = userService.findByUsername(username);
        GameEntity game = findById(rateGameServiceModel.getId());
        user.getRatedGames().add(game);
        int allRatings = game.getRatingCount() + 1;
        double newRating = (game.getAvgRating() * game.getRatingCount() + rateGameServiceModel.getAvgRating()) /allRatings;
        double roundedRating = Math.round(newRating * 10) / 10.0;
        game.setRatingCount(game.getRatingCount() + 1);
        game.setAvgRating(roundedRating);
        gameRepository.save(game);
        userService.save(user);
    }

    @Override
    public RateGameViewModel getRateGameViewModel(Long id) {
        GameEntity game = findById(id);
        return modelMapper.map(game, RateGameViewModel.class);
    }

    @Override
    public boolean hasUserAlreadyRatedGame(String username, GameEntity game) {
        UserEntity user = userService.findByUsername(username);
        List<GameEntity> ratedGames = user.getRatedGames();
        for (GameEntity ratedGame : ratedGames) {
            if (ratedGame.getName().equals(game.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin(UserEntity user){
        return user.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(roleEnum -> roleEnum == UserRoleEnum.ADMIN);
    }
}
