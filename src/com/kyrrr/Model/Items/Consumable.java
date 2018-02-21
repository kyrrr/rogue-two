package com.kyrrr.Model.Items;

import com.kyrrr.Model.Effects.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrrebugge on 20.02.2018.
 */
public class Consumable extends SelfItem {
    protected int uses;
    protected List<Effect> effects = new ArrayList<>();
    protected boolean usable;

    Consumable(){
        uses = 1;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public int getUses() {
        return uses;
    }

    @Override
    public void use() {
        if(isUsable()){
            uses--;
            if(uses <= 0){
                setUsable(false);
            }
        }
    }
}
