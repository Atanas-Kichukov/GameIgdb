package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.*;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.model.service.CommentServiceModel;
import com.example.GameImdb.model.view.CommentViewModel;
import com.example.GameImdb.repository.CommentRepository;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.GameService;
import com.example.GameImdb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    private CommentServiceImpl testService;
    private GameEntity game1;
    private GameEntity game2;
    private UserEntity testUser;
    private CommentEntity testComment;


    @Mock
    public CommentRepository commentRepository;
    @Mock
    public UserService userService;
    @Mock
    public GameService gameService;

    @Mock
    public GameRepository gameRepository;



    @BeforeEach
    public void setUp() {
        testService = new CommentServiceImpl(commentRepository,userService,gameService);

        GameCategoryEntity testCategory = new GameCategoryEntity().setCategories(GameCategoryEnum.ACTION);


        PictureEntity pictureTest = new PictureEntity()
                .setPublicId("213141432")
                .setUrl("randomUrl");


        UserRoleEntity user = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity admin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);




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
                .setRoles(Set.of(admin, user))
                .setFirstName("user")
                .setLastName("testing")
                .setPassword("123456")
                .setAge(13)
                .setRatedGames(new ArrayList<>());
        game1.setAuthor(testUser);


        testComment = new CommentEntity()
                .setAuthor(testUser)
                .setGame(game1)
                .setCreated(LocalDateTime.now())
                .setMessage("testTestTestTEst");

        game1.setComments(List.of(testComment));

    }

    @Test
    public void testGetComments(){
        long testId = 1;
        game1.setId(testId);
        when(gameService.findById(game1.getId())).thenReturn(game1);

        List<CommentViewModel> testComments = testService.getComments(testId, testUser.getUsername());

        assertEquals(testComments.size(),1 );

    }
}