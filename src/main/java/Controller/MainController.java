package Controller;

import java.io.IOException;

public class MainController {
    private static MainController mainController;
    private MainController() throws IOException{
        control();
    }

    public static MainController getInstance() throws IOException{
        if(mainController == null) {
            return new MainController();
        }
        return mainController;
    }

    private void control() throws IOException {
        init();
    }

    private static void init() throws IOException {

    }
}
