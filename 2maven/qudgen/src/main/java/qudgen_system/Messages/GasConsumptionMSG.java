package qudgen_system.Messages;

import qudgen_system.*;

public class GasConsumptionMSG extends TechnicalDetailsMSG{
    private String drivemode;
    private String key;


    public void init (String drivemode, CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        super.init(techData, carID, nlg, refGen);
        this.drivemode = drivemode;
        switch (drivemode) {
            case "innerorts":
                this.key = "VerbrauchInnerorts";
                break;
            case "außerorts":
                this.key = "VerbrauchAußerorts";
                break;
            case "insgesamt":
                this.key = "VerbrauchGesamt";
                break;
        }
        this.getDataFromDB();
        for (Scheme s: this.getSchemes())
            s.setSentMod(this.drivemode);
    }


    public GasConsumptionMSG(CarData techData, String carID, String drivemode, SimpleNLGInterface nlg, RefGen refGen){
        super(techData, carID, nlg, refGen);
        this.drivemode = drivemode;
        switch (drivemode) {
            case "innerorts":
                this.key = "VerbrauchInnerorts";
                break;
            case "außerorts":
                this.key = "VerbrauchAußerorts";
                break;
            case "insgesamt":
                this.key = "VerbrauchGesamt";
                break;
        }
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
