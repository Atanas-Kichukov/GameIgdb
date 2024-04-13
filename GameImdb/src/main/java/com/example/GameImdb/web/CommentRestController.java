package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.CommentAddBindingModel;
import com.example.GameImdb.model.service.CommentServiceModel;
import com.example.GameImdb.model.validation.ApiError;
import com.example.GameImdb.model.view.CommentViewModel;
import com.example.GameImdb.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api/{gameId}/comments")
    public ResponseEntity<List<CommentViewModel>> getAllComments(@PathVariable Long gameId,
                                                                 Principal principal) {
        return ResponseEntity.ok(commentService.getComments(gameId, principal.getName()));
    }

    @PostMapping("/api/{gameId}/comments")
    public ResponseEntity<CommentViewModel> newComment(@PathVariable Long gameId,
                                                       Principal principal,
                                                       @RequestBody @Valid CommentAddBindingModel commentAddBindingModel){

        CommentServiceModel commentServiceModel = modelMapper.map(commentAddBindingModel, CommentServiceModel.class);
        commentServiceModel.setGameId(gameId)
                .setAuthor(principal.getName());
        CommentViewModel commentViewModel = commentService.createComment(commentServiceModel);

        URI locationOfNewComment = URI.create(String.format("/api/%s/comments/%s",gameId,commentViewModel.getCommentId()));
        return ResponseEntity.created(locationOfNewComment).body(commentViewModel);
    }
    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exception){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exception.getFieldErrors().forEach(fe -> apiError.addFieldWithError(fe.getField()));
        return ResponseEntity.badRequest().body(apiError);
    }
}
