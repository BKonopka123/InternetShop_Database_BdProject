package Controller;


import Model.UtilsDatabase;
import View.MainGUIForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa MainGUIController odpowiadająca za kontrolę głównego okna programu
 */
public class MainGUIController {
    private static MainGUIForm mainGUIForm;
    private static MainGUIController mainGUIController;
    private static final UtilsDatabase utilsDatabase = new UtilsDatabase();

    /**
     * Konstruktor klasy MainGUIController
     */
    public MainGUIController() {
        control();
    }

    /**
     * Metoda getInstance() tworząca instancję klasy MainGUIController
     * @return instancja klasy MainGUIController
     */
    public static MainGUIController getInstance() {
        if(Objects.isNull(mainGUIController)) {
            mainGUIForm = new MainGUIForm();
            mainGUIController = new MainGUIController();
        }
        init();
        return mainGUIController;
    }

    /**
     * Metoda control() odpowiadająca za kontrolę głównego okna programu
     */
    public void control() {
        //Połączenie z bazą danych
            try {
                utilsDatabase.connectToDatabase();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        //Incjalizacja i usunięcie bazy danych
            /**
             * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu przycisku "Initialize Database"
             */
            mainGUIForm.getButton_initialize_database().addActionListener(e -> {
                try {
                    utilsDatabase.initialization_sql();
                } catch (Exception exception) {
                    JOptionPane.showConfirmDialog(null, "Błąd: Baza danych została już wcześniej zainicjowana", "Initialize Database", JOptionPane.DEFAULT_OPTION);
                    throw new RuntimeException(exception);
                }
                JOptionPane.showConfirmDialog(null, "Baza danych zainicjowana", "Initialize Database", JOptionPane.DEFAULT_OPTION);
            });

            /**
             * Metoda addActionListener() odpowiadająca za wywołanie akcji po kliknięciu przycisku "Delete Database"
             */
            mainGUIForm.getButton_delete_database().addActionListener(e -> {
                try {
                    utilsDatabase.delete_sql();
                } catch (Exception exception) {
                    JOptionPane.showConfirmDialog(null, "Błąd: Baza danych została już wcześniej usunięta", "Delete Database", JOptionPane.DEFAULT_OPTION);
                    throw new RuntimeException(exception);
                }
                JOptionPane.showConfirmDialog(null, "Baza danych usunięta", "Delete Database", JOptionPane.DEFAULT_OPTION);
            });
    }

    /**
     * Metoda init() odpowiadająca za inicjalizację głównego okna programu
     */
    private static void init() {
        mainGUIForm.getFrame().setVisible(true);
        MainSELECTGUIController.getInstance(utilsDatabase, mainGUIForm);
        MainINSERTGUIController.getInstance(utilsDatabase, mainGUIForm);
        UInterfaceProducentGUIController.getInstance(utilsDatabase, mainGUIForm);
        UInterfaceClientGUIController.getInstance(utilsDatabase, mainGUIForm);
        UInterfaceWorkerGUIController.getInstance(utilsDatabase, mainGUIForm);
        RaportOneGUIController.getInstance(utilsDatabase, mainGUIForm);
        RaportTwoGUIController.getInstance(utilsDatabase, mainGUIForm);
        RaportThreeGUIController.getInstance(utilsDatabase, mainGUIForm);
    }
}
