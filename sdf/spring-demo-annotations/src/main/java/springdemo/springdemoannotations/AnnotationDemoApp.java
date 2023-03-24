package springdemo.springdemoannotations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationDemoApp {

    public static void main(String[] args) {
        // Read spring config file

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TennisCoach.class);
        
        // Get bean from spring container
        Coach theCoach = context.getBean("tennisCoach", Coach.class);
        
        // Call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // Call method to get daily fortune
        System.out.println(theCoach.getDailyFortune());

        // Close the context
        context.close();
    }
    
}
