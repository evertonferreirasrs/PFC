/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.BaseService;
import br.com.localizae.model.dao.EventoDAO;
import br.com.localizae.model.entity.Evento;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class EventoService implements BaseService<Evento>{

    @Override
    public void create(Evento entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EventoDAO dao = new EventoDAO();
        
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
    public void update(Evento entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EventoDAO dao = new EventoDAO();

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
        EventoDAO dao = new EventoDAO();

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
    public Evento readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EventoDAO dao = new EventoDAO();
        Evento evento = null;

        try {
            evento = dao.readById(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return evento;
    }

    @Override
    public List<Evento> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EventoDAO dao = new EventoDAO();
        List<Evento> eventoList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }

        try {
            eventoList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return eventoList;
    }
    
}
