// Pega o código do professor e transforma em opcodes para ser interpretado pelo programa
// Utiliza somente métidos static
package src.auxi;

import java.io.*;
import java.util.Scanner;

import src.Memory;

public class Compilator {
    private static Memory mem;
    public static int enderecoOpcode=0;
    public static int enderecoP1=1;
    public static int enderecoP2=2;

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

        System.out.println(op + " " + p1 + " " + p2);
        

        /*Aparentemente p1 nunca serah uma constante*/
        boolean endereco = false; // eu poderia assumir que endereco eh registrador= false; mas acho que assim fica menos confuso
        boolean registrador = false;
        boolean regEndereco = false; // verifica se eh registrador de endereco
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
            p1 = "0";
            //kendy, guardar endereço 
        }
        else if (p1.equalsIgnoreCase(br)){
            regEndereco = true;
            p1 = "1";
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(cr)){
            regEndereco = true;
            p1 = "2";
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(dr)){
            regEndereco = true;
            p1 = "3";
            //kendy, guardar endereço
        }
        else if (p1.equalsIgnoreCase(a)){
            registrador = true;
            p1 = "0";
        }
        else if (p1.equalsIgnoreCase(b)){
            registrador = true;
            p1 = "1";
        }
        else if (p1.equalsIgnoreCase(c)){
            registrador = true;
            p1 = "2";
        }
        else if (p1.equalsIgnoreCase(d)){
            registrador = true;
            p1 = "3";
        }
        else if(Hexa.isHexa(p1))
        {}
        else{
            endereco = true;
            p1 = seeInsideAddress(p1);
        }
        mem.adicionaDireto(enderecoP1, p1);

     /*________________________________________________________________ */
     
     boolean regEndereco2 = false; // verifica se eh endereco de registrador
     boolean registrador2 = false;
     boolean endereco2 = false;
     boolean constante2 = false;
     
     if(!p2.equalsIgnoreCase("")){
     
        if (p2.equalsIgnoreCase(ar)){
            regEndereco2 = true;
            p2 = "0";
        }
                
        else if (p2.equalsIgnoreCase(br)){
            regEndereco2 = true;
            p2 = "1";
        }
                
        else if (p2.equalsIgnoreCase(cr)){
            regEndereco2 = true;
            p2 = "2";
        }
                
        else if (p2.equalsIgnoreCase(dr)){
            regEndereco2 = true;
            p2 = "3";
        }
        else if (p2.equalsIgnoreCase(a)){
            registrador2 = true;
            p2 = "0";
        }
        
        else if (p2.equalsIgnoreCase(b)){
            registrador2 = true;
            p2 = "1";
        }
        
        else if (p2.equalsIgnoreCase(c)){
            registrador2 = true;
            p2 = "2";
        }
            
        else if (p2.equalsIgnoreCase(d)){
            registrador2 = true;
            p2 = "3";
        }
        else if(Hexa.isHexa(p2)){
            constante2 = true;
        }
        else{
            endereco2 = true;
            p2 = seeInsideAddress(p2);
        }
        mem.adicionaDireto(enderecoP2, p2);
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

            // if(p1.equalsIgnoreCase(b)) opCode = "01" + opCode;
            // else if(p1.equalsIgnoreCase(c)) opCode = "10" + opCode;
            // else if(p1.equalsIgnoreCase(d)) opCode = "11" + opCode;
            // else opCode = "00" + opCode;

            // if(p2.equalsIgnoreCase(b)) opCode = "01" + opCode;
            // else if(p2.equalsIgnoreCase(c)) opCode = "10" + opCode;
            // else if(p2.equalsIgnoreCase(d)) opCode = "11" + opCode;
            // else opCode = "00" + opCode;
            
            System.out.println("Colocando " + opCode + " como " + Hexa.binToString(opCode) + " em " + enderecoOpcode);
            mem.adicionaDireto(enderecoOpcode, Hexa.binToString(opCode));

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
