/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.compiladores.analisadorlexico;

/**
 *
 * @author damac
 */
public class Lexeme {
    public String token;
    public Token type;
    
    public Lexeme(String token, Token type) {
        this.token = token;
        this.type = type;
    }
    public Lexeme() {
        this.token = null;
        this.type = null;
    }
}
