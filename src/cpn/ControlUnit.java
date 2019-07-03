// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

import java.io.*;

public class ControlUnit {
    public boolean[] aFlags;
    public Register CAR, CBR;
    public int time = 0;

    private boolean[] doors = new boolean[23];
    private String fFlag, arFlag, jmpCond;

    // The name of the file to open.
    String firmware = "firmware.txt";

    public ControlUnit() {
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
        String line = null;
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
                    bufferedReader.close();
                    return;
                }

                counter++;
            } while((line = bufferedReader.readLine()) != null);
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

    public void Update()
    {
        getLine(CAR.data);
        CAR.data++;
    }
}
