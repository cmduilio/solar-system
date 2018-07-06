package galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	SolarSystem.init();
    	SolarSystem.runForCycles(10);
//        SpringApplication.run(Application.class, args);
    }
}
