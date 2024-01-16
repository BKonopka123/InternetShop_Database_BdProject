package Model;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Klasa modelu raportu trzeciego */
public class RaportThreeModel {
    /** Metoda zwracająca wynik zapytania do bazy danych
     * @param connection połączenie z bazą danych
     * @param value wartość, od której mają być wyświetlane wyniki
     * @return wynik zapytania do bazy danych
     */
    public static ResultSet raportThreeButtonOneMethod(Connection connection, Double value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM raportthree WHERE sredni_koszt_zamowienia >= ?");
            preparedStatement.setDouble(1, value);
            return preparedStatement.executeQuery();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /** Metoda zapisująca wynik zapytania do pliku .csv
     * @param connection połączenie z bazą danych
     * @param mainGUIForm obiekt klasy View.MainGUIForm
     */
    public static void raportThreeButtonTwoMethod(Connection connection, View.MainGUIForm mainGUIForm){
        String path = System.getProperty("user.dir");
        String pathToSave = path + "\\src\\main\\java\\Raporty\\Raport3.csv";
        try {
            FileWriter csv = new FileWriter(pathToSave);
            for(int i = 0; i < mainGUIForm.getTable_raportsthree_output().getColumnCount(); i++){
                csv.write(mainGUIForm.getTable_raportsthree_output().getColumnName(i) + ";");
            }
            csv.write("\n");
            for(int i = 0; i < mainGUIForm.getTable_raportsthree_output().getRowCount(); i++){
                for(int j = 0; j < mainGUIForm.getTable_raportsthree_output().getColumnCount(); j++){
                    if(mainGUIForm.getTable_raportsthree_output().getValueAt(i, j) == null)
                        csv.write(";");
                    else
                        csv.write(mainGUIForm.getTable_raportsthree_output().getValueAt(i, j).toString() + ";");
                }
                csv.write("\n");
            }
            csv.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
