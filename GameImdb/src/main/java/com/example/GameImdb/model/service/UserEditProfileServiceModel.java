package com.example.GameImdb.model.service;

public class UserEditProfileServiceModel {
    private String lastName;
    private String email;
    private Integer age;


    public String getEmail() {
        return email;
    }

    public UserEditProfileServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEditProfileServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditProfileServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
