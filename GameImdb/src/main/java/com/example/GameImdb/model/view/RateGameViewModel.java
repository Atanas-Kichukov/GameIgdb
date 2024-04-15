package com.example.GameImdb.model.view;

public class RateGameViewModel {
    private Long id;
    private Double avgRating;

    public Long getId() {
        return id;
    }

    public RateGameViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public RateGameViewModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }
}
