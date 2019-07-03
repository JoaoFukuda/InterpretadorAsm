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
        if(door == 20) inData = data;
        else if(door == 21)
        {
            this.data = data;
            Update();
        }
    }

    private void Update()
    {
        if(calculation == -1) return;

        System.out.println("Making the operation " + calculation + "on " + inData + " and " + data);

        switch(calculation)
        {
            case 0:
                data = inData + data;
                break;
            case 1:
                data = inData - data;
                break;
            case 2:
                data = inData / data;
                break;
            case 3:
                data = inData * data;
                break;
            case 4:
                data = inData % data;
                break;
            case 5:
                data++;
                break;
            default:
                return;
        }

        calculation = -1;
    }
}
