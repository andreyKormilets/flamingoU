package com.example.flamingoui.controllers;

import com.example.flamingoui.controllers.dto.GameStateUiResponse;
import com.example.flamingoui.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;

@Controller
public class GameWebSocketController {
    @Autowired
    PlayService playService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Когда клиент нажимает "New Game", сервер отправляет уникальный sessionId
    @MessageMapping("/new-game")
    @SendToUser("/topic/session-id")
    public String newGame() {
        return playService.session();// Генерируем sessionId
    }

    // Когда клиент нажимает "Start Game", отправляем пустое поле 3x3
    @MessageMapping("/start-game")
    public void startGame(@Payload String sessionId) {
        playService.simulate(sessionId);
        String[] emptyBoard = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};

        // Отправляем только в канал с sessionId
        messagingTemplate.convertAndSend("/topic/game-board/" + sessionId, emptyBoard);
    }

    // Сервер отправляет обновления каждые несколько секунд
    public void sendGameUpdateMove(String sessionId, String OX, int place) {
        messagingTemplate.convertAndSend("/topic/game-update-move/" + sessionId, place + ":" + OX);
    }
    public void sendGameUpdateStatus(String sessionId, String gameStatus) {
        messagingTemplate.convertAndSend("/topic/game-update-result/" + sessionId, gameStatus);
    }

    // Когда клиент нажимает "Game Stat", сервер отправляет статистик

    @MessageMapping("/game-stat")
    public void getGameStats(@Payload String sessionId) {
         String history = playService.getHistory(sessionId).toJson() ;
        // Теперь отправляем в топик с sessionId
        messagingTemplate.convertAndSend("/topic/game-stat/" + sessionId, history);
    }
}
