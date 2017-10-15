/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.CriterioJuradoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.CriterioJurado;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class CriterioJuradoDAO implements BaseDAO<CriterioJurado>{

    @Override
    public void create(Connection conn, CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePartial(Connection conn, CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriterioJurado readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CriterioJurado> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<CriterioJurado> criterioList = new ArrayList<>();
        
        String sql = "SELECT cj.*, e.titulo estande, ca.nome criterio, u.nome usuario FROM criterioJurado cj JOIN estande e ON e.id = cj.estande_fk JOIN criterioAvaliacao ca ON ca.id = cj.criterioAvaliacao_fk JOIN usuario u ON u.id = cj.usuario_fk WHERE 1=1";
        
        List<Object> args = new ArrayList<>();
        
        sql += this.applyCriteria(criteria, args);
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        for(Object arg : args){
            ps.setObject(++i, arg);
        }
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));
            
            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setTitulo(rs.getString("estande"));
            
            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setId(rs.getLong("criterioAvaliacao_fk"));
            criterio.setNome(rs.getString("criterio"));
            
            CriterioJurado cj = new CriterioJurado();
            cj.setCriterioAvaliacao(criterio);
            cj.setEstande(estande);
            cj.setUsuario(usuario);
            
            criterioList.add(cj);
        }
        
        return criterioList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        if(criteria != null){
            Long usuario = (Long)criteria.get(CriterioJuradoCriteria.USUARIO_EQ);
            if(usuario != null){
                sql += " AND cj.usuario_fk=?";
                args.add(usuario);
            }
            Long estande = (Long) criteria.get(CriterioJuradoCriteria.ESTANDE_EQ);
            if(estande != null){
                sql += " AND cj.estande_fk=?";
                args.add(estande);
            }
            Long criterio = (Long) criteria.get(CriterioJuradoCriteria.CRITERIO_EQ);
            if(criterio != null){
                sql += " AND cj.criterioAvaliacao_fk";
                args.add(criterio);
            }
        }
        
        return sql;
    }
    
}
