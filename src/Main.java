// O ponto de entrada do código
package src;

import src.grafico.*;
import src.cpn.*;

class Main {

	public static void main(String[] args) {
        CPU cpu = new CPU();

        cpu.Init();

        cpu.Run();
	}
}

class CPU
{
    private Bus barramentoInterno, barramentoExterno;
    MainFrame GUI;
    ControlUnit UC;

    public CPU()
    {
        GUI = new MainFrame();
        UC = new ControlUnit();

        Register
        AX = new Register(),
        BX = new Register(),
        CX = new Register(),
        DX = new Register(),
        IR = new Register(),
        PC = new Register(),
        ULA = new ALU(),
        MAR = new MAR(),
        MBR = new MBR();

        barramentoInterno = new Bus(9);

        barramentoExterno = new Bus(3);
    }

    // Parse the file and store it on memory
    public void Init()
    {
    }
    
    // Run the program with the steps informed by the GUI
    public void Run()
    {
        Update();
        // Update the GUI with the necessary information
    }

    // Ticks the system, running one time a call
    private void Update()
    {
        //Update the PC and CAR

        barramentoInterno.Update();
        barramentoExterno.Update();
    }
    private void Undo()
    {
    }
}
