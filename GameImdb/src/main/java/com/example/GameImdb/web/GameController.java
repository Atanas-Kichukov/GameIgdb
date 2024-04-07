package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.GameAddBindingModel;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/game")
public class GameController {
    private final ModelMapper modelMapper;
    private final GameService gameService;

    public GameController(ModelMapper modelMapper, GameService gameService) {
        this.modelMapper = modelMapper;
        this.gameService = gameService;
    }

    @GetMapping("/add")
    public String addGame(){
        return "add-game";
    }

    @PostMapping("/add")
    public String addGameConfirm(@Valid GameAddBindingModel gameAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("gameAddBindingModel",gameAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gameAddBindingModel", bindingResult);
            return "redirect:add";
        }
        GameAddServiceModel gameAddServiceModel = modelMapper.map(gameAddBindingModel, GameAddServiceModel.class);
        gameService.addNewGame(gameAddServiceModel, principal.getName());
        return "redirect: /";
    }

    @GetMapping("/all")
    public String allGames(){
        return "all-games";
    }
    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel(){
        return new GameAddBindingModel();
    }
}
