package com.kyrrr.Model.Actors;

import net.slashie.libjcsi.CSIColor;


/**
 * Created by kyrrebugge on 16.02.2017.
 */
public class Enemy extends Actor {

    /** what up */
    public Enemy(){
        if(modelColor == null){
            setModelColor(CSIColor.AMBER);
        }
        setModel("<");
        setSpeed(10);
        getZone().setOrigin(getCoordinates());
        getZone().setHeight(4);
        getZone().setWidth(10);
        getZone().calcRect();
    }
}
