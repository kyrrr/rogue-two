package com.kyrrr.Model;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
public class Move {
    public String name;
    public int power;

    public Move(String name, int power){
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }
}
