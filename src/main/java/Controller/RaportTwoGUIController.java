package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Klasa RaportTwoGUIController odpowiadająca za kontrolę okna programu odpowiadającego za Raport 2
 */
public class RaportTwoGUIController {
    private static RaportTwoGUIController raportTwoGUIController;

    /**
     * Konstruktor klasy RaportTwoGUIController
     */
    public RaportTwoGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    /**
     * Metoda getInstance() odpowiadająca za zwrócenie instancji klasy RaportTwoGUIController
     */
    public static RaportTwoGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(raportTwoGUIController)) {
            raportTwoGUIController = new RaportTwoGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return raportTwoGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        /**
         * Metoda getButton_raportstwo_make() odpowiadająca za obsłużenie przycisku "Generuj raport"
         */
        mainGUIForm.getButton_raportstwo_make().addActionListener(e -> {
            try {
                ResultSet resultSet = utilsDatabase.raportTwoButtonOne();
                mainGUIForm.panel_raportstwo_output_init(resultSet);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        /**
         * Metoda getButton_raportstwo_save() odpowiadająca za obsłużenie przycisku "Zapisz raport"
         */
        mainGUIForm.getButton_raportstwo_save().addActionListener(e -> {
            try {
                utilsDatabase.raportTwoButtonTwo(mainGUIForm);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
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
