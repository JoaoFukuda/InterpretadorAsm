// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

import java.io.*;

import src.Memory;

public class ControlUnit {
    public boolean[] aFlags;
    public Register CAR, CBR;
    public int time = 0;
    public String code = "";

    private boolean[] doors = new boolean[23];
    private String fFlag, arFlag, jmpCond;
    private Bus internalBus, externalBus;
    private ALU alu;
    private Memory mem;

    // The name of the file to open.
    String firmware = "firmware.txt";

    public ControlUnit(ALU alu, Memory mem, Bus internal, Bus external) {
        this.alu = alu;
        this.mem = mem;
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
        switch(Integer.parseInt(opcode, 2))
        {
            case 0:
                CAR.data = 5;
                break;
            case 1:
                CAR.data = 6;
                break;
            case 2:
                CAR.data = 9;
                break;
            case 4:
                CAR.data = 12;
                break;
            case 5:
                CAR.data = 15;
                break;
            case 6:
                CAR.data = 19;
                break;
            case 7:
                CAR.data = 23;
                break;
            case 8:
                CAR.data = 26;
                break;
            case 9:
                CAR.data = 29;
                break;
            case 10:
                CAR.data = 32;
                break;
            case 11:
                CAR.data = 35;
                break;
            case 12:
                CAR.data = 38;
                break;
            case 13:
                CAR.data = 41;
                break;
            case 14:
                CAR.data = 44;
                break;
            case 15:
                CAR.data = 47;
                break;
            case 16:
                CAR.data = 50;
                break;
            case 17:
                CAR.data = 53;
                break;
            case 18:
                CAR.data = 54;
                break;
            case 19:
                CAR.data = 56;
                break;
            case 20:
                CAR.data = 58;
                break;
            case 21:
                CAR.data = 60;
                break;
            case 22:
                CAR.data = 62;
                break;
            case 23:
                CAR.data = 64;
                break;
            case 24:
                CAR.data = 66;
                break;
            case 25:
                CAR.data = 68;
                break;
            case 26:
                CAR.data = 69;
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
