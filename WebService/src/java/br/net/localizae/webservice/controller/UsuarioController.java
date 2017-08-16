package br.net.localizae.webservice.controller;


import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.Usuario;
import br.com.localizae.model.service.UsuarioService;
import br.net.localizae.webservice.converter.JsonConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @RequestMapping(value = "usuario", method = RequestMethod.GET)
    public List<Usuario> readByCriteria(String nome, String email, String senha, Long tipoUsuario, String situacao, Long limit, Long offset){
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
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuarioList;
    }
    
    @RequestMapping(value="usuario/{id}", method = RequestMethod.GET)
    public Usuario readById(@PathVariable Long id){
        Usuario usuario = null;
        
        UsuarioService service = new UsuarioService();
        
        try {
            usuario = service.readById(id);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    @RequestMapping(value="usuario/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        UsuarioService service = new UsuarioService();
        
        try {
            service.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping (value="usuario", method = RequestMethod.PUT)
    public Usuario update(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.update(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    @RequestMapping (value="usuario", method = RequestMethod.POST)
    public Usuario create(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.create(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    @RequestMapping(value="usuario", method=RequestMethod.PATCH)
    public Usuario updatePartial(@RequestBody String json){
        Usuario usuario = (Usuario)JsonConverter.convertFromJson(json, Usuario.class);
        
        UsuarioService service = new UsuarioService();
        
        try {
            service.updatePartial(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
}
