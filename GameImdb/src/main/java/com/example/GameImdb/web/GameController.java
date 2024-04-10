package com.example.GameImdb.web;

import com.example.GameImdb.model.binding.GameAddBindingModel;
import com.example.GameImdb.model.binding.GameEditBindingModel;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.model.service.GameEditServiceModel;
import com.example.GameImdb.model.view.GameDetailsViewModel;
import com.example.GameImdb.model.view.GameEditViewModel;
import com.example.GameImdb.model.view.GameViewModel;
import com.example.GameImdb.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
    public String allGames(Model model){
        List<GameViewModel> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);

        return "all-games";
    }

    @GetMapping("/details/{id}")
    public String gameDetails(@PathVariable Long id,Model model, Principal principal){

        GameDetailsViewModel gameDetailsViewModel = gameService.getGameDetailsViewById(id,principal.getName());
        model.addAttribute("gameWithDetails",gameDetailsViewModel);

    return "details";
    }

    @PreAuthorize("gameServiceImpl.isOwner(#id,#principal.name)")
    @GetMapping("/edit/{id}")
    public String editGameView(@PathVariable Long id, Model model, Principal principal){

        GameEditViewModel gameEditViewModel = gameService.getEditViewModel(id);
        GameEditBindingModel gameEditBindingModel = modelMapper.map(gameEditViewModel, GameEditBindingModel.class);
        model.addAttribute("gameEditBindingModel",gameEditBindingModel);
        return "game-edit";
    }
    @PreAuthorize("gameServiceImpl.isOwner(#id,#principal.name)")
    @PatchMapping("/edit/{id}")
    public String editGameConfirm(@PathVariable Long id,
                                  @Valid GameEditBindingModel gameEditBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("gameEditBindingModel",gameEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gameEditBindingModel", bindingResult);
            return "redirect:/game/edit/" + id ;
        }
        GameEditServiceModel gameEditServiceModel = modelMapper.map(gameEditBindingModel, GameEditServiceModel.class);
        gameService.editGame(gameEditServiceModel);
        return "redirect:/game/details/" + id ;
    }

    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel(){
        return new GameAddBindingModel();
    }

}
