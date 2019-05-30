// Guarda o hexadecimal (pensando em trocar para palavra ao invés de somente 8 bits)
package src.aux;

public class Hexa {
    private static char[] hexachar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private int valor;

    public Hexa() {
        valor = 0;
    }

    public void set(int valor) {
        if(valor > 255) valor %= 255;
        this.valor = valor;
    }

    public String getString() {
        return hexachar[valor/16] + "" + hexachar[valor%16];
    }
}
