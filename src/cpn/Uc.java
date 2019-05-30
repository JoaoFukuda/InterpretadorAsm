// Serve para simular a Unidade de Controle e seus comandos
package src.cpn;

import src.*;

public class Uc {
    boolean[] flags;
    Register car, cbr;

    public Uc() {
        // Inicializa as 3 flags necessárias: SINAL, IGUAL A 0 e ERRO
        flags = new boolean[3];

        // Portas são -1 para indicar que eles não fazem parte de nenhum barramento
        car = new Register(-1, -1);
        cbr = new Register(-1, -1);
    }
}
