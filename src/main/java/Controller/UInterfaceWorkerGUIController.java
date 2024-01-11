package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;
import java.util.Objects;

/**
 * Klasa UInterfaceProducentGUIController odpowiadająca za kontrolę okna programu odpowiadającego za INSERT
 */
public class UInterfaceWorkerGUIController {
    private static UInterfaceWorkerGUIController uInterfaceWorkerGUIController;

    public UInterfaceWorkerGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    public static UInterfaceWorkerGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(uInterfaceWorkerGUIController)) {
            uInterfaceWorkerGUIController = new UInterfaceWorkerGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return uInterfaceWorkerGUIController;
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
