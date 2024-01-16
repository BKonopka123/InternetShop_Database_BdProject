package Model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa MainINSERTModel odpowiadająca za INSERT w bazie danych
 */
public class MainINSERTModel {

    /**
     * Metoda insertMain() odpowiadająca za główny INSERT
     * @param mainGUIForm - główne okno programu
     * @param tableName - nazwa tabeli do której wykonywany będzie INSERT
     * @param number - liczba kolumn w tabeli
     * @return true/false
     * @throws SQLException - wyjątek
     */
    public static Boolean insertMainMethod(Connection connection, View.MainGUIForm mainGUIForm, String tableName, int number) {
        ArrayList<String> arrayList_labels = new ArrayList<>();
        ArrayList<String> arrayList_text = new ArrayList<>();
        try {
            for (int i = 1; i <= number; i++) {
                switch (i) {
                    case 1:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_1().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_1().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_1().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_1().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_1().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_1().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 2:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_2().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_2().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_2().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_2().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_2().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_2().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 3:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_3().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_3().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_3().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_3().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_3().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_3().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 4:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_4().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_4().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_4().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_4().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_4().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_4().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 5:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_5().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_5().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_5().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_5().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_5().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_5().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 6:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_6().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_6().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_6().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_6().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_6().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_6().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 7:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_7().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_7().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_7().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_7().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_7().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_7().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 8:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_8().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_8().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_8().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_8().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_8().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_8().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 9:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_9().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_9().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_9().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_9().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_9().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_9().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 10:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_10().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_10().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_10().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_10().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_10().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_10().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    case 11:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_11().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_11().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_11().getText());
                        }
                        else if(Objects.equals(mainGUIForm.getTextField_si_insert_input_values_11().getText(), "") && mainGUIForm.getLabel_si_insert_input_values_info_11().getText().matches(".*NOT NULL.*")) {
                            JOptionPane.showConfirmDialog(null, "Błąd: Pole " + mainGUIForm.getLabel_si_insert_input_values_11().getText() + " nie może być puste", "Choose option", JOptionPane.DEFAULT_OPTION);
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.println(arrayList_labels);
            System.out.println(arrayList_text);
        } catch (Exception exception) {
            return false;
        }
        try {
            String selectedColumns = String.join(", ", arrayList_labels);
            String selectedValues = String.join("', '", arrayList_text);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO sklep." + tableName + " (" + selectedColumns + ") VALUES ('" + selectedValues + "')");
            System.out.println(sql);
            sql.executeUpdate();
            return true;
        } catch(SQLException exception) {
            JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        catch (Exception exception) {
            JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
            return false;
        }
    }
}
