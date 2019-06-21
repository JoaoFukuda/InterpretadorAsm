package src.cpn;

public class Register{
    protected int data;
    protected boolean in, out;

    public Register(){
        data = 0;
        in = false;
        out = false;
    }

    public void setBus(boolean in, boolean out)
    {
        this.in = in;
        this.out = out;
    }
    public boolean inOpen(){
        return this.in;
    }
    public boolean outOpen(){
        return this.out;
    }
    
    public int getData(){
        return this.data;
    }
    public void setData(int data){
        this.data = data;
    }
}
