package src.cpn;

public class Register{
    public int data;
    protected int nOfDoors;
    protected boolean[] doors;
    protected int[] doorsN;

    public Register(int inDoor, int outDoor){
        data = 0;
        nOfDoors = 2;

        doors = new boolean[nOfDoors];
        for(int n = 0; n < nOfDoors; n++) doors[n] = false;

        doorsN = new int[nOfDoors];
        doorsN[0] = inDoor;
        doorsN[1] = outDoor;
    }

    public Register(int nOfDoors){
        data = 0;
        this.nOfDoors = nOfDoors;

        doors = new boolean[nOfDoors];
        for(int n = 0; n < nOfDoors; n++) doors[n] = false;

        doorsN = new int[nOfDoors];
    }

    protected int doorPos(int door)
    {
        for(int n = 0; n < nOfDoors; n++)
            if(doorsN[n] == door) return n;
        
            return -1;
    }

    public boolean isOpen(int door)
    {
        if(door == -1) return false;
        int pos;
        if((pos = doorPos(door)) == -1) return false;
        return doors[pos];
    }

    public void open(int door)
    {
        if(door == -1) return;
        int pos;
        if((pos = doorPos(door)) == -1) return;
        doors[pos] = true;
    }

    protected void reset()
    {
        for(int n = 0; n < nOfDoors; n++)
        {
            doors[n] = false;
        }
    }

    public int getData(int door)
    {
        if(door == -1) return -1;
        if(!doors[doorPos(door)]) return -1;
        reset();
        return this.data;
    }
    public void setData(int door, int data)
    {
        if(door == -1) return;
        if(!doors[doorPos(door)]) return;
        reset();
        this.data = data;
    }
}
