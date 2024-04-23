package com.example.GameImdb.model.view;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentViewModel {
    private Long commentId;
    private String message;
    private String author;
    private String created;
    private boolean canDelete;


    public String getAuthor() {
        return author;
    }

    public CommentViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }



    public Long getCommentId() {
        return commentId;
    }

    public CommentViewModel setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public CommentViewModel setCreated(LocalDateTime created) {
        this.created = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy").format(created);
        return this;
    }
}
