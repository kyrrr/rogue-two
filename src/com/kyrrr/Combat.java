package com.kyrrr;

import com.kyrrr.Model.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kyrrebugge on 03.07.2017.
 */
// TODO: ATTACKS/EVERYTHING
class Combat extends Loop {

    private List<Actor> actors = new ArrayList<>();
    
    Combat(/*Actor actor1, Actor actor2,*/ Actor... actors){
        Collections.addAll(this.actors, actors);
    }

    @Override
    public void loop(ConsoleSystemInterface csi){
        System.out.println("COMBAT");
        //boolean stop = false;
        boolean matchOver = false;
        //while (!stop){
        while(!matchOver){
            List<Actor> order = getOrder();
            for (Actor a : order){
                csi.print(a.getXpos(), a.getYpos(), a.getModel(), a.getModelColor());
            }
            for (Actor a : order) {
                if (a.isAlive()) {
                    Actor target = a.chooseTarget(this.actors);
                    System.out.println(a + " will attack: " + target);
                    if (a instanceof Player) {
                        //Actor target = a.chooseTarget(this.actors);
                        CharKey k = csi.inkey();
                        System.out.println(a + " pressed key: " + k);
                        //how2 exit if last?
                    } //else if (a instanceof Enemy) {
                        //System.out.println("Enemy will attack: " + target);
                        //a.attack(target, a.getMoves().get(0));
                    //}
                    a.attack(target, a.getMoves().get(0));
                } else {
                    if (a instanceof Player) {
                        matchOver = true;
                    } else if (getOrder().size() == 1) {
                        matchOver = true;
                    }
                    //iterator.remove();
                }
//                    iterator.remove();
            }
                //for (Actor a : order){
                    //csi.refresh();
                //}
            }
            //stop = true;
        //}
    }

    private List<Actor> getOrder(){
        List<Actor> order = Collections.synchronizedList(new ArrayList<>());
        TreeMap<Integer, Actor> aMap = new TreeMap<>(Collections.reverseOrder());
        this.actors.stream().filter(Actor::isAlive).forEach(actor -> {
            aMap.put(actor.getSpeed(), actor);
        });
        order.addAll(aMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));
        return order;
    }

    Actor getWinner(){
        /*if(actor1.isAlive()){
            return actor1;
        } else {
            return actor2;
        }*/
        return null;
    }
}
