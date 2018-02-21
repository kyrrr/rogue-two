package com.kyrrr.Model.Actors;

import com.kyrrr.Model.Status.Inventory;
import com.kyrrr.Model.Items.Item;
import net.slashie.libjcsi.CSIColor;

import java.util.List;

/**
 * Created by kyrrebugge on 09.08.2017.
 */
public class Loot extends Actor {

    private Inventory lInventory = new Inventory();

    public Loot(){
        model = "$";
        modelColor = CSIColor.ATOMIC_TANGERINE;
        deathModel = " ";
    }

    public Loot(List<Item> items){
        items.forEach(lInventory::add);
    }

    public Loot(Item... items){
        for (Item i : items) {
            lInventory.add(i);
        }
    }

    public void add(Item i){
        lInventory.add(i);
    }

    public Inventory getAll(){
        return lInventory;
    }

}
