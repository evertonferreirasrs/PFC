/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.AvaliacaoJuradoCriteria;
import br.com.localizae.model.entity.AvaliacaoJurado;
import br.com.localizae.model.entity.CriterioAvaliacao;
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
public class AvaliacaoJuradoDAO implements BaseDAO<AvaliacaoJurado> {

    @Override
    public void create(Connection conn, AvaliacaoJurado entity) throws Exception {
        String sql = "INSERT INTO avaliacaoJurado (nota, opiniao, dataHoraAbertura, dataHoraFechamento, usuario_fk, criterioAvaliacao_fk) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getNota());
        ps.setString(++i, entity.getOpiniao());
        ps.setTimestamp(++i, entity.getDataHoraAbertura());
        ps.setTimestamp(++i, entity.getDataHoraFechamento());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getCriterio().getId());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM avaliacaoJurado WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, AvaliacaoJurado entity) throws Exception {
        String sql = "UPDATE avaliacaoJurado SET nota=?, opiniao=?, dataHoraAbertura=?, dataHoraFechamento=?, usuario_fk=?, criterioAvaliacao_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getNota());
        ps.setString(++i, entity.getOpiniao());
        ps.setTimestamp(++i, entity.getDataHoraAbertura());
        ps.setTimestamp(++i, entity.getDataHoraFechamento());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setLong(++i, entity.getCriterio().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public AvaliacaoJurado readById(Connection conn, Long id) throws Exception {
        AvaliacaoJurado avaliacaoJurado = null;

        String sql = "select aj.*, u.nome usuario, ca.nome criterio, e.nome evento from avaliacaoJurado aj join usuario u on aj.usuario_fk = u.id join criterioAvaliacao ca on ca.id = aj.criterioAvaliacao_fk join estande e on e.id = aj.evento_fk WHERE a.id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            avaliacaoJurado = new AvaliacaoJurado();

            avaliacaoJurado.setId(rs.getLong("id"));
            avaliacaoJurado.setNota(rs.getLong("nota"));
            avaliacaoJurado.setOpiniao(rs.getString("opiniao"));
            avaliacaoJurado.setStatus(rs.getString("status"));
            avaliacaoJurado.setDataHoraAbertura(rs.getTimestamp("dataHoraAbertura"));
            avaliacaoJurado.setDataHoraFechamento(rs.getTimestamp("dataHoraFechamento"));

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("usuario"));
            usuario.setId(rs.getLong("usuario_fk"));

            Estande estande = new Estande();
            estande.setTitulo(rs.getString("estande"));
            estande.setId(rs.getLong("estande_fk"));

            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setNome(rs.getString("criterio"));
            criterio.setId(rs.getLong("criterio_fk"));
            
            avaliacaoJurado.setCriterio(criterio);
            avaliacaoJurado.setUsuario(usuario);
            avaliacaoJurado.setEstande(estande);
        }

        return avaliacaoJurado;
    }

    @Override
    public List<AvaliacaoJurado> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if (criteria == null) {
            criteria = new HashMap<>();
        }
        List<AvaliacaoJurado> avaliacaoJuradoList = new ArrayList<>();

        String sql = "SELECT a.*, i.usuario_fk usuario, i.estande_fk estande, i.criterioavaliacao_fk criterio FROM avaliacaoJurado a JOIN informacoesParaAvaliacao i ON a.informacoesParaAvaliacao_fk = i.id WHERE 1=1";

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
            AvaliacaoJurado avaliacaoJurado = new AvaliacaoJurado();

            avaliacaoJurado.setId(rs.getLong("id"));
            avaliacaoJurado.setNota(rs.getLong("nota"));
            avaliacaoJurado.setOpiniao(rs.getString("opiniao"));
            avaliacaoJurado.setStatus(rs.getString("status"));
            avaliacaoJurado.setDataHoraAbertura(rs.getTimestamp("dataHoraAbertura"));
            avaliacaoJurado.setDataHoraFechamento(rs.getTimestamp("dataHoraFechamento"));

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("usuario"));
            usuario.setId(rs.getLong("usuario_fk"));

            Estande estande = new Estande();
            estande.setTitulo(rs.getString("estande"));
            estande.setId(rs.getLong("estande_fk"));

            CriterioAvaliacao criterio = new CriterioAvaliacao();
            criterio.setNome(rs.getString("criterio"));
            criterio.setId(rs.getLong("criterio_fk"));
            
            avaliacaoJurado.setCriterio(criterio);
            avaliacaoJurado.setUsuario(usuario);
            avaliacaoJurado.setEstande(estande);
            
            avaliacaoJuradoList.add(avaliacaoJurado);
        }

        return avaliacaoJuradoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";

        //USUARIO_EQ
        Long usuario = (Long) criteria.get(AvaliacaoJuradoCriteria.USUARIO_EQ);
        if (usuario != null && usuario > 0) {
            sql += " AND aj.usuario_fk = ?";
            args.add(usuario);
        }
        //NOTA_EQ
        Long nota = (Long) criteria.get(AvaliacaoJuradoCriteria.NOTA_EQ);
        if (nota != null && nota > 0) {
            sql += " AND aj.nota = ?";
            args.add(nota);
        }
        //ESTANDE_EQ
        Long estande = (Long) criteria.get(AvaliacaoJuradoCriteria.ESTANDE_EQ);
        if (estande != null && estande > 0) {
            sql += " AND aj.estande_fk = ?";
            args.add(estande);
        }
        //CRITERIO_EQ
        Long criterio = (Long) criteria.get(AvaliacaoJuradoCriteria.CRITERIO_EQ);
        if (criterio != null && criterio > 0) {
            sql += " AND aj.criterioavaliacao_fk = ?";
            args.add(criterio);
        }

        return sql;
    }

    //Método responsável por calcular a média de notas dadas por Jurados
    //Retorna para o cliente um mapa contendo como chave o id do estande e como valor a média de notas.
    //Calcula a média de todas as notas independentemente de critério avaliado
    public Map<Long, Long> calcularMediaDeNotas(Connection conn) throws SQLException {
        Map<Long, Long> mediaList = new HashMap<>();
        String sql = "select estande_fk, avg(nota) from avaliacaoJurado aj join informacoesParaAvaliacao ipa on aj.informacoesParaAvaliacao_fk = ipa.id group by estande_fk order by estande_fk";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            mediaList.put(rs.getLong("estande_fk"), rs.getLong("avg"));
        }

        return mediaList;
    }
}