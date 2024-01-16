package Model;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RaportOneModel {
    public static ResultSet raportOneButtonOneMethod(Connection connection, Double value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Raport_one WHERE ocena >= ?");
            preparedStatement.setDouble(1, value);
            return preparedStatement.executeQuery();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void raportOneButtonTwoMethod(Connection connection, View.MainGUIForm mainGUIForm) {
        String path = System.getProperty("user.dir");
        String pathToSave = path + "\\src\\main\\java\\Raporty\\Raport1.csv";
        try {
            FileWriter csv = new FileWriter(pathToSave);
            for(int i = 0; i < mainGUIForm.getTable_raportsone_output().getColumnCount(); i++){
                csv.write(mainGUIForm.getTable_raportsone_output().getColumnName(i) + ";");
            }
            csv.write("\n");
            for(int i = 0; i < mainGUIForm.getTable_raportsone_output().getRowCount(); i++){
                for(int j = 0; j < mainGUIForm.getTable_raportsone_output().getColumnCount(); j++){
                    if(mainGUIForm.getTable_raportsone_output().getValueAt(i, j) == null)
                        csv.write(";");
                    else
                        csv.write(mainGUIForm.getTable_raportsone_output().getValueAt(i, j).toString() + ";");
                }
                csv.write("\n");
            }
            csv.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
