package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

    @Autowired
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public static void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(App.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }
}