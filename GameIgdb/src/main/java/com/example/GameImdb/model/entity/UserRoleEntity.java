package com.example.GameImdb.model.entity;

import com.example.GameImdb.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
