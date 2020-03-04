package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MinefieldGameTest {

    MinefieldGame makeGame() {
        return new MinefieldGame(new CellType[] {
            /* 0 */ CellType.Empty, 
            /* 1 */ CellType.Mine, 
            /* 2 */ CellType.Powerup,
            /* 3 */ CellType.Mine, 
            /* 4 */ CellType.Empty
        });
    }

    @Test
    public void testIsValidMove() {
        var game = makeGame();

        assertTrue(game.isValidMove(1));
        assertTrue(game.isValidMove(2));
        assertTrue(!game.isValidMove(0));
        assertTrue(!game.isValidMove(-1));
        assertTrue(!game.isValidMove(3));

        game.move(2);
        assertEquals(2, game.getCurrentPosition());

        // Now, see if we can move to spots 3 or 4
        assertTrue(game.isValidMove(3));
        assertTrue(game.isValidMove(4));

    }


    @Test
    public void testMove_HitsMine_DecrementsLives() {
        var game = makeGame();

        game.move(1);
        assertEquals(2, game.getLives());
        game.move(3);
        assertEquals(1, game.getLives());
    }

    @Test
    public void testIsGameOver() {
        var game = makeGame();

        assertFalse(game.isGameOver());
        game.move(2);
        game.move(4);
        assertTrue(game.isGameOver());
    }

}