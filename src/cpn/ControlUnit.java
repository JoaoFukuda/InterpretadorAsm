// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

public class ControlUnit {
    public boolean[] aFlags;
    public Register CAR, CBR;
    public int time = 0;

    public ControlUnit() {
        // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
        aFlags = new boolean[3];

        // Registradores para andar pelo firmware
        CAR = new Register(-1, -1);
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
}
