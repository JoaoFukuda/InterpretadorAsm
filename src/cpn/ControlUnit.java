// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

public class ControlUnit {
    public boolean[] aFlags;
    public Register CAR, CBR;

    public ControlUnit() {
        // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
        aFlags = new boolean[3];

        // Registradores para andar pelo firmware
        CAR = new Register();
        CBR = new Register();
    }
}
