// Guarda o hexadecimal (pensando em trocar para palavra ao invés de somente 8 bits)
package src.auxi;

public class Hexa {
    private static char[] hexachar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean isHexa(String hexa)
    {
        boolean res = true;

        if(hexa.length() != 2) res = false;
        else
        {
            res = false;
            for(int i = 0; i < 2; i++)
            {
                for(char c : hexachar)
                {
                    if(hexa.charAt(i) == c) res = true;
                    continue;
                }
                if(!res) break;
            }
        }

        return res;
    }

    public static int strToInt(String hexa) {
        int res = 0;

        for(int i = 0; i < 2; i++)
            for(int n = 0; n < hexachar.length; n++)
            {
                if(hexa.charAt(i) != hexachar[n]) continue;
                res += n * (16 * (1 - i));
            }

        return res;
    }

    public static String intToString(int hexa) {
        return hexachar[hexa/16] + "" + hexachar[hexa%16];
    }

    public static String binToString(String bin)
    {
        return "00";
    }

    public static String strToBin(String Hexa)
    {
        return "00";
    }
}
