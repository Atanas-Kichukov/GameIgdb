package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.*;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.model.service.GameEditServiceModel;
import com.example.GameImdb.model.service.RateGameServiceModel;
import com.example.GameImdb.model.view.GameDetailsViewModel;
import com.example.GameImdb.model.view.GameEditViewModel;
import com.example.GameImdb.model.view.GameViewModel;
import com.example.GameImdb.model.view.RateGameViewModel;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.CloudinaryService;
import com.example.GameImdb.service.GameCategoryService;
import com.example.GameImdb.service.PictureService;
import com.example.GameImdb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {


    private GameServiceImpl testService;
    private GameEntity game1;
    private GameEntity game2;
    private UserEntity testUser;


    @Mock
    private GameRepository gameRepository;
    @Mock
    private CloudinaryService cloudinaryService;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PictureService pictureService;
    @Mock
    private GameCategoryService gameCategoryService;


    @BeforeEach
    public void setUp() {
        testService = new GameServiceImpl(modelMapper, gameRepository, userService, pictureService, gameCategoryService, cloudinaryService);

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

    }

    @Test
    public void testGetAllGames() {
        when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

        List<GameViewModel> allGamesTest = testService.getAllGames();
        assertEquals(allGamesTest.size(), 2);

    }

    @Test
    public void testGetGameDetailsViewById() {
        GameDetailsViewModel gameDetailsViewModelTest = new GameDetailsViewModel()
                .setName(game1.getName())
                .setDescription(game1.getDescription())
                .setAgeRestriction(game1.getAgeRestriction())
                .setPictureUrl(game1.getPicture().getUrl())
                .setVideoUrl(game1.getVideoUrl());
        int testId = 1;

        when(modelMapper.map(game1, GameDetailsViewModel.class)).thenReturn(gameDetailsViewModelTest);

        when(gameRepository.findById((long) testId)).thenReturn(Optional.of(game1));

        when(userService.findByUsername(testUser.getUsername())).thenReturn(testUser);


        GameDetailsViewModel result = testService.getGameDetailsViewById((long) testId, testUser.getUsername());
        assertNotNull(result);
        assertEquals(result.getName(), gameDetailsViewModelTest.getName());
        assertEquals(result.getAgeRestriction(), gameDetailsViewModelTest.getAgeRestriction());
        assertEquals(result.getDescription(), gameDetailsViewModelTest.getDescription());
        assertEquals(result.getVideoUrl(), gameDetailsViewModelTest.getVideoUrl());

    }

    @Test
    public void testEditGame() {
        long testId = 1;

        when(gameRepository.findById(testId)).thenReturn(Optional.of(game1));

        GameEditServiceModel gameEditServiceModelTest = new GameEditServiceModel()
                .setId(testId)
                .setDescription(game1.getDescription())
                .setAgeRestriction(game1.getAgeRestriction())
                .setVideoUrl(game1.getVideoUrl());
        testService.editGame(gameEditServiceModelTest);

        verify(gameRepository, times(1)).save(game1);
    }

    @Test
    public void testDeleteGame() {
        long testId = 1;

        when(gameRepository.findById(testId)).thenReturn(Optional.of(game1));

        when(cloudinaryService.delete(game1.getPicture().getPublicId())).thenReturn(true);

        testService.deleteGame(testId);

    }

    @Test
    public void testGetEditViewModel() {
        long testId = 1;

        when(gameRepository.findById(testId)).thenReturn(Optional.of(game1));

        GameEditViewModel gameEditViewModelTest = new GameEditViewModel()
                .setVideoUrl(game1.getVideoUrl())
                .setDescription(game1.getDescription())
                .setAgeRestriction(game1.getAgeRestriction());

        when(modelMapper.map(game1, GameEditViewModel.class)).thenReturn(gameEditViewModelTest);
        GameEditViewModel result = testService.getEditViewModel(testId);
        assertNotNull(result);
        assertEquals(result.getAgeRestriction(), gameEditViewModelTest.getAgeRestriction());
        assertEquals(result.getDescription(), gameEditViewModelTest.getDescription());
        assertEquals(result.getVideoUrl(), gameEditViewModelTest.getVideoUrl());
    }

    @Test
    public void testGetRateGameViewModel() {
        long testId = 1;
        when(gameRepository.findById(testId)).thenReturn(Optional.of(game1));

        RateGameViewModel rateGameViewModelTest = new RateGameViewModel()
                .setAvgRating(game1.getAvgRating());

        when(modelMapper.map(game1, RateGameViewModel.class)).thenReturn(rateGameViewModelTest);
        RateGameViewModel result = testService.getRateGameViewModel(testId);
        assertNotNull(result);
        assertEquals(result.getAvgRating(), game1.getAvgRating());
    }

    @Test
    public void testAddNewGame() throws IOException {

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        GameAddServiceModel gameAddServiceModelTest = new GameAddServiceModel()
                .setName(game1.getName())
                .setAgeRestriction(game1.getAgeRestriction())
                .setPicture(multipartFile)
                .setVideoUrl(game1.getVideoUrl())
                .setDescription(game1.getDescription())
                .setCompany(game1.getCompany())
                .setReleaseDate(game1.getReleaseDate())
                .setAvgRating(game1.getAvgRating())
                .setCategories(Set.of(GameCategoryEnum.CO_OP));

        String username = "test";
        when(modelMapper.map(gameAddServiceModelTest, GameEntity.class)).thenReturn(game1);

        testService.addNewGame(gameAddServiceModelTest, username);

    }

    @Test
    public void testAddRating() {
        String username = "test";
        when(userService.findByUsername(username)).thenReturn(testUser);

        RateGameServiceModel rateGameServiceModelTest = new RateGameServiceModel()
                .setAvgRating(2.5)
                        .setId(1L);
        when(gameRepository.findById(rateGameServiceModelTest.getId())).thenReturn(Optional.of(game1));

        testService.addRating(rateGameServiceModelTest,username);

        assertEquals(game1.getAvgRating(),3);

    }

    @Test
    public void testUserHasAlreadyRatedGame() {
    String username = "test";
    when(userService.findByUsername(username)).thenReturn(testUser);

    List<GameEntity> testRatedGames = testUser.getRatedGames();
    testUser.setRatedGames(List.of(game1));
    assertTrue(testService.hasUserAlreadyRatedGame(username,game1));

    }
    @Test
    public void testUserHasntRatedGame(){
        String username = "test";
        when(userService.findByUsername(username)).thenReturn(testUser);
        List<GameEntity> testRatedGames = testUser.getRatedGames();
        testUser.setRatedGames(List.of(game1));
        assertFalse(testService.hasUserAlreadyRatedGame(username,game2));
    }
}