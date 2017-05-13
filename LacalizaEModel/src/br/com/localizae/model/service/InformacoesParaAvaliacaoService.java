/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseInformacoesParaAvaliacaoService;
import br.com.localizae.model.dao.InformacoesParaAvaliacaoDAO;
import br.com.localizae.model.entity.InformacoesParaAvaliacao;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class InformacoesParaAvaliacaoService implements BaseInformacoesParaAvaliacaoService{

    @Override
    public void create(InformacoesParaAvaliacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InformacoesParaAvaliacaoDAO dao = new InformacoesParaAvaliacaoDAO();
        
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
    public void update(InformacoesParaAvaliacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InformacoesParaAvaliacaoDAO dao = new InformacoesParaAvaliacaoDAO();
        
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
        InformacoesParaAvaliacaoDAO dao = new InformacoesParaAvaliacaoDAO();
        
        try{
            dao.readById(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
    }

    @Override
    public InformacoesParaAvaliacao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InformacoesParaAvaliacaoDAO dao = new InformacoesParaAvaliacaoDAO();
        InformacoesParaAvaliacao informacoes = null;
        
        try{
            informacoes = dao.readById(conn, id);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return informacoes;
    }

    @Override
    public List<InformacoesParaAvaliacao> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InformacoesParaAvaliacaoDAO dao = new InformacoesParaAvaliacaoDAO();
        List<InformacoesParaAvaliacao> informacoesList = null;
        if(criteria == null){
            criteria = new HashMap<>();
        }
        
        try{
            informacoesList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.close();
        }
        
        return informacoesList;
    }
    
}
