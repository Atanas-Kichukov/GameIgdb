package com.example.GameImdb.repository;

import com.example.GameImdb.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
