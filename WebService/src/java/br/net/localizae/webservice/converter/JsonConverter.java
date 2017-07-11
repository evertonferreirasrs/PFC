/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author marca
 */
public class JsonConverter {
    public static Object convertFromJson(String json, Class clazz){
        Gson gson = new GsonBuilder().create();
        
        Object entity = gson.fromJson(json, clazz);
        
        return entity;
    }
}
