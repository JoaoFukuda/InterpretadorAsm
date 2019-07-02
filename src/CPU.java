package src;

import javax.swing.*;
import java.awt.event.*;

import src.cpn.*;

public class CPU implements ActionListener
{
    Memory MEM;

    private Bus barramentoInterno, barramentoExterno;
    ControlUnit UC;

    public CPU()
    {
        UC = new ControlUnit();

        Register
        AX = new Register(),
        BX = new Register(),
        CX = new Register(),
        DX = new Register(),
        IR = new Register(),
        PC = new Register();
        ALU ULA = new ALU();
        MAR MAR = new MAR();
        MBR MBR = new MBR();
        MEM = new Memory();

        barramentoInterno = new Bus(10);
        barramentoInterno
        .Add(AX, 11, 10)
        .Add(BX, 9, 8)
        .Add(CX, 7, 6)
        .Add(DX, 4, 5)
        .Add(IR, 3, 2)
        .Add(PC, 1, 0)
        .Add(ULA, 21, 22)
        .Add(ULA, 20, -1)
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

    // Ticks the system, running one time a call
    private void Update()
    {
        //Update the PC and CAR

        barramentoInterno.Update();
        barramentoExterno.Update();
    }

    public Memory getMEM(){ return MEM; }

	public void actionPerformed(ActionEvent e) {
        switch(((JMenuItem)e.getSource()).getText())
        {
            case "Devagar":
                System.out.println("Não está funcionando");
                break;
            case "Passo-a-passo":
                System.out.println("Avançando um tempo");
                Run();
                break;
            case "Desfazer":
                System.out.println("Não está funcionando");
                break;
            case "Reiniciar":
                System.out.println("Zerar o estado do programa");
                break;
            default:
        }
	}
}
