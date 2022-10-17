package qudgen_system;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import qudgen_system.Messages.*;

public class Realiser {
    String carID;
    CarData technicalData;
    SimpleNLGInterface nlg;
    RefGen refGen;
    int SVOProb;
    int OVSProb;
    String lastSyntax;
    ArrayList<MSG> messages1;
    ArrayList<MSG> messages2;
    ArrayList<MSG> messages3;
    AccelerationMSG accelerationMSG;
    TopSpeedMSG topSpeedMSG;
    RevsPerMinuteMSG revsPerMinuteMSG;
    GasConsumptionMSG gasConsumptionMSGIn;
    GasConsumptionMSG gasConsumptionMSGOut;
    GasConsumptionMSG gasConsumptionMSGAll;
    HorsePowerMSG horsePowerMSG;
    WeightMSG weightMSG;
    TorqueMSG torqueMSG;
    
    public void init (String carID, CarData technicalData, SimpleNLGInterface nlg) {
        this.carID = carID;
        this.technicalData = technicalData;
        this.nlg = nlg;
        this.refGen = new RefGen(carID, technicalData);
        this.messages1 = new ArrayList<MSG>();
        this.messages2 = new ArrayList<MSG>();
        this.messages3 = new ArrayList<MSG>();
        initMessages();
    }

    public void initMessages() {
        this.revsPerMinuteMSG.init(technicalData, carID, nlg, refGen);
        this.messages1.add(this.revsPerMinuteMSG);

        this.horsePowerMSG.init(technicalData, carID, nlg, refGen);
        this.messages1.add(this.horsePowerMSG);

        this.torqueMSG.init(technicalData, carID, nlg, refGen);
        this.messages1.add(this.torqueMSG);



        this.accelerationMSG.init(technicalData, carID, nlg, refGen);
        this.messages2.add(this.accelerationMSG);

        this.topSpeedMSG.init(technicalData, carID, nlg, refGen);
        this.messages2.add(this.topSpeedMSG);

        this.weightMSG.init(technicalData, carID, nlg, refGen); 
        this.messages2.add(this.weightMSG);



        this.gasConsumptionMSGIn.init("innerorts", technicalData, carID, nlg, refGen);
        this.messages3.add(this.gasConsumptionMSGIn);
        this.gasConsumptionMSGOut.init("außerorts", technicalData, carID, nlg, refGen);
        this.messages3.add(this.gasConsumptionMSGOut);
        this.gasConsumptionMSGAll.init("insgesamt", technicalData, carID, nlg, refGen);
        this.messages3.add(this.gasConsumptionMSGAll);
    }

    public void realize() {
        System.out.println("##############\n" + carID + "\n");
        
        if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
            this.realiseList(messages1);
            this.realiseList(messages2);
        } else {
            this.realiseList(messages2);
            this.realiseList(messages1);
        }
        this.realiseList(messages3);
        
        System.out.println("\n##############");
    }

    public void realiseList(ArrayList<MSG> messages) {
        while (messages.size() != 0) {
            int msg = ThreadLocalRandom.current().nextInt(0, messages.size());
            System.out.print(messages.get(msg).surfaceRealize() + "\n");
            messages.remove(msg);
        }
    }

}
