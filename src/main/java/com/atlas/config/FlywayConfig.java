package com.atlas.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Explicit Flyway configuration to ensure migrations run on startup
 */
@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private FlywayProperties flywayProperties;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .baselineDescription("Initial baseline")
                .outOfOrder(false)
                .validateOnMigrate(true)
                .load();

        flyway.migrate();
        return flyway;
    }
}
