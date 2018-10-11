package com.github.naziii.rsp.rockscissorpaper.engine;

import java.util.Objects;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
public class Round {
    private Selection playerOneSelection;
    private Selection playerTwoSelection;

    public boolean setPlayerOneSelection(Selection playerOneSelection) {
        this.playerOneSelection = playerOneSelection;
        return Objects.nonNull(playerTwoSelection);
    }

    public boolean setPlayerTwoSelection(Selection playerTwoSelection) {
        this.playerTwoSelection = playerTwoSelection;
        return Objects.nonNull(playerOneSelection);
    }

    public enum PlayerBadge {
        ONE, TWO, NONE
    }

    public PlayerBadge getWinnerPlayer() {
        if (playerOneSelection.equals(Selection.ROCK)) {
            if (playerTwoSelection.equals(Selection.ROCK))
                return PlayerBadge.NONE;
            else if (playerTwoSelection.equals(Selection.PAPER))
                return PlayerBadge.TWO;
            else return PlayerBadge.ONE;
        } else if (playerOneSelection.equals(Selection.PAPER)) {
            if (playerTwoSelection.equals(Selection.ROCK))
                return PlayerBadge.ONE;
            else if (playerTwoSelection.equals(Selection.PAPER))
                return PlayerBadge.NONE;
            else return PlayerBadge.TWO;
        } else {
            if (playerTwoSelection.equals(Selection.ROCK))
                return PlayerBadge.TWO;
            else if (playerTwoSelection.equals(Selection.PAPER))
                return PlayerBadge.ONE;
            else return PlayerBadge.NONE;
        }
    }

    public Selection getPlayerOneSelection() {
        return playerOneSelection;
    }

    public Selection getPlayerTwoSelection() {
        return playerTwoSelection;
    }
}
