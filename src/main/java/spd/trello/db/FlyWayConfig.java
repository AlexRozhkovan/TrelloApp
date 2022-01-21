package spd.trello.db;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlyWayConfig {

    public static Flyway createFlyway(DataSource dataSource){
        return Flyway.configure().dataSource(dataSource).locations("classpath:migrationsForDB/tables").load();
    }
}
