package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa MainINSERTGUIController odpowiadająca za kontrolę okna programu odpowiadającego za INSERT
 */
public class MainINSERTGUIController {

    private static MainINSERTGUIController mainINSERTGUIController;
    private String selectedTable;

    /**
     * Konstruktor klasy MainINSERTGUIController
     */
    public MainINSERTGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    /**
     * Metoda getInstance() tworząca instancję klasy MainINSERTGUIController
     * @return instancja klasy MainGUIController
     */
    public static MainINSERTGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(mainINSERTGUIController)) {
            mainINSERTGUIController = new MainINSERTGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return mainINSERTGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {

        /**
         * Metoda getButton_si_insert_input_wpisz() odpowiadająca za zapisanie wybranej wartość w CheckBox
         */
        mainGUIForm.getComboBox_si_insert_input().addActionListener(e -> {
            selectedTable = (String) mainGUIForm.getComboBox_si_insert_input().getSelectedItem();
        });

        /**
         * Metoda getButton_si_insert_input_wpisz() odpowiadająca za zapisanie wybranej wartość w CheckBox w panelu
         */
        mainGUIForm.getButton_si_insert_input_wpisz().addActionListener(e -> {
            try {
                mainGUIForm.panel_si_insert_input_values_init(selectedTable);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: Wybierz opcję", "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        /**
         * Metoda getButton_si_insert_input_wpisz() odpowiadająca za wykonanie INSERTU
         */
        mainGUIForm.getButton_si_insert_input_make().addActionListener(e -> {
                String selectedTableNow = (String) mainGUIForm.getComboBox_si_insert_input().getSelectedItem();
                try {
                    int selectedTableNowNumber;
                    switch(selectedTableNow){
                        case "Producent", "Ocena", "Zamowione_produkty", "Dostawa", "Zamowiona_dostawa":
                            selectedTableNowNumber = 3;
                            break;
                        case "Klient", "Pracownik":
                            selectedTableNowNumber = 5;
                            break;
                        case "Produkt":
                            selectedTableNowNumber = 11;
                            break;
                        case "Typ_produktu":
                            selectedTableNowNumber = 1;
                            break;
                        case "Zamowienie":
                            selectedTableNowNumber = 4;
                            break;
                        default:
                            selectedTableNowNumber = 0;
                            break;
                    }
                    Boolean result = utilsDatabase.insertMain(mainGUIForm, selectedTableNow, selectedTableNowNumber);
                    mainGUIForm.label_si_insert_output_init(result);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd", "Error", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
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
