// Guarda o hexadecimal (pensando em trocar para palavra ao invés de somente 8 bits)
package src.auxi;

public class Hexa {
    private static char[] hexachar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean isHexa(String hexa)
    {
        try
        {
            Integer.parseInt(hexa, 16);
        } catch(java.lang.NumberFormatException ex)
        {
            return false;
        }

        return true;
    }

    public static int strToInt(String hexa) {
        return Integer.parseInt(hexa, 16);
    }

    public static String intToString(int hexa) {
        return Integer.toHexString(hexa);
    }

    public static String binToString(String hexa)
    {
        int i = Integer.parseInt(hexa, 2);
        return Integer.toHexString(i);
    }
    
    public static String strToBin(String hex)
    {
        int i = Integer.parseInt(hex, 16);
        return Integer.toBinaryString(i);
    }
}
