package com.example.GameImdb.service;

import com.example.GameImdb.model.entity.GameCategoryEntity;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;

public interface GameCategoryService {
    GameCategoryEntity findCategoryByName(GameCategoryEnum gameCategoryEnum);
}
