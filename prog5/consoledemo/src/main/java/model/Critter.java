package model;

import java.util.Random;

public class Critter {

    protected int id;
    protected CritterType critterType;
    protected int x;
    protected int y;
    protected int speed;
    protected int direction; // 0-359

    private static int nextId;

    public Critter(CritterType critterType) {
        this.critterType = critterType;
        var rand = new Random();
        this.x = rand.nextInt(World.instance().getWidth());
        this.y = rand.nextInt(World.instance().getHeight());
        this.speed = rand.nextInt(10) + 1;
        this.direction = rand.nextInt(360);
        this.id = ++nextId;
    }

    public int getId() {
        return id;
    }

    public void updatePosition() {
        x += speed * Math.cos(direction * Math.PI / 180);
        y += speed * Math.cos(direction * Math.PI / 180);
    }

    @Override
    public String toString() {
        return "Critter{" +
                "id=" + id +
                ", critterType=" + critterType +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", direction=" + direction +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
