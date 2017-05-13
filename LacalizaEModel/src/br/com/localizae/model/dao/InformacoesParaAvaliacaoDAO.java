/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.InformacoesParaAvaliacaoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.InformacoesParaAvaliacao;
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
 * @author LocalizaÊ
 */
public class InformacoesParaAvaliacaoDAO implements BaseDAO<InformacoesParaAvaliacao> {

    @Override
    public void create(Connection conn, InformacoesParaAvaliacao entity) throws Exception {
        String sql = "INSERT INTO informacoesParaAvaliacao(estande_fk, criterioAvaliacao_fk, usuario_fk) VALUES(?,?,?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getEstande().getId());
        ps.setLong(++i, entity.getCriterioAvaliacao().getId());
        ps.setLong(++i, entity.getUsuario().getId());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM informacoesParaAvaliacao WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, InformacoesParaAvaliacao entity) throws Exception {
        String sql = "UPDATE informacoesParaAvaliacao SET estande_fk=?, criterioAvaliacao_fk=?, usuario_fk=? WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getEstande().getId());
        ps.setLong(++i, entity.getCriterioAvaliacao().getId());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public InformacoesParaAvaliacao readById(Connection conn, Long id) throws Exception {
        InformacoesParaAvaliacao informacao = null;

        String sql = "SELECT * FROM informacoesParaAvaliacao WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            informacao = new InformacoesParaAvaliacao();

            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));

            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setId(rs.getLong("criterioAvaliacao_fk"));

            informacao.setCriterioAvaliacao(criterio);
            informacao.setEstande(estande);
            informacao.setUsuario(usuario);
            informacao.setId(id);
        }

        return informacao;
    }

    @Override
    public List<InformacoesParaAvaliacao> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if(criteria == null){
            criteria = new HashMap<>();
        }
        List<InformacoesParaAvaliacao> informacaoList = new ArrayList<>();

        String sql = "SELECT * FROM informacoesParaAvaliacao WHERE 1=1";
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);

        if (limit != null && limit > 0) {
            sql += " LIMIT ?";
            args.add(limit);
        }

        if (offset != null && offset > 0) {
            sql += " OFFSET ?";
            args.add(offset);
        }

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        for (Object arg : args) {
            ps.setObject(++i, arg);
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            InformacoesParaAvaliacao informacao = new InformacoesParaAvaliacao();

            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));

            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setId(rs.getLong("criterioAvaliacao_fk"));

            informacao.setCriterioAvaliacao(criterio);
            informacao.setEstande(estande);
            informacao.setUsuario(usuario);
            informacao.setId(rs.getLong("id"));
            informacaoList.add(informacao);
        }

        return informacaoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        //USUARIO_EQ
        Long usuario = (Long)criteria.get(InformacoesParaAvaliacaoCriteria.USUARIO_EQ);
        if(usuario != null && usuario > 0){
            sql += " AND usuario_fk = ?";
            args.add(usuario);
        }
        //ESTANDE_EQ
        Long estande = (Long)criteria.get(InformacoesParaAvaliacaoCriteria.ESTANDE_EQ);
        if(estande != null && estande > 0){
            sql += " AND estande_fk = ?";
            args.add(estande);
        }
        //CRITERIO_EQ
        Long criterio = (Long)criteria.get(InformacoesParaAvaliacaoCriteria.CRITERIO_EQ);
        if(criterio != null && criterio > 0){
            sql += " AND criterioAvaliacao_fk = ?";
            args.add(criterio);
        }
        
        return sql;
    }

}
