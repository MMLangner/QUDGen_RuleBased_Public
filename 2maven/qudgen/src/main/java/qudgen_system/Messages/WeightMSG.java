package qudgen_system.Messages;

import qudgen_system.*;

public class WeightMSG extends TechnicalDetailsMSG{
    // Er hat 2437 Kilogramm Gewicht.

    private final String key = "Gesamtgewicht";
    public WeightMSG(){}


    public WeightMSG(String unit, String value){
        super(unit, value);
    }
    
    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
        this.getDataFromDB();
    }

    public WeightMSG(CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super(techData, carID, nlg, refGen);
        this.getDataFromDB();
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
    
    public void lexicalize(){
        super.lexicalize();
    };
}