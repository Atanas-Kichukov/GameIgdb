package com.example.GameImdb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    @GetMapping("/game/add")
    public String addGame(){
        return "add-game";
    }
}
