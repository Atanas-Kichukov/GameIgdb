package com.example.GameImdb.model.view;

public class GameEditViewModel {
    private Long id;
    private String videoUrl;
    private Integer ageRestriction;
    private String description;


    public String getVideoUrl() {
        return videoUrl;
    }

    public GameEditViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameEditViewModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GameEditViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
