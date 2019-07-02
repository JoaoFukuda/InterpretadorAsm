package src.cpn;

public class MBR extends Register
{
    int inDoorE, outDoorE;
    public MBR(int inDoorI, int outDoorI, int inDoorE, int outDoorE)
    {
        super(4);
        
        doorsN[0] = inDoorI;
        doorsN[1] = outDoorI;
        doorsN[2] = inDoorE;
        doorsN[3] = outDoorE;
    }
}
