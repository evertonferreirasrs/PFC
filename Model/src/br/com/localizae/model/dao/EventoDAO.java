package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.EventoCriteria;
import br.com.localizae.model.entity.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoDAO implements BaseDAO<Evento> {

    @Override
    public void create(Connection conn, Evento entity) throws Exception {
        String sql = "INSERT INTO evento (nome, endereco, dataHoraEventoInicio, dataHoraEventoFim) VALUES (?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEndereco());
        ps.setTimestamp(++i, entity.getDataHoraEventoInicio());
        ps.setTimestamp(++i, entity.getDataHoraEventoFim());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM evento WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Evento entity) throws Exception {
        String sql = "UPDATE evento SET nome=?, endereco=?, dataHoraEventoInicio=?, dataHoraEventoFim=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEndereco());
        ps.setTimestamp(++i, entity.getDataHoraEventoInicio());
        ps.setTimestamp(++i, entity.getDataHoraEventoFim());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public Evento readById(Connection conn, Long id) throws Exception {
        Evento evento = null;

        String sql = "SELECT * FROM evento WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            evento = new Evento();
            evento.setNome(rs.getString("nome"));
            evento.setEndereco(rs.getString("endereco"));
            evento.setDataHoraEventoInicio(rs.getTimestamp("dataHoraEventoInicio"));
            evento.setDataHoraEventoFim(rs.getTimestamp("dataHoraEventoFim"));
            evento.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();

        return evento;
    }

    @Override
    public List<Evento> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if(criteria == null){
            criteria = new HashMap<>();
        }
        List<Evento> eventoList = new ArrayList<>();

        String sql = "SELECT * FROM evento WHERE 1=1";
        List<Object> args = new ArrayList<>();
        
        sql += this.applyCriteria(criteria, args);
        
        sql += "order by evento.id";
        
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

        while (rs.next()) {
            Evento evento = new Evento();
            evento.setNome(rs.getString("nome"));
            evento.setEndereco(rs.getString("endereco"));
            evento.setDataHoraEventoInicio(rs.getTimestamp("dataHoraEventoInicio"));
            evento.setDataHoraEventoFim(rs.getTimestamp("dataHoraEventoFim"));
            evento.setId(rs.getLong("id"));
            
            eventoList.add(evento);
        }

        rs.close();
        ps.close();

        return eventoList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        String nome = (String)criteria.get(EventoCriteria.NOME_ILIKE);
        if(nome != null && !nome.isEmpty()){
            sql += " AND nome ILIKE ?";
            nome = "%"+nome+"%";
            args.add(nome);
        }
        
        String endereco = (String)criteria.get(EventoCriteria.ENDERECO_ILIKE);
        if(endereco != null && !endereco.isEmpty()){
            sql += " AND endereco ILIKE ?";
            endereco = "%"+endereco+"%";
            args.add(endereco);
        }
        
        return sql;
    }

}
