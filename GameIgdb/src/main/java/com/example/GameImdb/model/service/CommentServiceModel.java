package com.example.GameImdb.model.service;

public class CommentServiceModel {
    private Long gameId;
    private String message;
    private String author;

    public String getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

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


}
