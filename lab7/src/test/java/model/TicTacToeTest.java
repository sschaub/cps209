package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTest {


    @Test
    public void testXWin() {
        var game = new TicTacToe();

        game.processMove(0, 0); // X
        game.processMove(1, 0); // O
        game.processMove(0, 1); // X
        game.processMove(1, 1); // O

        assertEquals(Player.NONE, game.getWinner());
        game.processMove(0, 2); // X

        assertEquals(Player.X, game.getWinner());

    }

}