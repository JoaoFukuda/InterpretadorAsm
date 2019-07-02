package src.cpn;

public class ALU extends Register
{
    int inData, calculation;
    int inX;

    public ALU(int inX, int inDoor, int outDoor)
    {
        super(inDoor, outDoor);
        this.inX = inX;

        inData = -1;
        calculation = -1;
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
                data = inData + data;
                break;
            case 1:
                data = inData - data;
                break;
            case 2:
                data = inData * data;
                break;
            case 3:
                data = inData / data;
        }

        if(calculation != -1) openOut();

        inData = -1;
        calculation = -1;
    }
}
