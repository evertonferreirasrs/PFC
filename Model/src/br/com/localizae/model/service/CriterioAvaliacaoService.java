/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseCriterioAvaliacaoService;
import br.com.localizae.model.dao.CriterioAvaliacaoDAO;
import br.com.localizae.model.entity.CriterioAvaliacao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class CriterioAvaliacaoService implements BaseCriterioAvaliacaoService {

    @Override
    public void create(CriterioAvaliacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();

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
    public void update(CriterioAvaliacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();

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
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();

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
    public CriterioAvaliacao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
        CriterioAvaliacao criterio = null;

        try {
            criterio = dao.readById(conn, id);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }

        return criterio;
    }

    @Override
    public List<CriterioAvaliacao> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
        List<CriterioAvaliacao> criterioList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }

        try {
            criterioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }

        return criterioList;
    }

}
