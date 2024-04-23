package com.example.GameImdb.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Positive
    @Column(nullable = false)
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    private Set<GameEntity> games;
    @OneToMany(fetch = FetchType.EAGER)
    private List<GameEntity> ratedGames;


    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Set<GameEntity> getGames() {
        return games;
    }

    public UserEntity setGames(Set<GameEntity> games) {
        this.games = games;
        return this;
    }

    public List<GameEntity> getRatedGames() {
        return ratedGames;
    }

    public UserEntity setRatedGames(List<GameEntity> ratedGames) {
        this.ratedGames = ratedGames;
        return this;
    }

}
