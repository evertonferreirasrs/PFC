/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.Usuario;
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
    public ResponseEntity readByCriteria(){
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
}
