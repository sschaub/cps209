package model;

import java.util.*;

public class Minefield {
    private int lives;
    private CellType[] field;
    private int currentPosition;

    public Minefield(int size) {
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

    public int getSize() {
        return field.length;
    }

    public int getLives() {
        return lives;
    }


    
    public boolean isValidMove(int position) {
        return (position > currentPosition && position < currentPosition + 3);
    }

    public void move(int position) {
        if (isValidMove(position)) {
            currentPosition = position;
            if (field[position] == CellType.Mine) {
                --lives;
            } else if (field[position] == CellType.Powerup) {
                ++lives;
            }
        }
    }

    public boolean isGameOver() {
        return lives == 0 || currentPosition == field.length - 1;
    }

    public CellType getCurrentCellType() {
        return field[currentPosition];
    }

    public CellType[] getField() {
        return field;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

}