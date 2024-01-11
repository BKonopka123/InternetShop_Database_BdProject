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

    //Interfejs użytkownika - Producent

    //Interfejs użytkownika - Klient

    //Interfejs użytkownika - Pracownik

    //Raport 1

    //Raport 2

    //Raport 3
}
