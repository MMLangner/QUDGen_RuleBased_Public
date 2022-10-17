package qudgen_system.Messages;

public class MotorUsageMSG extends TechnicalDetailsMSG{
    private String motormode; 
    private String cylinderUsage;
    private String handlingChange;

    public MotorUsageMSG(){}

    public MotorUsageMSG(String unit, String value){
        super(unit, value);
    }

    public String surfaceRealize(){
        return super.surfaceRealize();
    }
    
    public void lexicalize(){
        super.lexicalize();
    };

    public String getMotorMode(){
        return this.motormode;
    }
    public void setMotorMode(String motormode){
        this.motormode = motormode;
    }

    public String getCylinderUsage(){
        return this.cylinderUsage;
    }
    public void setCylinderUsage(String cusage){
        this.cylinderUsage = cusage;
    }
    public String getHandlingChange(){
        return this.handlingChange;
    }
    public void setHandlingChange(String hchange){
        this.handlingChange = hchange;
    }
}