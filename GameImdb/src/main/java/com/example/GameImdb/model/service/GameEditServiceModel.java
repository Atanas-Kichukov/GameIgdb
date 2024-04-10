package com.example.GameImdb.model.service;

public class GameEditServiceModel {
    private Long id;
    private String videoUrl;
    private Integer ageRestriction;
    private String description;

    public Long getId() {
        return id;
    }

    public GameEditServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameEditServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameEditServiceModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEditServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
