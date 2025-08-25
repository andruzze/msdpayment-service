package ru.msd.msdapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "msd.api")
public class ApplicationProperties{
    private int carRegistersLimit;
}
