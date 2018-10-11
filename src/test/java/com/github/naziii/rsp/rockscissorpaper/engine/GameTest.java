package com.github.naziii.rsp.rockscissorpaper.engine;

import com.github.naziii.rsp.rockscissorpaper.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/11/18
 */
public class GameTest {

    private Player playerOne;
    private Player playerTwo;

    @Before
    public void initilize() {
        playerOne = new Player();
        playerOne.setRank(0);
        playerOne.setUsername("player1");
        playerOne.setPassword("1234");
        playerOne.setId(1L);
        playerTwo = new Player();
        playerTwo.setRank(0);
        playerTwo.setUsername("player2");
        playerTwo.setPassword("1234");
        playerTwo.setId(2L);
    }

    @Test
    public void join() {
        Game game = new Game(playerOne);
        game.join(playerTwo);
        Assert.assertTrue(game.isReady());
    }

    @Test
    public void respond() {
        Game game = new Game(playerOne);
        game.join(playerTwo);
        GameState respondOne = game.respond(playerOne, Selection.ROCK);
        GameState respondTwo = game.respond(playerTwo, Selection.PAPER);

        Assert.assertEquals(0, game.getPlayerOneWinCount());
        Assert.assertEquals(1, game.getPlayerTwoWinCount());
        Assert.assertNull(respondOne);
        Assert.assertNotNull(respondTwo);
        Assert.assertTrue(!respondTwo.isFinished());
        Assert.assertEquals(playerTwo.getUsername(), respondTwo.getWinnerUsername());
    }
}