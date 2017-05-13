/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.PromocaoCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class PromocaoDAO implements BaseDAO<Promocao> {

    @Override
    public void create(Connection conn, Promocao entity) throws Exception {
        String sql = "INSERT INTO promocao (nome, descricao, dataHora, estande_fk) VALUES (?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.setTimestamp(++i, entity.getDataHora());
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
        String sql = "DELETE FROM promocao WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Promocao entity) throws Exception {
        String sql = "UPDATE promocao SET nome=?, descricao=?, dataHora=?, estande_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.setTimestamp(++i, entity.getDataHora());
        ps.setLong(++i, entity.getEstande().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public Promocao readById(Connection conn, Long id) throws Exception {
        Promocao promocao = null;
        String sql = "SELECT p.*, e.nome estande, e.areaTematica FROM promocao p JOIN estande e ON p.estande_fk = e.id WHERE p.id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            promocao = new Promocao();
            promocao.setDataHora(rs.getTimestamp("dataHora"));
            promocao.setDescricao(rs.getString("descricao"));
            promocao.setId(rs.getLong("id"));
            promocao.setNome(rs.getString("nome"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setNome(rs.getString("estande"));
            promocao.setEstande(estande);
        }

        return promocao;
    }

    @Override
    public List<Promocao> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if (criteria == null) {
            criteria = new HashMap<>();
        }
        List<Promocao> promocaoList = new ArrayList<>();
        String sql = "SELECT p.*, e.nome estande, e.areaTematica FROM promocao p JOIN estande e ON p.estande_fk = e.id WHERE 1=1";

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
            Promocao promocao = new Promocao();
            promocao.setDataHora(rs.getTimestamp("dataHora"));
            promocao.setDescricao(rs.getString("descricao"));
            promocao.setId(rs.getLong("id"));
            promocao.setNome(rs.getString("nome"));

            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setNome(rs.getString("estande"));
            promocao.setEstande(estande);

            promocaoList.add(promocao);
        }

        return promocaoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";

        //ESTANDE_EQ
        Long estande = (Long) criteria.get(PromocaoCriteria.ESTANDE_EQ);
        if (estande != null && estande > 0) {
            sql += " AND p.estande_fk = ?";
            args.add(estande);
        }
        //NOME_EQ
        String nome = (String) criteria.get(PromocaoCriteria.NOME_EQ);
        if (nome != null && !nome.isEmpty()) {
            sql += " AND p.nome ILIKE ?";
            nome = "%" + nome + "%";
            args.add(nome);
        }
        //DATA_EQ
        Timestamp dataHora = (Timestamp) criteria.get(PromocaoCriteria.DATA_EQ);
        if (dataHora != null) {
            sql = " AND p.dataHora = ?";
            args.add(dataHora);
        }
        //AREA_TEMATICA_EQ
        String areaTematica = (String) criteria.get(PromocaoCriteria.AREA_TEMATICA_EQ);
        if (areaTematica != null && !areaTematica.isEmpty()) {
            sql = " AND e.areaTematica ILIKE ?";
            areaTematica = "%" + areaTematica + "%";
            args.add(areaTematica);
        }

        return sql;
    }

}
