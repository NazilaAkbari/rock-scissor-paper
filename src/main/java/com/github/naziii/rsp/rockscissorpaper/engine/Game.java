package com.github.naziii.rsp.rockscissorpaper.engine;

import com.github.naziii.rsp.rockscissorpaper.model.Player;

import java.util.*;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
public class Game {
    private static final int GAME_ROUNDS = 3;
    private UUID id;
    private Player playerOne;
    private Player playerTwo;
    private int playerOneWinCount;
    private int playerTwoWinCount;
    private Queue<Round> rounds = new ArrayDeque<>();
    private Round currentRound;
    private volatile boolean ready;

    public Game(Player playerOne) {
        this.id = UUID.randomUUID();
        this.playerOne = playerOne;
        for (int i = 0; i < GAME_ROUNDS; i++)
            rounds.add(new Round());
    }

    /**
     * @param player adds second player to game
     */
    public void join(Player player) {
        if (Objects.isNull(playerTwo)) {
            this.playerTwo = player;
            this.ready = true;
            this.currentRound = rounds.poll();
        } else
            throw new IllegalStateException("This Game Is Full");
    }

    /**
     * @param player
     * @param selection
     * @return handles player selection and changes game status to proper state
     */
    public GameState respond(Player player, Selection selection) {
        boolean finished = false;
        if (player.equals(playerOne))
            finished = currentRound.setPlayerOneSelection(selection);
        else if (player.equals(playerTwo))
            finished = currentRound.setPlayerTwoSelection(selection);

        if (finished) {
            Round.PlayerBadge winnerPlayer = currentRound.getWinnerPlayer();
            if (winnerPlayer == Round.PlayerBadge.NONE)
                rounds.add(new Round());

            Map<String, Selection> selectionMap = new HashMap<>();
            selectionMap.put(playerOne.getUsername(), currentRound.getPlayerOneSelection());
            selectionMap.put(playerTwo.getUsername(), currentRound.getPlayerTwoSelection());

            String winner = "NONE";
            switch (winnerPlayer) {
                case ONE:
                    winner = playerOne.getUsername();
                    playerOneWinCount++;
                    break;
                case TWO:
                    winner = playerTwo.getUsername();
                    playerTwoWinCount++;
                    break;
            }

            HashMap<String, Integer> playerWinNum = new HashMap<>();
            playerWinNum.put(playerOne.getUsername(), playerOneWinCount);
            playerWinNum.put(playerTwo.getUsername(), playerTwoWinCount);
            currentRound = rounds.poll();
            boolean gameFinished = false;
            if (Objects.isNull(currentRound))
                gameFinished = true;
            return new GameState(selectionMap, winner, playerWinNum, gameFinished);
        }
        return null;
    }

    public UUID getId() {
        return id;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public int getPlayerOneWinCount() {
        return playerOneWinCount;
    }

    public int getPlayerTwoWinCount() {
        return playerTwoWinCount;
    }

    public Queue<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public boolean isReady() {
        return ready;
    }
}
