package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;

public class App01GamingBasicJava {

    public static void main(String[] args) {

        //var game = new MarioGame();
        //var game = new SuperContraGame();
        var game = new PacManGame(); // 1. Object creation
        // gameRunner class is coupled with an interface
        var gameRunner = new GameRunner(game);
        // 2. Object creation + Wiring of dependencies
        // Game is a dependency of GameRunner
        // The Game is injected/wired into gameRunner
        gameRunner.run();


        
    }
    
}
