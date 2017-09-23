/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.EstatisticaCriteria;
import br.com.localizae.model.entity.Estatistica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class EstatisticaDAO implements BaseDAO<Estatistica>{

    @Override
    public void create(Connection conn, Estatistica entity) throws Exception {
        String sql = "INSERT INTO estatistica(posicaoX, posicaoY, dataHora, usuario_fk) VALUES(?,?,?,?) RETURNING id;";
        
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
        
        String sql = "SELECT * FROM estatistica WHERE id=?";
        
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
        List<Estatistica> estatisticaList = new ArrayList<>();
        
        String sql = "SELECT * FROM estatistica WHERE 1=1";
        List<Object> args = new ArrayList<>();
        
        sql += this.applyCriteria(criteria, args);
        sql += " ORDER BY id";
        
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
        
        System.out.println(ps);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Estatistica estatistica = new Estatistica();
            estatistica.setDataHora(rs.getTimestamp("dataHora").getTime());
            estatistica.setId(rs.getLong("id"));
            estatistica.setPosicaoX(rs.getLong("posicaoX"));
            estatistica.setPosicaoY(rs.getLong("posicaoY"));
            
            estatisticaList.add(estatistica);
        }
        
        return estatisticaList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        if(criteria != null){
            //POSX_EQ,
            Long posX_eq = (Long) criteria.get(EstatisticaCriteria.POSX_EQ);
            if(posX_eq != null && posX_eq >= 0){
                sql += " AND posicaoX = ?";
                args.add(posX_eq);
            }
            
            //POSX_LT,
            Long posX_lt = (Long) criteria.get(EstatisticaCriteria.POSX_LT);
            if(posX_lt != null && posX_lt >= 0){
                sql += " AND posicaoX < ?";
                args.add(posX_lt);
            }
            
            //POSX_GT,
            Long posX_gt = (Long) criteria.get(EstatisticaCriteria.POSX_GT);
            if(posX_gt != null && posX_gt >= 0){
                sql += " AND posicaoX > ?";
                args.add(posX_gt);
            }
            
            //POSY_EQ,
            Long posY_eq = (Long) criteria.get(EstatisticaCriteria.POSY_EQ);
            if(posY_eq != null && posY_eq >= 0){
                sql += " AND posicaoY = ?";
                args.add(posY_eq);
            }
            
            //POSY_LT,
            Long posY_lt = (Long) criteria.get(EstatisticaCriteria.POSY_LT);
            if(posY_lt != null && posY_lt >= 0){
                sql += " AND posicaoY < ?";
                args.add(posY_lt);
            }
            
            //POSY_GT,
            Long posY_gt = (Long) criteria.get(EstatisticaCriteria.POSY_GT);
            if(posY_gt != null && posY_gt >= 0){
                sql += " AND posicaoY > ?";
                args.add(posY_gt);
            }
            
            //DATAHORA_EQ,
            Long datahora_eq = (Long) criteria.get(EstatisticaCriteria.DATAHORA_EQ);
            if(datahora_eq != null && datahora_eq >= 0){
                sql += " AND datahora = ?";
                args.add(new Timestamp(datahora_eq));
            }
            
            //DATAHORA_LT,
            Long datahora_lt = (Long) criteria.get(EstatisticaCriteria.DATAHORA_LT);
            if(datahora_lt != null && datahora_lt >= 0){
                sql += " AND datahora < ?";
                args.add(new Timestamp(datahora_lt));
            }
            
            //DATAHORA_GT
            Long datahora_gt = (Long) criteria.get(EstatisticaCriteria.DATAHORA_GT);
            if(datahora_gt != null && datahora_gt >= 0){
                sql += " AND datahora > ?";
                args.add(new Timestamp(datahora_gt));
            }
        }
        
        return sql;
    }
    
}
