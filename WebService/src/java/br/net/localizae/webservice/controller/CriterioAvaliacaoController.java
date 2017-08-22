/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.CriterioAvaliacaoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.service.CriterioAvaliacaoService;
import br.net.localizae.webservice.converter.JsonConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@RestController
public class CriterioAvaliacaoController {
    @RequestMapping(value="criterio", method = RequestMethod.GET)
    public ResponseEntity readByCriteria(String nome, Long peso, Long limit, Long offset){
        List<CriterioAvaliacao> criterioAvaliacaoList = null;
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.NOME_EQ, nome);
        criteria.put(CriterioAvaliacaoCriteria.PESO_EQ, peso);
        
        try {
            criterioAvaliacaoList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(criterioAvaliacaoList, HttpStatus.OK);
    }
    
    @RequestMapping(value="criterio/{id}", method = RequestMethod.GET)
    public ResponseEntity readById(@PathVariable Long id){
        CriterioAvaliacao criterio = null;
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        try {
            criterio = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(criterio, HttpStatus.OK);
    }
    
    @RequestMapping(value="criterio", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody String json){
        CriterioAvaliacao criterio = (CriterioAvaliacao)JsonConverter.convertFromJson(json, CriterioAvaliacao.class);
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        try {
            service.update(criterio);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(criterio, HttpStatus.OK);
    }
    
    @RequestMapping(value="criterio/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="criterio", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String json){
        CriterioAvaliacao criterio = (CriterioAvaliacao)JsonConverter.convertFromJson(json, CriterioAvaliacao.class);
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        
        try {
            service.create(criterio);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(criterio, HttpStatus.OK);
    }
    
    @RequestMapping(value="criterio", method=RequestMethod.PATCH)
    public ResponseEntity updatePartial(@RequestBody String json){
        CriterioAvaliacao criterio = (CriterioAvaliacao)JsonConverter.convertFromJson(json, CriterioAvaliacao.class);
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        
        try {
            service.updatePartial(criterio);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(criterio, HttpStatus.OK);
    }
}
