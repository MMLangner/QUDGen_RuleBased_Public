package qudgen_system;

import java.util.Arrays;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class RefGen {
    List<String> SPECREFS;
    List<String> GENREFS;
    int lastMention;
    String lastType;

    String carID;
    CarData techData;

    public RefGen(String carID, CarData techData) {
        this.SPECREFS = Arrays.asList("MARKE", "MODELL");
        this.GENREFS = Arrays.asList("TYPE", "PRON");
        this.lastMention = -1;
        this.lastType = "";
        this.carID = carID;
        this.techData = techData;
    }

    public String getREForCar() {
        String refType = "";
        switch (this.lastMention) {
            case -1:
                this.lastMention = 1;
                refType = "MODELL";
                break;
            case 1:
                refType = this.choose(this.GENREFS);
                this.incrLastMentioned();
                break;
            case 2:
                refType = this.choose(this.GENREFS, this.SPECREFS);
                if (this.SPECREFS.contains(refType)) {
                    this.lastMention = 1;
                } else {
                    this.incrLastMentioned();
                }
                break;
            case 3:
                refType = this.choose(this.SPECREFS);
                this.lastMention = 1;
                break;
        }

        String returnStr = this.getREForType(refType);
        this.lastType = refType;
        return returnStr;
    }

    public void incrLastMentioned() {
        if (this.lastMention != 3)
            this.lastMention += 1;
    }

    private String choose(List<String> li) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, li.size());
        return li.get(randomNum) != this.lastType ? li.get(randomNum) : this.choose(li);
    }

    private String choose(List<String> li1, List<String> li2) {
        List <String> li;
        if (ThreadLocalRandom.current().nextBoolean()) {
            li = li1;
        } else {
            li = li2;
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, li.size());
        return li.get(randomNum) != this.lastType ? li.get(randomNum) : this.choose(li1, li2);
    }

    private String getREForType(String type) {
        switch (type) {
            case "MODELL":
                return this.carID;
            case "MARKE":
                return this.techData.getSingleEntry(this.carID, type);
            case "PRON":
                if (SPECREFS.contains(lastType)) {
                    return "er";
                }
                return "es";
            case "TYPE":
                return "Auto";
        }
        return "";
    }
}
