package qudgen_system.Messages;

public class HandlingMSG extends TechnicalDetailsMSG {
    private String handling;

    public HandlingMSG(){}

    public HandlingMSG(String content){
        this.handling = content;
    }

    public void setHandling(String handling){
        this.handling = handling;
    }

    public String getHandling(){
        return this.handling;
    }

    public String surfaceRealize(){
        return "the handling of this car is " + handling;
    }
    
    public void lexicalize(){
        super.lexicalize();
    }
}