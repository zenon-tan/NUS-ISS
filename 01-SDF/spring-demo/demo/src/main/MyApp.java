import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MyApp.class);
        
        // Create the object
        BaseballCoach theCoach = new BaseballCoach();
        
        // Use the object
        System.out.println(theCoach.getDailyWorkout());
    }
    
}
