/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unime.math.functioneasy.util;

import java.text.DecimalFormat;

/**
 *
 * @author Mauricio Barbosa
 */
public class NumberUtils {
    public static boolean isNumber(String value) {
        try {
            Double a = Double.valueOf(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public static String FormatDecimal(Double value, Integer digitsAfterDot) {
        String pattern = "##.";
        for(int i = 0; i < digitsAfterDot; i++) {
            pattern += "#";
        }
        return new DecimalFormat(pattern).format(value);
    }
}
