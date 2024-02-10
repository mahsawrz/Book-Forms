package jse.metaco.project;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyConnectionPool {
    private static MyConnectionPool myConnectionPool = new MyConnectionPool();
    private BasicDataSource dataSource;

    private MyConnectionPool() {
        ResourceBundle rb = ResourceBundle.getBundle("databaseInfo");
        dataSource = new BasicDataSource();
        String port = rb.getString("database.port");
        String ip = rb.getString("database.ip");
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin://" + "@" + ip + ":" + port + ":" + "XE");
        String username = rb.getString("database.username");
        String password = rb.getString("database.password");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(6);
        dataSource.setMaxTotal(8);
    }
//****************************************************************************************************
    public static Connection getConnection() {
        try {
            return myConnectionPool.dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Can not open connect in the DBCP Pool");
            e.printStackTrace();
            return null;
        }

    }
}
