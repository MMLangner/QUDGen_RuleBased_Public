package qudgen_system.Messages;

import qudgen_system.*;

public class RevsPerMinuteMSG extends TechnicalDetailsMSG{
    // Er hat 1350 Umdrehungen in der Minute.

    private final String key = "LeistungMax";
    public RevsPerMinuteMSG(){}

    public RevsPerMinuteMSG(String unit, String value){
        super(unit, value);
    }

    public RevsPerMinuteMSG(CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
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

    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
        this.getDataFromDB();
    }
    
    public void lexicalize(){
        super.lexicalize();
    };
}