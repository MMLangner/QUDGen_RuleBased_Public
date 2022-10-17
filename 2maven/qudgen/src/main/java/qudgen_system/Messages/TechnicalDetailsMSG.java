package qudgen_system.Messages;

import qudgen_system.*;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class TechnicalDetailsMSG extends MSG{

    private String unit;
    private String value;
    private CarData techData;
    private String carID;
    SimpleNLGInterface nlg;
    RefGen refGen;
    private List<Scheme> schemes = null;

    public TechnicalDetailsMSG(){}

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }

    public String surfaceRealize(){
        Scheme s = this.pickScheme();
        s.replacePHs(this.refGen.getREForCar(), this.getValue() + this.getUnit());
        return this.nlg.realiseScheme(s);
        //return "Es beschleunigt von 0 auf 100 in " + this.getValue()+ " "  + this.getUnit() + ".";
    }

    public TechnicalDetailsMSG(String unit, String value){
        this.unit = unit;
        this.value = value;
    }

    public TechnicalDetailsMSG (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        this.refGen = refGen;
        this.techData = techData;
        this.carID = carID;
        this.nlg = nlg;
    }

    public void init (CarData techData, String carID, SimpleNLGInterface nlg, RefGen refGen){
        this.refGen = refGen;
        this.techData = techData;
        this.carID = carID;
        this.nlg = nlg;
    }

    public Scheme pickScheme() {
        return this.getSchemes().get(ThreadLocalRandom.current().nextInt(0, this.getSchemes().size()));
    }

    public CarData getTechData() {
        return this.techData;
    }

    public String getUnit(){
        return this.unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }
    public void setCarID(String carID){
        this.carID = carID;
    }
    public String getCarID(){
        return this.carID;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void lexicalize(){
        System.out.println("todo...");
    }
}