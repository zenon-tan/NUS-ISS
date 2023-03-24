package springdemo.springdemoannotations;

import org.springframework.stereotype.Component;

@Component

public class SoccerCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Time to Messi idk soccer";
    }

    @Override
    public String getDailyFortune() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
