package View;

import lombok.Getter;

import javax.swing.*;
import java.sql.ResultSet;

/**
 * Klasa MainGUIForm odpowiadająca za wygląd głównego okna programu
 */
@Getter
public class MainGUIForm {

    private JFrame frame;
    private JPanel panel_main;
    private JButton button_initialize_database;
    private JButton button_delete_database;
    private JPanel panel_initialization;
    private JPanel panel_bottom;
    private JTabbedPane tabbedPane_main;
    private JPanel panel_tab_si;
    private JPanel panel_tab_user;
    private JPanel panel_tab_raports;
    private JTabbedPane tabbedPane_si;
    private JPanel panel_si_select;
    private JPanel panel_si_insert;
    private JTabbedPane tabbedPane_forms;
    private JPanel panel_user_producent;
    private JPanel pacel_user_client;
    private JPanel panel_user_worker;
    private JTabbedPane tabbedPane_raports;
    private JPanel panel_raports_one;
    private JPanel panel_raports_two;
    private JPanel panel_raports_three;
    private JPanel panel_si_select_input;
    private JPanel panel_si_select_output;
    private JLabel label_si_select_input_table;
    private JComboBox comboBox_si_select_input_table;
    private JButton button_si_select_input_make;
    private JPanel panel_si_select_input_left;
    private JPanel panel_si_select_input_right;
    private JPanel panel_si_select_input_right_more;
    private JButton button_si_select_input_right_wypisz;
    private JTable table_si_select_output;
    private JPanel panel_si_select_own;
    private JLabel label_si_select_own;
    private JButton button_si_select_own;
    private JTextField textField_si_select_own;
    private JPanel panel_si_insert_input;
    private JPanel panel_si_insert_output;
    private JLabel label_si_insert_output;
    private JLabel label_si_insert_input_1;
    private JComboBox comboBox_si_insert_input;
    private JButton button_si_insert_input_wpisz;
    private JButton button_si_insert_input_make;
    private JPanel panel_si_insert_input_values;

    private JCheckBox checkBox_si_select_input_right_more_0;
    private JCheckBox checkBox_si_select_input_right_more_1;
    private JCheckBox checkBox_si_select_input_right_more_2;
    private JCheckBox checkBox_si_select_input_right_more_3;
    private JCheckBox checkBox_si_select_input_right_more_4;
    private JCheckBox checkBox_si_select_input_right_more_5;
    private JCheckBox checkBox_si_select_input_right_more_6;
    private JCheckBox checkBox_si_select_input_right_more_7;
    private JCheckBox checkBox_si_select_input_right_more_8;
    private JCheckBox checkBox_si_select_input_right_more_9;
    private JCheckBox checkBox_si_select_input_right_more_10;
    private JCheckBox checkBox_si_select_input_right_more_11;
    private JCheckBox checkBox_si_select_input_right_more_12;

    private JLabel label_si_insert_input_values_1;
    private JLabel label_si_insert_input_values_2;
    private JLabel label_si_insert_input_values_3;
    private JLabel label_si_insert_input_values_4;
    private JLabel label_si_insert_input_values_5;
    private JLabel label_si_insert_input_values_6;
    private JLabel label_si_insert_input_values_7;
    private JLabel label_si_insert_input_values_8;
    private JLabel label_si_insert_input_values_9;
    private JLabel label_si_insert_input_values_10;
    private JLabel label_si_insert_input_values_11;
    private JTextField textField_si_insert_input_values_1;
    private JTextField textField_si_insert_input_values_2;
    private JTextField textField_si_insert_input_values_3;
    private JTextField textField_si_insert_input_values_4;
    private JTextField textField_si_insert_input_values_5;
    private JTextField textField_si_insert_input_values_6;
    private JTextField textField_si_insert_input_values_7;
    private JTextField textField_si_insert_input_values_8;
    private JTextField textField_si_insert_input_values_9;
    private JTextField textField_si_insert_input_values_10;
    private JTextField textField_si_insert_input_values_11;

    /**
     * Konstruktor klasy MainGUIForm
     */
    public MainGUIForm() {
        frame = new JFrame("MainGUIForm");
        frame.setContentPane(panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        //frame.pack();
        frame.setVisible(true);
        createUIComponents();
    }

    /**
     * Metoda createUIComponents() odpowiadająca za inicjalizację komponentów
     */
    private void createUIComponents() {
        comboBox_si_select_input_table_init();
        comboBox_si_insert_input_init();
    }

    //Główny SELECT
        /**
         * Metoda panel_si_select_input_right_more_init() odpowiadająca za inicjalizację panelu panel_si_select_input_right_more
         * @param
         */
        private void comboBox_si_select_input_table_init(){
            comboBox_si_select_input_table.addItem("Producent");
            comboBox_si_select_input_table.addItem("Klient");
            comboBox_si_select_input_table.addItem("Pracownik");
            comboBox_si_select_input_table.addItem("Produkt");
            comboBox_si_select_input_table.addItem("Typ_produktu");
            comboBox_si_select_input_table.addItem("Ocena");
            comboBox_si_select_input_table.addItem("Zamowienie");
            comboBox_si_select_input_table.addItem("Zamowione_produkty");
            comboBox_si_select_input_table.addItem("Dostawa");
            comboBox_si_select_input_table.addItem("Zamowiona_dostawa");
        }

        /**
         * Metoda panel_si_select_input_right_more_init() odpowiadająca za inicjalizację panelu panel_si_select_input_right_more
         * @param option - opcja wybrana z comboBox_si_select_input_table
         */
        public void panel_si_select_input_right_more_init(String option){
            getPanel_si_select_input_right_more().removeAll();
            getPanel_si_select_input_right_more().revalidate();
            getPanel_si_select_input_right_more().repaint();
            getPanel_si_select_input_right_more().setLayout(new BoxLayout(getPanel_si_select_input_right_more(), BoxLayout.Y_AXIS));
            switch(option){
                case "Producent":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("producent_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("imie");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("nazwisko");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("firma");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    break;
                case "Klient":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("klient_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("imie");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("nazwisko");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("pesel");
                    checkBox_si_select_input_right_more_5 = new JCheckBox("data_urodzenia");
                    checkBox_si_select_input_right_more_6 = new JCheckBox("email");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_5);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_6);
                    break;
                case "Pracownik":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("pracownik_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("imie");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("nazwisko");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("pesel");
                    checkBox_si_select_input_right_more_5 = new JCheckBox("data_urodzenia");
                    checkBox_si_select_input_right_more_6 = new JCheckBox("pensja");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_5);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_6);
                    break;
                case "Produkt":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("produkt_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("typ_produktu_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("producent_id");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("tematyka");
                    checkBox_si_select_input_right_more_5 = new JCheckBox("nazwa");
                    checkBox_si_select_input_right_more_6 = new JCheckBox("cena");
                    checkBox_si_select_input_right_more_7 = new JCheckBox("rabat");
                    checkBox_si_select_input_right_more_8 = new JCheckBox("wysokosc");
                    checkBox_si_select_input_right_more_9 = new JCheckBox("szerokosc");
                    checkBox_si_select_input_right_more_10 = new JCheckBox("ilosc_egzemplarzy");
                    checkBox_si_select_input_right_more_11 = new JCheckBox("ocena");
                    checkBox_si_select_input_right_more_12 = new JCheckBox("ilosc_ocen");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_5);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_6);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_7);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_8);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_9);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_10);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_11);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_12);
                    break;
                case "Typ_produktu":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("typ_produktu_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("typ_produktu");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    break;
                case "Ocena":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("ocena_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("klient_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("produkt_id");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("ocena");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    break;
                case "Zamowienie":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("zamowienie_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("pracownik_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("klient_id");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("data_zamowienia");
                    checkBox_si_select_input_right_more_5 = new JCheckBox("data_zrealizowania");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_5);
                    break;
                case "Zamowione_produkty":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("zamowione_produkty_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("zamowienie_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("produkt_id");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("ilosc_egzemplarzy");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    break;
                case "Dostawa":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("dostawa_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("pracownik_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("data_zlozenia");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("data_realizacji");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    break;
                case "Zamowiona_dostawa":
                    panel_si_select_input_right_more.removeAll();
                    checkBox_si_select_input_right_more_0 = new JCheckBox("*");
                    checkBox_si_select_input_right_more_1 = new JCheckBox("zamowiona_dostawa_id");
                    checkBox_si_select_input_right_more_2 = new JCheckBox("produkt_id");
                    checkBox_si_select_input_right_more_3 = new JCheckBox("dostawa_id");
                    checkBox_si_select_input_right_more_4 = new JCheckBox("ilosc_egzemplarzy");
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_0);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_1);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_2);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_3);
                    panel_si_select_input_right_more.add(checkBox_si_select_input_right_more_4);
                    break;
                default:
                    break;
            }
        }

        /**
         * Metoda panel_si_select_output_init() odpowiadająca za inicjalizację panelu panel_si_select_output
         * @param result - wynik zapytania
         */
        public void panel_si_select_output_init(ResultSet result){
            getPanel_si_select_output().removeAll();
            getPanel_si_select_output().setLayout(new BoxLayout(getPanel_si_select_output(), BoxLayout.Y_AXIS));
            try {
                int columnCount = result.getMetaData().getColumnCount();
                String[] columnNames = new String[columnCount];
                for(int i = 0; i < columnCount; i++){
                    columnNames[i] = result.getMetaData().getColumnName(i+1);
                }
                Object[][] data = new Object[100][columnCount];
                int i = 0;
                while(result.next()){
                    for(int j = 0; j < columnCount; j++){
                        data[i][j] = result.getObject(j+1);
                    }
                    i++;
                }
                JTable table = new JTable(data, columnNames);
                table_si_select_output = table;
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setVisible(true);
                scrollPane.setSize(800, 600);
                scrollPane.revalidate();
                scrollPane.repaint();
                getPanel_si_select_output().add(scrollPane);
                getPanel_si_select_output().revalidate();
                getPanel_si_select_output().repaint();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }

    //Główny INSERT
        /**
         * Metoda panel_si_insert_input_init() odpowiadająca za inicjalizację panelu panel_si_insert_input
         */
        private void comboBox_si_insert_input_init(){
            comboBox_si_insert_input.addItem("Producent");
            comboBox_si_insert_input.addItem("Klient");
            comboBox_si_insert_input.addItem("Pracownik");
            comboBox_si_insert_input.addItem("Produkt");
            comboBox_si_insert_input.addItem("Typ_produktu");
            comboBox_si_insert_input.addItem("Ocena");
            comboBox_si_insert_input.addItem("Zamowienie");
            comboBox_si_insert_input.addItem("Zamowione_produkty");
            comboBox_si_insert_input.addItem("Dostawa");
            comboBox_si_insert_input.addItem("Zamowiona_dostawa");
        }

        /**
         * Metoda panel_si_insert_input_values_init() odpowiadająca za inicjalizację panelu panel_si_insert_input_values
         * @param selectedTable - opcja wybrana z comboBox_si_insert_input
         */
        public void panel_si_insert_input_values_init(String selectedTable) {
            getPanel_si_insert_input_values().removeAll();
            getPanel_si_insert_input_values().revalidate();
            getPanel_si_insert_input_values().repaint();
            getPanel_si_insert_input_values().setLayout(new BoxLayout(getPanel_si_insert_input_values(), BoxLayout.Y_AXIS));
            switch (selectedTable) {
                case "Producent":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("imie");
                    label_si_insert_input_values_2 = new JLabel("nazwisko");
                    label_si_insert_input_values_3 = new JLabel("firma");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    break;
                case "Klient":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("imie");
                    label_si_insert_input_values_2 = new JLabel("nazwisko");
                    label_si_insert_input_values_3 = new JLabel("pesel");
                    label_si_insert_input_values_4 = new JLabel("data_urodzenia");
                    label_si_insert_input_values_5 = new JLabel("email");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    textField_si_insert_input_values_4 = new JTextField();
                    textField_si_insert_input_values_5 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    panel_si_insert_input_values.add(label_si_insert_input_values_4);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_4);
                    panel_si_insert_input_values.add(label_si_insert_input_values_5);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_5);
                    break;
                case "Pracownik":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("imie");
                    label_si_insert_input_values_2 = new JLabel("nazwisko");
                    label_si_insert_input_values_3 = new JLabel("pesel");
                    label_si_insert_input_values_4 = new JLabel("data_urodzenia");
                    label_si_insert_input_values_5 = new JLabel("pensja");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    textField_si_insert_input_values_4 = new JTextField();
                    textField_si_insert_input_values_5 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    panel_si_insert_input_values.add(label_si_insert_input_values_4);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_4);
                    panel_si_insert_input_values.add(label_si_insert_input_values_5);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_5);
                    break;
                case "Produkt":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("typ_produktu_id");
                    label_si_insert_input_values_2 = new JLabel("producent_id");
                    label_si_insert_input_values_3 = new JLabel("tematyka");
                    label_si_insert_input_values_4 = new JLabel("nazwa");
                    label_si_insert_input_values_5 = new JLabel("cena");
                    label_si_insert_input_values_6 = new JLabel("rabat");
                    label_si_insert_input_values_7 = new JLabel("wysokosc");
                    label_si_insert_input_values_8 = new JLabel("szerokosc");
                    label_si_insert_input_values_9 = new JLabel("ilosc_egzemplarzy");
                    label_si_insert_input_values_10 = new JLabel("ocena");
                    label_si_insert_input_values_11 = new JLabel("ilosc_ocen");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    textField_si_insert_input_values_4 = new JTextField();
                    textField_si_insert_input_values_5 = new JTextField();
                    textField_si_insert_input_values_6 = new JTextField();
                    textField_si_insert_input_values_7 = new JTextField();
                    textField_si_insert_input_values_8 = new JTextField();
                    textField_si_insert_input_values_9 = new JTextField();
                    textField_si_insert_input_values_10 = new JTextField();
                    textField_si_insert_input_values_11 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    panel_si_insert_input_values.add(label_si_insert_input_values_4);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_4);
                    panel_si_insert_input_values.add(label_si_insert_input_values_5);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_5);
                    panel_si_insert_input_values.add(label_si_insert_input_values_6);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_6);
                    panel_si_insert_input_values.add(label_si_insert_input_values_7);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_7);
                    panel_si_insert_input_values.add(label_si_insert_input_values_8);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_8);
                    panel_si_insert_input_values.add(label_si_insert_input_values_9);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_9);
                    panel_si_insert_input_values.add(label_si_insert_input_values_10);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_10);
                    panel_si_insert_input_values.add(label_si_insert_input_values_11);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_11);
                    break;
                case "Typ_produktu":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("typ_produktu");
                    textField_si_insert_input_values_1 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    break;
                case "Ocena":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("klient_id");
                    label_si_insert_input_values_2 = new JLabel("produkt_id");
                    label_si_insert_input_values_3 = new JLabel("ocena");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    break;
                case "Zamowienie":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("pracownik_id");
                    label_si_insert_input_values_2 = new JLabel("klient_id");
                    label_si_insert_input_values_3 = new JLabel("data_zamowienia");
                    label_si_insert_input_values_4 = new JLabel("data_zrealizowania");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    textField_si_insert_input_values_4 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    panel_si_insert_input_values.add(label_si_insert_input_values_4);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_4);
                    break;
                case "Zamowione_produkty":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("zamowienie_id");
                    label_si_insert_input_values_2 = new JLabel("produkt_id");
                    label_si_insert_input_values_3 = new JLabel("ilosc_egzemplarzy");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    break;
                case "Dostawa":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("pracownik_id");
                    label_si_insert_input_values_2 = new JLabel("data_zlozenia");
                    label_si_insert_input_values_3 = new JLabel("data_realizacji");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    break;
                case "Zamowiona_dostawa":
                    panel_si_insert_input_values.removeAll();
                    label_si_insert_input_values_1 = new JLabel("produkt_id");
                    label_si_insert_input_values_2 = new JLabel("dostawa_id");
                    label_si_insert_input_values_3 = new JLabel("ilosc_egzemplarzy");
                    textField_si_insert_input_values_1 = new JTextField();
                    textField_si_insert_input_values_2 = new JTextField();
                    textField_si_insert_input_values_3 = new JTextField();
                    panel_si_insert_input_values.add(label_si_insert_input_values_1);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_1);
                    panel_si_insert_input_values.add(label_si_insert_input_values_2);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_2);
                    panel_si_insert_input_values.add(label_si_insert_input_values_3);
                    panel_si_insert_input_values.add(textField_si_insert_input_values_3);
                    break;
                default:
                    break;
            }
        }

    //Interfejs użytkownika - Producent

    //Interfejs użytkownika - Klient

    //Interfejs użytkownika - Pracownik

    //Raport 1

    //Raport 2

    //Raport 3
}
