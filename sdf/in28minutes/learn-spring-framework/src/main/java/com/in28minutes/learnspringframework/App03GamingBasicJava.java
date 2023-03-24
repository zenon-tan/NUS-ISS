package com.in28minutes.learnspringframework;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;

public class App03GamingBasicJava {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(GameConfiguration.class)) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        } catch (BeansException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






        
    }
    
}
