package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.PictureEntity;
import com.example.GameImdb.repository.PictureRepository;
import com.example.GameImdb.service.CloudinaryService;
import com.example.GameImdb.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public PictureEntity createPictureEntity(MultipartFile file) throws IOException {
        PictureEntity newPicture = cloudinaryService.upload(file);
        return pictureRepository.save(newPicture);
    }

}
