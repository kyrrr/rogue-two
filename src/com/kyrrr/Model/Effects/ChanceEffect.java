package com.kyrrr.Model.Effects;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 19.02.2018.
 */
public class ChanceEffect extends Effect {
    protected int chance; // percent

    public void setChance(int chance) {
        this.chance = chance;
    }

    public boolean roll(){
        System.out.println(chance);
        int r = ThreadLocalRandom.current().nextInt(1,101);
        System.out.println(r);
        System.out.println(r < chance);
        return r < chance;
    }
}
