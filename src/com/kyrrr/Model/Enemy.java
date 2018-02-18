package com.kyrrr.Model;

import com.kyrrr.Model.Effects.BoostHealth;
import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Effects.Poison;
import net.slashie.libjcsi.CSIColor;


/**
 * Created by kyrrebugge on 16.02.2017.
 */
public class Enemy extends Actor {

    /** what up */
    public Enemy(){
        addMove(new Move("Scratch", 40, new DamageHealth(18)));
        addMove(new Move("Spit", 10, new Poison()));
        addMove(new Move("Heal", 10, new BoostHealth(25)));
        setModelColor(CSIColor.AMBER);
        setModel("<");
        setSpeed(10);
        getZone().setOrigin(getCoordinates());
        getZone().setHeight(4);
        getZone().setWidth(10);
        getZone().calcRect();
    }
}
