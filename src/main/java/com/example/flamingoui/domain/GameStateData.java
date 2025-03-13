package com.example.flamingoui.domain;

import com.example.flamingoui.domain.GameStatus;

public record GameStateData(int [] board , int [] moves, GameStatus gameStatus){
}
