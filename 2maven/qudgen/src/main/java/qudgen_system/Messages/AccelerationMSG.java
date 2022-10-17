package qudgen_system.Messages;

import qudgen_system.*;


public class AccelerationMSG extends TechnicalDetailsMSG {
    // In 3,8 Sekunden schafft er es, 2437 Kilogramm Gewicht von 0 auf 100 km/h zu beschleunigen.
    private final String key = "Beschleunigung";

    public AccelerationMSG(String unit, String value){
        super(unit, value);
    }

    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
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