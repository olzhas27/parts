package ru.javarush.intership.dao.impl.mysql;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static final ConnectionFactory instance =  new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    private MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

    private ConnectionFactory() {
        ds.setURL("jdbc:mysql://localhost:3306/test");
        ds.setUser("root");
        ds.setPassword("1qaz@WSX");
        try {
            ds.setCharacterEncoding("UTF8");
            ds.setVerifyServerCertificate(false);
            ds.setUseSSL(false);
            ds.setServerTimezone("Europe/Moscow");
        } catch (SQLException e) {
            logger.error("Connection to DB cannot been established", e);
            throw new RuntimeException(e);
        }

    }


    private Connection connection;
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ds.getConnection();
        }
        return ds.getConnection();
    }
}
