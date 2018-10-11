package com.github.naziii.rsp.rockscissorpaper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.naziii.rsp.rockscissorpaper.engine.Game;
import com.github.naziii.rsp.rockscissorpaper.engine.GameState;
import com.github.naziii.rsp.rockscissorpaper.engine.Selection;
import com.github.naziii.rsp.rockscissorpaper.exception.PlayerNotFoundException;
import com.github.naziii.rsp.rockscissorpaper.model.Player;
import com.github.naziii.rsp.rockscissorpaper.repository.PlayerRepository;
import com.github.naziii.rsp.rockscissorpaper.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
@Service
@Transactional
public class GameService {

    private Queue<Game> waitingGames = new LinkedList<>();
    private Map<Player, Game> gameMap = new HashMap<>();

    private SimpMessageSendingOperations messageTemplate;
    private ObjectMapper mapper;


    private PlayerRepository playerRepository;

    @Autowired
    public GameService(SimpMessageSendingOperations messageTemplate, ObjectMapper mapper, PlayerRepository playerRepository) {
        this.messageTemplate = messageTemplate;
        this.mapper = mapper;
        this.playerRepository = playerRepository;
    }

    public UUID startGame(String currentUser) {
        Optional<Player> playerOptional = playerRepository.findByUsername(currentUser);
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(currentUser));
        Game game = waitingGames.poll();
        if (Objects.isNull(game)) {
            game = new Game(player);
            waitingGames.add(game);
        } else {
            game.join(player);
        }
        gameMap.put(player, game);
        return game.getId();
    }


    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        String username = event.getUser().getName();
        Optional<Player> playerOptional = playerRepository.findByUsername(username);
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(username));
        Game game = gameMap.get(player);
        if (game.isReady())
            messageTemplate.convertAndSend("/game/" + game.getId(), Constant.GAME_START_COMMAND);
    }

    public void response(String message, Principal principal) throws JsonProcessingException {
        Optional<Player> playerOptional = playerRepository.findByUsername(principal.getName());
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(principal.getName()));
        Game game = gameMap.get(player);
        GameState gameState = game.respond(player, Selection.valueOf(message));
        if (Objects.nonNull(gameState) && gameState.isFinished()) {
            Integer playerOneRank = game.getPlayerOne().getRank();
            Integer playerTwoRank = game.getPlayerTwo().getRank();
            if (gameState.getPlayerWinNum().get(game.getPlayerOne().getUsername()) >
                    gameState.getPlayerWinNum().get(game.getPlayerTwo().getUsername())) {
                playerOneRank = ++playerOneRank;
                game.getPlayerOne().setRank(playerOneRank);
                playerRepository.save(game.getPlayerOne());
            } else {
                playerTwoRank = ++playerTwoRank;
                game.getPlayerTwo().setRank(playerTwoRank);
                playerRepository.save(game.getPlayerTwo());
            }
            gameMap.remove(game.getPlayerOne());
            gameMap.remove(game.getPlayerTwo());
        }
        if (Objects.nonNull(gameState))
            messageTemplate.convertAndSend("/game/" + game.getId(), mapper.writeValueAsString(gameState));
    }


}
