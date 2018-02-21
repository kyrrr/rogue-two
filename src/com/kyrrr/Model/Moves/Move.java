package com.kyrrr.Model.Moves;

import com.kyrrr.Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kyrrebugge on 17.02.2017.
 */
public class Move { // TODO: extend
    protected String name;
    protected int uses;
    protected Effect effect;
    protected List<Effect> effects = new ArrayList<>();

    public void addEffects(Effect... effects){
        Collections.addAll(this.effects, effects);
    }

    @Override
    public String toString() {
        return name;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public boolean usable(){
        return uses > 0;
    }

    public void expend(){
        //if(uses > 0){
        uses--;
        if(uses < 0){
            uses = 0; //lol
        }
       // }
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public Effect getFirstEffect() {
        return effects.get(0);
    }

    public int getUses() {
        return uses;
    }
}
