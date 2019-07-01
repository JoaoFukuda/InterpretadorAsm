package src.cpn;

public class Register{
    protected int data;
    protected boolean in, out;

    public Register(){
        data = 0;
        in = false;
        out = false;
    }

    public void openIn()
    {
        in = true;
    }
    public void openOut()
    {
        out = true;
    }

    public boolean isInOpen(){
        return this.in;
    }
    public boolean isOutOpen(){
        return this.out;
    }
    
    public int getData(){
        return this.data;
    }
    public void setData(int data){
        this.data = data;
    }
}
