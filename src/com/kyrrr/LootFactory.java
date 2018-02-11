package com.kyrrr;

import com.kyrrr.Model.Effect;
import com.kyrrr.Model.Effects.*;
import com.kyrrr.Model.Item;
import com.kyrrr.Model.Items.RottenDagger;
import com.kyrrr.Model.Loot;
import com.kyrrr.Model.Items.Sword;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public class LootFactory {

    private List<Item> choices = new ArrayList<>();
    // TODO: keep a production log?

    public LootFactory(){
        //effects.add(new DamageHealth(25));
        //effects.add(new Poison());
        //effects.add(new Antidote());
        choices.add(new Sword());
        choices.add(new RottenDagger());
    }

    List<Loot> makeAtRand(int amount, int xBound, int yBound){
        List<Loot> loots = new ArrayList<>();
        for (int i = 0; i <= amount; i++){
            Loot loot = new Loot();
            int randX = ThreadLocalRandom.current().nextInt(1, xBound - 1);
            int randY = ThreadLocalRandom.current().nextInt(1, yBound - 1);
            loot.setPos(randX, randY);
            //System.out.println(loot.getCoordinates().getX());
            //System.out.println(loot.getCoordinates().getY());
            loot.setModel("$");
            loot.setModelColor(CSIColor.ALIZARIN);
            loot.add(new Sword());
            loots.add(loot);
        }
        return loots;
    }

}
