package com.example.GameImdb.model.binding;

import javax.validation.constraints.NotNull;

public class RateGameBindingModel {
    private Long id;
    @NotNull
    private Double avgRating;

    public Double getAvgRating() {
        return avgRating;
    }

    public RateGameBindingModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RateGameBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
