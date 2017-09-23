/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.EstatisticaCriteria;
import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.Estatistica;
import br.com.localizae.model.entity.Usuario;
import br.com.localizae.model.service.EstatisticaService;
import br.com.localizae.model.service.UsuarioService;
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
public class EstatisticaController {

    @RequestMapping(value = "estatistica/numberVisitors", method = RequestMethod.GET)
    public ResponseEntity readByCriteria() {
        List<Usuario> usuarioList = null;
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.TIPO_USUARIO_EQ, 2l);
        UsuarioService service = new UsuarioService();
        try {
            usuarioList = service.readByCriteria(criteria, 0l, 0l);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(usuarioList.size(), HttpStatus.OK);
    }

    @RequestMapping(value = "estatistica", method = RequestMethod.GET)
    public ResponseEntity get(Long posicaoX, Long posicaoY, Long usuario, Long datahora_eq, Long datahora_lt, Long datahora_gt, Long limit, Long offset) {
        List<Estatistica> estatisticaList = null;

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstatisticaCriteria.POSX_EQ, posicaoX);
        criteria.put(EstatisticaCriteria.POSY_EQ, posicaoY);
        criteria.put(EstatisticaCriteria.DATAHORA_EQ, datahora_eq);
        criteria.put(EstatisticaCriteria.DATAHORA_LT, datahora_lt);
        criteria.put(EstatisticaCriteria.DATAHORA_GT, datahora_gt);

        EstatisticaService service = new EstatisticaService();

        try {
            estatisticaList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(estatisticaList, HttpStatus.OK);
    }

    @RequestMapping(value = "estatistica/count", method = RequestMethod.GET)
    public ResponseEntity count(Long posicaoX_lt, Long posicaoX_gt,Long posicaoX, Long posicaoY_lt, Long posicaoY_gt, Long posicaoY, Long usuario, Long datahora_eq, Long datahora_lt, Long datahora_gt, Long limit, Long offset) {
        try {
            List<Estatistica> estatisticaList = null;

            Map<Enum, Object> criteria = new HashMap<>();
            criteria.put(EstatisticaCriteria.POSX_EQ, posicaoX);
            criteria.put(EstatisticaCriteria.POSX_LT, posicaoX_lt);
            criteria.put(EstatisticaCriteria.POSX_GT, posicaoX_gt);
            criteria.put(EstatisticaCriteria.POSY_EQ, posicaoY);
            criteria.put(EstatisticaCriteria.POSY_LT, posicaoY_lt);
            criteria.put(EstatisticaCriteria.POSY_GT, posicaoY_gt);
            criteria.put(EstatisticaCriteria.DATAHORA_EQ, datahora_eq);
            criteria.put(EstatisticaCriteria.DATAHORA_LT, datahora_lt);
            criteria.put(EstatisticaCriteria.DATAHORA_GT, datahora_gt);

            EstatisticaService service = new EstatisticaService();
            estatisticaList = service.readByCriteria(criteria, limit, offset);
            
            return new ResponseEntity(estatisticaList.size(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
