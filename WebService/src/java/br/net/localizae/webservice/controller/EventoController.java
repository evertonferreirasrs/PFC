/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.EventoCriteria;
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.service.EventoService;
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
public class EventoController {
    @RequestMapping(value = "evento", method = RequestMethod.GET)
    public ResponseEntity readByCriteria(String nome, String endereco, Long limit, Long offset){
        List<Evento> eventoList = null;
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EventoCriteria.NOME_ILIKE, nome);
        criteria.put(EventoCriteria.ENDERECO_ILIKE, endereco);
        
        EventoService service = new EventoService();
        try {
            eventoList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(eventoList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "evento/{id}", method = RequestMethod.GET)
    public ResponseEntity readById(@PathVariable Long id){
        Evento evento = null;
        EventoService service = new EventoService();
        try {
            evento = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(evento, HttpStatus.OK);
    }
    
    @RequestMapping(value = "evento/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        EventoService service = new EventoService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "evento", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody String json){
        Evento evento = (Evento)JsonConverter.convertFromJson(json, Evento.class);
        
        EventoService service = new EventoService();
        try {
            service.update(evento);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(evento, HttpStatus.OK);
    }
    
    @RequestMapping(value = "evento", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String json){
        Evento evento = (Evento)JsonConverter.convertFromJson(json, Evento.class);
        
        EventoService service = new EventoService();
        try {
            service.create(evento);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(evento, HttpStatus.OK);
    }
    
    @RequestMapping(value="evento", method=RequestMethod.PATCH)
    public ResponseEntity updatePartial(@RequestBody String json){
        Evento evento = (Evento)JsonConverter.convertFromJson(json, Evento.class);
        
        EventoService service = new EventoService();
        try {
            service.updatePartial(evento);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(evento, HttpStatus.OK);
    }
}
