package spd.trello;

import org.flywaydb.core.Flyway;
import spd.trello.db.ConnectionForDB;
import spd.trello.db.FlyWayConfig;

import javax.sql.DataSource;
import java.io.IOException;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        ConnectionForDB.getConnection();
        DataSource dataSource = ConnectionForDB.createDataSource();
        Flyway flyway = FlyWayConfig.createFlyway(dataSource);
        flyway.migrate();

    }

}

