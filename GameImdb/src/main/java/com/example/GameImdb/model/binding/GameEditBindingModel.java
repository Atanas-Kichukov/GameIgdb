package com.example.GameImdb.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class GameEditBindingModel {
    private Long id;

    @NotNull
    private String videoUrl;
    @NotNull
    @Positive
    private Integer ageRestriction;
    @Size(min = 10)
    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public GameEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameEditBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameEditBindingModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
