package com.kyrrr.Model;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
public class Move { // TODO: extend
    protected String name;
    protected int uses;
    protected Effect effect;

    public Move(String name, int uses, Effect effect){
        this.name = name;
        this.uses = uses;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getUses() {
        return uses;
    }
}
