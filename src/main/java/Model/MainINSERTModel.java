package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Objects;

public class MainINSERTModel {
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
                        break;
                    case 2:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_2().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_2().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_2().getText());
                        }
                        break;
                    case 3:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_3().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_3().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_3().getText());
                        }
                        break;
                    case 4:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_4().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_4().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_4().getText());
                        }
                        break;
                    case 5:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_5().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_5().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_5().getText());
                        }
                        break;
                    case 6:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_6().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_6().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_6().getText());
                        }
                        break;
                    case 7:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_7().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_7().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_7().getText());
                        }
                        break;
                    case 8:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_8().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_8().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_8().getText());
                        }
                        break;
                    case 9:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_9().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_9().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_9().getText());
                        }
                        break;
                    case 10:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_10().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_10().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_10().getText());
                        }
                        break;
                    case 11:
                        if(!Objects.equals(mainGUIForm.getTextField_si_insert_input_values_11().getText(), "")) {
                            arrayList_labels.add(mainGUIForm.getLabel_si_insert_input_values_11().getText());
                            arrayList_text.add(mainGUIForm.getTextField_si_insert_input_values_11().getText());
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
        } catch (Exception exception) {
            return false;
        }
    }
}
