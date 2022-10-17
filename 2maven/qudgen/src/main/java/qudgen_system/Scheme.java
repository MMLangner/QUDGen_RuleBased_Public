package qudgen_system;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class Scheme {

    private String verb = "";
    private Integer probability = 0;
    private List<String> subj = new ArrayList<>();
    private List<String> subjPremod = new ArrayList<>();
    private List<String> subjPostmod = new ArrayList<>();
    private List<String> objPremod = new ArrayList<>();
    private List<String> objPostmod = new ArrayList<>();
    private List<String> obj = new ArrayList<>();
    private String objArt = "";
    private String sentMod = "";

    private String pickFromList(List<String> li) {
        if (li == null || li.size() == 0)
            return "";
        return li.get(ThreadLocalRandom.current().nextInt(0, li.size()));
    }

    public String pickSubj() {
        return this.pickFromList(this.subj);
    }

    public String pickSubjPremod() {
        return this.pickFromList(this.subjPremod);
    }

    public String pickSubjPostmod() {
        return this.pickFromList(this.subjPostmod);
    }

    public String pickObj() {
        return this.pickFromList(this.obj);
    }

    public String pickObjPremod() {
        return this.pickFromList(this.objPremod);
    }

    public String pickObjPostmod() {
        return this.pickFromList(this.objPostmod);
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public List<String> getSubj() {
        return subj;
    }

    public void setSubj(List<String> subj) {
        this.subj = subj;
    }

    public List<String> getSubjPremod() {
        return subjPremod;
    }

    public void setSubjPremod(List<String> subjPremod) {
        this.subjPremod = subjPremod;
    }

    public List<String> getSubjPostmod() {
        return subjPostmod;
    }

    public void setSubjPostmod(List<String> subjPostmod) {
        this.subjPostmod = subjPostmod;
    }

    public List<String> getObjPremod() {
        return objPremod;
    }

    public void setObjPremod(List<String> objPremod) {
        this.objPremod = objPremod;
    }

    public List<String> getObjPostmod() {
        return objPostmod;
    }

    public void setObjPostmod(List<String> objPostmod) {
        this.objPostmod = objPostmod;
    }

    public List<String> getObj() {
        return obj;
    }

    public void setObj(List<String> obj) {
        this.obj = obj;
    }

    public String getObjArt() {
        return objArt;
    }

    public void setObjArt(String objArt) {
        this.objArt = objArt;
    }

    public void setSentMod(String s) {
        this.sentMod = s;
    }

    public String getSentMod() {
        return this.sentMod;
    }

    private void replaceFor(List<String> li, String car, String val) {
        for (int i = 0; i < li.size(); i++) {
            li.set(i, li.get(i).replace("%car%", car));
            li.set(i, li.get(i).replace("%val%", val));
        }
    }

    public void replacePHs(String car, String val) {
        replaceFor(subj, car, val);
        if (car.equals("er") || car.equals("es") || car.equals("Auto"))
            car = "Autos";
        replaceFor(subjPremod, car, val);
        replaceFor(subjPostmod, car, val);
        replaceFor(obj, car, val);
        replaceFor(objPremod, car, val);
        replaceFor(objPostmod, car, val);
    }
}