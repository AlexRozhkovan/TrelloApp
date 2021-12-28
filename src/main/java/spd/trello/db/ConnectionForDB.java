package spd.trello.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionForDB {

    public static Connection getConnection() throws SQLException, IOException {
        DataSource dataSource = createDataSource();
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            if (connection != null)
            {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else
            {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e)
        {
            getSQLExceptionInfo(e);
        }

        return connection;
    }

    public static void getSQLExceptionInfo(SQLException e)
    {
        System.out.println("SQLException message:" + e.getMessage());
        System.out.println("SQLException SQL state:" + e.getSQLState());
        System.out.println("SQLException SQL error code:" + e.getErrorCode());
    }


    private static Properties loadProperties() throws IOException{
        InputStream in = ConnectionForDB.class.getClassLoader()
                .getResourceAsStream("application.properties");

        Properties properties = new Properties();
        properties.load(in);

        return properties;
    }

    public static DataSource createDataSource() throws IOException {
        Properties properties = loadProperties();

        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(properties.getProperty("jdbc.url"));
        cfg.setUsername(properties.getProperty("jdbc.username"));
        cfg.setPassword(properties.getProperty("jdbc.password"));

        int maxConnections = Integer.parseInt(properties.getProperty("jdbc.pool.maxConnection"));
        cfg.setMaximumPoolSize(maxConnections);

        return new HikariDataSource(cfg);
    }
}
