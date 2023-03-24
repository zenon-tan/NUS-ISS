package com.in28minutes.learnspringframework.game;

import com.in28minutes.learnspringframework.GamingConsole;
import com.in28minutes.learnspringframework.MarioGame;
import com.in28minutes.learnspringframework.SuperContraGame;

public class GameRunner {

    //MarioGame game;
    private GamingConsole game;

    // GameRunner is coupled with GamingConsole

    public GameRunner(GamingConsole game) {
        this.game = game;
    }

    public void run() {

        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }

}
