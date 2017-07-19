package com.kyrrr.Model;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
public class Status {
    private int health = 100;
    private int speed = 0;
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() { return health; }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}
