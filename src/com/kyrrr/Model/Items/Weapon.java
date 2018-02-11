package com.kyrrr.Model.Items;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Effect;
import com.kyrrr.Model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrrebugge on 09.08.2017.
 */
public class Weapon extends Item {

    protected int durability;
    protected List<Effect> effects = new ArrayList<>();
    protected boolean usable;

    Weapon(){
        durability = 100;
    }


    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

}
