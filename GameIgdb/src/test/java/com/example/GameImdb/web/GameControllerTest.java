package com.example.GameImdb.web;

import com.example.GameImdb.model.entity.GameCategoryEntity;
import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.entity.PictureEntity;
import com.example.GameImdb.model.entity.enums.GameCategoryEnum;
import com.example.GameImdb.model.view.GameDetailsViewModel;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("testUser")
@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {
    private final static String TEST_GAME_NAME = "testGame";
    private final static Set<GameCategoryEntity> TEST_GAME_CATEGORIES = Collections.singleton(new GameCategoryEntity().setCategories(GameCategoryEnum.ACTION));
    ;
    private final static Integer TEST_GAME_AGE_RESTRICTION = 15;
    private final static String TEST_GAME_VIDEO_URL = "testUtl";
    private final static String TEST_GAME_DESCRIPTION = "testDescription";
    private final static String TEST_GAME_COMPANY = "testCompany";
    private final static Double TEST_GAME_AVG_RATING = 5.5;
    private final static Integer TEST_GAME_RATING_COUNT = 1;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;

    @Test
    public void testAddGame() throws Exception {
        mockMvc.perform(get("/game/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-game"));
    }

}