package com.github.naziii.rsp.rockscissorpaper.controller;

import com.github.naziii.rsp.rockscissorpaper.engine.Game;
import com.github.naziii.rsp.rockscissorpaper.service.GameService;
import com.github.naziii.rsp.rockscissorpaper.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
@RestController
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public UUID startGame() {
        return gameService.startGame(SecurityUtil.currentUser());
    }

    @MessageMapping("/response")
    public void response(String message, Principal principal) throws Exception {
        gameService.response(message,principal);
    }
}
