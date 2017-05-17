/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseAvaliacaoJuradoService;
import br.com.localizae.model.dao.AvaliacaoJuradoDAO;
import br.com.localizae.model.entity.AvaliacaoJurado;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class AvaliacaoJuradoService implements BaseAvaliacaoJuradoService{

    @Override
    public void create(AvaliacaoJurado entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoJuradoDAO dao = new AvaliacaoJuradoDAO();
        
        try {
            dao.create(conn, entity);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public void update(AvaliacaoJurado entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoJuradoDAO dao = new AvaliacaoJuradoDAO();
        
        try {
            dao.update(conn, entity);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoJuradoDAO dao = new AvaliacaoJuradoDAO();
        
        try {
            dao.delete(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public AvaliacaoJurado readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoJuradoDAO dao = new AvaliacaoJuradoDAO();
        AvaliacaoJurado avaliacao = null;
        
        try {
            avaliacao = dao.readById(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return avaliacao;
    }

    @Override
    public List<AvaliacaoJurado> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoJuradoDAO dao = new AvaliacaoJuradoDAO();
        List<AvaliacaoJurado> avaliacaoList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }
        
        try {
            avaliacaoList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return avaliacaoList;
    }
    
}
