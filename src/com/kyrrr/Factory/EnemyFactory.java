package com.kyrrr.Factory;

import com.kyrrr.Model.Actors.Enemy;
import com.kyrrr.Model.Moves.Fireball;
import net.slashie.libjcsi.CSIColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kyrrebugge on 18.02.2017.
 */
public class EnemyFactory extends Factory {

    private int xbound, ybound;


    @Override
    public List makeAtRandom(int amount, int xBound, int yBound){
        this.xbound = xBound;
        this.ybound = yBound;
        List<Enemy> enemies = new ArrayList<>();
        //Zone copy = exclude.getCopy();
        for (int i = 1; i <= amount; i++){
            //Enemy enemy = new Enemy();
            Enemy enemy;
            int randy = ThreadLocalRandom.current().nextInt(0,4);
            if(randy == 0){
                enemy = getPreset("Magic Man");
            } else if(randy == 1){
                enemy = getPreset("Thunder Cougar");
            } else if(randy == 2){
                enemy = getPreset("Falcon Bird");
            } else {
                enemy = getPreset("Bear on fire");
            }
           // int randX = ThreadLocalRandom.current().nextInt(12, xbound - 1);
          //  int randY = ThreadLocalRandom.current().nextInt(1, ybound - 1);
          //  enemy.setName("enemy"+i);
         //   enemy.setPos(randX, randY);
          //  enemy.setSpeed(9000);
            enemies.add(enemy);
        }
        return enemies;
    }

    public Enemy getPreset(String preset){
        Enemy e = new Enemy();
        int randX = ThreadLocalRandom.current().nextInt(12, xbound - 1);
        int randY = ThreadLocalRandom.current().nextInt(1, ybound - 1);
        int randSpeed = ThreadLocalRandom.current().nextInt(20, 69);
        e.setPos(randX, randY);
        e.setSpeed(randSpeed);
        switch (preset.toLowerCase()){
            case "magic man":
                //e.addMove(new Move("PoisonChance", 40, new PoisonChance()));
                e.addMove(new Fireball());
                //e.addMove(new Move("Game", 10, new BoostHealth(25)));
                e.setModelColor(CSIColor.BROWN);
                e.setName(preset);
                break;
            case "thunder cougar":
                e.setName(preset);
                e.addMove(new Fireball());

                e.setModelColor(CSIColor.GREEN);
                break;
            case "falcon bird":
                e.setName(preset);
                e.addMove(new Fireball());

                e.setModelColor(CSIColor.GREEN);
            break;
            case "bear on fire":
                e.setName(preset);
                e.addMove(new Fireball());

                e.setModelColor(CSIColor.GREEN);
            break;
            default:
                e.setName("DefaultGuy");
                e.addMove(new Fireball());

                e.setModelColor(CSIColor.GREEN);
                break;
        }
        return e;
    }
}
