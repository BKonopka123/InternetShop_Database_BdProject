package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

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


    }

    /**
     * Metoda init() odpowiadająca za inicjalizację głównego okna programu
     */
    private static void init(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        mainGUIForm.getFrame().setVisible(true);
    }

}
