package com.example.GameImdb.model.binding;

import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import java.util.List;
import java.util.Set;

public class GameAddBindingModel {
    private Long id;
    @Size(min = 5, max = 30)
    private String name;
    @NotNull
    @Positive
    private Integer ageRestriction;
    @NotNull
    private Set<GameCategoryEnum> categories;
    private MultipartFile picture;
    @NotNull
    private String videoUrl;
    @Size(min = 10)
    @NotNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;
    @Size(min = 2)
    @NotNull
    private String company;
    @NotNull
    private Double avgRating;
    private Integer ratingCount;

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



    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    public MultipartFile getPicture() {
        return picture;
    }

    public GameAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public Set<GameCategoryEnum> getCategories() {
        return categories;
    }

    public GameAddBindingModel setCategories(Set<GameCategoryEnum> categories) {
        this.categories = categories;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public GameAddBindingModel setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public GameAddBindingModel setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }
}

