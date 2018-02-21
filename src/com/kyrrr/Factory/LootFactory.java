package com.kyrrr.Factory;

import com.kyrrr.Factory.Factory;
import com.kyrrr.Model.Items.Item;
import com.kyrrr.Model.Items.Antidote;
import com.kyrrr.Model.Items.PotionSmall;
import com.kyrrr.Model.Actors.Loot;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public class LootFactory extends Factory {

    //private List<Item> choices = new ArrayList<>();
    // TODO: keep a production log?

    public LootFactory(){
        addChoice(new PotionSmall());
        addChoice(new Antidote());
    }


    @Override
    public List makeAtRandom(int amount, int xBound, int yBound){
        List<Loot> loots = new ArrayList<>();
        for (int i = 0; i <= amount; i++){
            Loot loot = new Loot();
            int randX = ThreadLocalRandom.current().nextInt(1, xBound - 1);
            int randY = ThreadLocalRandom.current().nextInt(1, yBound - 1);
            int randW = ThreadLocalRandom.current().nextInt(0, choices.size());
            loot.setPos(randX, randY);
            loot.setModel("$");
            loot.setModelColor(CSIColor.ALIZARIN);
            loot.setDeathModel(" ");
            loot.add((Item)choices.get(randW));
            loots.add(loot);
        }
        return loots;
    }

    public void addChoice(Item i){
        choices.add(i);
    }

    //getrandom
}
