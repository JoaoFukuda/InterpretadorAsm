// Pega o código do professor e transforma em opcodes para ser interpretado pelo programa
// Utiliza somente métidos static
package src.auxi;

import java.io.*;
import java.util.Scanner;

import src.Memory;

public class Compilator {
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
        /*Aparentemente p1 nunca serah uma constante*/
     boolean endereco= true; // eu poderia assumir que endereco eh registrador= false; mas acho que assim fica menos confuso
     boolean registrador= false;
     boolean regEndereco= false; // verifica se eh registrador de endereco
     /* Kendy, se esse endereço tem que guardar tem que ter um jeito de saber
     qual que é o número, eu não sei direito  */
     String ar= "[AX]";       
            if (p1.equals(ar)){
                regEndereco = true;
                endereco=false;
                //kendy, guardar endereço 
            }
            String br= "[BX]";       
            if (p1.equals(br)){
                regEndereco = true;
                endereco=false;
                //kendy, guardar endereço
            }
            String cr= "[CX]";       
            if (p1.equals(cr)){
                regEndereco = true;
                endereco=false;
                //kendy, guardar endereço
            }
            String dr= "[DX]";       
            if (p1.equals(dr)){
                regEndereco = true;
                endereco=false;
                //kendy, guardar endereço
            }
     
     String a= "AX";       
            if (p1.equals(a)){
                registrador = true;
                endereco=false;
            }
            String b= "BX";       
            if (p1.equals(b)){
                registrador = true;
                endereco=false;
            }
            String c= "CX";       
            if (p1.equals(c)){
                registrador = true;
                endereco=false;
            }
            String d= "DX";       
            if (p1.equals(d)){
                registrador = true;
                endereco=false;
            }
     /*__________________________________________________________________ */
     // aqui verifica se eh constante
     boolean ehConstante2= true;
            try{
             int foi2 = Integer.parseInt(p2);	
             // kendy, guardar foi2, que é o parametro 2 (constante)
             System.out.println(foi2);
            }
            catch(NumberFormatException foi2){
                 ehConstante2 = false;
                 String enderecoEmP2 = seeInsideAddress(p2);
                 // kendy, ou guardar enderecoEmP2 que é o parametro 2 (endereço)
            }
            
     /*________________________________________________________________ */
     
     boolean regEndereco2= false; // verifica se eh endereco de registrador
     boolean registrador2= false;
     boolean endereco2= true;
         
        
            if (p2.equals(ar)){
                regEndereco2 = true;
                endereco2=false;
            }
                   
            if (p2.equals(br)){
                regEndereco2 = true;
                endereco2=false;
            }
                   
            if (p2.equals(cr)){
                regEndereco2 = true;
                endereco2=false;
            }
                   
            if (p2.equals(dr)){
                regEndereco2 = true;
                endereco2 =false;
            }
     
     
            if (p2.equals(a)){
                registrador2 = true;
                endereco2= false;
            }
            
            if (p2.equals(b)){
                registrador2 = true;
                endereco2= false;
            }
         
            if (p2.equals(c)){
                registrador2 = true;
                endereco2= false;
            }
                
            if (p2.equals(d)){
                registrador2 = true;
                endereco2= false;
            }
     /* ______________________________________________________________________________________*/
     //aqui seleciona o opcode
         String opCode="";
           String m="MOV";
           String ad= "ADD";
           String s ="SUB";
           String mu="MUL";
           String dv="DIV";
           String cmp= "CMP";
            if (op.equals(m)){
                if(registrador){	//MOV R1, const
                    opCode="000000";
                } 
                if(regEndereco && registrador2){ //	MOV [R1], R2
                    opCode="000001";
                }

                if(regEndereco && ehConstante2){ //	MOV [R1], const
                    opCode="000010";
                }
                if(endereco && ehConstante2){   //		MOV [const], const
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

            if (op.equals(ad)){
                if(registrador && registrador2){   //		ADD R1, R2
                    opCode="000111";
                }
                if(registrador && ehConstante2){   //	ADD R1, constante
                    opCode="001000";
                }
            }

            if (op.equals(s)){
                if(registrador && registrador2){   //	SUB R1, R2
                    opCode="001001";
                }
                if(registrador && ehConstante2){   //	SUB R1, constante
                    opCode="001010";
                }
            }

            if (op.equals(mu)){
                if(registrador && registrador2){   //		MUL R1, R2
                    opCode="001011";
                }
                if(registrador && ehConstante2){   //	 	MUL R1, constante
                    opCode="001100";
                }
            }

            if (op.equals(dv)){
                if(registrador && registrador2){   //			DIV R1, R2
                   		
                    opCode="001101";
                }
                if(registrador && ehConstante2){   //	 	DIV R1, constante
                    opCode="001110";
                }
            }

            		

            if (op.equals(d)){
                if(registrador && registrador2){   //			MOD R1, R2
                   		
                    opCode="001111";
                }
                if(registrador && ehConstante2){   //	 		MOD R1,constante
                    opCode="010000";
                }
            }   

                   
if (op.equals(cmp)){
    if(registrador && registrador2){   //			CMP R1, R2
               
        opCode="011000";
    }
    if(registrador && ehConstante2){   //          CMP R1, constante
        opCode="011010";
    }
}   
            
               
            
            String jmp="JMP";
            if (op.equals(jmp)){
                opCode="010001";
            }
            String jne="JNE";
            if (op.equals(jne)){
                opCode="010011";
            }
            String je="JE";
            if (op.equals(je)){
                opCode="010010";
            }
            String jg="JG";
            if (op.equals(jg)){
                opCode="010100";
            }
            String jge="JGE";
            if (op.equals(jge)){
                opCode="010101";
            }
            String jl="JL";
            if (op.equals(jl)){
                opCode="010110";
            }
            String jle="JLE";
            if (op.equals(jle)){
                opCode="010111";
            }
         
            // kendy, guardar opCode
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
         System.out.println(operacao);
         
         if (operacao.equals("JE")|| operacao.equals("JG")|| operacao.equals("JL")){
            for ( j= 3; j<comando.length;j++){
                p1+=comando[j];
             }
            
              p2="oi";
         }else{
        int i=4;
       if ( operacao.equals("JLE") ||  operacao.equals("JGE") ||operacao.equals("JMP") ||operacao.equals("JNE") ){
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
        Scanner in = new Scanner(input);

        while(in.hasNextLine())
        {
            String x = in.nextLine();
            separaIssu(x);
        }

        // Colocar os opcodes na memória

        in.close();
    }
}
