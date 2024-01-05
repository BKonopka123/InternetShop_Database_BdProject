package Model;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class UtilsDatabase {
    private static final String DB_NAME = "zqyaexfc";
    private static final String DB_USER = "zqyaexfc";
    private static final String DB_PASSWORD = "vqi_DfuYKHGl9sZhflMgQ7lJZ_1CKive";
    private static final String DB_HOST = "flora.db.elephantsql.com";
    private static final String DB_PORT = "5432";
    private static final String JDBC_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static Connection connection;

    public void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database.");
    }

    public void disconnectFromDatabase() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initialization_sql() {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setSendFullScript(true);
        scriptRunner.setStopOnError(true);
        try {
            scriptRunner.runScript(new java.io.FileReader("src/main/resources/initialization_sql.sql"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete_sql() {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setSendFullScript(true);
        scriptRunner.setStopOnError(true);
        try {
            scriptRunner.runScript(new java.io.FileReader("src/main/resources/delete_sql.sql"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setSearchPath(String searchPath) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Execute the SET search_path query
            statement.execute("SET search_path TO " + searchPath);
        }
    }
}