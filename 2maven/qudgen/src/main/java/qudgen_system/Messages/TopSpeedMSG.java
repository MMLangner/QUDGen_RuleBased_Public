package qudgen_system.Messages;

import qudgen_system.*;

public class TopSpeedMSG extends TechnicalDetailsMSG{
    // Mit einem Spitzentempo von 333 km/h - mehr als 200 Meilen in der Stunde - ist es die schnellste Serienlimousine der Welt.
    private final String key = "Hoechstgeschwindigkeit";
    
    public TopSpeedMSG(CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super(techData, carID, nlg, refGen);
        this.getDataFromDB();
    }

    public TopSpeedMSG(String unit, String value){
        super(unit, value);
    }

    public void getDataFromDB() {
        String[] dbString;
        dbString = this.getTechData().getUVPairs(this.getCarID(), this.key);
        if (dbString != null){
            this.setValue(dbString[0]);
            this.setUnit(dbString[1]);
        } else {
            System.out.println("error on fetching data from db");
        }
    }

    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
        this.getDataFromDB();
    }
    
    public void lexicalize(){
        super.lexicalize();
    };

}