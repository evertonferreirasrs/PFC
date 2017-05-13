/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.IntegranteEquipe;
import br.com.localizae.model.entity.Usuario;
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
public class EstandeDAO implements BaseDAO<Estande> {

    @Override
    public void create(Connection conn, Estande entity) throws Exception {
        String sql = "INSERT INTO estande (curso, descricao, periodo, nome, areaTematica, numero) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getCurso());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getPeriodo());
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getAreaTematica());
        ps.setLong(++i, entity.getNumero());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        for (IntegranteEquipe integrante : entity.getEquipe()) {
            String usuarioSQL = "INSERT INTO integranteEquipe (usuario_fk, estande_fk, responsavel) VALUES (?, ?, ?);";

            PreparedStatement usuarioPS = conn.prepareStatement(usuarioSQL);

            int j = 0;
            usuarioPS.setLong(++j, integrante.getId());
            usuarioPS.setLong(++j, entity.getId());
            usuarioPS.setBoolean(++j, integrante.getResponsavel());

            usuarioPS.execute();

            usuarioPS.close();
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM estande WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Estande entity) throws Exception {
        String sql = "UPDATE estande SET curso=?, descricao=?, periodo=?, nome=?, areaTematica=?, numero=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getCurso());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getPeriodo());
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getAreaTematica());
        ps.setLong(++i, entity.getNumero());
        ps.setLong(++i, entity.getId());

        ps.execute();

        String delUserSQL = "DELETE FROM integranteEquipe WHERE estande_fk=?;";

        PreparedStatement delUserPS = conn.prepareStatement(delUserSQL);

        delUserPS.setLong(1, entity.getId());
        delUserPS.execute();

        for (IntegranteEquipe integrante : entity.getEquipe()) {
            String usuarioSQL = "INSERT INTO integranteEquipe (usuario_fk, estande_fk, responsavel) VALUES (?, ?, ?);";

            PreparedStatement usuarioPS = conn.prepareStatement(usuarioSQL);

            int j = 0;
            usuarioPS.setLong(++j, integrante.getId());
            usuarioPS.setLong(++j, entity.getId());
            usuarioPS.setBoolean(++j, integrante.getResponsavel());

            usuarioPS.execute();
            usuarioPS.close();
        }

        ps.close();
        delUserPS.close();
    }

    @Override
    public Estande readById(Connection conn, Long id) throws Exception {
        Estande estande = null;
        String sql = "SELECT e.*, i.id integrante_id, i.usuario_fk, i.responsavel, u.nome usuario FROM estande e FULL JOIN integranteEquipe i ON e.id = i.estande_fk LEFT JOIN usuario u ON u.id = i.usuario_fk WHERE e.id=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (estande == null) {
                estande = new Estande();
                estande.setAreaTematica(rs.getString("areaTematica"));
                estande.setCurso(rs.getString("curso"));
                estande.setDescricao(rs.getString("descricao"));
                estande.setId(rs.getLong("id"));
                estande.setNome(rs.getString("nome"));
                estande.setNumero(rs.getLong("numero"));
                estande.setPeriodo(rs.getLong("periodo"));
                estande.setEquipe(new ArrayList<>());
            }

            IntegranteEquipe integrante = new IntegranteEquipe();
            integrante.setId(rs.getLong("integrante_id"));
            integrante.setResponsavel(rs.getBoolean("responsavel"));
            
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));
            
            integrante.setUsuario(usuario);

            if(integrante.getId() != 0){
                estande.getEquipe().add(integrante);
            }
        }

        return estande;
    }

    @Override
    public List<Estande> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if(criteria == null){
            criteria = new HashMap<>();
        }
        List<Estande> estandeList = new ArrayList<>();

        String sql = "SELECT e.*, i.id integrante_id, i.usuario_fk, i.responsavel, u.nome usuario FROM estande e FULL JOIN integranteEquipe i ON e.id = i.estande_fk LEFT JOIN usuario u ON u.id = i.usuario_fk WHERE 1=1";
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);

        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        for(Object obj : args){
            ps.setObject(++i, obj);
        }
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Estande ultimoEstande = null;
            if (!estandeList.isEmpty()) {
                ultimoEstande = estandeList.get(estandeList.size() - 1);
            }

            Estande estande = new Estande();
            estande.setAreaTematica(rs.getString("areaTematica"));
            estande.setCurso(rs.getString("curso"));
            estande.setDescricao(rs.getString("descricao"));
            estande.setId(rs.getLong("id"));
            estande.setNome(rs.getString("nome"));
            estande.setNumero(rs.getLong("numero"));
            estande.setPeriodo(rs.getLong("periodo"));
            
            IntegranteEquipe integrante = new IntegranteEquipe();
            integrante.setId(rs.getLong("integrante_id"));
            integrante.setResponsavel(rs.getBoolean("responsavel"));
            
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));
            
            integrante.setUsuario(usuario);
            
            if(estande.equals(ultimoEstande)){
                ultimoEstande.getEquipe().add(integrante);
            }else{
                estande.setEquipe(new ArrayList<>());
                if(integrante.getId() != 0){
                    estande.getEquipe().add(integrante);
                }
                estandeList.add(estande);
            }
        }

        return estandeList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";

        //NOME_EQ
        String nome = (String)criteria.get(EstandeCriteria.NOME_EQ);
        if(nome != null && !nome.isEmpty()){
            sql += " AND e.nome ILIKE ?";
            nome = "%"+nome+"%";
            args.add(nome);
        }
        //CURSO_EQ
        String curso = (String)criteria.get(EstandeCriteria.CURSO_EQ);
        if(curso != null && !curso.isEmpty()){
            sql += " AND e.curso ILIKE ?";
            curso = "%"+curso+"%";
            args.add(curso);
        }
        //PERIODO_EQ
        Long periodo = (Long)criteria.get(EstandeCriteria.PERIODO_EQ);
        if(periodo != null && periodo > 0){
            sql += " AND e.periodo = ?";
            args.add(periodo);
        }
        //NUMERO_EQ
        Long numero = (Long)criteria.get(EstandeCriteria.NUMERO_EQ);
        if(numero != null && numero > 0){
            sql += " AND e.numero = ?";
            args.add(numero);
        }
        //AREATEMATICA_EQ
        String areaTematica = (String)criteria.get(EstandeCriteria.AREATEMATICA_EQ);
        if(areaTematica != null && !areaTematica.isEmpty()){
            sql += " AND e.areaTematica ILIKE ?";
            areaTematica = "%"+areaTematica+"%";
            args.add(areaTematica);
        }
        
        return sql;
    }

}
