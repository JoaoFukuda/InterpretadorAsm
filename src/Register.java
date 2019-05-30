package src;

public class Register{
    private int data;
    private int doorNumber;

    public Register(int data, int doorNumber){
        this.data = data;
        this.doorNumber = doorNumber;
    }
    
    public int getData(){
        return this.data;
    }

    public int getdoorNumber(){
        return this.doorNumber;
    }

    public void setData(int data){
        this.data = data;
    }
}