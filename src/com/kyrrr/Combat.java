package com.kyrrr;

import com.kyrrr.Model.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by kyrrebugge on 03.07.2017.
 * COMBAAAT
 */
// TODO: ATTACKS/EVERYTHING
class Combat extends Loop {

    private List<Actor> actors = new ArrayList<>();
    private ConsoleSystemInterface csi;
    
    Combat(ConsoleSystemInterface csi, Actor... actors){ // not really just use 2
        this.csi = csi;
        csi.cls();
        Collections.addAll(this.actors, actors);
    }

    @Override
    public void loop(){
        boolean matchOver = false;
        while(!matchOver){
            csi.cls();
            printBorder();
            printUi();
            List<Actor> order = getOrder();
            for (Actor a : order){


            }
            if (getOrder().size() > 1) {
                for (Actor a : order) {
                    List<Move> m = a.getMoves();
                    Actor target = a.chooseTarget(this.actors);
                    if (a instanceof Player) {
                        CharKey k = csi.inkey();
                        Move move = m.get(k.code);
                        System.out.println("Player used " + move.getName());
                        a.attack(target, move);
                    } else if(a instanceof Enemy){
                        int randAtk = ThreadLocalRandom.current().nextInt(0, m.size() - 1);
                        Move move = m.get(randAtk);
                        System.out.println("Enemy used " + move.getName());
                        a.attack(target, move);
                    }
                }
            } else {
                matchOver = true;
            }
            csi.refresh();
        }
    }

    @LOL
    private void printBorder(){
        int yLimitFromBottom = 6;
        for (int x=0;x<Settings.screenWidth;x++){
            csi.print(x, 0, "_", CSIColor.WHITE);
        }
        for (int y=1;y<Settings.screenHeight;y++){
            csi.print(0, y, "|", CSIColor.WHITE);
        }
        for (int i=Settings.screenHeight;i>0;i--){ // right t -> d
            csi.print(Settings.screenWidth - 1, i, "|", CSIColor.WHITE);
        }
        for (int j=Settings.screenWidth - 2;j>0;j--){ // bottom r -> l
            csi.print(j, Settings.screenHeight - yLimitFromBottom, "-", CSIColor.WHITE);
        }

    }

    private void printUi(){
        Coordinates origin = new Coordinates(1,Settings.screenHeight - 6);
        Zone z = new Zone();


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
}
