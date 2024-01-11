package Controller;

import Model.UtilsDatabase;
import View.MainGUIForm;
import java.util.Objects;

/**
 * Klasa UInterfaceClientGUIController odpowiadająca za kontrolę okna programu odpowiadającego za INSERT
 */
public class UInterfaceClientGUIController {
    private static UInterfaceClientGUIController uInterfaceClientGUIController;

    public UInterfaceClientGUIController(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm) {
        control(utilsDatabase, mainGUIForm);
    }

    public static UInterfaceClientGUIController getInstance(UtilsDatabase utilsDatabase, MainGUIForm mainGUIForm){
        if(Objects.isNull(uInterfaceClientGUIController)) {
            uInterfaceClientGUIController = new UInterfaceClientGUIController(utilsDatabase, mainGUIForm);
        }
        init(utilsDatabase, mainGUIForm);
        return uInterfaceClientGUIController;
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
