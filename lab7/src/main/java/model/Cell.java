package model;

public class Cell {
    private Player player = Player.NONE;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

