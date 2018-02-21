package com.kyrrr.Model.Status;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
public class Status {
    private int health = 100;
    private int speed = 0;
    private boolean paralyzed = false;
    private boolean poisoned = false;
    private int poisonStack = 0;
    private boolean burned = false;
    private boolean protection = false;
    private int protectionDuration = 1;
    private int initialHealth;
    private int level = 1;

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
    public void setParalyzed(boolean paralyzed) { this.paralyzed = paralyzed; }
    public boolean isParalyzed() { return paralyzed; }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
        setPoisonStack(getPoisonStack()+1);
    }

    public void setPoisonStack(int poisonStack) {
        this.poisonStack = poisonStack;
    }

    public int getPoisonStack() {
        return poisonStack;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }

    public boolean isBurned() {
        return burned;
    }

    public boolean isProtected() {
        return protection && protectionDuration > 0;
    }

    public void setProtection(int duration) {
        protection = true;
        protectionDuration = duration;
    }

    public void tickProtection(){
        protectionDuration--;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
