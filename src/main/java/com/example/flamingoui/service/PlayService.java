package com.example.flamingoui.service;

import com.example.flamingoui.client.SessionClient;
import com.example.flamingoui.controllers.GameWebSocketController;
import com.example.flamingoui.controllers.dto.GameStateUiResponse;
import com.example.flamingoui.domain.GameStateData;
import com.example.flamingoui.domain.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class PlayService {
    @Autowired
    SessionClient sessionClient;

    public String session(){
       return  sessionClient.getSession();
    }
    public void simulate(String sessionId){
          sessionClient.simulateGame(sessionId);
    }
    public GameStateUiResponse getHistory(String sessionId){
        GameStateData data=sessionClient.getGameState(sessionId);

        long moveCount = Arrays.stream(data.board()).filter(a->a!=0).count();
        List<Integer> moves = new LinkedList<>();
        for (int i= 0; i < moveCount; i++){
            moves.add(data.moves()[i]) ;
        }

        return new GameStateUiResponse(moves, data.gameStatus());
    }
}
