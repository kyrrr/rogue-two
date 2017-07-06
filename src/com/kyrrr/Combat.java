package com.kyrrr;

import com.kyrrr.Model.Actor;
import com.kyrrr.Model.Loop;
import com.kyrrr.Model.Player;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kyrrebugge on 03.07.2017.
 */
// TODO: ATTACKS/EVERYTHING
class Combat extends Loop {

    Actor actor1;
    Actor actor2;
    
    Combat(Actor actor1, Actor actor2){
        this.actor1 = actor1;
        this.actor2 = actor2;
    }

    @Override
    public void loop(ConsoleSystemInterface csi){
        boolean stop = false;
        boolean matchOver = false;
        while (!stop){
            csi.cls();
            csi.print(5, 10, "COMBAT LOL", CSIColor.ALICE_BLUE);
            csi.refresh();
            CharKey charKey = csi.inkey();
            int code = charKey.code;
            if(code == CharKey.UARROW){
                System.out.println("ofofof ouch owie my bones");
            }
            while(!matchOver){
                List<Actor> actors = getOrder();
                Actor first = actors.get(0);
                Actor second = actors.get(1);
                if(first.isAlive()){
                    System.out.println(first.getMoves().get(0).getName());
                    System.out.println(first.getMoves().get(0).getPower());
                    first.attack(second, first.getMoves().get(0));
                }
                if(second.isAlive()){
                    System.out.println(second.getMoves().get(0).getName());
                    System.out.println(second.getMoves().get(0).getPower());
                    second.attack(first, second.getMoves().get(0));
                }
                if(!first.isAlive() || !second.isAlive()){
                    matchOver = true;
                }
            }
            stop = true;
        }
    }

    List<Actor> getOrder(){
        List<Actor> order = Collections.synchronizedList(new ArrayList<>());
        if(actor1.getSpeed() > actor2.getSpeed()){
            order.add(actor1);
            order.add(actor2);
        } else {
            order.add(actor2);
            order.add(actor1);
        }
        return order;
    }

    Actor getWinner(){
        if(actor1.isAlive()){
            return actor1;
        } else {
            return actor2;
        }
    }
}
