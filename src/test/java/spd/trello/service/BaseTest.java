package spd.trello.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static HikariDataSource dataSource;

    @BeforeAll
    public static void init(){
        HikariConfig cfg = new HikariConfig();
        cfg.setPassword("sa");
        cfg.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
        cfg.setUsername("sa");
        cfg.setDriverClassName("org.h2.Driver");
        dataSource = new HikariDataSource(cfg);
        Flyway flyway = Flyway.configure()
                .locations("classpath:migrationsForDB/tables")
                .dataSource(dataSource)
                .load();
        flyway.migrate();


    }
}
