package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.CommentEntity;
import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.service.CommentServiceModel;
import com.example.GameImdb.model.view.CommentViewModel;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.CommentService;
import com.example.GameImdb.service.GameService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final GameRepository gameRepository;
    private final GameService gameService;

    public CommentServiceImpl(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long gameId, String username) {
        GameEntity game = gameService.findById(gameId);

        return game.getComments()
                .stream()
                .map(commentEntity -> mapAsComment(commentEntity, username))
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
     //TODO
        return null;
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity, String username) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel
                .setCommentId(commentEntity.getId())
                .setCanDelete(commentEntity.getAuthor().getUsername().equals(username))
                .setCreated(LocalDateTime.now())
                .setMessage(commentEntity.getMessage())
                .setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;

    }
}
