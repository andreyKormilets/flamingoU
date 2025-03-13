package com.example.flamingoui.service;

import com.example.flamingoui.client.SessionClient;
import com.example.flamingoui.controllers.dto.GameStateUiResponse;
import com.example.flamingoui.domain.GameStateData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class PlayService {
    private final SessionClient sessionClient;

    public PlayService(SessionClient sessionClient) {
        this.sessionClient = sessionClient;
    }

    public String session() {
        return sessionClient.getSession();
    }

    public void simulate(String sessionId) {
        sessionClient.simulateGame(sessionId);
    }

    public GameStateUiResponse getHistory(String sessionId) {
        GameStateData data = sessionClient.getGameState(sessionId);
        long moveCount = Arrays.stream(data.board()).filter(a -> a != 0).count();
        List<Integer> moves = new LinkedList<>();
        for (int i = 0; i < moveCount; i++) {
            moves.add(data.moves()[i]);
        }
        return new GameStateUiResponse(moves, data.gameStatus());
    }
}
