/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseEstatisticaService;
import br.com.localizae.model.dao.EstatisticaDAO;
import br.com.localizae.model.entity.Estatistica;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class EstatisticaService implements BaseEstatisticaService{
    
    private final EstatisticaDAO dao;

    public EstatisticaService() {
        dao = new EstatisticaDAO();
    }

    @Override
    public void create(Estatistica entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try{
            this.validate(entity);
            dao.create(conn, entity);
            conn.commit();
        }catch(Exception ex){
            conn.rollback();
            throw ex;
        }finally{
            conn.close();
        }
    }

    @Override
    public void update(Estatistica entity) throws Exception {
        throw new UnsupportedOperationException("Método não suportado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try{
            dao.delete(conn, id);
            conn.commit();
        }catch(Exception ex){
            conn.rollback();
            throw ex;
        }finally{
            conn.close();
        }
    }

    @Override
    public Estatistica readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Estatistica estatistica = null;
        try{
            estatistica = dao.readById(conn, id);
            conn.commit();
        }catch(Exception ex){
            conn.rollback();
            throw ex;
        }finally{
            conn.close();
        }
        
        return estatistica;
    }

    @Override
    public void updatePartial(Estatistica entity) throws Exception {
        throw new UnsupportedOperationException("Método não suportado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estatistica> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Estatistica> estatisticaList = null;
        try{
            estatisticaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception ex){
            conn.rollback();
            throw ex;
        }finally{
            conn.close();
        }
        
        return estatisticaList;
    }

    @Override
    public void validate(Estatistica entity) throws Exception {
        if(entity.getDataHora() == null || entity.getDataHora() <= 0){
            throw new IllegalArgumentException("Campo DataHora inválido.");
        }
        
        if(entity.getPosicaoX() == null || entity.getPosicaoX() < 0){
            throw new IllegalArgumentException("Campo Posição X inválido.");
        }
        
        if(entity.getPosicaoY() == null || entity.getPosicaoY() < 0){
            throw new IllegalArgumentException("Campo Posição Y inválido.");
        }
        
        if(entity.getUsuario() == null || entity.getUsuario().getId() == null){
            throw new IllegalArgumentException("Campo usuário inválido, forneça um objeto com id do usuário");
        }
        
    }
    
}
