package ru.msd.msdapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "ru.msd.msdapi",
        "ru.msd.msd.agent.telegram.config",
        "ru.msd.msd.agent.config"
})
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
