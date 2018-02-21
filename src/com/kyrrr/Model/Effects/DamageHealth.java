package com.kyrrr.Model.Effects;

import com.kyrrr.Model.Actors.Actor;
import net.slashie.libjcsi.CSIColor;

/**
 * Created by kyrrebugge on 21.07.2017.
 */
public class DamageHealth extends Effect {

    private int damage;

    public DamageHealth(int damage){
      //  beneficialToCaster = false;
        this.damage = damage;
        color = CSIColor.GREEN;
    }

    @Override
    public String getEffectString(Actor actor) {
        return actor.getName() + " loses " +  damage + " health";
    }

    @Override
    public boolean affect(Actor actor) {
        int before = actor.getStatus().getHealth();
        actor.getStatus().setHealth(actor.getStatus().getHealth() - damage);
        return actor.getStatus().getHealth() < before;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
