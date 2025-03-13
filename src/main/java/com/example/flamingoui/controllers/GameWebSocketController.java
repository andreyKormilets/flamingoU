package com.example.flamingoui.controllers;

import com.example.flamingoui.service.PlayService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GameWebSocketController {
    private final PlayService playService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameWebSocketController(SimpMessagingTemplate messagingTemplate, PlayService playService) {
        this.messagingTemplate = messagingTemplate;
        this.playService = playService;
    }

    @MessageMapping("/new-game")
    @SendToUser("/topic/session-id")
    public String newGame() {
        return playService.session();
    }

    @MessageMapping("/start-game")
    public void startGame(@Payload String sessionId) {
        playService.simulate(sessionId);
        String[] emptyBoard = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};

        messagingTemplate.convertAndSend("/topic/game-board/" + sessionId, emptyBoard);
    }

    public void sendGameUpdateMove(String sessionId, String OX, int place) {
        messagingTemplate.convertAndSend("/topic/game-update-move/" + sessionId, place + ":" + OX);
    }

    public void sendGameUpdateStatus(String sessionId, String gameStatus) {
        messagingTemplate.convertAndSend("/topic/game-update-result/" + sessionId, gameStatus);
    }

    @MessageMapping("/game-stat")
    public void getGameStats(@Payload String sessionId) {
        String history = playService.getHistory(sessionId).toJson();
        messagingTemplate.convertAndSend("/topic/game-stat/" + sessionId, history);
    }
}
