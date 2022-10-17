package qudgen_system.Messages;

import qudgen_system.*;
public class HorsePowerMSG extends TechnicalDetailsMSG{
    public final String key = "LeistungPS";

    public HorsePowerMSG(){}

    public HorsePowerMSG(String unit, String value){
        super(unit, value);
    }

    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
        this.getDataFromDB();
    }

    public void getDataFromDB(){
        String dataA = this.getTechData().getSingleEntry(this.getCarID(), this.key);
        if (dataA != null) {
            this.setValue(dataA);
            this.setUnit("PS");
        } else {
            System.out.println("Data not found, returned null");
        }
    }

    public void lexicalize(){
        super.lexicalize();
    };

}