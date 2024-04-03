package com.example.GameImdb.model.binding;

import com.example.GameImdb.model.entity.GameCategoryEntity;
import com.example.GameImdb.model.entity.PictureEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import java.util.Set;

public class GameAddBindingModel {
    private Long id;
    @Size(min = 5, max = 30)
    private String name;

    @NotNull
    private Integer ageRestriction;
    @NotNull
    private Set<GameCategoryEntity> categories;

    private Set<PictureEntity> pictures;
    @NotNull
    private String videoUrl;
    @Size(min = 10)
    private String description;
    @PastOrPresent
    private LocalDate releaseDate;
    @Size(min = 2)
    private String company;
    private Double rating;

    public Long getId() {
        return id;
    }

    public GameAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameAddBindingModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public Set<GameCategoryEntity> getCategories() {
        return categories;
    }

    public GameAddBindingModel setCategories(Set<GameCategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public GameAddBindingModel setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }



    public String getCompany() {
        return company;
    }

    public GameAddBindingModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public GameAddBindingModel setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
