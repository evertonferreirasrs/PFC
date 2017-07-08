/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.converter;

import com.google.gson.Gson;

/**
 *
 * @author marca
 */
public class JsonConverter {
    public static Object convertFromJson(String json, Class clazz){
        Gson gson = new Gson();
        
        Object entity = gson.fromJson(json, clazz);
        
        return entity;
    }
}
