package src;

public class Register{
    private int data;
    private int doorIN, doorOUT;

    public Register(int doorIN, int doorOUT){
        data = 0;
        this.doorIN = doorIN;
        this.doorOUT = doorOUT;
    }
    
    public int getData(){
        return this.data;
    }
    public int getDoorIN(){
        return this.doorIN;
    }
    public int getDoorOUT(){
        return this.doorOUT;
    }

    public void setData(int data){
        this.data = data;
    }
}