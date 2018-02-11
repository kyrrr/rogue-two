package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Effect;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class DamageHealth extends Effect {

    private int damage;

    public DamageHealth(int damage){
        good = false;
        this.damage = damage;
        color = CSIColor.GREEN;
    }

    @Override
    public void affect(Actor actor) {
        actor.getStatus().setHealth(actor.getStatus().getHealth() - damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
