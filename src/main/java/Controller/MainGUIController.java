package Controller;


import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.SQLException;
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
        try {
            utilsDatabase.connectToDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        mainGUIForm.getButton_initialize_database().addActionListener(e -> {
            utilsDatabase.initialization_sql();
            JOptionPane.showConfirmDialog(null, "Baza danych zainicjowana", "Initialize Database", JOptionPane.DEFAULT_OPTION);
        });

        mainGUIForm.getButton_delete_database().addActionListener(e -> {
            utilsDatabase.delete_sql();
            JOptionPane.showConfirmDialog(null, "Baza danych usunięta", "Delete Database", JOptionPane.DEFAULT_OPTION);
        });
    }

    private static void init() {

        mainGUIForm.getFrame().setVisible(true);
    }
}
