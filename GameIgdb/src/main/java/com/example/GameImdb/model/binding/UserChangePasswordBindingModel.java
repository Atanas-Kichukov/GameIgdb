package com.example.GameImdb.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserChangePasswordBindingModel {
    private Long id;
    @NotBlank
    @Size(min = 5,max = 30)
    private String currentPassword;
    @NotBlank
    @Size(min = 5,max = 30)
    private String newPassword;
    @NotBlank
    @Size(min = 5,max = 30)
    private String confirmPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public UserChangePasswordBindingModel setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordBindingModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserChangePasswordBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserChangePasswordBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
