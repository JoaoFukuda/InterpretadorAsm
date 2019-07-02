package src.cpn;

public class ALU extends Register
{
    int inData, calculation;

    public ALU(int inX, int inDoor, int outDoor)
    {
        super(3);

        doorsN[0] = inX;
        doorsN[1] = inDoor;
        doorsN[2] = outDoor;

        inData = 0;
        calculation = -1;
    }

    public void setData(int door, int data)
    {
        if(!doors[doorPos(door)]) return;
        reset();
        if(door == 20) this.data = data;
        else if(door == 21) inData = data;
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
                break;
            case 4:
                data = inData % data;
        }

        if(calculation != -1) open(22);

        calculation = -1;
    }
}
