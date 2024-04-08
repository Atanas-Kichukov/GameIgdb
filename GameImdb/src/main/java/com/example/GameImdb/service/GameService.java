package com.example.GameImdb.service;

import com.example.GameImdb.model.entity.GameEntity;
import com.example.GameImdb.model.service.GameAddServiceModel;
import com.example.GameImdb.model.view.GameDetailsViewModel;
import com.example.GameImdb.model.view.GameEditViewModel;
import com.example.GameImdb.model.view.GameViewModel;

import java.io.IOException;
import java.util.List;

public interface GameService {
    void addNewGame(GameAddServiceModel gameAddServiceModel,String username) throws IOException;

    List<GameViewModel> getAllGames();

    GameDetailsViewModel getGameDetailsViewById(Long id, String name);

    GameEntity findById(Long id);
    boolean isOwner(Long id, String username);

    GameEditViewModel getEditViewModel(Long id);
}
