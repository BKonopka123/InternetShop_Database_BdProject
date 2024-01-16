package Model;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RaportTwoModel {
    public static ResultSet raportTwoButtonOneMethod(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Raport_two");
            return preparedStatement.executeQuery();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void raportTwoButtonTwoMethod(Connection connection, View.MainGUIForm mainGUIForm){
        String path = System.getProperty("user.dir");
        String pathToSave = path + "\\src\\main\\java\\Raporty\\Raport2.csv";
        try {
            FileWriter csv = new FileWriter(pathToSave);
            for(int i = 0; i < mainGUIForm.getTable_raportstwo_output().getColumnCount(); i++){
                csv.write(mainGUIForm.getTable_raportstwo_output().getColumnName(i) + ";");
            }
            csv.write("\n");
            for(int i = 0; i < mainGUIForm.getTable_raportstwo_output().getRowCount(); i++){
                for(int j = 0; j < mainGUIForm.getTable_raportstwo_output().getColumnCount(); j++){
                    if(mainGUIForm.getTable_raportstwo_output().getValueAt(i, j) == null)
                        csv.write(";");
                    else
                        csv.write(mainGUIForm.getTable_raportstwo_output().getValueAt(i, j).toString() + ";");
                }
                csv.write("\n");
            }
            csv.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
