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
public enum Token {
    // PALAVRAS RESERVADAS
    INIT,
    STOP,
    IS,
    IF,
    BEGIN, 
    END,
    ELSE, 
    DO,
    WHILE,
    READ,
    WRITE,
    INTEGER,
    STRING, 
    REAL,
    
    // SIMBOLOS
    ASSIGN, // := 
    COMMA, // , 
    LEFT_PARENTHESIS, // ( 
    RIGHT_PARENTHESIS, // )
    LEFT_SHIFT, // << 
    RIGHT_SHIFT, // >>
    COLON, // :
    SEMICOLON, // ;
    NOT, // !
    AND, // &&
    OR, // ||
    EQUAL, // ==
    DIFF, // <>
    LOWER, // <
    GREATER, // >
    LOWER_EQUAL, // <=
    GREATER_EQUAL, // >=
    ADD, // +
    SUB, // -
    MUL, // *
    DIV, // /
    
    IDENTIFIER,
    INTEGER_CONST,
    REAL_CONST,
    LITERAL,
    
    // INVALID TOKEN
    INVALID,
    
    //END OF FILE
    EOF,
}
