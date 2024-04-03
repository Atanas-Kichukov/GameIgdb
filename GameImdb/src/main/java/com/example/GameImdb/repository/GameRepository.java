package com.example.GameImdb.repository;

import com.example.GameImdb.model.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Long> {
}
