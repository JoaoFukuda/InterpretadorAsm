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
            data = registers[n].getData(outDoors[n]);
            if(data == -1) continue;
            hasData = true;
            break;
        }

        System.out.println(data + " is on the bus");

        if(hasData) for(int n = 0; n < nOfRegisters; n++)
        {
            registers[n].setData(inDoors[n], data);
        }

        data = -1;
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
        for(int n = 0; n < 23; n++)
            if(doors[n])
                for(Register reg : registers)
                    reg.open(n);
    }
}
