package com.example.GameImdb.service.impl;

import com.cloudinary.Cloudinary;
import com.example.GameImdb.model.entity.PictureEntity;
import com.example.GameImdb.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private static final  String TEMP_FILE = "temp-file";
    private static final  String URL = "url";
    private static final  String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;
    public CloudinaryServiceImpl(Cloudinary cloudinary) {

        this.cloudinary = cloudinary;
    }

    @Override
    public PictureEntity upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.uploader().upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://s3.envato.com/files/10065422/Extra%20Previews/02_Preview2.png");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new PictureEntity().setPublicId(publicId).setUrl(url);
        }finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId,Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
