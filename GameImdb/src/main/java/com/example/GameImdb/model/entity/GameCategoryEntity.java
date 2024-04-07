package com.example.GameImdb.model.entity;

import com.example.GameImdb.model.entity.enums.GameCategoryEnum;


import javax.persistence.*;

@Entity
@Table(name = "game_categories")
public class GameCategoryEntity extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GameCategoryEnum categories;

    public GameCategoryEnum getCategories() {
        return categories;
    }

    public GameCategoryEntity setCategories(GameCategoryEnum categories) {
        this.categories = categories;
        return this;
    }
}
