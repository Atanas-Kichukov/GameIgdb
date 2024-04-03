package com.example.GameImdb.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer ageRestriction;
    @ManyToMany
    private Set<GameCategoryEntity> categories;
    @OneToMany
    private Set<PictureEntity> pictures;
    @Column(nullable = false)
    private String videoUrl;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(nullable = false)
    private String company;
    @Positive
    @Column(nullable = false)
    private Double rating;
    @ManyToOne
    private UserEntity publisher;
    @OneToMany(mappedBy = "game")
    private List<CommentEntity> comments;

    public List<CommentEntity> getComments() {
        return comments;
    }

    public GameEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public GameEntity setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public Set<GameCategoryEntity> getCategories() {
        return categories;
    }

    public GameEntity setCategories(Set<GameCategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public GameEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public GameEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEntity setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getCompany() {
        return company;
    }

    public GameEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public UserEntity getPublisher() {
        return publisher;
    }

    public GameEntity setPublisher(UserEntity publisher) {
        this.publisher = publisher;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public GameEntity setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
