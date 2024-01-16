package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Klasa RaportOneGUIController odpowiadająca za kontrolę okna programu odpowiadającego za Raport 1
 */
public class RaportOneGUIController {
    private static RaportOneGUIController raportOneGUIController;

    /**
     * Konstruktor klasy RaportOneGUIController
     */
    public RaportOneGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    /**
     * Metoda getInstance() odpowiadająca za zwrócenie instancji klasy RaportOneGUIController
     */
    public static RaportOneGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(raportOneGUIController)) {
            raportOneGUIController = new RaportOneGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return raportOneGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        /**
         * Metoda getButton_raportsone_make().addActionListener() odpowiadająca za obsługę zdarzenia naciśnięcia przycisku "Generuj raport"
         */
        mainGUIForm.getButton_raportsone_make().addActionListener(e -> {
            try {
                Double value = Double.parseDouble(mainGUIForm.getTextField_raportsone_value().getText());
                ResultSet resultSet = utilsDatabase.raportOneButtonOne(value);
                mainGUIForm.panel_raportsone_output_init(resultSet);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        /**
         * Metoda getButton_raportsone_save().addActionListener() odpowiadająca za obsługę zdarzenia naciśnięcia przycisku "Zapisz raport"
         */
        mainGUIForm.getButton_raportsone_save().addActionListener(e -> {
            try {
                utilsDatabase.raportOneButtonTwo(mainGUIForm);
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
