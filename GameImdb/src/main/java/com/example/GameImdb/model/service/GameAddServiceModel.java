package com.example.GameImdb.model.service;

import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public class GameAddServiceModel {
    private Long id;
    private String name;
    private Integer ageRestriction;
    private MultipartFile picture;
    private String videoUrl;
    private String description;
    private String company;
    private LocalDate releaseDate;
    private Set<GameCategoryEnum> categories;
    private UserEntity author;
    private Double averageRating;
    private Integer ratingCount;

    public Long getId() {
        return id;
    }

    public GameAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameAddServiceModel setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public GameAddServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameAddServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public GameAddServiceModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameAddServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Set<GameCategoryEnum> getCategories() {
        return categories;
    }

    public GameAddServiceModel setCategories(Set<GameCategoryEnum> categories) {
        this.categories = categories;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public GameAddServiceModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public GameAddServiceModel setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public GameAddServiceModel setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }
}
