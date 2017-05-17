/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseTipoUsuarioService;
import br.com.localizae.model.dao.TipoUsuarioDAO;
import br.com.localizae.model.entity.TipoUsuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class TipoUsuarioService implements BaseTipoUsuarioService{

    @Override
    public void create(TipoUsuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        
        try{
            dao.create(conn, entity);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public void update(TipoUsuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        
        try{
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
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        
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
    public TipoUsuario readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        TipoUsuario tipoUsuario = null;
        
        try{
            tipoUsuario = dao.readById(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return tipoUsuario;
    }

    @Override
    public List<TipoUsuario> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        List<TipoUsuario> tipoUsuarioList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }
        
        try{
            tipoUsuarioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return tipoUsuarioList;
    }
    
}
