package src;
import java.util.TreeMap;
import src.cpn.Register;

public class Memory extends Register{
    
    TreeMap<Integer,Integer> map;
    
    int anotherData, readOrWrite;   

    public Memory(){
        super();
        this.map = new TreeMap<Integer,Integer>();
        this.anotherData = -1;
        this.readOrWrite = NONE;
    }


    /*
    public boolean searchAddressPhase(int endereco){ //Coloca no registrador da memoria o endereco que se quer buscar e retorna se ja ha conteudo na memoria com esse endereco
        super.setData(endereco);
        return this.map.containsKey(endereco);
    }
    */

    public void setData(int data){ //MODIFICACAO: agora -> anotherData = endereco; super.data = dados.
        if(anotherData == -1) anotherData = data;
        else super.data = data;
    }

    public void Update(){
        if(this.readOrWrite == 0){
            readMemory();
        }else{
            if(this.readOrWrite == 1){
                writeMemory(super.data);
            }
        }
        this.readOrWrite = NONE;
        this.anotherData = -1;
    }

    void readMemory(){                        //retorna o que esta no endereco guardado pelo registrador da memoria
        super.setData(this.map.get(super.getData()));
        super.setBus(false,true);
    }

    int writeMemory(int data){              //escreve no endereco armazenado no registrador da memoria e retorna o que estava antes
        return this.map.put(super.getData(),data);
    }
    /*
    Para usar o mapa:
    map.put(int endereco, int conteudo) - coloca o conteudo no endereco(chave do mapa) e retorna o que estava antes (null se nao tinha nada)
    map.get(int endereco) - retorna o que tem no endereco
    map.containsKey(int endereco) - true ou false se tiver ou nao algo no endereco
    */
    /*
    Para usar a memoria em si:
    Para seguir a regra de que so se pode ou buscar ou ler/escrever em um dado momento fiz o metodo que 
    busca um endereco e o coloca no registrador da memoria. Apenas depois disso se faz a leitura/escrita na memoria,
    no endereco que agora esta no registrador

    O registrador que usei para a memoria tem numero de entrada 18, do jeito que estabelecemos a entrada da memoria

    Tudo isso foi feito deixando a classe memoria como filha de registrador e usando suas funcoes
    Does it work? No clue. I hope we get to test soon, so we can see alllll the problems just so we can consider 
    sudoku right afterwards
    */
}