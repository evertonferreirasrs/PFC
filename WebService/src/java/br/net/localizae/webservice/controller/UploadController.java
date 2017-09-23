/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.entity.File;
import br.com.localizae.model.service.FileServiceLocal;
import br.net.localizae.webservice.converter.JsonConverter;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UploadController {
    
    @RequestMapping(value = "imagem", method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody String json) throws IOException{
        File file = (File) JsonConverter.convertFromJson(json, File.class);
        
        FileServiceLocal service = new FileServiceLocal();
        try {
            service.upload(file);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "imagem", method = RequestMethod.GET, produces = "image/jpg")
    public ResponseEntity getId(Long id, String uri) throws IOException{
        byte[] file = null;
        
        FileServiceLocal service = new FileServiceLocal();
        try {
            if(id != null && id > 0){
                file = service.download(id);
            }else if(uri != null && !uri.isEmpty()){
                file = service.download(uri);
            }else{
                throw new IllegalArgumentException("É preciso fornecer id ou uri da imagem pesquisada.");
            }
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(file, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "imagem/{id}", method = RequestMethod.GET, produces = "image/jpg")
    public ResponseEntity getUri(@PathVariable("id") Long id) throws IOException{
        byte[] file = null;
        
        FileServiceLocal service = new FileServiceLocal();
        try {
            file = service.download(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(file, HttpStatus.ACCEPTED);
    }
}
