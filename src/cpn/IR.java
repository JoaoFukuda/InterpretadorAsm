package src.cpn;

import src.auxi.Hexa;

public class IR extends Register
{
    ControlUnit uc;

    public IR(int inDoor, int outDoor)
    {
        super(inDoor, outDoor);
    }

    public void addUC(ControlUnit uc)
    {
        this.uc = uc;
    }

    public void setData(int door, int data)
    {
        if(door == -1) return;
        if(!doors[doorPos(door)]) return;
        reset();
        this.data = data;
        uc.updateIR(Hexa.intToString(data));
    }
}
