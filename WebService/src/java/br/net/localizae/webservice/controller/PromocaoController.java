/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.PromocaoCriteria;
import br.com.localizae.model.entity.Promocao;
import br.com.localizae.model.service.PromocaoService;
import br.net.localizae.webservice.converter.JsonConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class PromocaoController {
    @RequestMapping(value = "promocao", method = RequestMethod.GET)
    public ResponseEntity get(Long estande, String nome, Long data, String areaTematica, Long limit, Long offset){
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(PromocaoCriteria.ESTANDE_EQ, estande);
        criteria.put(PromocaoCriteria.AREA_TEMATICA_EQ, areaTematica);
        criteria.put(PromocaoCriteria.DATA_EQ, data);
        criteria.put(PromocaoCriteria.NOME_EQ, nome);
        
        PromocaoService service = new PromocaoService();
        List<Promocao> promocaoList = null;
        
        try {
            promocaoList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(promocaoList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "promocao/{id}", method = RequestMethod.GET)
    public ResponseEntity getId(@PathVariable("id") Long id){        
        PromocaoService service = new PromocaoService();
        Promocao promocao = null;
        
        try {
            promocao = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(promocao, HttpStatus.OK);
    }
    
    @RequestMapping(value = "promocao", method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody String json){
        try{
            Promocao promocao = (Promocao) JsonConverter.convertFromJson(json, Promocao.class);
            
            PromocaoService service = new PromocaoService();
            service.create(promocao);
            
            return new ResponseEntity(promocao, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "promocao", method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody String json){
        try{
            Promocao promocao = (Promocao) JsonConverter.convertFromJson(json, Promocao.class);
            
            PromocaoService service = new PromocaoService();
            service.update(promocao);
            
            return new ResponseEntity(promocao, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "promocao", method = RequestMethod.PATCH)
    public ResponseEntity patch(@RequestBody String json){
        try{
            Promocao promocao = (Promocao) JsonConverter.convertFromJson(json, Promocao.class);
            
            PromocaoService service = new PromocaoService();
            service.updatePartial(promocao);
            
            return new ResponseEntity(promocao, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "promocao/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id){
        PromocaoService service = new PromocaoService();
        
        try{
            service.delete(id);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
