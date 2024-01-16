package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Klasa RaportOneGUIController odpowiadająca za kontrolę okna programu odpowiadającego za Raport 1
 */
public class RaportThreeGUIController {
    private static RaportThreeGUIController raportThreeGUIController;

    public RaportThreeGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    public static RaportThreeGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(raportThreeGUIController)) {
            raportThreeGUIController = new RaportThreeGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return raportThreeGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        mainGUIForm.getButton_raportsthree_make().addActionListener(e -> {
            try {
                Double value = Double.parseDouble(mainGUIForm.getTextField_raportsthree_option().getText());
                ResultSet resultSet = utilsDatabase.raportThreeButtonOne(value);
                mainGUIForm.panel_raportsthree_output_init(resultSet);
            } catch (Exception exception) {
                JOptionPane.showConfirmDialog(null, "Błąd: " + exception.getMessage(), "Choose option", JOptionPane.DEFAULT_OPTION);
                throw new RuntimeException(exception);
            }
        });

        mainGUIForm.getButton_raportsthree_save().addActionListener(e -> {
            try {
                utilsDatabase.raportThreeButtonTwo(mainGUIForm);
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
