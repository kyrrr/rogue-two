package com.kyrrr.Model.Actors;

import com.kyrrr.Model.Moves.Fireball;
import com.kyrrr.Model.Moves.Protect;
import com.kyrrr.Model.Moves.Thunderbolt;
import net.slashie.libjcsi.CSIColor;

public class Player extends Actor {

    public Player(){
        //status.setLevel(1);
        alive = true;
        coordinates.setCoordinates(0, 0);
        zone.setOrigin(coordinates);
        zone.setHeight(2);
        zone.setWidth(4);
        zone.calcRect();
     //   inventory.add(new PotionSmall());
        model = ">";
        modelColor = CSIColor.PAPAYA_WHIP; // tasty
        status.setSpeed(42);
        double growth = Math.log(status.getLevel() + 1) * 145;
      //  System.out.println(growth);
        int health = (int)Math.round(growth) + 74;
        //System.out.println(health);
        //status.setInitialHealth(f);
        status.setHealth(health);
      //  moves.add(new Move("Punch", 10, new DamageHealth(51)));
        moves.add(new Fireball());
       // moves.add(new Move("Hadoken", 69, new DamageHealth(1337)));
        moves.add(new Protect());
        moves.add(new Thunderbolt());
    }


    @Override
    public void levelUp() {
        status.setLevel(status.getLevel() + 1);
        double growth = Math.log(status.getLevel() + 1) * 145;
        int health = (int)Math.round(growth) + 74;
        //status.setInitialHealth(f);
        status.setHealth(health);
    }
}
