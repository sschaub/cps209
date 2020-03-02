package model;

import java.util.function.Consumer;

public class TicTacToe {

    private Cell[][] grid;

    private Player currentPlayer = Player.X;

    private Player winner = Player.NONE;

    public TicTacToe() {
        grid = new Cell[3][3];
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid.length; ++col) {
                grid[row][col] = new Cell();
            }
        };
    }

    public void processMove(int row, int col) {
        if (winner != Player.NONE)
            return;

        if (grid[row][col].getPlayer() != Player.NONE)
            return;

        grid[row][col].setPlayer(currentPlayer);
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

        // Search for winner horizontally
        for (int searchRow = 0; searchRow < grid.length && winner == Player.NONE; ++searchRow) {
            if (grid[searchRow][0].getPlayer() == grid[searchRow][1].getPlayer() &&
                    grid[searchRow][0].getPlayer() == grid[searchRow][2].getPlayer()) {
                winner = grid[searchRow][0].getPlayer();
            }
        }

        // Search for winner vertically
        for (int searchCol = 0; searchCol < grid.length && winner == Player.NONE; ++searchCol) {
            if (grid[0][searchCol].getPlayer() == grid[1][searchCol].getPlayer() &&
                    grid[0][searchCol].getPlayer() == grid[2][searchCol].getPlayer()) {
                winner = grid[0][searchCol].getPlayer();
            }
        }

        if (winner != Player.NONE) {
            // TODO: Report the winner using an event
        }
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public Player getWinner() {
        return winner;
    }
}
