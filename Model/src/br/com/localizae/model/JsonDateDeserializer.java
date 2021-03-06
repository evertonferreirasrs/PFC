package br.com.localizae.model;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Date;

public class JsonDateDeserializer implements JsonDeserializer<Date> {
   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      String s = json.getAsJsonPrimitive().getAsString();
      long l = Long.parseLong(s.substring(6, s.length() - 2));
      Date d = new Date(l);
      return d; 
   } 

    @Override
    public Date deserialize(JsonElement json, java.lang.reflect.Type type, JsonDeserializationContext jdc) throws JsonParseException {
        String s = json.getAsJsonPrimitive().getAsString();
      long l = Long.parseLong(s.substring(6, s.length() - 2));
      Date d = new Date(l);
      return d;
    }
}