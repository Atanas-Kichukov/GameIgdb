package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.CommentEntity;
import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.service.CommentServiceModel;
import com.example.GameImdb.model.view.CommentViewModel;
import com.example.GameImdb.repository.CommentRepository;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.CommentService;
import com.example.GameImdb.service.GameService;
import com.example.GameImdb.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final UserService userService;
    private final GameService gameService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, GameService gameService) {

        this.commentRepository = commentRepository;
        this.userService = userService;
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

        UserEntity author = userService.findByUsername(commentServiceModel.getAuthor());

        GameEntity game = gameService.findById(commentServiceModel.getGameId());

        CommentEntity comment = new CommentEntity()
                .setAuthor(author)
                .setGame(game)
                .setMessage(commentServiceModel.getMessage())
                .setCreated(LocalDateTime.now());

        CommentEntity savedComment = commentRepository.save(comment);
        return mapAsComment(savedComment, commentServiceModel.getAuthor());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity, String username) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel
                .setCommentId(commentEntity.getId())
                .setCanDelete(commentEntity.getAuthor().getUsername().equals(username))
                .setCreated(commentEntity.getCreated())
                .setMessage(commentEntity.getMessage())
                .setAuthor(commentEntity.getAuthor().getUsername());

        return commentViewModel;

    }
}
