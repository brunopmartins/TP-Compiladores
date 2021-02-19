/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.compiladores.utils;

/**
 *
 * @author damac
 */
public class Utils {
    public static boolean isLetter(int c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z');
    }
    
    public static boolean isDigit(int c) {
        return (c >= '0' && c <= '9');
    }
}
