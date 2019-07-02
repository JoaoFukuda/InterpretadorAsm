package src.cpn;

public class Register{
    protected int data;
    protected boolean in, out;
    protected int inDoor, outDoor;

    public Register(int inDoor, int outDoor){
        data = 0;
        
        in = false;
        out = false;

        this.inDoor = inDoor;
        this.outDoor = outDoor;
    }

    public void openIn(){ in = true; }
    public boolean isInOpen(){ return this.in; }
    
    public void openOut(){ out = true; }
    public boolean isOutOpen(){ return this.out; }
    
    public int getData()
    {
        out = false;
        return this.data;
    }
    public void setData(int data)
    {
        in = false;
        this.data = data;
    }
}
