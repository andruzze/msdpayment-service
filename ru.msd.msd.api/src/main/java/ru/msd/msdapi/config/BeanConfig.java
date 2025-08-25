package ru.msd.msdapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.msd.msdapi.converter.*;

@Configuration
public class BeanConfig {
    @Bean ApplicationProperties applicationProperties() {return new ApplicationProperties();}

    @Bean
    public CarNumberConverter carNumberConverter() {
        return new CarNumberConverter();
    }

    @Bean
    public UserConverter userConverter() {
        return new UserConverter();
    }

    @Bean
    public CarRegisterConverter carRegisterConverter() {
        return new CarRegisterConverter(this.carNumberConverter(), this.userConverter());
    }

    @Bean
    public TransitConverter transitConverter() {
        return new TransitConverter(this.carNumberConverter());
    }

    @Bean
    public TransactionConverter transactionConverter() {
        return new TransactionConverter(this.transitConverter());
    }
}
