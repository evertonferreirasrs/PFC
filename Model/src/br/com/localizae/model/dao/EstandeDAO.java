/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
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
        String sql = "INSERT INTO estande (curso, descricao, periodo, titulo, areaTematica, numero, evento_fk, posicaoX, posicaoY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getCurso());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getPeriodo());
        ps.setString(++i, entity.getTitulo());
        ps.setString(++i, entity.getAreaTematica());
        ps.setLong(++i, entity.getNumero());
        ps.setLong(++i, entity.getEvento().getId());
        ps.setLong(++i, entity.getPosicaoX());
        ps.setLong(++i, entity.getPosicaoY());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
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
        String sql = "UPDATE estande SET curso=?, descricao=?, periodo=?, titulo=?, areaTematica=?, numero=?, evento_fk=?, posicaoX=?, posicaoY=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getCurso());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getPeriodo());
        ps.setString(++i, entity.getTitulo());
        ps.setString(++i, entity.getAreaTematica());
        ps.setLong(++i, entity.getNumero());
        ps.setLong(++i, entity.getEvento().getId());
        ps.setLong(++i, entity.getPosicaoX());
        ps.setLong(++i, entity.getPosicaoY());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public Estande readById(Connection conn, Long id) throws Exception {
        Estande estande = null;
        String sql = "SELECT e.*, i.usuario_fk, i.responsavel, u.nome usuario, u.email, ev.nome evento FROM estande e FULL JOIN integranteEquipe i ON e.id = i.estande_fk LEFT JOIN usuario u ON u.id = i.usuario_fk JOIN evento ev ON ev.id = e.evento_fk WHERE e.id=?";

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
                estande.setTitulo(rs.getString("titulo"));
                estande.setNumero(rs.getLong("numero"));
                estande.setPeriodo(rs.getLong("periodo"));
                estande.setPosicaoX(rs.getLong("posicaoX"));
                estande.setPosicaoY(rs.getLong("posicaoY"));
                estande.setEquipe(new ArrayList<>());

                Evento evento = new Evento();
                evento.setNome(rs.getString("evento"));
                evento.setId(rs.getLong("evento_fk"));

                estande.setEvento(evento);
            }

            IntegranteEquipe integranteEquipe = new IntegranteEquipe();

            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usuario_fk"));
            usuario.setNome(rs.getString("usuario"));
            usuario.setEmail(rs.getString("email"));
            integranteEquipe.setResponsavel(rs.getBoolean("responsavel"));
            integranteEquipe.setUsuario(usuario);

            if (usuario.getId() != 0) {
                estande.getEquipe().add(integranteEquipe);
            }
        }

        return estande;
    }

    @Override
    public List<Estande> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if (criteria == null) {
            criteria = new HashMap<>();
        }
        List<Estande> estandeList = new ArrayList<>();

        String sql = "SELECT e.*, i.usuario_fk, i.responsavel, u.nome usuario, u.email, ev.nome evento FROM estande e FULL JOIN integranteEquipe i ON e.id = i.estande_fk LEFT JOIN usuario u ON u.id = i.usuario_fk JOIN evento ev ON ev.id = e.evento_fk WHERE 1=1";
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);

        sql += " order by e.id";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        for (Object obj : args) {
            ps.setObject(++i, obj);
        }
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Estande estande = null;
            if (estandeList.isEmpty()) {
                estande = new Estande();
                estande.setAreaTematica(rs.getString("areaTematica"));
                estande.setCurso(rs.getString("curso"));
                estande.setDescricao(rs.getString("descricao"));
                estande.setId(rs.getLong("id"));
                estande.setTitulo(rs.getString("titulo"));
                estande.setNumero(rs.getLong("numero"));
                estande.setPeriodo(rs.getLong("periodo"));
                estande.setPosicaoX(rs.getLong("posicaoX"));
                estande.setPosicaoY(rs.getLong("posicaoY"));
                estande.setEquipe(new ArrayList<>());

                Evento evento = new Evento();
                evento.setNome(rs.getString("evento"));
                evento.setId(rs.getLong("evento_fk"));

                estande.setEvento(evento);

                estandeList.add(estande);
            } else {
                Estande ultimoEstande = estandeList.get(estandeList.size() - 1);
                if (ultimoEstande.getId() == rs.getLong("id")) {
                    estande = ultimoEstande;
                    IntegranteEquipe integranteEquipe = new IntegranteEquipe();

                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("usuario_fk"));
                    usuario.setNome(rs.getString("usuario"));
                    usuario.setEmail(rs.getString("email"));
                    integranteEquipe.setResponsavel(rs.getBoolean("responsavel"));

                    if (usuario.getId() != 0) {
                        estande.getEquipe().add(integranteEquipe);
                    }
                } else {
                    estande = new Estande();
                    estande.setAreaTematica(rs.getString("areaTematica"));
                    estande.setCurso(rs.getString("curso"));
                    estande.setDescricao(rs.getString("descricao"));
                    estande.setId(rs.getLong("id"));
                    estande.setTitulo(rs.getString("titulo"));
                    estande.setNumero(rs.getLong("numero"));
                    estande.setPeriodo(rs.getLong("periodo"));
                    estande.setEquipe(new ArrayList<>());

                    Evento evento = new Evento();
                    evento.setNome(rs.getString("evento"));
                    evento.setId(rs.getLong("evento_fk"));

                    estande.setEvento(evento);

                    estandeList.add(estande);

                    IntegranteEquipe integranteEquipe = new IntegranteEquipe();

                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("usuario_fk"));
                    usuario.setNome(rs.getString("usuario"));
                    usuario.setEmail(rs.getString("email"));
                    integranteEquipe.setResponsavel(rs.getBoolean("responsavel"));

                    if (usuario.getId() != 0) {
                        estande.getEquipe().add(integranteEquipe);
                    }
                }
            }
        }

        return estandeList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";

        //NOME_EQ
        String nome = (String) criteria.get(EstandeCriteria.TITULO_EQ);
        if (nome != null && !nome.isEmpty()) {
            sql += " AND e.titulo ILIKE ?";
            nome = "%" + nome + "%";
            args.add(nome);
        }
        //CURSO_EQ
        String curso = (String) criteria.get(EstandeCriteria.CURSO_EQ);
        if (curso != null && !curso.isEmpty()) {
            sql += " AND e.curso ILIKE ?";
            curso = "%" + curso + "%";
            args.add(curso);
        }
        //PERIODO_EQ
        Long periodo = (Long) criteria.get(EstandeCriteria.PERIODO_EQ);
        if (periodo != null && periodo > 0) {
            sql += " AND e.periodo = ?";
            args.add(periodo);
        }
        //NUMERO_EQ
        Long numero = (Long) criteria.get(EstandeCriteria.NUMERO_EQ);
        if (numero != null && numero > 0) {
            sql += " AND e.numero = ?";
            args.add(numero);
        }
        //AREATEMATICA_EQ
        String areaTematica = (String) criteria.get(EstandeCriteria.AREATEMATICA_EQ);
        if (areaTematica != null && !areaTematica.isEmpty()) {
            sql += " AND e.areaTematica ILIKE ?";
            areaTematica = "%" + areaTematica + "%";
            args.add(areaTematica);
        }

        Long usuario_fk = (Long) criteria.get(EstandeCriteria.USUARIO_FK_EQ);
        if (usuario_fk != null && usuario_fk > 0) {
            sql += " AND i.usuario_fk = ?";
            args.add(usuario_fk);
        }

        return sql;
    }

    @Override
    public void updatePartial(Connection conn, Estande entity) throws Exception {
        String sql = "UPDATE estande SET id=?";
        List<Object> args = new ArrayList<>();
        args.add(entity.getId());
        
        if(entity.getTitulo() != null && !entity.getTitulo().isEmpty()){
            sql += ", titulo=?";
            args.add(entity.getTitulo());
        }
        
        if(entity.getAreaTematica() != null && !entity.getAreaTematica().isEmpty()){
            sql += ", areaTematica=?";
            args.add(entity.getAreaTematica());
        }
        
        if(entity.getCurso() != null && !entity.getCurso().isEmpty()){
            sql += ", curso=?";
            args.add(entity.getCurso());
        }
        
        if(entity.getDescricao() != null && !entity.getDescricao().isEmpty()){
            sql += ", descricao=?";
            args.add(entity.getDescricao());
        }
        
        if(entity.getEvento() != null && entity.getEvento().getId() != null){
            sql += ", evento_fk=?";
            args.add(entity.getEvento().getId());
        }
        
        if(entity.getPosicaoX()!= null){
            sql += ", posicaoX=?";
            args.add(entity.getPosicaoX());
        }
        
        if(entity.getPosicaoY()!= null){
            sql += ", posicaoY=?";
            args.add(entity.getPosicaoY());
        }
        
        if(entity.getNumero() != null){
            sql += ", numero=?";
            args.add(entity.getNumero());
        }
        
        if(entity.getPeriodo() != null){
            sql += ", periodo=?";
            args.add(entity.getPeriodo());
        }
        
        sql += " WHERE id=?";
        args.add(entity.getId());
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        for(Object arg : args){
            ps.setObject(++i, arg);
        }
        
        ps.execute();
        ps.close();
    }

}
