package Controller;

import java.io.IOException;
import java.util.Objects;

/**
 * Klasa głównego Controllera
 */
public class MainController {
    private static MainController mainController;

    /**
     * Konstruktor klasy MainController
     * @throws IOException
     */
    public MainController() throws IOException{
        control();
    }

    /**
     * Metoda zwracająca instancję klasy MainController
     * @return
     * @throws IOException
     */
    public static MainController getInstance() throws IOException{
        if(Objects.isNull(mainController)) {
            return new MainController();
        }
        return mainController;
    }

    /**
     * Metoda inicjalizująca program
     * @throws IOException
     */
    public void control() throws IOException {
        init();
    }

    /**
     * Metoda inicjalizująca GUI
     * @throws IOException
     */
    private static void init() throws IOException {
        MainGUIController.getInstance();
    }
}
