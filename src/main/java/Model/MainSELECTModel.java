package Model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa MainSELECTModel odpowiadająca za SELECT
 */
public class MainSELECTModel {
    /**
     * Metoda selectMain() odpowiadająca za główny SELECT
     * @param mainGUIForm - główne okno programu
     * @param tableName - nazwa tabeli z którego wykonywany będzie SELECT
     * @param number - liczba kolumn w tabeli
     * @return resultSet
     * @throws SQLException - wyjątek
     */
    public static ResultSet selectMainMethod(Connection connection, View.MainGUIForm mainGUIForm, String tableName, int number) throws SQLException {
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
            PreparedStatement sql = connection.prepareStatement("SELECT " + selectedColumns + " FROM sklep." + tableName);
            //System.out.println(sql);
            ResultSet resultSet = sql.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda selectOwn() odpowiadająca za własny SELECT
     * @param mainGUIForm - główne okno programu
     * @param query - zapytanie
     * @return resultSet
     * @throws SQLException - wyjątek
     */
    public static ResultSet selectOwnMethod(Connection connection, View.MainGUIForm mainGUIForm, String query) throws SQLException {
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
