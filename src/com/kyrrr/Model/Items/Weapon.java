package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrrebugge on 09.08.2017.
 */
public class Weapon extends OffensiveItem {

    protected int durability;
    protected List<Effect> effects = new ArrayList<>();
    protected boolean usable;
    protected int hitsBeforeDegrade;

    Weapon(){
        durability = 100;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public int getHitsBeforeDegrade() {
        return hitsBeforeDegrade;
    }

    public void setHitsBeforeDegrade(int hitsBeforeDegrade) {
        this.hitsBeforeDegrade = hitsBeforeDegrade;
    }

    @Override
    public boolean isUsable() {
        return usable;
    }

    public void degrade(){
        durability--;
        checkDurability();
    }
    public void degrade(int amount){
        durability-=amount;
        checkDurability();
    }
    protected void checkDurability(){
        if(durability <= 0){
            setUsable(false);
        }
    }
}
