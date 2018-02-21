package com.kyrrr;

import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Actors.Enemy;
import com.kyrrr.Model.Actors.Player;
import com.kyrrr.Model.Effects.Effect;
import com.kyrrr.Model.Items.Consumable;
import com.kyrrr.Model.Items.Item;
import com.kyrrr.Model.Items.SelfItem;
import com.kyrrr.Model.Items.Weapon;
import com.kyrrr.Model.Moves.Move;
import com.kyrrr.Model.Moves.SelfMove;
import com.kyrrr.Model.Status.Inventory;
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
 //   TODO: refactor. move that printing loop
class Combat extends Loop {

    private List<Actor> actors = new ArrayList<>(); //weh
    private Player player;
    private Enemy enemy;
    private ConsoleSystemInterface csi;
    private int selectionY = 0;
    private int selectionX = 0;
    private int firstMoveY = Settings.screenHeight - 5;
    private int moveListLeftPad = 3;
    private int firstLogY = 4;
    private int logLeftPad = 3;
    private List<String> log = new ArrayList<>();
    private int logMaxLength = 6;
    private Actor winner;
    private Move selectedMove;
    private Item selectedItem;

    Combat(ConsoleSystemInterface csi, Player player, Enemy enemy){
        this.csi = csi;
        this.player = player;
        this.enemy = enemy;
        actors.add(player);
        actors.add(enemy);
        setup();
    }

    @Override
    public void setup() {
        List<String> vowels = new ArrayList<>(5);
        vowels.addAll(Arrays.asList("a", "e", "i", "o", "u"));
        String prefix = "A ";
        if(vowels.contains(enemy.getName().toLowerCase())){
            prefix = "An ";
        }
        csi.cls();
        String intro = prefix + enemy.getName() + " appeared!";
        int f = (Settings.screenWidth / 2) - (intro.length() / 2);
        csi.print(f, Math.round(Settings.screenHeight/2), intro);
        csi.print(f, Math.round(Settings.screenHeight/2) + 2, "Let's fighting spirit!", CSIColor.RED);
        //csi.print(Math.round(Settings.screenWidth / 2) - 3, Math.round(Settings.screenHeight) / 2, intro);
        csi.refresh();
        csi.inkey();
        printBorder();
    }

    protected void printChoices(Actor a){
        List<Move> moveList = a.getMoves();
        Inventory itemList = a.getInventory();
        int i = 0;
        int j = 0;
        int moveY = firstMoveY;
        int itemY = firstMoveY;
        for (Move m : moveList){
            String prefix = "";
            if(selectionX == 0 && i == selectionY){
                prefix = "> " + prefix;
            }
            csi.print(moveListLeftPad, moveY,  prefix + m.getName() + "(" + m.getUses() + ")");
            csi.refresh();
            moveY++;
            i++;
        }
        for (Item t : itemList.getContents()){
            String prefix = "";
            if(selectionX == 1 && j == selectionY){
                prefix = "> " + prefix;
            }
            String out = prefix + t.getName();
            if(t instanceof Weapon){
                out += "(" + ((Weapon) t).getDurability() + ")";
            } else if(t instanceof Consumable){
                out += "(" + ((Consumable) t).getUses() + ")";
            }
           // System.out.println(out);
            csi.print(moveListLeftPad + 20, itemY,  out);
            csi.refresh();
            itemY++;
            j++;
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

    protected void clearMoveXY(int y, Item replaceWith){
        csi.cls();
        int first = firstMoveY;
        int selectedPos = first + y;
        String p = "";
        for(int i=0;i<=replaceWith.getName().length();i++){
            p += " ";
        }
        csi.print(moveListLeftPad + 20, selectedPos, p);
        csi.refresh();
    }

    void moveCursor(CharKey k, int low, int maxCol1, int maxCol2, boolean hasCol2){
        if(k.isDownArrow()){
            selectionY++;
        } else if(k.isUpArrow()){
            selectionY--;
        } else if(k.isLeftArrow()){
            selectionX--;
        } else if(k.isRightArrow() && hasCol2){
            selectionX++;
        }

        if(selectionY < low){
            selectionY = low;
        } else if(selectionX == 0 && selectionY >= maxCol1){
            selectionY = maxCol1;
        } else if(selectionX == 1 && selectionY >= maxCol2){
            selectionY = maxCol2;
        }

        if(selectionX < 0){
            selectionX = 0;
        } else if(selectionX > 1){
            selectionX = 1;
        }
    }

    void clearSelection(List<Move> moveList, Inventory itemList){
        if(selectionX == 0){
            clearMove(selectionY, moveList.get(selectionY)); //todo:clearHorizontal(xstart, ylength)
        } else if(selectionX == 1 && selectionY < itemList.size()){ //select an item
            clearMoveXY(selectionY, itemList.get(selectionY));
        }
    }

    boolean trySelection(CharKey k){
        return k.code == CharKey.ENTER;
    }

    void doSelection(List<Move> moveList, Inventory itemList){
        if(selectionX == 0){
            selectedMove = moveList.get(selectionY);
        } else if(selectionX == 1){
            selectedItem = itemList.get(selectionY);
        }
    }

    void selectAction(Actor a){
        printChoices(a);
        log("Your move!");
        printLog();
        List<Move> moveList = a.getMoves();
        Inventory itemList = a.getInventory();
        int low = 0; //future scrolling maybe? arrow down on last load next 4
        int maxMoves = moveList.size() - 1;
        boolean selected = false;
        while (!selected){
            int maxItems = itemList.size();
            int itemIndexMax = maxItems - 1;
            boolean useItemColumn = maxItems > 0;
            printStatus(a);
            printEnemyStatus();
            CharKey k = csi.inkey();
            moveCursor(k, low, maxMoves, itemIndexMax, useItemColumn);
            clearSelection(moveList, itemList);
            printChoices(a);
            if(!trySelection(k)){
                printBorder();
                printLog();
            } else {
                selected = true;
            }
        }
        doSelection(moveList, itemList);
    }

    @LOL
    void printEnemyStatus(){
        int h = enemy.getStatus().getHealth();
        String hstr = "Health: " + h;
        int i = 0;
        csi.print(Settings.screenWidth - enemy.getName().length() - 1, 5, enemy.getName());
        i++;
        csi.print(Settings.screenWidth - hstr.length() - 1, 5 + i, hstr);
    }

    @LOL
    void printStatus(Actor a){
        int h = a.getStatus().getHealth();
        String hstr = h + " HP";
        String topStr = a.getName();
        if(a.getStatus().isPoisoned()){
            topStr += " PSN x" + a.getStatus().getPoisonStack();
        }
        int i = 0;
        csi.print(Settings.screenWidth - topStr.length() - 1, firstMoveY, topStr);
        i++;
        csi.print(Settings.screenWidth - hstr.length() - 1, firstMoveY + i, hstr);
    }

    @LOL
    protected void playerAction(Actor p){
        //int selectionY = 0;
        //printChoices(p);
        //System.out.println(k.code);
        selectAction(p);
        if(selectedMove == null && selectedItem == null){
            playerAction(p);
        }
       // printLog();
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

    void doPlayerMove(Actor target){
        Effect e = selectedMove.getFirstEffect();
        if(selectedMove instanceof SelfMove){
            log(e.getEffectString(player));
            player.attackSelf(selectedMove);
        } else {
            if(!target.getStatus().isProtected()){
                log(e.getEffectString(target));
                player.attack(target, selectedMove);
            }else{
                log(target.getName() + " is protected!");
                target.getStatus().tickProtection();
            }
        }
    }

    void doPlayerItem(Actor target){
        Effect e = selectedItem.getEffects().get(0); // look out!
        if(selectedItem instanceof SelfItem){
            player.handleEffect(e);
            log(e.getEffectString(player));
        } else {
            log(e.getEffectString(target));
            target.handleEffect(e);
        }
        selectedItem.use();
        if(!selectedItem.isUsable()){
            player.getInventory().remove(selectedItem);
        }
        if(player.getInventory().isEmpty()){
            selectionX = 0;
        }
    }

    protected boolean combatAction(){
        List<Actor> order = getOrder();
        if (getOrder().size() > 1) {
            for (Actor a : order) {
                List<Move> moveList = a.getMoves();
                Actor target = a.chooseTarget(this.actors);
                Move move;
                Effect e;
                if (a instanceof Player) {
                    //System.out.println(a.getStatus().isProtected());
                    while(selectedMove == null && selectedItem == null){
                        playerAction(a);
                    }
                    if(selectedMove != null){
                        doPlayerMove(target);
                    } else if(selectedItem != null){
                        doPlayerItem(target);
                    }
                    selectedItem = null;
                    selectedMove = null;
                } else if(a instanceof Enemy) {
                    //printEnemyStatus((Enemy)a);
                    //printEnemyStatus((Enemy)a);
                    int bound = moveList.size();
                    if(bound != 1){
                        bound--;
                    }
                    int randAtk = ThreadLocalRandom.current().nextInt(0, bound);
                   // System.out.println(randAtk);
                    move = moveList.get(randAtk);
                    if(target != null){
                        //e = move.getFirstEffect();
                        if(move instanceof SelfMove){

                        } else {
                            if(!target.getStatus().isProtected()){
                                log(a.getName() + " used " + move.getName());
                                //String effectLog = "";
                                for (Effect ef : move.getEffects()) {
                                    log(ef.getEffectString(target));
                                }
                                a.attack(target, move);
                            } else {
                                log(target.getName() + " is protected!");
                                target.getStatus().tickProtection();
                            }
                        }
                    }
                }
            }
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
            if(!combatAction()){
                log(" ");
                log("A winner is " + getWinner().getName());
                log("Press the Any key to continue");
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
        for (int y=1;y<=Settings.screenHeight;y++){
            csi.print(0, y, "|", CSIColor.WHITE);
        }
        for (int i=Settings.screenHeight;i>0;i--){ // right t -> d
            csi.print(Settings.screenWidth - 1, i, "|", CSIColor.WHITE);
        }
        for (int j=Settings.screenWidth - 2;j>0;j--){ // bottom r -> l
            csi.print(j, Settings.screenHeight - yLimitFromBottom, "-", CSIColor.WHITE);
        }
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
