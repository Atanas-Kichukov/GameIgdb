package com.example.GameImdb.model.entity;


import javax.persistence.*;
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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<GameCategoryEntity> categories;
    @OneToOne
    private PictureEntity picture;
    @Column(nullable = false)
    private String videoUrl;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private Double avgRating;
    private Integer ratingCount;
    @ManyToOne
    private UserEntity author;

    @OneToMany(mappedBy = "game",fetch = FetchType.LAZY)
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

    public List<GameCategoryEntity> getCategories() {
        return categories;
    }

    public GameEntity setCategories(List<GameCategoryEntity> categories) {
        this.categories = categories;
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


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    public PictureEntity getPicture() {
        return picture;
    }

    public GameEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public GameEntity setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public GameEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public GameEntity setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }
}
