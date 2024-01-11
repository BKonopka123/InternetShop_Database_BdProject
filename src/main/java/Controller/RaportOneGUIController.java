package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import java.util.Objects;

/**
 * Klasa RaportOneGUIController odpowiadająca za kontrolę okna programu odpowiadającego za Raport 1
 */
public class RaportOneGUIController {
    private static RaportOneGUIController raportOneGUIController;

    public RaportOneGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

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


    }

    /**
     * Metoda init() odpowiadająca za inicjalizację głównego okna programu
     */
    private static void init(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        mainGUIForm.getFrame().setVisible(true);
    }

}
