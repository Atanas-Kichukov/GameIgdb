package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.UserLoginBindingModel;
import com.example.GameImdb.model.binding.UserRegisterBindingModel;
import com.example.GameImdb.model.service.UserServiceModel;
import com.example.GameImdb.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

            if(notMatchingPasswords){
                redirectAttributes.addFlashAttribute("passwordsDontMatch",true);
            }

            if(!userService.isUsernameFree(userRegisterBindingModel.getUsername())){
                redirectAttributes.addFlashAttribute("usernameNotFree",true);
            }
            return "redirect:register";
        }

        userService.registerAndLoginUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:/";
    }
    @PostMapping("login-error")
    public String invalidLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)String username, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("invalidCredentials", true);
        redirectAttributes.addFlashAttribute("username",username);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
