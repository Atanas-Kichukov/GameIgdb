package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.GameCategoryEntity;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import com.example.GameImdb.repository.GameCategoryRepository;
import com.example.GameImdb.service.GameCategoryService;
import com.example.GameImdb.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GameCategoryServiceImpl implements GameCategoryService {
    private final GameCategoryRepository gameCategoryRepository;

    public GameCategoryServiceImpl(GameCategoryRepository gameCategoryRepository) {
        this.gameCategoryRepository = gameCategoryRepository;
    }

    @Override
    public GameCategoryEntity findCategoryByName(GameCategoryEnum gameCategoryEnum) {
        return gameCategoryRepository.findByCategories(gameCategoryEnum)
                .orElseThrow(() -> new ObjectNotFoundException("This category doesn't exist"));

    }
}
