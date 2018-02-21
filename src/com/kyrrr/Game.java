package com.kyrrr;

import com.kyrrr.Enum.Directions;
import com.kyrrr.Factory.EnemyFactory;
import com.kyrrr.Factory.LootFactory;
import com.kyrrr.Model.Actors.Actor;
import com.kyrrr.Model.Actors.Enemy;
import com.kyrrr.Model.Actors.Loot;
import com.kyrrr.Model.Actors.Player;
import com.kyrrr.Model.Items.Item;
import com.kyrrr.Model.Items.PotionSmall;
import com.kyrrr.Model.Moves.Hadoken;
import com.kyrrr.Model.Position.Coordinates;
import com.kyrrr.Model.Position.Zone;
import com.kyrrr.Model.Status.Inventory;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Game extends Loop {

    private boolean stop = false;
    private Player p;
    private List<Enemy> enemies;
    private List<String> log = new ArrayList<>();
    private List<Loot> loot;
    private Zone obstacles;
    private Actor goal;
    private ConsoleSystemInterface csi;
    private int level;
    private boolean progress = false;

    protected void log(String message){
        log.add(message);
    }

    protected void printLog(){
        csi.cls();
        int logLength = log.size();
        if(logLength >= 1){
            log = log.subList(Math.max(log.size() - 1, 0), log.size());
        }
        //int y = 25;
        for (String m : log){
            csi.print(Settings.screenWidth / 2, Settings.screenHeight / 2, m);
        }
        csi.refresh();
        csi.inkey();
    }



    Game(int level, Player p, ConsoleSystemInterface csi){
        this.csi = csi;
        this.level = level;
        this.p = p;
        setup();
        int numEnemies = level + 10;
       // p = new Player();
        EnemyFactory enFac = new EnemyFactory();
        enemies = enFac.makeAtRandom(numEnemies, Settings.screenWidth, Settings.screenHeight); //todo: actorfac
        Enemy extraEnemy = new Enemy();
        extraEnemy.setXpos(p.getXpos() + 1);
        extraEnemy.setYpos(p.getYpos() + 2);
        extraEnemy.setName("Pile of Trash");
        extraEnemy.addMove(new Hadoken());
       // extraEnemy.addMove(new Move("BAR", 100, new DamageHealth(10)));
       // enemies.add(extraEnemy);
        LootFactory lFac = new LootFactory();
        loot = lFac.makeAtRandom(20, Settings.screenWidth, Settings.screenHeight);
        Loot extraLoot = new Loot();
        extraLoot.setXpos(p.getXpos() + 1);
        extraLoot.setYpos(p.getYpos() + 1);
        extraLoot.add(new PotionSmall());
      /*  Loot extraLoot2 = new Loot(); //LOL JUST FOR TESTS!!
        extraLoot2.setXpos(p.getXpos() + 1);
        extraLoot2.setYpos(p.getYpos() + 2);
        extraLoot2.add(new PotionSmall());
        loot.add(extraLoot2);*/
       // System.out.println(loot);
        //List<Coordinates> exclude = new ArrayList<>();
        Zone exclude = new Zone();
        //List<Coordinates> exclude = iFac.getCoordinates();
        goal = new Actor();
        goal.setXpos(Settings.screenWidth - 1);
        goal.setYpos(Settings.screenHeight - 2);
        Zone gZ = new Zone(goal.getCoordinates(), 2, 2);
        gZ.calcRect();
        gZ.getArea().forEach(exclude::add);
        p.getZone().getArea().forEach(exclude::add);
        enemies.forEach(enemy -> exclude.add(enemy.getCoordinates()));
        loot.forEach(l -> exclude.add(l.getCoordinates()));
        //System.out.println(exclude.size());
        obstacles = new Zone();
        obstacles.setOrigin(p.getCoordinates());

        //obstacles.setSize(100); // over size means get everything
        obstacles.setHeight(Settings.screenHeight);
        obstacles.setWidth(Settings.screenWidth);
        obstacles.calcRectZoneWithObstacles(4, exclude);
    }

    @Override
    public void setup() {
        if(level == 1){
            csi.print(0,0,"FIGHT TO REVEAL THE GOLDEN DOOR");
            csi.refresh();
            csi.inkey();
            csi.cls();
            csi.print(0,0,"Enter name!!!");
            csi.refresh();
            csi.inkey();
            csi.cls();
            String name = csi.input();
            p = new Player();
            p.setName(name);
        } else {
            p.setName("TESTY");
            p.levelUp();
        }
    }

    private void printMap(){
        String obstacleChar = "|";
        String goalChar = "O";
        // print the goal as an obstacle until progression params met
        if(!progress){
            csi.print(goal.getXpos(), goal.getYpos(), obstacleChar, CSIColor.BROWN);
        }else{
            csi.print(goal.getXpos(), goal.getYpos(), goalChar, CSIColor.GOLD);
        }
        for (Coordinates obstacle : obstacles.getArea()){
            csi.print(obstacle.getX(), obstacle.getY(), obstacleChar, CSIColor.BROWN);
        }
    }

    private void printPlayer(){
        /*if(debug){
            for (Coordinates c : p.getZone().getArea()){
                csi.print(c.getX(), c.getY(), ".");
            }
        }*/
        csi.print(p.getXpos(), p.getYpos(), p.getModel(), p.getModelColor());
    }

    private void playerAction(){
        //System.out.println(p.getInventory());
        if(p.isAlive()){
            if(progress && p.detectCollision(goal)){
                restart("WIN");
            }
            CharKey dir = csi.inkey(); // wait for a key press.. i don't wanna wait OR DO I
            //System.out.println(dir);
            //System.out.println(dir.code);
           // System.out.println(dir.code);
            Coordinates next = p.getNextPos(dir.code);
            if(!obstacles.contains(next)){ // TODO: bounds check
                p.move(dir.code);
                p.getStatus().setHealth(p.getStatus().getHealth() - 1);
            }
        } else {
            restart("DEAD");
        }
    }

    private void enemyAction(int omniscience){ // lower = higher chance at moving towards target
        int alive = 0;
            for (Enemy e : enemies) {
                if(e.isAlive()) {
                    alive++;
                    int d = ThreadLocalRandom.current().nextInt(0, 4); // 1 of 4 directions
                    int cheat = ThreadLocalRandom.current().nextInt(0, omniscience);
                    if (e.getZone().intersects(p.getZone())) {
                        e.setModelColor(CSIColor.RAZZMATAZZ); // get snazzy
                        d = e.getCharCodeMoveTowards(p);
                    } else if (cheat == omniscience - 1) {
                        e.setModelColor(CSIColor.GREEN);
                        d = e.getCharCodeMoveTowards(p);
                    } else if (e.getModelColor() != CSIColor.AMBER) {
                        e.setModelColor(CSIColor.AMBER);
                    }
                    if (!obstacles.contains(e.getNextPos(d))) { // don't go through walls
                        e.move(d);
                    } else {
                        int r = ThreadLocalRandom.current().nextInt(Directions.values().length);
                        while (obstacles.contains(e.getNextPos(r))) {
                            r = ThreadLocalRandom.current().nextInt(Directions.values().length);
                        }
                        e.move(r);
                    }
                    if (e.detectCollision(p)) {
                        Combat combat = new Combat(csi, p, e);
                        combat.loop();
                    }
                }
            }
            if(alive == 0){
                progress = true;
            }
    }

    private void lootAction(){
        loot.stream().filter(l -> l.detectCollision(p)).forEach(l -> {
            if(l.isAlive()){
                Inventory i = l.getAll();
                //p.getInventory().addAll(i.getContents());
                String toLog = "";
             //   String toLog = "Picked up ";
                System.out.println(p.getInventory().getContents());
                for (Item it : i.getContents()){
                    p.getInventory().add(it);
                    toLog += it.getName() + " ";
                }
                System.out.println(p.getInventory().getContents());
                log(toLog);
                printLog();
                l.die();
            }
        });
       // System.out.println(p.getInventory());
    }

    private void printEnemy(){
        for (Enemy e : enemies){
            /*for (Coordinates ec : e.getZone().getArea()){
                csi.print(ec.getX(), ec.getY(), ".", CSIColor.YELLOW);
            }*/
            csi.print(e.getXpos(), e.getYpos(), e.getModel(), e.getModelColor()); // print enemies at their current position

        }
    }

    private void printLoot(){
        loot.stream().filter(Actor::isAlive).forEach(
                l -> csi.print(l.getXpos(), l.getYpos(), l.getModel(), l.getModelColor())
        );
    }

    private void printUI(){
        //System.out.println(p);
        int pHealth = p.getStatus().getHealth();
        csi.print(
                Settings.screenWidth - String.valueOf(pHealth).length(),
                Settings.screenHeight, String.valueOf(pHealth),
                p.getModelColor());
        String bottomLine = p.getName() + " lvl:" + p.getStatus().getLevel() + " | Floor: " + String.valueOf(level);
        if(p.getStatus().isPoisoned()){
            bottomLine += " | PSNx" + p.getStatus().getPoisonStack();
        }
        csi.print(0, Settings.screenHeight, bottomLine, p.getModelColor());
    }

    @Override
    public void loop() {
        while(!stop){
            printUI();
            printEnemy();
            printLoot();
            printPlayer();
            printMap();
            csi.refresh();
            playerAction();
            enemyAction(4);
            lootAction();
            csi.refresh();
            csi.cls();
        }
    }

    private void restart(String message){
        csi.cls();
        if(message.equals("WIN")){
            csi.print(Settings.screenWidth - message.length() - 1, Settings.screenHeight - 1, message, CSIColor.GREEN);
            csi.refresh();
            Game ng = new Game(level + 1, p, csi);
            csi.inkey();
            csi.cls();
            ng.loop();
        } else {
            csi.print(Settings.screenWidth - message.length() - 1, Settings.screenHeight - 1, message, CSIColor.RED);
            csi.refresh();
            int actionCode = csi.inkey().code;
            csi.cls();
            if(actionCode == CharKey.R || actionCode == CharKey.r) {
                Game g = new Game(1, p, csi);
                g.loop();
            } else {
                restart("R to restart");
            }
        }
    }

}
