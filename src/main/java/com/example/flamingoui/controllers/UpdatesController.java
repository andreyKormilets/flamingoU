package com.example.flamingoui.controllers;

import com.example.flamingoui.controllers.dto.MoveRequest;
import com.example.flamingoui.domain.GameStatus;
import com.example.flamingoui.service.PlayService;
import com.example.flamingoui.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatesController {
    @Autowired
    UpdateService updateService;

    @PostMapping("/games/{sessionId}/move")
    public void moveUpdate(@PathVariable String sessionId, @RequestBody MoveRequest move) {

        updateService.updateBoard(sessionId,move.OX(),move.place());
    }

    @PostMapping("/games/{sessionId}/status")
    public void moveUpdate(@PathVariable String sessionId, @RequestBody GameStatus gameStatus) {

        updateService.updateStatus(sessionId,gameStatus);
    }
}
