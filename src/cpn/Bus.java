package src.cpn;

public class Bus
{
    Register[] registers;
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
    }

    public void Update()
    {
        for(Register reg : registers)
        {
            data = reg.getData();
            hasData = true;
            break;
        }

        if(hasData) for(Register reg : registers)
        {
            reg.setData(data);
        }
    }

    public Bus Add(Register reg)
    {
        registers[end] = reg;
        end++;
        return this;
    }

    public void openDoors(boolean[] doors)
    {
        for(int n = 0; n <= 22; n++)
        {
            if(doors[n])
            {
            }
        }
    }
}
