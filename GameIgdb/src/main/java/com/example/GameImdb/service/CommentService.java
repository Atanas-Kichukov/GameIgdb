package com.example.GameImdb.service;

import com.example.GameImdb.model.service.CommentServiceModel;
import com.example.GameImdb.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel> getComments(Long gameId, String username);
    CommentViewModel createComment(CommentServiceModel commentServiceModel);
}
