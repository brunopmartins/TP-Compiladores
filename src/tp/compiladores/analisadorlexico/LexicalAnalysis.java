/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.compiladores.analisadorlexico;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import tp.compiladores.utils.Utils;

/**
 *
 * @author damac
 */
public class LexicalAnalysis {
    private int line;
    private SymbolTable st;
    private PushbackInputStream input;
    
    public LexicalAnalysis(String filename) throws LexicalException {
        try {
            input = new PushbackInputStream(new FileInputStream(filename));
        } catch (Exception e) {
            throw new LexicalException("Error opening file " + filename);
        }
        st = new SymbolTable();
        line = 1;
    }

    public int getLine() {
        return line;
    }
    
    public Lexeme nextToken() throws IOException,LexicalException {
        int estado = 1; //estado inicial
        Lexeme lex = new Lexeme("", Token.EOF);
        int c;
        while(estado != 0 && estado != 99) {
            c = input.read();
            switch(estado) {
                case 1: // estado inicial
                    if (c == ' ' || c == '\t' || c == '\r') {
                        continue;
                    }
                    else if (c == '\n') {
                        line++;
                        continue;
                    }
                    else if (c == '(' || c == ')' || c == ',' ||  c == ';' || c == '+' || c == '-' || c == '*' || c == '/') {
                        lex.token += (char) c;
                        estado = 0;
                    }
                    else if(c == '=' ) {
                        lex.token += (char) c;
                        estado = 0;
                    }
                    else if (c == '>') {
                        lex.token += (char) c;
                        estado = 3;
                    }
                    else if (c == '<') {
                        lex.token += (char) c;
                        estado = 4;
                    }
                    else if (Utils.isDigit(c)) {
                        lex.type = Token.INTEGER_CONST;
                        lex.token += (char) c;
                        estado = 5;
                    }
                    else if (Utils.isLetter(c)) {
                        lex.token += (char) c;
                        estado = 8;
                    }
                    else if (c == '\"') {
                        lex.type = Token.LITERAL;
                        estado = 11;
                    }
                    else if (c == ':' )
                    {
                        lex.token += (char) c;
                        estado = 2;
                    }
                    else if (c == '%') {
                        estado = 9;
                    }
                    else if (c == -1 || c == 255) {
                        estado = 99; //final
                    } else {
                        throw new LexicalException("Unexpected caracter '" + (char) c + "' in line: " + line + "." );
                    }
                    break;
                case 2:
                    if (c == '=' ) {
                        lex.token += (char) c;
                    }
                    else {
                        throw new LexicalException("Error at line: " + line + ". Assign missing the =" );
                    }
                    estado = 0; //final
                    break;
                case 3: // >
                    if (c == '=' || c == '>') {
                        lex.token += (char) c;
                    }
                    else if (c != -1) {
                        input.unread(c);
                    }
                    estado = 0; //final
                    break;
                case 4: // <
                    if (c == '>' || c == '=') {
                        lex.token += (char) c;
                    }
                    else if (c != -1) {
                        input.unread(c);
                    }
                    estado = 0; //final
                    break;
                case 5: //numero inteiro
                    if (Utils.isDigit(c)) {
                        lex.token += (char) c;
                        estado = 5;
                    }
                    else if (c == '.') {
                        lex.token += (char) c;
                        estado = 6;
                    }
                    else {
                        if (c != -1) {
                            input.unread(c);
                        }
                        estado = 99; //final
                    }
                    break;
                case 6: //numero float
                    if (Utils.isDigit(c)) {
                        lex.token += (char) c;
                        lex.type = Token.REAL_CONST;
                        estado = 7; //float valido
                    }
                    else {
                        throw new LexicalException("Error at line: " + line + ". Float number must have at least 1 decimal point" );
                    }
                    break;
                case 7: //numero float valido
                    if (Utils.isDigit(c)) {
                        lex.token += (char) c;
                        estado = 7;
                    }
                    else {
                        if (c != -1) {
                            input.unread(c);
                        }
                        estado = 99; //final
                    }
                    break;
                case 8: //letra
                    if (!Utils.isLetter(c) && !Utils.isDigit(c) && c != '_') {
                        input.unread(c);
                        estado = 0; //final
                    }
                    else {
                        lex.token += (char) c;
                        estado = 8;
                    }
                    break;
                case 9:
                    if (c == -1) {
                        throw new LexicalException("Unexpected EOF in line: " + line + ". Expecting % " );
                    }
                    if (c == '%') {
                        estado = 1;
                    }
                    else {
                        if (c == '\n') {
                            line++;
                        }
                        estado = 9;
                    }
                    break;
                case 11: //string
                    if (c == -1) {
                        throw new LexicalException("Unexpected EOF in line: " + line + ". Expecting \" " );
                    }
                    else if (c != '\"') {
                        lex.token += (char) c;
                        if (c == '\n') {
                            line++;
                        }
                        estado = 11;
                    }
                    else {
                        estado = 99; //final
                    }
                    break;
            }
        }
        
        if (estado == 0) {            
            if (st.contains(lex.token)) {
                lex.type = st.find(lex.token);
            }
            else {
                lex.type = Token.IDENTIFIER;
            }
        }
        
        return lex;
    }
}
