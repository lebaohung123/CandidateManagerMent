package app.repository.impl;

import app.utils.Log4jClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE = "asss";
    private static final String URL = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + "; databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PW = "123456";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PW);
            System.out.println("KET NOI THANH CONG");
            Log4jClass.info("Log info");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("KET NOI THAT BAI");
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("KET NOI THAT BAI");
        }
        return null;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
