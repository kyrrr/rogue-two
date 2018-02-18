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
    private int selection = 0;
    private int firstMoveY = Settings.screenHeight - 5;
    private int moveListLeftPad = 3;
    private int firstLogY = 4;
    private int logLeftPad = 3;
    private List<String> log = new ArrayList<>();
    private int logMaxLength = 6;
    private Actor winner;
    
    Combat(ConsoleSystemInterface csi, Actor... actors){ // not really just use 2
        this.csi = csi;
        csi.cls();
        csi.refresh();
        printBorder();
        Collections.addAll(this.actors, actors);
    }

    protected void printMoves(Actor a){
        List<Move> moveList = a.getMoves();
        int i = 0;
        int y = firstMoveY;
        for (Move m : moveList){
            String prefix = "";
            if(i == selection){
                prefix = "> " + prefix;
            }
            csi.print(moveListLeftPad, y,  prefix + m.getName());
            csi.refresh();
            y++;
            i++;
        }

    }

    public Actor getWinner() {
        return winner;
    }

    protected void clearMove(int index, Move m){
        csi.cls();
        int first = firstMoveY;
        int selectedPos = first + index;
        String p = "";
        for(int i=0;i<=m.getName().length();i++){
            p += " ";
        }
        csi.print(moveListLeftPad, selectedPos, p);
        csi.refresh();
    }

    protected Move selectMove(Actor a){
        printMoves(a);
        log(a.getName() + " to move");
       // clearLog();
        printLog();
        List<Move> moveList = a.getMoves();
        boolean selected = false;
        while (!selected){
            printStatus(a);
            CharKey k = csi.inkey();
            if(k.isDownArrow()){
                selection++;
            } else if(k.isUpArrow()){
                selection--;
            }
            if(selection < 0){
                selection = 0;
            } else if(selection > 3){
                selection = 3;
            }
            clearMove(selection, moveList.get(selection)); //todo:clearHorizontal(xstart, ylength)
            printMoves(a);
            if(k.code == CharKey.ENTER){
                selected = true;
            } else {
                printBorder();
                printLog();
            }
        }
        return moveList.get(selection);
    }

    protected void printStatus(Actor a){
        int h = a.getStatus().getHealth();
        String hstr = "" + h;
        csi.print(Settings.screenWidth - hstr.length() - 1, firstMoveY, hstr);
    }

    @LOL
    protected Move playerAction(Actor p){
        //int selection = 0;
        //printMoves(p);
        //System.out.println(k.code);
        Move m = selectMove(p);
        log("Player used " + m.getName());
       // printLog();
        return m;
    }


    protected void log(String message){
        log.add(message);
    }

    protected void printLog(){
        int logLength = log.size();
        if(logLength >= logMaxLength){
            log = log.subList(Math.max(log.size() - logMaxLength, 0), log.size());
        }
        int y = firstLogY;
        for (String m : log){
            csi.print(logLeftPad, y, m);
            y+=2;
        }
        csi.refresh();
    }

    protected boolean combatAction(){
        List<Actor> order = getOrder();
        if (getOrder().size() > 1) {
            for (Actor a : order) {
                List<Move> moveList = a.getMoves();
                Actor target = a.chooseTarget(this.actors);
                Move move = null;
                Effect e;
                if (a instanceof Player) {
                    move = playerAction(a);
                    e = move.getEffect();
                    if(e.isGood()){
                        log(e.getEffectString(a));
                    } else {
                        log(e.getEffectString(target));
                    }
                } else if(a instanceof Enemy) {
                    int randAtk = ThreadLocalRandom.current().nextInt(0, moveList.size() - 1);
                    move = moveList.get(randAtk);
                    if(target != null){
                        e = move.getEffect();
                        log("Enemy used " + move.getName());
                        log(e.getEffectString(target));
                    }
                }
                a.attack(target, move);
            }
            //clearLog();
            csi.refresh();
            return true;
        } else {
            winner = order.get(0);
            return false;
        }
    }



    @Override
    public void loop(){
        boolean matchOver = false;
        while(!matchOver){
            csi.cls();
            printBorder();
            printLog();

            if(!combatAction()){
                log("A winner is " + getWinner().getName());
                printLog();
                csi.inkey();
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
        Coordinates origin = new Coordinates(1, Settings.screenHeight - 4);
        Zone z = new Zone();
        //csi.print(origin.getX(), origin.getY(), "Hello");

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
