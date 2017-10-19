/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseUsuarioService;
import br.com.localizae.model.dao.UsuarioDAO;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class UsuarioService implements BaseUsuarioService{

    @Override
    public void create(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
        try{
            this.validate(entity);
            dao.create(conn, entity);
            conn.commit();
        }catch(SQLException ex){
            conn.rollback();
            int errorCode = Integer.parseInt(ex.getSQLState());
            if(errorCode == 23505){
                throw new IllegalArgumentException("Este e-mail já está sendo utilizado.");
            }else{
                throw ex;
            }
            
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public void update(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
        try{
            this.validate(entity);
            dao.update(conn, entity);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
        try{
            dao.delete(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = null;
        
        try{
            usuario = dao.readById(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarioList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }
        
        try{
            usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return usuarioList;
    }

    @Override
    public void updatePartial(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
        try{
            dao.updatePartial(conn, entity);
            conn.commit();
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }
    
    @Override
    public void validate(Usuario entity) throws Exception{
        if(entity.getNome() == null || entity.getNome().isEmpty()){
            throw new IllegalArgumentException("Campo nome obrigatório!");
        }
        
        if(entity.getEmail() == null || entity.getEmail().isEmpty()){
            throw new IllegalArgumentException("Campo email obrigatório!");
        }
        
        if(entity.getSenha() == null || entity.getSenha().isEmpty()){
            throw new IllegalArgumentException("Campo senha obrigatório!");
        }
        
        if(entity.getTipoUsuario() == null){
            throw new IllegalArgumentException("Campo tipo de usuário obrigatório!");
        }
    }
}
