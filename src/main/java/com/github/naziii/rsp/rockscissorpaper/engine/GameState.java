package com.github.naziii.rsp.rockscissorpaper.engine;

import java.util.Map;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
public class GameState {
    private final Map<String, Selection> playerSelections;
    private final String winnerUsername;
    private final Map<String, Integer> playerWinNum;
    private final boolean finished;

    public GameState(Map<String, Selection> playerSelections, String winnerUsername, Map<String, Integer> playerWinNum, boolean finished) {
        this.playerSelections = playerSelections;
        this.winnerUsername = winnerUsername;
        this.playerWinNum = playerWinNum;
        this.finished = finished;
    }

    public Map<String, Selection> getPlayerSelections() {
        return playerSelections;
    }

    public String getWinnerUsername() {
        return winnerUsername;
    }

    public Map<String, Integer> getPlayerWinNum() {
        return playerWinNum;
    }

    public boolean isFinished() {
        return finished;
    }
}
