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
        AX = new Register(11, 10),
        BX = new Register(9, 8),
        CX = new Register(7, 6),
        DX = new Register(5, 4),
        IR = new Register(3, 2),
        PC = new Register(1, 0);
        ALU ULA = new ALU(20, 21, 22);
        MAR MAR = new MAR(12, 13);
        MBR MBR = new MBR(14, 15, 17, 16);
        MEM = new Memory(19, 18);

        barramentoInterno = new Bus(9);
        barramentoInterno
        .Add(AX)
        .Add(BX)
        .Add(CX)
        .Add(DX)
        .Add(IR)
        .Add(PC)
        .Add(ULA)
        .Add(MAR)
        .Add(MBR);

        barramentoExterno = new Bus(3);
        barramentoExterno
        .Add(MAR)
        .Add(MBR)
        .Add(MEM);
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
