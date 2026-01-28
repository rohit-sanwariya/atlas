package com.atlas.config;

import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * Explicit Flyway configuration to ensure migrations run correctly on startup
 */
@Configuration
public class FlywayConfig {

    @Bean
    public FlywayConfigurationCustomizer flywayConfigurationCustomizer() {
        return configuration -> {
            configuration.baselineOnMigrate(true);
            configuration.baselineVersion("0");
            configuration.baselineDescription("Initial baseline");
            configuration.outOfOrder(false);
            configuration.validateOnMigrate(true);
            configuration.locations("classpath:db/migration");
        };
    }
}
