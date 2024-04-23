package com.example.GameImdb.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class PictureBindingModel {

    private String url;
    private MultipartFile picture;

    public String getUrl() {
        return url;
    }

    public PictureBindingModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
