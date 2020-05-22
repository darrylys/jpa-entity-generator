/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

/**
 *
 * @author darryl
 */
public class StringUtils {
    
    public static boolean isBlank(String s) {
        return s == null || s.trim().equals("");
    }
    
}
