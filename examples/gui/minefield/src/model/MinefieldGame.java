package model;

import java.util.*;

public class MinefieldGame {
    private int lives;           // number of lives remaining
    private CellType[] field;    // defines the state of each position in the game
    private int currentPosition; // player's current position (0 .. field.length - 1)

    // Initialize state of new game with a minefield of length <size>
    public MinefieldGame(int size) {
        field = new CellType[size];
        var rand = new Random();
        for (int i = 0; i < size; ++i) {
            int num = rand.nextInt(5);
            switch (num) {
                case 0:
                case 1:
                    field[i] = CellType.Mine;
                    break;
                case 2:
                    field[i] = CellType.Powerup;
                    break;
                default:
                    field[i] = CellType.Empty;
                    break;
            }
        }
        lives = 3;
        currentPosition = 0;
    }

    public MinefieldGame(CellType[] field) {
        this.field = field;
        lives = 3;
        currentPosition = 0;
    }

    // returns true if player's move to <position> is legal
    public boolean isValidMove(int position) {
        return (position > currentPosition && position < currentPosition + 3);
    }

    // updates state of game for a move to <position>
    public void moveTo(int position) {
        if (isValidMove(position)) {
            currentPosition = position;
            if (field[position] == CellType.Mine) {
                --lives;
            } else if (field[position] == CellType.Powerup) {
                ++lives;
            }
        }
    }

    // returns true if game is over
    public boolean isGameOver() {
        return lives == 0 || currentPosition == field.length - 1;
    }

    // returns the type of cell in the player's current position
    public CellType getCurrentCellType() {
        return field[currentPosition];
    }

    // ------ getters ----------

    public int getSize() {
        return field.length;
    }

    public int getLives() {
        return lives;
    }

    public CellType[] getField() {
        return field;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

}