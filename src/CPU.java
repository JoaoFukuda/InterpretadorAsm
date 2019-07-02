package src;

import src.cpn.*;

public class CPU
{
    Memory MEM;

    private Bus barramentoInterno, barramentoExterno;
    public ControlUnit UC;
    public ALU ULA;
    public MAR MAR;
    public MBR MBR;
    public Register AX, BX, CX, DX, IR, PC;

    public CPU()
    {
        UC = new ControlUnit();

        AX = new Register(11, 10);
        BX = new Register(9, 8);
        CX = new Register(7, 6);
        DX = new Register(5, 4);
        IR = new Register(3, 2);
        PC = new Register(1, 0);
        ULA = new ALU(20, 21, 22);
        MAR = new MAR(12, 13);
        MBR = new MBR(14, 15, 17, 16);
        MEM = new Memory(19, 18);

        barramentoInterno = new Bus(10);
        barramentoInterno
        .Add(AX, 11, 10)
        .Add(BX, 9, 8)
        .Add(CX, 7, 6)
        .Add(DX, 5, 4)
        .Add(IR, 3, 2)
        .Add(PC, 1, 0)
        .Add(ULA, 20, -1)
        .Add(ULA, 21, 22)
        .Add(MAR, 12, -1)
        .Add(MBR, 14, 15);

        barramentoExterno = new Bus(3);
        barramentoExterno
        .Add(MAR, -1, 13)
        .Add(MBR, 17, 16)
        .Add(MEM, 19, 18);
    }
    
    // Run the program with the steps informed by the GUI
    public void Run()
    {
        // Open and close the necessary doors
        // Transit the data through the open doors
        Update();
        // Update the GUI with the necessary information
    }

    public int[] getRegisters()
    {
        return(new int[]{AX.data, BX.data, CX.data, DX.data, PC.data, IR.data, MAR.data, MBR.data, UC.CAR.data, UC.CBR.data});
    }

    // Ticks the system, running one time a call
    private void Update()
    {
        //Update the PC and CAR

        barramentoInterno.Update();
        barramentoExterno.Update();
    }

    public Memory getMEM(){ return MEM; }
}
