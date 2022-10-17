package qudgen_system;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class CarData {
    private HashMap<String, HashMap<String,String>> data; 

    public CarData(String file) throws FileNotFoundException{
         this.data = this.readData();
    }

    public HashMap<String, HashMap<String,String>> readData() throws FileNotFoundException {
        HashMap<String, HashMap<String,String>> technicalData = new HashMap<>();
        String DELIMITER = "\t";
        File technicalDataFileName = new File("res/DataExcerpt.csv");
        Scanner reader = new Scanner(technicalDataFileName);
        String[] columnNames = reader.nextLine().replaceAll("\"", "").split(DELIMITER);
        while (reader.hasNextLine()){
            String[] line = reader.nextLine().replaceAll("\"", "").split(DELIMITER);
            int cloumnCount = 0;
            HashMap<String,String> values = new HashMap<>();
            String carName = line[1];
            for (String column:columnNames){
                values.put(column, line[cloumnCount]);
                cloumnCount ++;
            }
            technicalData.put(carName, values);
        }
        reader.close();
        return technicalData;
    }

    public String[] getUVPairs (String carID, String key){
        String[] dbString = this.data.get(carID).get(key).split(" ");
        if (dbString.length == 2)
            return dbString;
        else
            return null;
    }

    public String getSingleEntry (String carID, String key){
        String dbString = this.data.get(carID).get(key);
        return dbString;
    }

}