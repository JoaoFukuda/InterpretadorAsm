package src.cpn;

public class ALU extends Register
{
    static int
    NONE = -1,
    ADD = 0,
    SUB = 1,
    MUL = 2,
    DIV = 3;

    int inData, outData, calculation;

    public ALU()
    {
        super();

        outData = 0;
        inData = -1;
        calculation = NONE;
    }

    public void setData(int data)
    {
        if(inData == -1) inData = data;
        else this.data = data;
    }

    public void Update()
    {
        switch(calculation)
        {
            case 0:
                outData = inData + data;
                break;
            case 1:
                outData = inData - data;
                break;
            case 2:
                outData = inData * data;
                break;
            case 3:
                outData = inData / data;
        }

        if(calculation != NONE) out = true;
        inData = -1;

        calculation = NONE;
    }
}
