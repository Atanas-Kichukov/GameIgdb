package com.example.GameImdb.model.binding;

import javax.validation.constraints.*;

public class UserEditProfileBindingModel {
    @NotBlank
    @Size(min = 2,max = 20)
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @Min(10)
    @NotNull
    private Integer age;

    public String getEmail() {
        return email;
    }

    public UserEditProfileBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEditProfileBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditProfileBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
