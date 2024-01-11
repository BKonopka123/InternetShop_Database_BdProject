import Controller.MainController;

import javax.swing.*;
import java.io.IOException;

/**
 * Klasa Main odpowiadająca za uruchomienie programu
 */
public class Main {
    /**
     * Metoda Main wywołująca cały program
     * @param args
     * @throws IOException
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainController.getInstance();
    }
}
