package Model;

import org.apache.ibatis.jdbc.ScriptRunner;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa UtilsDatabase odpowiadająca za połączenie z bazą danych
 */
public class UtilsDatabase {
    private static final String DB_NAME = "zqyaexfc";
    private static final String DB_USER = "zqyaexfc";
    private static final String DB_PASSWORD = "vqi_DfuYKHGl9sZhflMgQ7lJZ_1CKive";
    private static final String DB_HOST = "flora.db.elephantsql.com";
    private static final String DB_PORT = "5432";
    private static final String JDBC_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static Connection connection;

    /**
     * Konstruktor klasy UtilsDatabase
     */
    public void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database.");
    }

    /**
     * Metoda disconnectFromDatabase() odpowiadająca za rozłączenie z bazą danych
     */
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

    //Incjalizacja i usunięcie bazy danych
    /**
     * Metoda initialization_sql() odpowiadająca za inicjalizację bazy danych
     */
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

        /**
         * Metoda delete_sql() odpowiadająca za usunięcie bazy danych
         */
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

        /**
         * Metoda setSearchPath() odpowiadająca za ustawienie ścieżki wyszukiwania
         * @param searchPath
         * @throws SQLException
         */
        private static void setSearchPath(String searchPath) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                // Execute the SET search_path query
                statement.execute("SET search_path TO " + searchPath);
            }
        }

    //Główny SELECT
        /**
         * Metoda selectMain() odpowiadająca za główny SELECT
         * @param mainGUIForm - główne okno programu
         * @param tableName - nazwa tabeli z którego wykonywany będzie SELECT
         * @param number - liczba kolumn w tabeli
         * @return resultSet
         * @throws SQLException - wyjątek
         */
        public static ResultSet selectMain(View.MainGUIForm mainGUIForm, String tableName, int number) throws SQLException {
            setSearchPath("sklep");
            MainSELECTModel mainSELECTModel = new MainSELECTModel();
            ResultSet resultset = mainSELECTModel.selectMainMethod(connection, mainGUIForm, tableName, number);
            return resultset;
        }

        /**
         * Metoda selectOwn() odpowiadająca za własny SELECT
         * @param mainGUIForm - główne okno programu
         * @param query - zapytanie
         * @return resultSet
         * @throws SQLException - wyjątek
         */
        public static ResultSet selectOwn(View.MainGUIForm mainGUIForm, String query) throws SQLException {
            setSearchPath("sklep");
            MainSELECTModel mainSELECTModel = new MainSELECTModel();
            ResultSet resultset = mainSELECTModel.selectOwnMethod(connection, mainGUIForm, query);
            return resultset;
        }

    //Główny INSERT
        public static Boolean insertMain(View.MainGUIForm mainGUIForm, String tableName, int number) throws SQLException {
            setSearchPath("sklep");
            MainINSERTModel mainINSERTModel = new MainINSERTModel();
            Boolean result = mainINSERTModel.insertMainMethod(connection, mainGUIForm, tableName, number);
            return result;
        }

}