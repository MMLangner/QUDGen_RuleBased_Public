package qudgen_system;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.gson.*;
import java.io.FileReader;

public class genmain {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader f = new FileReader("res/templates.json");
        
        Realiser r = gson.fromJson(f, Realiser.class);
        String carID = "Golf 1.6 TDI SCR"; //"A7 Sportback 50 TDI"
        SimpleNLGInterface nlg = new SimpleNLGInterface();
        CarData technicalData = new CarData("DataExcerpt.csv");

        r.init(carID, technicalData, nlg);
        r.realize();
    }
}
 