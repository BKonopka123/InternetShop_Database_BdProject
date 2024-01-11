package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;

import java.util.Objects;

/**
 * Klasa MainINSERTGUIController odpowiadająca za kontrolę okna programu odpowiadającego za INSERT
 */
public class MainINSERTGUIController {

    private static MainINSERTGUIController mainINSERTGUIController;

    public MainINSERTGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

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


    }

    /**
     * Metoda init() odpowiadająca za inicjalizację głównego okna programu
     */
    private static void init(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        mainGUIForm.getFrame().setVisible(true);
    }
}
