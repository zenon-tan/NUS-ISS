package com.in28minutes.learnspringframework.game;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
//YourBusinessClass
class YourBusinessClass {

    // @Autowired // Field Injection
    Dependency1 dependency1;

    // @Autowired // Field Injection
    Dependency2 dependency2;

    
    @Autowired // Don't even need this for constructor injection
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }



    @Override
    public String toString() {
        return "YourBusinessClass [dependency1=" + dependency1 + ", dependency2=" + dependency2 + "]";
    }

    // @Autowired
    // public void setDependency1(Dependency1 dependency1) {
    //     System.out.println("Setter Injection - setDependency1");
    //     this.dependency1 = dependency1;
    // }

    // @Autowired
    // public void setDependency2(Dependency2 dependency2) {
    //     System.out.println("Setter Injection - setDependency2");
    //     this.dependency2 = dependency2;
    // }

    

    
}
@Component
//Dependency1
class Dependency1 {

}

@Component
//Dependency2
class Dependency2 {

}



@Configuration
@ComponentScan

public class GamingAppLauncherApplication {


    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {

            // context.getBean(GamingConsole.class).up();
            // context.getBean(GameRunner.class).run();

            Arrays.stream(context.getBeanDefinitionNames())
            .forEach(System.out::println);

            System.out.println(context.getBean(YourBusinessClass.class));
            context.getBean(YourBusinessClass.class);



        } catch (BeansException e) {
            e.printStackTrace();
        }

        
    }
    
}