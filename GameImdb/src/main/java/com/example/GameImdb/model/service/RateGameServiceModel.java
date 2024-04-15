package com.example.GameImdb.model.service;

public class RateGameServiceModel {
    private Long id;
    private Double avgRating;

    public Long getId() {
        return id;
    }

    public RateGameServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public RateGameServiceModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }
}
