/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

import br.com.localizae.model.entity.File;
import br.com.localizae.model.service.FileServiceRemote;
import br.com.localizae.model.service.PromocaoService;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author marca
 */
public class Main {

    public static void main(String[] args) throws Exception {
        FileServiceRemote fs = new FileServiceRemote();

        java.io.File file = new java.io.File("C:\\Users\\marca\\Downloads\\imagem.jpg");
        InputStream input = new FileInputStream(file);
                
        byte[] byteArray = IOUtils.toByteArray(input);

        String base64 = Base64.encodeBase64String(byteArray);
        
        File fileToUpload = new File();
        fileToUpload.setBase64("");
        
        
        PromocaoService service = new PromocaoService();
//        service.create(promocao);
        
        
        fs.upload(fileToUpload);

//try {
//            byte[] name = Base64.getEncoder().encode("hello World".getBytes());
//            byte[] decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
//            System.out.println(new String(decodedString));
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        
    }
}
