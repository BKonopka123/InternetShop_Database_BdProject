package Controller;


import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MainGUIController {
    private static MainGUIForm mainGUIForm;
    private static MainGUIController mainGUIController;
    private static final UtilsDatabase utilsDatabase = new UtilsDatabase();

    private String selectedTable;

    public MainGUIController() {
        control();
    }

    public static MainGUIController getInstance() {
        if(Objects.isNull(mainGUIController)) {
            mainGUIForm = new MainGUIForm();
            mainGUIController = new MainGUIController();
        }
        init();
        return mainGUIController;
    }

    public void control() {
        try {
            utilsDatabase.connectToDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        mainGUIForm.getButton_initialize_database().addActionListener(e -> {
            try {
                utilsDatabase.initialization_sql();
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: Baza danych została już wcześniej zainicjowana", "Initialize Database", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
            JOptionPane.showConfirmDialog(null, "Baza danych zainicjowana", "Initialize Database", JOptionPane.DEFAULT_OPTION);
        });

        mainGUIForm.getButton_delete_database().addActionListener(e -> {
            try {
                utilsDatabase.delete_sql();
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: Baza danych została już wcześniej usunięta", "Delete Database", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
            JOptionPane.showConfirmDialog(null, "Baza danych usunięta", "Delete Database", JOptionPane.DEFAULT_OPTION);
        });

        mainGUIForm.getComboBox_si_select_input_table().addActionListener(e -> {
            selectedTable = (String) mainGUIForm.getComboBox_si_select_input_table().getSelectedItem();

        });

        mainGUIForm.getButton_si_select_input_right_wypisz().addActionListener(e -> {
            try {
                mainGUIForm.panel_si_select_input_right_more_init(selectedTable);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: Wybierz opcję", "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        mainGUIForm.getButton_si_select_input_make().addActionListener(e -> {
            String selectedTableNow = (String) mainGUIForm.getComboBox_si_select_input_table().getSelectedItem();
            try {
                int selectedTableNowNumber;
                switch(selectedTableNow){
                    case "Producent":
                        selectedTableNowNumber = 4;
                        break;
                    case "Klient":
                        selectedTableNowNumber = 6;
                        break;
                    case "Pracownik":
                        selectedTableNowNumber = 6;
                        break;
                    case "Produkt":
                        selectedTableNowNumber = 12;
                        break;
                    case "Typ_produktu":
                        selectedTableNowNumber = 2;
                        break;
                    case "Ocena":
                        selectedTableNowNumber = 4;
                        break;
                    case "Zamowienie":
                        selectedTableNowNumber = 5;
                        break;
                    case "Zamowione_produkty":
                        selectedTableNowNumber = 4;
                        break;
                    case "Dostawa":
                        selectedTableNowNumber = 4;
                        break;
                    case "Zamowiona_dostawa":
                        selectedTableNowNumber = 4;
                        break;
                    default:
                        selectedTableNowNumber = 0;
                        break;
                }
                ResultSet result = utilsDatabase.selectMain(mainGUIForm, selectedTableNow, selectedTableNowNumber);
                mainGUIForm.panel_si_select_output_init(result);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        mainGUIForm.getButton_si_select_own().addActionListener(e -> {
            try {
                String query = mainGUIForm.getTextField_si_select_own().getText();
                ResultSet result = utilsDatabase.selectOwn(mainGUIForm, query);
                mainGUIForm.panel_si_select_output_init(result);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void init() {
        mainGUIForm.getFrame().setVisible(true);
    }
}
