package Controller;


import Model.UtilsDatabase;
import View.MainGUIForm;

import java.util.Objects;

public class MainGUIController {
    private static MainGUIForm mainGUIForm;
    private static MainGUIController mainGUIController;
    private static final UtilsDatabase utilsDatabase = new UtilsDatabase();

    public MainGUIController() {
        control();
    }

    public static MainGUIController getInstance() {
        if(Objects.isNull(mainGUIController)) {
            mainGUIForm = new MainGUIForm();
            mainGUIController = new MainGUIController();
        }
        init();
        return mainGUIController;
    }

    public void control() {
        mainGUIForm.getButton1().addActionListener(e -> {
            System.out.println("Button1 clicked");
        });
    }

    private static void init() {
        mainGUIForm.getFrame().setVisible(true);
    }
}
