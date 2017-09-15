package br.net.localizae.webservice.controller;


import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.Usuario;
import br.com.localizae.model.service.UsuarioService;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marca
 */
@RestController
public class UsuarioController {
    
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "usuario", method = RequestMethod.GET)
    public ResponseEntity readByCriteria(String nome, String email, String senha, Long tipoUsuario, String situacao, Long limit, Long offset){
        List<Usuario> usuarioList = null;
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, nome);
        criteria.put(UsuarioCriteria.EMAIL_EQ, email);
        criteria.put(UsuarioCriteria.SENHA_EQ, senha);
        criteria.put(UsuarioCriteria.SITUACAO_ILIKE, situacao);
        criteria.put(UsuarioCriteria.TIPO_USUARIO_EQ, tipoUsuario);
        
        UsuarioService service = new UsuarioService();
        
        try {
            usuarioList = service.readByCriteria(criteria, limit, offset);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(usuarioList, HttpStatus.OK);
    }
    
    @RequestMapping(value="usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity readById(@PathVariable Long id){
        Usuario usuario = null;
        
        UsuarioService service = new UsuarioService();
        
        try {
            usuario = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    @RequestMapping(value="usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        UsuarioService service = new UsuarioService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping (value="usuario", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.update(usuario);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    @RequestMapping (value="usuario", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.create(usuario);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    @RequestMapping(value="usuario", method=RequestMethod.PATCH)
    public ResponseEntity updatePartial(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.updatePartial(usuario);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(usuario, HttpStatus.CREATED);
    }
}
