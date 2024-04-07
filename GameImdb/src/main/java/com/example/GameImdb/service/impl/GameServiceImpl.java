package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.entity.PictureEntity;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.GameCategoryService;
import com.example.GameImdb.service.GameService;
import com.example.GameImdb.service.PictureService;
import com.example.GameImdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final GameCategoryService gameCategoryService;

    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, UserService userService, PictureService pictureService, GameCategoryService gameCategoryService) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.gameCategoryService = gameCategoryService;
    }

    @Override
    public void addNewGame(GameAddServiceModel gameAddServiceModel, String username) throws IOException {

        GameEntity newGame = modelMapper.map(gameAddServiceModel, GameEntity.class);
        UserEntity author = userService.findByUsername(username);
        //PictureEntity picture = pictureService.createPictureEntity(gameAddServiceModel.getPicture());TODO fix nullPointerException

        newGame.setAuthor(author)
               // .setPicture(picture) TODO fix nullPointerException
                .setRatingCount(1)
                .setCategories(gameAddServiceModel
                        .getCategories()
                        .stream()
                        .map(gameCategoryService::findCategoryByName)
                        .collect(Collectors.toSet()));

        gameRepository.save(newGame);
    }
}
