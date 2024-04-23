package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.entity.UserRoleEntity;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.repository.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class GameImdbUserServiceImplTest {

    private GameImdbUserServiceImpl serviceToTest;
    private UserEntity testUser;
    @Mock
    private UserRepository mockUserRepository;


    @BeforeEach
    void init() {
        serviceToTest = new GameImdbUserServiceImpl(mockUserRepository);
        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
        testUser = new UserEntity()
                .setUsername("test")
                .setEmail("email@test")
                .setRoles(Set.of(adminRole, userRole))
                .setFirstName("user")
                .setLastName("testing")
                .setPassword("123456")
                .setAge(13);

    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () ->
                    serviceToTest.loadUserByUsername("invalid@Username")

        );
    }

    @Test
    void testUserFound(){
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(actual.getUsername(),testUser.getUsername());

        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        Assertions.assertEquals(expectedRoles,actualRoles);


    }
}
