/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.service.EstandeService;
import br.net.localizae.webservice.converter.JsonConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@RestController
public class EstandeController {
    @RequestMapping(value="estande", method = RequestMethod.GET)
    public List<Estande> readByCriteria(String titulo, String curso, Long periodo, Long numero, String areaTematica, Long usuario, Long limit, Long offset){
        List<Estande> estandeList = null;
        
        EstandeService service = new EstandeService();
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.AREATEMATICA_EQ, areaTematica);
        criteria.put(EstandeCriteria.CURSO_EQ, curso);
        criteria.put(EstandeCriteria.NUMERO_EQ, numero);
        criteria.put(EstandeCriteria.PERIODO_EQ, periodo);
        criteria.put(EstandeCriteria.TITULO_EQ, titulo);
        criteria.put(EstandeCriteria.USUARIO_FK_EQ, usuario);
        
        
        try {
            estandeList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estandeList;
    }
    
    @RequestMapping(value="estande/{id}", method = RequestMethod.GET)
    public Estande readById(@PathVariable Long id){
        Estande estande = null;
        
        EstandeService service = new EstandeService();
        try {
            estande = service.readById(id);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return estande;
    }
    
    @RequestMapping(value="estande", method = RequestMethod.PUT)
    public Estande update(String json){
        Estande estande = (Estande)JsonConverter.convertFromJson(json, Estande.class);
        
        EstandeService service = new EstandeService();
        try {
            service.update(estande);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estande;
    }
    
    @RequestMapping(value="estande/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        EstandeService service = new EstandeService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
;
    }
    
    @RequestMapping(value="estande", method = RequestMethod.POST)
    public Estande create(String json){
        Estande estande = (Estande)JsonConverter.convertFromJson(json, Estande.class);
        
        EstandeService service = new EstandeService();
        
        try {
            service.create(estande);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estande;
    }
}