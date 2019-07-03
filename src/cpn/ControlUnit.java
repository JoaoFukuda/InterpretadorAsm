// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

import java.io.*;

import src.Memory;

public class ControlUnit {
    public boolean[] aFlags;
    public Register CAR, CBR;
    public int time = 0;
    public String code = "";

    public boolean[] doors = new boolean[23];
    private String fFlag, arFlag, jmpCond;
    private Bus internalBus, externalBus;
    private ALU alu;
    private Memory mem;
    private Register pc;
    private MBR mbr;

    // The name of the file to open.
    String firmware = "firmware.txt";

    public ControlUnit(ALU alu, Memory mem, Bus internal, Bus external, Register pc, MBR mbr) {
        this.alu = alu;
        this.mem = mem;
        this.pc = pc;
        this.mbr = mbr;
        internalBus = internal;
        externalBus = external;

        // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
        aFlags = new boolean[3];

        // Registradores para andar pelo firmware
        CAR = new Register(-1, -1);
        CAR.data = 0;
        CBR = new Register(-1, -1);
    }

    public String flagsToString()
    {
        String res = "";

        res += aFlags[0] ? "1" : "0";
        res += aFlags[1] ? "1" : "0";
        res += aFlags[2] ? "1" : "0";

        return res;
    }

    private void getLine(int lineN) {
        int counter = 0;
    
        // This will reference one line at a time
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
    
        try {
            // FileReader reads text files in the default encoding.
            fileReader = new FileReader(firmware);
    
            // Always wrap FileReader in BufferedReader.
            bufferedReader = new BufferedReader(fileReader);
    
            do{
                if(counter == lineN)
                {
                    for(int n = 0; n < 23; n++)
                        doors[n] = (bufferedReader.read() == '1') ? true : false;
                    
                    bufferedReader.read();

                    fFlag = "";
                    for(int n = 0; n < 2; n++)
                        fFlag += (char)bufferedReader.read();
                    
                    bufferedReader.read();

                    arFlag = "";
                    for(int n = 0; n < 3; n++)
                        arFlag += (char)bufferedReader.read();

                    bufferedReader.read();

                    jmpCond = "";
                    for(int n = 0; n < 3; n++)
                        jmpCond += (char)bufferedReader.read();

                    bufferedReader.close();
                    return;
                }

                counter++;
            } while(bufferedReader.readLine() != null);
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Não foi possívei achar o arquivo '" + firmware + "'");
        }
        catch(IOException ex) {
            System.out.println("Erro ao ler o arquivo '" + firmware + "'");                  
        }
        return;
    }

    public void updateIR(String opcode)
    {
        System.out.println("Pegando OpCode " + opcode + " como " + Integer.parseInt(opcode, 16));

        switch(Integer.parseInt(opcode, 16))
        {
            case 0:
                CAR.data = 4;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 1:
                CAR.data = 5;
                break;
            case 2:
                CAR.data = 8;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 4:
                CAR.data = 11;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 5:
                CAR.data = 14;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 6:
                CAR.data = 18;
                break;
            case 7:
                CAR.data = 22;
                break;
            case 8:
                CAR.data = 25;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 9:
                CAR.data = 28;
                break;
            case 10:
                CAR.data = 31;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 11:
                CAR.data = 34;
                break;
            case 12:
                CAR.data = 37;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 13:
                CAR.data = 40;
                break;
            case 14:
                CAR.data = 43;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 15:
                CAR.data = 46;
                break;
            case 16:
                CAR.data = 49;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
            case 17:
                CAR.data = 52;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 18:
                CAR.data = 53;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 19:
                CAR.data = 55;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 20:
                CAR.data = 57;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 21:
                CAR.data = 59;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 22:
                CAR.data = 61;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 23:
                CAR.data = 63;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 1), 16);
                break;
            case 24:
                CAR.data = 65;
                break;
            case 25:
                CAR.data = 67;
                break;
            case 26:
                CAR.data = 68;
                mbr.data = Integer.parseInt(mem.map.get((pc.data - 3) + 2), 16);
                break;
        }
    }

    public void Update()
    {
        time++;
        
        getLine(CAR.data);

        CAR.data++;

        switch(fFlag)
        {
            case "00":
                mem.AVOrreadOrWrite = 0;
                break;
            case "01":
                mem.AVOrreadOrWrite = 1;
                break;
            case "10":
                mem.AVOrreadOrWrite = 2;
                break;
            default:
                System.out.println("fFlags:" + fFlag);
        }

        switch(arFlag)
        {
            case "000":
                alu.calculation = 0;
                break;
            case "001":
                alu.calculation = 1;
                break;
            case "010":
                alu.calculation = 2;
                break;
            case "011":
                alu.calculation = 3;
                break;
            case "100":
                alu.calculation = 4;
                break;
            case "101":
                alu.calculation = 5;
            default:
                System.out.println("arFlags:" + arFlag);
        }

        switch(jmpCond)
        {
            case "001":
                time = 0;
                CAR.data = 0;
                break;
            default:
        }

        internalBus.openDoors(doors);
        externalBus.openDoors(doors);
    }
}
