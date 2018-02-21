package com.kyrrr;

import com.kyrrr.Model.Actors.Player;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        ConsoleSystemInterface csi = window.make();
        Player p = new Player();
        p.getStatus().setLevel(12);
        Game game = new Game(2, p, csi);
        game.loop();
    }
}
