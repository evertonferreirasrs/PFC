/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.utils;

import java.security.MessageDigest;

/**
 *
 * @author marca
 */
public class Criptografia {
    public static String criptografarComMD5(String texto){
        String textoCriptografado = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());

            byte[] hash = md.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }

            textoCriptografado = hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return textoCriptografado;
    }
}
