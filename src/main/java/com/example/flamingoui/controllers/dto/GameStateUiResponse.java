package com.example.flamingoui.controllers.dto;

import com.example.flamingoui.domain.GameStatus;
import com.google.gson.Gson;

import java.util.List;


public record GameStateUiResponse(List<Integer> moves, GameStatus gameStatus) {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
