package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.*;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.model.service.UserEditProfileServiceModel;
import com.example.GameImdb.model.service.UserServiceModel;
import com.example.GameImdb.model.view.GameViewModel;
import com.example.GameImdb.repository.UserRepository;
import com.example.GameImdb.repository.UserRoleRepository;
import com.example.GameImdb.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl testService;
    private UserEntity testUser;
    private UserRoleEntity user;
    private GameEntity game1;
    private GameEntity game2;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private GameImdbUserServiceImpl gameImdbUserService;

    @BeforeEach
    public void setUp() {
        testService = new UserServiceImpl(userRepository, modelMapper, userRoleRepository, passwordEncoder, gameImdbUserService);

        user = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity admin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

        GameCategoryEntity testCategory = new GameCategoryEntity().setCategories(GameCategoryEnum.ACTION);

        PictureEntity pictureTest = new PictureEntity()
                .setPublicId("213141432")
                .setUrl("randomUrl");

        game1 = new GameEntity()
                .setAgeRestriction(12)
                .setName("Test")
                .setPicture(pictureTest)
                .setReleaseDate(LocalDate.now())
                .setCompany("testCompany")
                .setAvgRating(3.5)
                .setVideoUrl("randomUrl")
                .setDescription("test Description")
                .setAuthor(testUser)
                .setCategories(List.of(testCategory))
                .setRatingCount(1);

        game2 = new GameEntity()
                .setAgeRestriction(13)
                .setName("Test2")
                .setPicture(pictureTest)
                .setReleaseDate(LocalDate.now())
                .setCompany("testCompany2")
                .setAvgRating(5.3)
                .setVideoUrl("randomUrl2")
                .setDescription("test Description2")
                .setRatingCount(1);


        testUser = new UserEntity()
                .setUsername("test")
                .setEmail("email@test")
                .setFirstName("user")
                .setLastName("testing")
                .setPassword("123456")
                .setAge(13)
                .setRatedGames(new ArrayList<>())
                        .setGames(Set.of(game1,game2));
        game1.setAuthor(testUser);
        game2.setAuthor(testUser);
    }

    @Test
    public void testRegisterAndLoginUser(){
        when(userRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(user);

        UserServiceModel userServiceModel = new UserServiceModel()
                .setUsername(testUser.getUsername())
                .setEmail(testUser.getEmail())
                .setFirstName(testUser.getFirstName())
                .setLastName(testUser.getLastName())
                .setPassword(passwordEncoder.encode(testUser.getPassword()))
                .setAge(testUser.getAge())
                .setRoles(Set.of(user));

        UserDetails userDetails = mock(UserDetails.class);
        when(gameImdbUserService.loadUserByUsername(testUser.getUsername())).thenReturn(userDetails);

        testService.registerAndLoginUser(userServiceModel);

    }

    @Test
    public void testEditProfile(){
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn((Optional.of(testUser)));

        UserEditProfileServiceModel testUserEditProfileServiceModel = new UserEditProfileServiceModel()
                .setLastName(testUser.getLastName())
                .setAge(testUser.getAge())
                .setEmail(testUser.getEmail());
        testService.editProfile(testUserEditProfileServiceModel, testUser.getUsername());

        verify(userRepository, times(1)).findByUsername(testUser.getUsername());
        verify(userRepository,times(1)).save(testUser);
    }

    @Test
    public void testFindAllGames(){
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        List<GameViewModel> allGamesByUser = testService.findAllGamesByUser(testUser.getUsername());
        assertEquals(allGamesByUser.size(),2);
    }


}