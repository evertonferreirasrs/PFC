/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.AvaliacaoVisitanteCriteria;
import br.com.localizae.model.entity.AvaliacaoVisitante;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class AvaliacaoVisitanteDAO implements BaseDAO<AvaliacaoVisitante> {

    @Override
    public void create(Connection conn, AvaliacaoVisitante entity) throws Exception {
        String sql = "INSERT INTO avaliacaoVisitante(nota, comentario, usuario_fk, estande_fk) VALUES (?,?,?,?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getNota());
        ps.setString(++i, entity.getOpiniao());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getEstande().getId());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM avaliacaoVisitante WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, AvaliacaoVisitante entity) throws Exception {
        String sql = "UPDATE avaliacaoVisitante SET nota=?, comentario=?, usuario_fk=?, estande_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getNota());
        ps.setString(++i, entity.getOpiniao());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getEstande().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public AvaliacaoVisitante readById(Connection conn, Long id) throws Exception {
        AvaliacaoVisitante avaliacaoVisitante = null;

        String sql = "SELECT a.*, u.nome usuario, e.titulo estande FROM avaliacaoVisitante a JOIN usuario u ON a.usuario_fk = u.id JOIN estande e ON a.estande_fk = e.id WHERE a.id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            avaliacaoVisitante = new AvaliacaoVisitante();
            avaliacaoVisitante.setOpiniao(rs.getString("comentario"));
            avaliacaoVisitante.setNota(rs.getLong("nota"));
            avaliacaoVisitante.setId(rs.getLong("id"));

            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setTitulo(rs.getString("estande"));

            avaliacaoVisitante.setEstande(estande);
            avaliacaoVisitante.setUsuario(usuario);
        }

        return avaliacaoVisitante;
    }

    @Override
    public List<AvaliacaoVisitante> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if(criteria == null){
            criteria = new HashMap<>();
        }
        List<AvaliacaoVisitante> avaliacaoVisitanteList = new ArrayList<>();

        String sql = "SELECT a.*, u.nome usuario, e.titulo estande FROM avaliacaoVisitante a JOIN usuario u ON a.usuario_fk = u.id JOIN estande e ON a.estande_fk = e.id WHERE 1=1";
        
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);
        
        sql += " order by a.id";
        
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
        for(Object arg: args){
            ps.setObject(++i, arg);
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            AvaliacaoVisitante avaliacaoVisitante = new AvaliacaoVisitante();
            avaliacaoVisitante.setOpiniao(rs.getString("comentario"));
            avaliacaoVisitante.setNota(rs.getLong("nota"));
            avaliacaoVisitante.setId(rs.getLong("id"));

            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setTitulo(rs.getString("estande"));

            avaliacaoVisitante.setEstande(estande);
            avaliacaoVisitante.setUsuario(usuario);
            
            avaliacaoVisitanteList.add(avaliacaoVisitante);
        }

        return avaliacaoVisitanteList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        //USUARIO_EQ
        Long usuario = (Long)criteria.get(AvaliacaoVisitanteCriteria.USUARIO_EQ);
        if(usuario != null && usuario > 0){
            sql += " AND usuario_fk = ?";
            args.add(usuario);
        }
        //ESTANDE_EQ
        Long estande = (Long)criteria.get(AvaliacaoVisitanteCriteria.ESTANDE_EQ);
        if(estande != null && estande > 0){
            sql += " AND estande_fk = ?";
            args.add(estande);
        }
        //NOTA_EQ
        Long nota = (Long)criteria.get(AvaliacaoVisitanteCriteria.NOTA_EQ);
        if(nota != null && nota >= 0){
            sql += " AND nota = ?";
            args.add(nota);
        }
        
        return sql;
    }

    //Método responsável por calcular a média de notas dadas por visitante
    //Retorna para o cliente um mapa contendo como chave o id do estande e como valor a média de notas.
    public Map<Long, Long> calcularMediaDeNotas(Connection conn) throws SQLException{
        Map<Long, Long> mediaList = new HashMap<>();
        String sql = "select estande_fk, avg(nota) from avaliacaoVisitante group by estande_fk order by estande_fk";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            mediaList.put(rs.getLong("estande_fk"), rs.getLong("avg"));
        }
        
        return mediaList;
    }
}
