package com.example.flamingoui.controllers.dto;

import com.example.flamingoui.domain.GameStatus;

public record MoveRequest(GameStatus OX,
                          Integer place) {
}
