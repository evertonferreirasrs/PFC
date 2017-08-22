/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.AvaliacaoVisitanteCriteria;
import br.com.localizae.model.entity.AvaliacaoVisitante;
import br.com.localizae.model.service.AvaliacaoVisitanteService;
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
public class AvaliacaoVisitanteController {

    @RequestMapping(value = "avaliacaoVisitante", method = RequestMethod.GET)
    public ResponseEntity readByCriteria(Long nota, Long usuario, Long estande, Long limit, Long offset) {
        List<AvaliacaoVisitante> avaliacaoVisitanteList = null;

        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.ESTANDE_EQ, estande);
        criteria.put(AvaliacaoVisitanteCriteria.NOTA_EQ, nota);
        criteria.put(AvaliacaoVisitanteCriteria.USUARIO_EQ, usuario);

        try {
            avaliacaoVisitanteList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoVisitanteList, HttpStatus.OK);
    }

    @RequestMapping(value = "avaliacaoVisitante/{id}", method = RequestMethod.GET)
    public ResponseEntity readById(@PathVariable Long id) {
        AvaliacaoVisitante avaliacaoVisitante = null;

        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();
        try {
            avaliacaoVisitante = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoVisitante, HttpStatus.OK);
    }

    @RequestMapping(value = "avaliacaoVisitante", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody String json) {
        AvaliacaoVisitante avaliacaoVisitante = (AvaliacaoVisitante) JsonConverter.convertFromJson(json, AvaliacaoVisitante.class);

        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();
        try {
            service.update(avaliacaoVisitante);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoVisitante, HttpStatus.OK);
    }

    @RequestMapping(value = "avaliacaoVisitante/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();

        try {
            service.delete(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "avaliacaoVisitante", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String json) {
        AvaliacaoVisitante avaliacaoVisitante = (AvaliacaoVisitante) JsonConverter.convertFromJson(json, AvaliacaoVisitante.class);

        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();

        try {
            service.create(avaliacaoVisitante);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoVisitante, HttpStatus.OK);
    }
    
    @RequestMapping(value="avaliacaoVisitante", method=RequestMethod.PATCH)
    public ResponseEntity updatePartial(@RequestBody String json){
        AvaliacaoVisitante avaliacaoVisitante = (AvaliacaoVisitante) JsonConverter.convertFromJson(json, AvaliacaoVisitante.class);

        AvaliacaoVisitanteService service = new AvaliacaoVisitanteService();

        try {
            service.updatePartial(avaliacaoVisitante);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(avaliacaoVisitante, HttpStatus.OK);
    }
}
