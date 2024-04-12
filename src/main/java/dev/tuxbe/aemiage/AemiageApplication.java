package dev.tuxbe.aemiage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AemiageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AemiageApplication.class, args);
    }

}
