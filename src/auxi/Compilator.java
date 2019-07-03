// Pega o código do professor e transforma em opcodes para ser interpretado pelo programa
// Utiliza somente métidos static
package src.auxi;

import java.io.*;
import java.util.Scanner;

import src.Memory;

public class Compilator {
    private static Memory mem;

    static private String seeInsideAddress(String shell)
    {
        char [] casca = shell.toCharArray();
        String semCasca="";
        for (int i=1; i<casca.length -1;i++)
        {
            semCasca += casca[i];
        }

        return semCasca;
    }

    static private String traduzIssu(String op , String p1, String p2){
        int enderecoOpcode=0;
        int enderecoP1=1;
        int enderecoP2=2;
        

        /*Aparentemente p1 nunca serah uma constante*/
        boolean endereco = false; // eu poderia assumir que endereco eh registrador= false; mas acho que assim fica menos confuso
        boolean registrador = false;
        boolean regEndereco = false; // verifica se eh registrador de endereco
        boolean constante = false;
        /* Kendy, se esse endereço tem que guardar tem que ter um jeito de saber
        qual que é o número, eu não sei direito  */
        String ar= "[AX]";
        String br= "[BX]";
        String cr= "[CX]";    
        String dr= "[DX]";
        String a= "AX";
        String b= "BX";
        String c= "CX";
        String d= "DX";

        if (p1.equalsIgnoreCase(ar)){
            regEndereco = true;
            //kendy, guardar endereço 
        }
        else if (p1.equalsIgnoreCase(br)){
            regEndereco = true;
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(cr)){
            regEndereco = true;
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(dr)){
            regEndereco = true;
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(a)){
            registrador = true;
        }
        else if (p1.equalsIgnoreCase(b)){
            registrador = true;
        }
        else if (p1.equalsIgnoreCase(c)){
            registrador = true;
        }
        else if (p1.equalsIgnoreCase(d)){
            registrador = true;
        }
        else if(Hexa.isHexa(p1)){
            constante = true;
            int endP1 = Hexa.strToInt(p1);
            mem.adicionaDireto(enderecoP1, endP1);
        }
        else{
            endereco = true;
            int endP1 = Hexa.strToInt(seeInsideAddress(p1));
            mem.adicionaDireto(enderecoP1, endP1);
        }
            
     /*________________________________________________________________ */
     
     boolean regEndereco2 = false; // verifica se eh endereco de registrador
     boolean registrador2 = false;
     boolean endereco2 = false;
     boolean constante2 = false;
     
     if(!p2.equalsIgnoreCase("")){
     
        if (p2.equalsIgnoreCase(ar)){
            regEndereco2 = true;
        }
                
        else if (p2.equalsIgnoreCase(br)){
            regEndereco2 = true;
        }
                
        else if (p2.equalsIgnoreCase(cr)){
            regEndereco2 = true;
        }
                
        else if (p2.equalsIgnoreCase(dr)){
            regEndereco2 = true;
        }
        else if (p2.equalsIgnoreCase(a)){
            registrador2 = true;
        }
        
        else if (p2.equalsIgnoreCase(b)){
            registrador2 = true;
        }
        
        else if (p2.equalsIgnoreCase(c)){
            registrador2 = true;
        }
            
        else if (p2.equalsIgnoreCase(d)){
            registrador2 = true;
        }
        else if(Hexa.isHexa(p2)){
            constante2 = true;
            int endP2 = Hexa.strToInt(p2);
            mem.adicionaDireto(enderecoP2, endP2);
        }
        else{
            endereco2 = true;
            int endP2 = Hexa.strToInt(seeInsideAddress(p2));
            mem.adicionaDireto(enderecoP2, endP2);
        }
    }
     /* ______________________________________________________________________________________*/
     //aqui seleciona o opcode
        String opCode="";

        if (op.equalsIgnoreCase("MOV")){
            if(registrador && constante2){	//MOV R1, const
                opCode="000000";
            } 
            if(regEndereco && registrador2){ //	MOV [R1], R2
                opCode="000001";
            }

            if(regEndereco && constante2){ //	MOV [R1], const
                opCode="000010";
            }
            if(endereco && constante2){   //		MOV [const], const
                opCode="000011";
            }
            if(endereco && registrador2){   //	MOV [const], R1
                opCode="000100";
            }
            if(registrador && endereco2){   //	MOV R1, [const]
                opCode="000101";
            }
            if(registrador && regEndereco2){   //		MOV R1,[R2]
                opCode="000110";
            }
            if(registrador && registrador2){   //		MOV R1, R2
                opCode="011001";
            }
        }

        if (op.equalsIgnoreCase("ADD")){
            if(registrador && registrador2){   //		ADD R1, R2
                opCode="000111";
            }
            if(registrador && constante2){   //	ADD R1, constante
                opCode="001000";
            }
        }

        if (op.equalsIgnoreCase("SUB")){
            if(registrador && registrador2){   //	SUB R1, R2
                opCode="001001";
            }
            if(registrador && constante2){   //	SUB R1, constante
                opCode="001010";
            }
        }

        if (op.equalsIgnoreCase("MUL")){
            if(registrador && registrador2){   //		MUL R1, R2
                opCode="001011";
            }
            if(registrador && constante2){   //	 	MUL R1, constante
                opCode="001100";
            }
        }

        if (op.equalsIgnoreCase("DIV")){
            if(registrador && registrador2){   //			DIV R1, R2
                    
                opCode="001101";
            }
            if(registrador && constante2){   //	 	DIV R1, constante
                opCode="001110";
            }
        } 

        if (op.equalsIgnoreCase("MOD")){
            if(registrador && registrador2){   //			MOD R1, R2
                    
                opCode="001111";
            }
            if(registrador && constante2){   //	 		MOD R1,constante
                opCode="010000";
            }
        }   

                   
if (op.equalsIgnoreCase("CMP")){
    if(registrador && registrador2){   //			CMP R1, R2
               
        opCode="011000";
    }
    if(registrador && constante2){   //          CMP R1, constante
        opCode="011010";
    }
}   
            
               
            
            String jmp="JMP";
            if (op.equalsIgnoreCase(jmp)){
                opCode="010001";
            }
            String jne="JNE";
            if (op.equalsIgnoreCase(jne)){
                opCode="010011";
            }
            String je="JE";
            if (op.equalsIgnoreCase(je)){
                opCode="010010";
            }
            String jg="JG";
            if (op.equalsIgnoreCase(jg)){
                opCode="010100";
            }
            String jge="JGE";
            if (op.equalsIgnoreCase(jge)){
                opCode="010101";
            }
            String jl="JL";
            if (op.equalsIgnoreCase(jl)){
                opCode="010110";
            }
            String jle="JLE";
            if (op.equalsIgnoreCase(jle)){
                opCode="010111";
            }
            int opcodee = Integer.parseInt(opCode);
            mem.adicionaDireto(enderecoOpcode,opcodee);
            enderecoOpcode+=3;
            enderecoP1+=3;
            enderecoP2+=3;
            

            return opCode;
            }
         
    
    static private void separaIssu(String c){
        char [] comando = c.toCharArray();
        String operacao="";
        String p1="";
        String p2="";
        
        int j=0;
         while (comando[j]!=' '){ 
            operacao+=comando[j];
            j++;
         }
         
         if (operacao.equalsIgnoreCase("JE")|| operacao.equalsIgnoreCase("JG")|| operacao.equalsIgnoreCase("JL")){
            for ( j= 3; j<comando.length;j++){
                p1+=comando[j];
             }
            
              p2="";
         }else{
        int i=4;
       if ( operacao.equalsIgnoreCase("JLE") ||  operacao.equalsIgnoreCase("JGE") ||operacao.equalsIgnoreCase("JMP") ||operacao.equalsIgnoreCase("JNE") ){
        for (int y= i; y<comando.length;y++){
            p1+=comando[y];
         }
       }else{
        while (comando[i]!=','){
            p1+=comando[i];
            i++;
         }
         
            for (int y= i+1; y<comando.length;y++){
                p2+=comando[y];
             }
            }
        }

        System.out.println(operacao + " " + p1 + " " + p2);
        
        System.out.println(traduzIssu(operacao,p1,p2));
    }

    static public void compilar(InputStream input, Memory mem)
    {
        Compilator.mem = mem;
        Scanner in = new Scanner(input);

        while(in.hasNextLine())
        {
            String x = in.nextLine();
            separaIssu(x);
        }

        in.close();
    }
}
