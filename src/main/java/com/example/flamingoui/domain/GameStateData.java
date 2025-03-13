package com.example.flamingoui.domain;

public record GameStateData(int[] board, int[] moves, GameStatus gameStatus) {
}
