package com.kyrrr;

import com.kyrrr.Model.Item;
import com.kyrrr.Model.Move;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 05.07.2017.
 */
public class ItemFactory {
    List<Item> makeAtRand(int amount, int xbound, int ybound){
        List<Item> items = new ArrayList<>();
        /*for (int i = 1; i <= amount; i++){
            Item Item = new Item();
            int randX = ThreadLocalRandom.current().nextInt(1, xbound);
            int randY = ThreadLocalRandom.current().nextInt(1, ybound);
            //Item.setAlive(true);
            Item.setPos(randX, randY);
            Item.setModel("I");
            Item.setModelColor(CSIColor.ALICE_BLUE);
            items.add(Item);
        }*/
        return items;
    }
}
