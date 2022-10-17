package qudgen_system.Messages;

public abstract class MSG {

    private String type;
    private String status;
    private int statusID;

    public String getType() {
        return this.type;
    }

    public void setUnit(String type) {
        this.type = type;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public int getStatusID(){
        return this.statusID;
    }
    public void setStatusID(int statusID){
        this.statusID = statusID;
    }

    abstract public String surfaceRealize();
    abstract public void lexicalize();
}
