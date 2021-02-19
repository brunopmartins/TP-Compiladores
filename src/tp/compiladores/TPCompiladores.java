/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.compiladores;

import java.io.IOException;
import tp.compiladores.analisadorlexico.Lexeme;
import tp.compiladores.analisadorlexico.LexicalAnalysis;
import tp.compiladores.analisadorlexico.LexicalException;
import tp.compiladores.analisadorlexico.SymbolTable;
import tp.compiladores.analisadorlexico.Token;

/**
 *
 * @author damac
 */
public class TPCompiladores {

    /**
     * @param args the command line arguments
     * @throws tp.compiladores.analisadorlexico.LexicalException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, LexicalException{
//        if (args.length != 1) {
//            System.out.println("Usage: java TPCompiladores [Source file]");
//        }
        LexicalAnalysis la;
        
        // O código a seguir é usado apenas para testar o analisador léxico.
        // TODO: depois de pronto, comentar o código abaixo.
        try {
            la = new LexicalAnalysis("test.txt");
            
            Lexeme lex = new Lexeme();
        
            do{
                try{
                    lex = la.nextToken();
                    System.out.printf("(\"%s\", %s)\n", lex.token, lex.type);
                }
                catch(LexicalException e){
                    System.out.println(e.getMessage()); 
                } 
                catch(IOException e){
                     System.out.println(e.getMessage()); 
                }
            }
            while (checkType((lex).type));
        } catch (Exception e) {
            System.err.println("Internal error: " + e.getMessage());
        }
        
    }
    
    private static boolean checkType(Token type) {
        return !(type == Token.EOF);
    }    
}
