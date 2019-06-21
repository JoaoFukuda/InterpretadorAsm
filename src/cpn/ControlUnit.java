// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

public class ControlUnit {
    public boolean[] flags;
    public Register CAR, CBR;

    public ControlUnit() {
        // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
        flags = new boolean[3];

        CAR = new Register();
        CBR = new Register();
    }
}
