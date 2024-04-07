package com.example.GameImdb.service;

import com.example.GameImdb.model.service.GameAddServiceModel;

import java.io.IOException;

public interface GameService {
    void addNewGame(GameAddServiceModel gameAddServiceModel,String username) throws IOException;
}
