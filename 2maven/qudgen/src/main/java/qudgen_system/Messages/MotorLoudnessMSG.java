package qudgen_system.Messages;

public class MotorLoudnessMSG extends TechnicalDetailsMSG {
    // Das Poltern liegt ihm fern.
    // Sein akustischer Auftritt bleibt ruhig.
    private String loudness;

    public MotorLoudnessMSG(){}

    public MotorLoudnessMSG(String content){
        this.loudness = content;
    }

    public String surfaceRealize(){
        return "this car is " + loudness;
    }

    public void setLoudness(String loudness){
        this.loudness = loudness;
    }

    public String getLoudness(){
        return this.loudness;
    }
    
    public void lexicalize(){
        super.lexicalize();
    }
}