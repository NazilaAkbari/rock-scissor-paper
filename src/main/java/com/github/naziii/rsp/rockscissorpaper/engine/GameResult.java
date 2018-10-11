package com.github.naziii.rsp.rockscissorpaper.engine;

import com.github.naziii.rsp.rockscissorpaper.model.Player;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
public class GameResult {

    private final int playerOneWinCount;
    private final int playerTwoWinCount;
    private final Player winner;

    public GameResult(int playerOneWinCount, int playerTwoWinCount, Player winner) {
        this.playerOneWinCount = playerOneWinCount;
        this.playerTwoWinCount = playerTwoWinCount;
        this.winner = winner;
    }

    public int getPlayerOneWinCount() {
        return playerOneWinCount;
    }

    public int getPlayerTwoWinCount() {
        return playerTwoWinCount;
    }

    public Player getWinner() {
        return winner;
    }
}
