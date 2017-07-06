package com.kyrrr;

import net.slashie.libjcsi.ConsoleSystemInterface;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        ConsoleSystemInterface csi = window.make();
        Game game = new Game();
        game.loop(csi);
    }
}