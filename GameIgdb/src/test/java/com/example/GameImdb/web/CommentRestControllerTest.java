package com.example.GameImdb.web;


import com.example.GameImdb.model.binding.CommentAddBindingModel;
import com.example.GameImdb.model.entity.CommentEntity;
import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.repository.CommentRepository;
import com.example.GameImdb.repository.GameRepository;
import com.example.GameImdb.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WithMockUser("testUser")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {

    private static final String COMMENT_1 = "first Comment";
    private static final String COMMENT_2 = "second Comment";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser = new UserEntity()
                .setUsername("testUser")
                .setEmail("email@test")
                .setFirstName("user")
                .setLastName("testing")
                .setPassword("123456")
                .setAge(13);

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        gameRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    void testGetComments() throws Exception {
        var game = initComments(initGames());

        mockMvc.perform(get("/api/" + game.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_1)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComment() throws Exception {
        CommentAddBindingModel testComment = new CommentAddBindingModel()
                .setMessage(COMMENT_1);

        var emptyGame = initGame();

        mockMvc.perform(post("/api/" + emptyGame.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", matchesPattern("/api/" + emptyGame.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));
    }

    private GameEntity initGame() {
        GameEntity testGame = new GameEntity();
        testGame.setName("Testing game")
                .setDescription("Description")
                .setAvgRating(4.5)
                .setAgeRestriction(10)
                .setVideoUrl("some url")
                .setCompany("testCompany")
                .setReleaseDate(LocalDate.now());
        return gameRepository.save(testGame);
    }

    private GameEntity initComments(GameEntity game) {

        CommentEntity testComment = new CommentEntity();
        testComment.setCreated(LocalDateTime.now());
        testComment.setAuthor(testUser);
        testComment.setMessage(COMMENT_1);
        testComment.setGame(game);

        CommentEntity testComment2 = new CommentEntity();
        testComment2.setCreated(LocalDateTime.now());
        testComment2.setAuthor(testUser);
        testComment2.setMessage(COMMENT_2);
        testComment2.setGame(game);

        game.setComments(List.of(testComment, testComment2));
        return gameRepository.save(game);
    }

    private GameEntity initGames() {
        GameEntity testGame = new GameEntity();
        testGame.setName("Testing game")
                .setDescription("Description")
                .setAvgRating(4.5)
                .setAgeRestriction(10)
                .setVideoUrl("some url")
                .setCompany("testCompany")
                .setReleaseDate(LocalDate.now());
        return gameRepository.save(testGame);

    }
}
