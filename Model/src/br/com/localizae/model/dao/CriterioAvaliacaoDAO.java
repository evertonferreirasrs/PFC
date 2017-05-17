/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.CriterioAvaliacaoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class CriterioAvaliacaoDAO implements BaseDAO<CriterioAvaliacao>{

    @Override
    public void create(Connection conn, CriterioAvaliacao entity) throws Exception {
        String sql = "INSERT INTO criterioAvaliacao (nome) VALUES (?) RETURNING id;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setString(++i, entity.getNome());
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM criterioAvaliacao WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, CriterioAvaliacao entity) throws Exception {
        String sql = "UPDATE criterioAvaliacao SET nome=? WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setLong(++i, entity.getId());
        
        ps.execute();
        ps.close();
    }

    @Override
    public CriterioAvaliacao readById(Connection conn, Long id) throws Exception {
        CriterioAvaliacao criterioAvaliacao = null;
        
        String sql = "SELECT * FROM criterioAvaliacao WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            criterioAvaliacao = new CriterioAvaliacao();
            
            criterioAvaliacao.setId(rs.getLong("id"));
            criterioAvaliacao.setNome(rs.getString("nome"));
        }
        
        return criterioAvaliacao;
    }

    @Override
    public List<CriterioAvaliacao> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if(criteria == null){
            criteria = new HashMap<>();
        }
        List<CriterioAvaliacao> criterioAvaliacaoList = new ArrayList<>();
        
        String sql = "SELECT * FROM criterioAvaliacao WHERE 1=1";
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);
        
        if(limit != null && limit > 0){
            sql += " LIMIT ?";
            args.add(limit);
        }
        
        if(offset != null && offset > 0){
            sql += " OFFSET ?";
            args.add(offset);
        }
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        for(Object arg : args){
            ps.setObject(++i, arg);
        }
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            CriterioAvaliacao criterioAvaliacao = new CriterioAvaliacao();
            
            criterioAvaliacao.setId(rs.getLong("id"));
            criterioAvaliacao.setNome(rs.getString("nome"));
            
            criterioAvaliacaoList.add(criterioAvaliacao);
        }
        
        return criterioAvaliacaoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        String nome = (String)criteria.get(CriterioAvaliacaoCriteria.NOME_EQ);
        if(nome != null && !nome.isEmpty()){
            sql += " AND nome ILIKE ?";
            nome = "%"+nome+"%";
            args.add(nome);
        }
        
        return sql;
    }
    
}
