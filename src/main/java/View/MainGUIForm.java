package View;

import lombok.Getter;

import javax.swing.*;

@Getter
public class MainGUIForm {

    private JFrame frame;
    private JPanel panel_main;
    private JButton button_initialize_database;
    private JButton button_delete_database;
    private JPanel panel_initialization;

    public MainGUIForm() {
        frame = new JFrame("MainGUIForm");
        frame.setContentPane(panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
