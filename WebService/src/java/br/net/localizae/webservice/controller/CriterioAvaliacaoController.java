/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.CriterioAvaliacaoCriteria;
import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.service.CriterioAvaliacaoService;
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
public class CriterioAvaliacaoController {
    @RequestMapping(value="criterio", method = RequestMethod.GET)
    public List<CriterioAvaliacao> readByCriteria(String nome, Long peso, Long limit, Long offset){
        List<CriterioAvaliacao> criterioAvaliacaoList = null;
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.NOME_EQ, nome);
        criteria.put(CriterioAvaliacaoCriteria.PESO_EQ, peso);
        
        try {
            criterioAvaliacaoList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return criterioAvaliacaoList;
    }
    
    @RequestMapping(value="criterio/{id}", method = RequestMethod.GET)
    public CriterioAvaliacao readById(@PathVariable Long id){
        CriterioAvaliacao criterio = null;
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        try {
            criterio = service.readById(id);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return criterio;
    }
    
    @RequestMapping(value="criterio", method = RequestMethod.PUT)
    public CriterioAvaliacao update(String json){
        CriterioAvaliacao criterio = (CriterioAvaliacao)JsonConverter.convertFromJson(json, CriterioAvaliacao.class);
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        try {
            service.update(criterio);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return criterio;
    }
    
    @RequestMapping(value="criterio/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
;
    }
    
    @RequestMapping(value="criterio", method = RequestMethod.POST)
    public CriterioAvaliacao create(String json){
        CriterioAvaliacao criterio = (CriterioAvaliacao)JsonConverter.convertFromJson(json, CriterioAvaliacao.class);
        
        CriterioAvaliacaoService service = new CriterioAvaliacaoService();
        
        try {
            service.create(criterio);
        } catch (Exception ex) {
            Logger.getLogger(EstandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return criterio;
    }
}
