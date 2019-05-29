package Registrador;
public class Register{
    private int data;
    private int opcode;
    public Register(int data, int opcode){
        this.data = data;
        this.opcode = opcode;
    }
    public int getData(){
        return this.data;
    }

    public int getOpcode(){
        return this.opcode;
    }

    public void setData(int data){
        this.data = data;
    }
}