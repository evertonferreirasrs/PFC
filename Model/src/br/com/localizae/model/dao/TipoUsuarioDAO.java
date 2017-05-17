/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.TipoUsuarioCriteria;
import br.com.localizae.model.entity.TipoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class TipoUsuarioDAO implements BaseDAO<TipoUsuario> {

    @Override
    public void create(Connection conn, TipoUsuario entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, TipoUsuario entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoUsuario readById(Connection conn, Long id) throws Exception {
        TipoUsuario tipo = null;
        
        String sql = "SELECT * FROM tipoUsuario WHERE id=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            tipo = new TipoUsuario();
            tipo.setId(rs.getLong("id"));
            tipo.setNome(rs.getString("nome"));
        }
        
        return tipo;
    }

    @Override
    public List<TipoUsuario> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<TipoUsuario> tipoList = new ArrayList<>();
        
        String sql = "SELECT * FROM tipoUsuario WHERE 1=1";
        List<Object> args = new ArrayList<>();
        this.applyCriteria(criteria, args);
        
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
            TipoUsuario tipo = new TipoUsuario();
            tipo.setId(rs.getLong("id"));
            tipo.setNome(rs.getString("nome"));
            
            tipoList.add(tipo);
        }
        
        return tipoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        String nome = (String)criteria.get(TipoUsuarioCriteria.NOME_ILIKE);
        if(nome != null && !nome.isEmpty()){
            sql += " AND nome ILIKE %?%";
            args.add(nome);
        }
        
        return sql;
    }
    
}
