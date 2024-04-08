package com.example.GameImdb.model.view;

public class GameViewModel {
    private Long id;
    private String name;
    private String description;
    private String pictureUrl;
    private Double avgRating;

    public Long getId() {
        return id;
    }

    public GameViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public GameViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public GameViewModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }
}
