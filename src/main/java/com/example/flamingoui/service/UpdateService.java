package com.example.flamingoui.service;

import com.example.flamingoui.controllers.GameWebSocketController;
import com.example.flamingoui.domain.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    @Autowired
    GameWebSocketController gameWebSocketController;
    public void updateBoard(String sessionId, GameStatus OX, int place){
        gameWebSocketController.sendGameUpdateMove(sessionId,OX.name(),place);
    }
    public void updateStatus(String sessionId, GameStatus gameStatus){
        gameWebSocketController.sendGameUpdateStatus(sessionId,gameStatus.name());
    }
}
