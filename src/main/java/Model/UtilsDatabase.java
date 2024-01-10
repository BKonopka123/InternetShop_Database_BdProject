package Model;

import org.apache.ibatis.jdbc.ScriptRunner;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
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

    public static ResultSet selectMain(View.MainGUIForm mainGUIForm, String tableName, int number) throws SQLException {
        setSearchPath("sklep");
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i <= number; i++) {
                if (mainGUIForm.getCheckBox_si_select_input_right_more_0().isSelected()) {
                    arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_0().getText());
                    break;
                }
                switch (i) {
                    case 0:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_0().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_0().getText());
                        break;
                    case 1:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_1().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_1().getText());
                        break;
                    case 2:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_2().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_2().getText());
                        break;
                    case 3:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_3().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_3().getText());
                        break;
                    case 4:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_4().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_4().getText());
                        break;
                    case 5:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_5().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_5().getText());
                        break;
                    case 6:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_6().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_6().getText());
                        break;
                    case 7:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_7().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_7().getText());
                        break;
                    case 8:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_8().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_8().getText());
                        break;
                    case 9:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_9().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_9().getText());
                        break;
                    case 10:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_10().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_10().getText());
                        break;
                    case 11:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_11().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_11().getText());
                        break;
                    case 12:
                        if (mainGUIForm.getCheckBox_si_select_input_right_more_12().isSelected())
                            arrayList.add((String) mainGUIForm.getCheckBox_si_select_input_right_more_12().getText());
                    default:
                        break;
                }
            }
        } catch (Exception exception) {
            JOptionPane.showConfirmDialog(null, "Błąd", "Error", JOptionPane.DEFAULT_OPTION);
            throw new RuntimeException(exception);
        }
        try {
            String selectedColumns = String.join(", ", arrayList);
            PreparedStatement sql = connection.prepareStatement("SELECT " + selectedColumns + " FROM " + tableName);
            ResultSet resultSet = sql.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet selectOwn(View.MainGUIForm mainGUIForm, String query) throws SQLException {
        setSearchPath("sklep");
        try {
            PreparedStatement sql = connection.prepareStatement(query);
            ResultSet resultSet = sql.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Błąd: Nieprawidłowy SELECT", "Error", JOptionPane.DEFAULT_OPTION);
            throw new RuntimeException(e);
        }
    }
}