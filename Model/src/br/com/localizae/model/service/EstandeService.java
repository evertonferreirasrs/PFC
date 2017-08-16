/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseEstandeService;
import br.com.localizae.model.dao.EstandeDAO;
import br.com.localizae.model.entity.Estande;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class EstandeService implements BaseEstandeService {

    @Override
    public void create(Estande entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EstandeDAO dao = new EstandeDAO();

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
    public void update(Estande entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EstandeDAO dao = new EstandeDAO();

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
        EstandeDAO dao = new EstandeDAO();

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
    public Estande readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EstandeDAO dao = new EstandeDAO();
        Estande estande = null;

        try {
            estande = dao.readById(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return estande;
    }

    @Override
    public List<Estande> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EstandeDAO dao = new EstandeDAO();
        List<Estande> estandeList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }

        try {
            estandeList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        
        return estandeList;
    }

    @Override
    public void updatePartial(Estande entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        EstandeDAO dao = new EstandeDAO();

        try {
            dao.updatePartial(conn, entity);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

}
