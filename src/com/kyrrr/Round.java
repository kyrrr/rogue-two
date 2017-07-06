package com.kyrrr;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Enemy;
import com.kyrrr.Model.Loop;
import com.kyrrr.Model.Player;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kyrrebugge on 16.06.2017.
 */
public class Round extends Loop {

    public Actor actor1;
    public Actor actor2;

    Round(Actor actor1, Actor actor2){
        this.actor1 = actor1;
        this.actor2 = actor2;
    }
}
