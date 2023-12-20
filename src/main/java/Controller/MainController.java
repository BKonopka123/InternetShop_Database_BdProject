package Controller;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    private static MainController mainController;
    public MainController() throws IOException{
        control();
    }

    public static MainController getInstance() throws IOException{
        if(Objects.isNull(mainController)) {
            return new MainController();
        }
        return mainController;
    }

    public void control() throws IOException {
        init();
    }

    private static void init() throws IOException {
        MainGUIController.getInstance();
    }
}
