package src.cpn;

public class Bus
{
    Register[] registers;
    boolean hasData;
    int data;

    public Bus(int nOfRegisters)
    {
        registers = new Register[nOfRegisters];
    }

    public void Update()
    {
        for(Register reg : registers)
        {
            if(!reg.outOpen()) continue;
            
            hasData = true;
            data = reg.getData();
            break;
        }

        if(hasData) for(Register reg : registers)
        {
            if(!reg.inOpen())
            {
                reg.setData(data);
            }
        }
    }
}
