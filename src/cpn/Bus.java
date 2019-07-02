package src.cpn;

public class Bus
{
    Register[] registers;
    int[] inDoors, outDoors;
    boolean hasData;
    int data, nOfRegisters, end = 0;

    public enum Dir{
        IN,
        OUT
    };

    public Bus(int nOfRegisters)
    {
        this.nOfRegisters = nOfRegisters;
        registers = new Register[nOfRegisters];
        inDoors = new int[nOfRegisters];
        outDoors = new int[nOfRegisters];
    }

    public void Update()
    {
        for(int n = 0; n < nOfRegisters; n++)
        {
            if(!registers[n].isOutOpen()) continue;
            
            hasData = true;
            data = registers[n].getData();
            break;
        }

        if(hasData) for(int n = 0; n < nOfRegisters; n++)
        {
            if(!registers[n].isInOpen())
            {
                registers[n].setData(data);
            }
        }
    }

    public Bus Add(Register reg, int inDoor, int outDoor)
    {
        registers[end] = reg;
        inDoors[end] = inDoor;
        outDoors[end] = outDoor;
        end++;
        return this;
    }

    public void openDoors(boolean[] doors)
    {
    }
}
