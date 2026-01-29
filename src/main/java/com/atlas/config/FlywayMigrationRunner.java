package com.atlas.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Explicitly runs Flyway migrations on application startup
 */
@Component
public class FlywayMigrationRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(FlywayMigrationRunner.class);

    @Autowired
    private DataSource dataSource;

    @Value("${spring.flyway.enabled:true}")
    private boolean flywayEnabled;

    @Value("${spring.flyway.locations:classpath:db/migration}")
    private String locations;

    @Override
    public void run(String... args) throws Exception {
        if (!flywayEnabled) {
            logger.info("Flyway is disabled");
            return;
        }

        logger.info("Starting Flyway migrations from: {}", locations);

        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations(locations)
                    .baselineOnMigrate(true)
                    .baselineVersion("0")
                    .baselineDescription("Initial baseline")
                    .outOfOrder(false)
                    .validateOnMigrate(true)
                    .load();

            var migrateResult = flyway.migrate();
            logger.info("✓ Flyway completed: {} migrations applied", migrateResult.migrationsExecuted);
        } catch (Exception e) {
            logger.error("✗ Flyway migration failed", e);
            throw e;
        }
    }
}
