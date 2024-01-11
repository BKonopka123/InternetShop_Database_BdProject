package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa MainSELECTGUIController odpowiadająca za kontrolę okna programu odpowiadającego za SELECT
 */
public class MainSELECTGUIController {
    private static MainSELECTGUIController mainSELECTGUIController;
    private String selectedTable;

    /**
     * Konstruktor klasy MainSELECTGUIController
     */
    public MainSELECTGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    /**
     * Metoda getInstance() tworząca instancję klasy MainSELECTGUIController
     * @return instancja klasy MainGUIController
     */
    public static MainSELECTGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(mainSELECTGUIController)) {
            mainSELECTGUIController = new MainSELECTGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return mainSELECTGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        /**
         * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu checkBoxa "ComboBox_si_select_input_table"
         */
        mainGUIForm.getComboBox_si_select_input_table().addActionListener(e -> {
            selectedTable = (String) mainGUIForm.getComboBox_si_select_input_table().getSelectedItem();

        });

        /**
         * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu przycisku "ComboBox_si_select_input_table"
         */
        mainGUIForm.getButton_si_select_input_right_wypisz().addActionListener(e -> {
            try {
                mainGUIForm.panel_si_select_input_right_more_init(selectedTable);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: Wybierz opcję", "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        /**
         * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu przycisku "ComboBox_si_select_input_table"
         */
        mainGUIForm.getButton_si_select_input_make().addActionListener(e -> {
            String selectedTableNow = (String) mainGUIForm.getComboBox_si_select_input_table().getSelectedItem();
            try {
                int selectedTableNowNumber;
                switch(selectedTableNow){
                    case "Producent", "Ocena", "Zamowione_produkty", "Dostawa", "Zamowiona_dostawa":
                        selectedTableNowNumber = 4;
                        break;
                    case "Klient", "Pracownik":
                        selectedTableNowNumber = 6;
                        break;
                    case "Produkt":
                        selectedTableNowNumber = 12;
                        break;
                    case "Typ_produktu":
                        selectedTableNowNumber = 2;
                        break;
                    case "Zamowienie":
                        selectedTableNowNumber = 5;
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

        /**
         * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu przycisku "ComboBox_si_select_input_table"
         */
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

    /**
     * Metoda init() odpowiadająca za inicjalizację głównego okna programu
     */
    private static void init(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        mainGUIForm.getFrame().setVisible(true);
    }

}
