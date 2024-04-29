package com.example.GameImdb.model.view;


import com.example.GameImdb.model.entity.GameCategoryEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class GameDetailsViewModel {
    private Long id;
    private String name;
    private String description;
    private Integer ageRestriction;
    private String pictureUrl;
    private String videoUrl;
    private Double avgRating;
    private String author;
    private String releaseDate;
    private String company;

    private boolean isOwner;

    public Long getId() {
        return id;
    }

    public GameDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameDetailsViewModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public GameDetailsViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameDetailsViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }



    public String getAuthor() {
        return author;
    }

    public GameDetailsViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public GameDetailsViewModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public GameDetailsViewModel setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public GameDetailsViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(releaseDate);
        return this;
    }

    public String getCompany() {
        return company;
    }

    public GameDetailsViewModel setCompany(String company) {
        this.company = company;
        return this;
    }
}
