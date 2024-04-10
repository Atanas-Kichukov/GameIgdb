package com.example.GameImdb.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentAddBindingModel {
    @NotBlank
    @Size(min = 10)
    private String message;

    public String getMessage() {
        return message;
    }

    public CommentAddBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
