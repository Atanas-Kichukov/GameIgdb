package com.example.GameImdb.service;

import com.example.GameImdb.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {
    PictureEntity createPictureEntity(MultipartFile file) throws IOException;
}
