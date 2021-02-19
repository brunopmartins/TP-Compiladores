/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.compiladores.analisadorlexico;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author damac
 */
public class SymbolTable {
    private Map<String, Token> st;
    
    public SymbolTable() {
        st = new HashMap<String, Token>();
        
        // PALAVRAS RESERVADAS
        st.put("stop", Token.STOP);
        st.put("is", Token.IS);
        st.put("init", Token.INIT);
        st.put("begin", Token.BEGIN);
        st.put("end", Token.END);
        st.put("integer", Token.INTEGER);
        st.put("real", Token.REAL);
        st.put("string", Token.STRING);
        st.put("if", Token.IF);
        st.put("else", Token.ELSE);
        st.put("while", Token.WHILE);
        st.put("do", Token.DO);
        st.put("or", Token.OR);
        st.put("and", Token.AND);
        st.put("read", Token.READ);
        st.put("write", Token.WRITE);

        
        // SIMBOLOS
        st.put(":=", Token.ASSIGN);
        st.put(",", Token.COMMA);
        st.put("(", Token.LEFT_PARENTHESIS);
        st.put(")", Token.RIGHT_PARENTHESIS);
        st.put(";", Token.SEMICOLON);
        st.put("not", Token.NOT);
        st.put("=", Token.EQUAL);
        st.put("<>", Token.DIFF);
        st.put("<", Token.LOWER);
        st.put(">", Token.GREATER);
        st.put("<=", Token.LOWER_EQUAL);
        st.put(">=", Token.GREATER_EQUAL);
        st.put("+", Token.ADD);
        st.put("-", Token.SUB);
        st.put("*", Token.MUL);
        st.put("/", Token.DIV);
    }
    
    public boolean contains(String token) {
        return st.containsKey(token);
    }
    
    public Token find(String token) {
        return this.contains(token) ? st.get(token) : Token.INVALID;
    }
    
}
