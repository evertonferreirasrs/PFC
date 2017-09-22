/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.entity.Estatistica;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class EstatisticaDAO implements BaseDAO<Estatistica>{

    @Override
    public void create(Connection conn, Estatistica entity) throws Exception {
        String sql = "INSERT INTO (posicaoX, posicaoY, dataHora, usuario_fk) VALUES(?,?,?,?) RETURNING id;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, entity.getPosicaoX());
        ps.setLong(++i, entity.getPosicaoY());
        ps.setTimestamp(++i, new Timestamp(entity.getDataHora()));
        ps.setLong(++i, entity.getUsuario().getId());
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM estatistica WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Estatistica entity) throws Exception {
        throw new UnsupportedOperationException("Método não suportado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePartial(Connection conn, Estatistica entity) throws Exception {
        throw new UnsupportedOperationException("Método não suportado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estatistica readById(Connection conn, Long id) throws Exception {
        Estatistica estatistica = null;
        
        String sql = "SELECT * FROM estatistica WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            estatistica = new Estatistica();
            estatistica.setDataHora(rs.getTimestamp("dataHora").getTime());
            estatistica.setId(id);
            estatistica.setPosicaoX(rs.getLong("posicaoX"));
            estatistica.setPosicaoY(rs.getLong("posicaoY"));
        }
        
        return estatistica;
    }

    @Override
    public List<Estatistica> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
