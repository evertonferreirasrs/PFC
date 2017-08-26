/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.AvaliacaoJuradoCriteria;
import br.com.localizae.model.entity.AvaliacaoJurado;
import br.com.localizae.model.service.AvaliacaoJuradoService;
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
public class AvaliacaoJuradoController {

    @RequestMapping(value = "avaliacaoJurado", method = RequestMethod.GET)
    public ResponseEntity getAll(Long usuario, Long nota, Long estande, Long criterio, Long limit, Long offset) {
        List<AvaliacaoJurado> avaliacaoList = null;

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.CRITERIO_EQ, criterio);
        criteria.put(AvaliacaoJuradoCriteria.ESTANDE_EQ, estande);
        criteria.put(AvaliacaoJuradoCriteria.USUARIO_EQ, nota);
        criteria.put(AvaliacaoJuradoCriteria.USUARIO_EQ, usuario);

        try {
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            avaliacaoList = service.readByCriteria(criteria, limit, offset);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoList, HttpStatus.OK);
    }

    @RequestMapping(value = "avaliacaoJurado/{id}", method = RequestMethod.GET)
    public ResponseEntity getId(@PathVariable Long id) {
        AvaliacaoJurado avaliacao = null;

        try {
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            avaliacao = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    
    @RequestMapping(value = "avaliacaoJurado", method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody String json){
        AvaliacaoJurado avaliacao = (AvaliacaoJurado) JsonConverter.convertFromJson(json, AvaliacaoJurado.class);
        
        try{
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            service.update(avaliacao);
            
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    
    @RequestMapping(value = "avaliacaoJurado", method = RequestMethod.PATCH)
    public ResponseEntity patch(@RequestBody String json){
        AvaliacaoJurado avaliacao = (AvaliacaoJurado) JsonConverter.convertFromJson(json, AvaliacaoJurado.class);
        
        try{
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            service.updatePartial(avaliacao);
            
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    
    @RequestMapping(value = "avaliacaoJurado", method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody String json){
        AvaliacaoJurado avaliacao = (AvaliacaoJurado) JsonConverter.convertFromJson(json, AvaliacaoJurado.class);
        
        try{
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            service.create(avaliacao);
            
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    
    @RequestMapping(value = "avaliacaoJurado/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        try{
            AvaliacaoJuradoService service = new AvaliacaoJuradoService();
            service.delete(id);
        }catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
