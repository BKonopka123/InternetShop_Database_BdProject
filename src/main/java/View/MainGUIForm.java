package View;

import lombok.Getter;

import javax.swing.*;

@Getter
public class MainGUIForm {

    private JFrame frame;
    private JButton button_initialize;
    private JButton button1;
    private JPanel panel_main;

    public MainGUIForm() {
        frame = new JFrame("MainGUIForm");
        frame.setContentPane(panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
