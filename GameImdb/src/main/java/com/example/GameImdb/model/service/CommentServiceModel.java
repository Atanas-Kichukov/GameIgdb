package com.example.GameImdb.model.service;

public class CommentServiceModel {
    private Long gameId;
    private String message;
    private String creator;

    public Long getGameId() {
        return gameId;
    }

    public CommentServiceModel setGameId(Long gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public CommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
