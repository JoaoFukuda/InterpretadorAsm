package src;
import java.util.TreeMap;
import src.cpn.Register;

public class Memory extends Register{
    public TreeMap<Integer,Integer> map;
    public Memory(){
        this.map = new TreeMap<Integer,Integer>();
    }

    /*
    Para usar o mapa:
    map.put(int endereco, int conteudo) - coloca o conteudo no endereco(chave do mapa) e retorna o que estava antes (null se nao tinha nada)
    map.get(int endereco) - retorna o que tem no endereco
    map.containsKey(int endereco) - true ou false se tiver ou nao algo no endereco
    */
}