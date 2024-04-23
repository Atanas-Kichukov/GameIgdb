package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.UserEditProfileBindingModel;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.entity.UserRoleEntity;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.repository.UserRepository;
import com.example.GameImdb.repository.UserRoleRepository;
import com.example.GameImdb.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private final static String TEST_USERNAME = "testUser";
    private final static String TEST_PASSWORD= "123456";
    private final static String TEST_CONFIRM_PASSWORD = "123456";
    private final static String TEST_EMAIL = "test@user.bg";
    private final static String TEST_FIRST_NAME = "User";
    private final static String TEST_LAST_NAME = "Test";
    private final static int TEST_AGE = 21;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserService userService;


    @BeforeEach
    public void init(){

        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        userRoleRepository.saveAll(List.of(adminRole,userRole));
    }
    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();;
    }



    @Test
    void testOpenRegisterPage() throws Exception {
        mockMvc.perform(
                        get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("firstName",TEST_FIRST_NAME)
                .param("lastName",TEST_LAST_NAME)
                .param("username",TEST_USERNAME)
                .param("password",TEST_PASSWORD)
                .param("confirmPassword",TEST_CONFIRM_PASSWORD)
                .param("email",TEST_EMAIL)
                .param("age",String.valueOf(TEST_AGE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1,userRepository.count());

        Optional<UserEntity> testUser = userRepository.findByUsername(TEST_USERNAME);
        assertTrue(testUser.isPresent());

        UserEntity newUser = testUser.get();

        assertEquals(newUser.getUsername(),TEST_USERNAME);
        assertEquals(newUser.getFirstName(),TEST_FIRST_NAME);
        assertEquals(newUser.getLastName(),TEST_LAST_NAME);
        assertEquals(newUser.getEmail(),TEST_EMAIL);
        assertEquals(newUser.getAge(),(TEST_AGE));
        assertTrue(passwordEncoder.matches(TEST_PASSWORD, newUser.getPassword()));


    }
    @Test
    void testOpenLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

}