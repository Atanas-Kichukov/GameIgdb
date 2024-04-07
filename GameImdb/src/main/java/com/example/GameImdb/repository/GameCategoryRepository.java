package com.example.GameImdb.repository;

import com.example.GameImdb.model.entity.GameCategoryEntity;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameCategoryRepository extends JpaRepository<GameCategoryEntity,Long> {
    Optional<GameCategoryEntity> findByCategories(GameCategoryEnum gameCategoryEnum);



}
