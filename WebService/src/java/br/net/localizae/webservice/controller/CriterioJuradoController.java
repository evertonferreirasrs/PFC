/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.CriterioJuradoCriteria;
import br.com.localizae.model.entity.CriterioJurado;
import br.com.localizae.model.service.CriterioJuradoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@RestController
public class CriterioJuradoController {
    @RequestMapping(value = "criterioJurado", method = RequestMethod.GET)
    public ResponseEntity get(Long usuario, Long estande, Long criterio){
        List<CriterioJurado> criterioJuradoList = null;
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioJuradoCriteria.CRITERIO_EQ, criterio);
        criteria.put(CriterioJuradoCriteria.ESTANDE_EQ, estande);
        criteria.put(CriterioJuradoCriteria.USUARIO_EQ, usuario);
        
        CriterioJuradoService service = new CriterioJuradoService();
        try {
            criterioJuradoList = service.readByCriteria(criteria, null, null);
            return new ResponseEntity(criterioJuradoList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
