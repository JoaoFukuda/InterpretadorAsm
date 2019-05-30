// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

import src.*;

public class Uc {
    boolean[] flags;
    Register car, cbr;

    public Uc() {
        flags = new boolean[3]; // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
    }
}
