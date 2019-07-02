// Guarda o hexadecimal (pensando em trocar para palavra ao invés de somente 8 bits)
package src.auxi;

public class Hexa {
    private static char[] hexachar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static int toInt(String hexa) {
        int res = 0;

        for(int i = 0; i < 2; i++)
            for(int n = 0; n < hexachar.length; n++)
            {
                if(hexa.charAt(i) != hexachar[n]) continue;
                res += n * (16 * (1 - i));
            }

        return res;
    }

    public static String toString(int hexa) {
        return hexachar[hexa/16] + "" + hexachar[hexa%16];
    }
}
