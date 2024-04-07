package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.UserChangePasswordBindingModel;
import com.example.GameImdb.model.binding.UserEditProfileBindingModel;
import com.example.GameImdb.model.binding.UserRegisterBindingModel;
import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.service.UserChangePasswordServiceModel;
import com.example.GameImdb.model.service.UserEditProfileServiceModel;
import com.example.GameImdb.model.service.UserServiceModel;
import com.example.GameImdb.model.view.UserProfileViewModel;
import com.example.GameImdb.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean notMatchingPasswords = !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || notMatchingPasswords) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            if (notMatchingPasswords) {
                redirectAttributes.addFlashAttribute("passwordsDontMatch", true);
            }

            return "redirect:register";
        }
        if (!userService.isUsernameFree(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("usernameNotFree", true);
            return "redirect:register";
        }

        userService.registerAndLoginUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:/";
    }

    @PostMapping("login-error")
    public String invalidLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("invalidCredentials", true);
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model, Principal principal) {
        UserEntity currentUser = userService.findByUsername(username);
        UserProfileViewModel userProfileViewModel = modelMapper.map(currentUser, UserProfileViewModel.class);

        model.addAttribute("user", userProfileViewModel);
        model.addAttribute("isCurrentUser", principal.getName().equals(username));
        return "profile";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @GetMapping("/profile/change-password/{username}")
    public String changePassword(@PathVariable String username, Principal principal) {
        return "change-password";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name,#username")
    @PatchMapping("/profile/change-password/{username}")
    public String confirmChangePassword(@PathVariable String username,
                                        @Valid UserChangePasswordBindingModel userChangePasswordBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        Principal principal) {

        boolean passwordsDontMatch = !userChangePasswordBindingModel.getNewPassword().equals(userChangePasswordBindingModel.getConfirmPassword());
        boolean isCurrentPasswordDifferent = !userService.isCurrentPasswordValid(userChangePasswordBindingModel.getCurrentPassword(),username);
        boolean isNewPasswordTheSame = userService.isCurrentPasswordValid(userChangePasswordBindingModel.getNewPassword(),username);

        if(bindingResult.hasErrors() || passwordsDontMatch || isNewPasswordTheSame || isCurrentPasswordDifferent){

            redirectAttributes.addFlashAttribute("userChangePasswordBindingModel",userChangePasswordBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel", bindingResult);

            if (passwordsDontMatch){
                redirectAttributes.addFlashAttribute("currentPasswordAndPasswordMissMatch", true);
            }
            else if(isCurrentPasswordDifferent){
                redirectAttributes.addFlashAttribute("wrongCurrentPassword",true);
            }
            else if(isNewPasswordTheSame){
                redirectAttributes.addFlashAttribute("newPasswordIsntDifferent",true);
            }

            return "redirect:/users/profile/change-password/" + username;
        }
        UserChangePasswordServiceModel userChangePasswordServiceModel = modelMapper.map(userChangePasswordBindingModel, UserChangePasswordServiceModel.class);
        userService.userChangePasswordServiceModel(userChangePasswordServiceModel,username);

        return "redirect:/users/profile/" + username;
    }
    @PreAuthorize("userServiceImpl.isCurrentUser(#principal.name,#username")
    @GetMapping("/profile/edit-profile/{username}")
    public String editProfile(@PathVariable String username, Principal principal){
        return "edit-profile";

    }
    @PreAuthorize("userServiceImpl.isCurrentUser(#principal.name,#username")
    @PatchMapping("/profile/edit-profile/{username}")
    public String editProfileConfirm(@PathVariable String username,
                                     @Valid UserEditProfileBindingModel userEditProfileBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     Principal principal){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditProfileBindingModel", userEditProfileBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditProfileBindingModel", bindingResult);
            return "redirect:/users/profile/edit-profile/" + username;
        }
        UserEditProfileServiceModel userEditProfileServiceModel = modelMapper.map(userEditProfileBindingModel,UserEditProfileServiceModel.class);
        userService.editProfile(userEditProfileServiceModel,username);





        return "redirect:/users/profile/" + username;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserChangePasswordBindingModel userChangePasswordBindingModel(){
        return new UserChangePasswordBindingModel();
    }

    @ModelAttribute
    public UserEditProfileBindingModel userEditProfileBindingModel(){
        return new UserEditProfileBindingModel();
    }
}
