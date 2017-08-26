/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BasePromocaoService;
import br.com.localizae.model.dao.PromocaoDAO;
import br.com.localizae.model.entity.Promocao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class PromocaoService implements BasePromocaoService{

    @Override
    public void create(Promocao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PromocaoDAO dao = new PromocaoDAO();
        
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
    public void update(Promocao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PromocaoDAO dao = new PromocaoDAO();
        
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
        PromocaoDAO dao = new PromocaoDAO();
        
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
    public Promocao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PromocaoDAO dao = new PromocaoDAO();
        Promocao promocao = null;
        
        try{
            promocao = dao.readById(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return promocao;
    }

    @Override
    public List<Promocao> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PromocaoDAO dao = new PromocaoDAO();
        List<Promocao> promocaoList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }
        
        try{
            promocaoList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return promocaoList;
    }

    @Override
    public void updatePartial(Promocao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PromocaoDAO dao = new PromocaoDAO();
        
        try{
            dao.updatePartial(conn, entity);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public void validate(Promocao entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
