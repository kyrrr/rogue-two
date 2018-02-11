package com.kyrrr.Model;

import com.kyrrr.Model.Effects.BoostHealth;
import com.kyrrr.Model.Effects.DamageHealth;
import com.kyrrr.Model.Effects.Poison;
import com.kyrrr.Model.Items.Sword;
import net.slashie.libjcsi.CSIColor;

public class Player extends Actor {

    public Player(){
        alive = true;
        coordinates.setCoordinates(0, 0);
        zone.setOrigin(coordinates);
        zone.setHeight(2);
        zone.setWidth(4);
        zone.calcRect();
        //inventory.add(new Sword());
        //zone.calcRectZoneTrailing(); TODO: one where keep trail = no new arraylist
        model = ">";
        modelColor = CSIColor.PAPAYA_WHIP;
        status.setSpeed(20);
        status.setHealth(500);
        moves.add(new Move("Punch", 10, new DamageHealth(49)));
        moves.add(new Move("Cool Hwip", 15, new BoostHealth(10)));
        moves.add(new Move("Spit", 0, new Poison()));
        moves.add(new Move("Hadoken", 1, new DamageHealth(1000000)));
    }
}
