/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.IntegranteEquipe;
import br.com.localizae.model.entity.TipoUsuario;
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
public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public void create(Connection conn, Usuario entity) throws Exception {
        String sql = "INSERT INTO usuario (nome, email, senha, situacao, motivo, tokenRedeSocial, tokenAutenticacao, dataHoraExpiracaoToken, tipoUsuario_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getSenha());
        ps.setString(++i, entity.getSituacao());
        ps.setString(++i, entity.getMotivo());
        ps.setString(++i, entity.getTokenRedeSocial());
        ps.setString(++i, entity.getTokenAutenticacao());
        ps.setTimestamp(++i, entity.getDataHoraExpiracaoToken());
        ps.setLong(++i, entity.getTipoUsuario().getId());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();

        if (entity.getTipoUsuario().getId() == 3) {
            this.createIntegrante(entity, conn);
        }

        if (entity.getTipoUsuario().getId() == 4) {
            this.createJurado(entity, conn);
        }
    }

    private void createJurado(Usuario entity, Connection conn) throws SQLException {
        for (CriterioAvaliacao criterio : entity.getCriterioAvaliacaoList()) {
            String sql = "INSERT INTO criterioJurado (usuario_fk, criterio_fk) VALUES (?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            int i = 0;
            ps.setLong(++i, entity.getId());
            ps.setLong(++i, criterio.getId());

            ps.execute();
            ps.close();
        }
    }

    private void createIntegrante(Usuario entity, Connection conn) throws Exception {
        IntegranteEquipe integrante = (IntegranteEquipe) entity;
        String sql = "INSERT INTO integranteEquipe (responsavel, usuario_fk, estande_fk) VALUES(?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setBoolean(++i, integrante.getResponsavel());
        ps.setLong(++i, integrante.getId());
        ps.setLong(++i, integrante.getEstande().getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, situacao=?, motivo=?, tokenRedeSocial=?, tokenAutenticacao=?, dataHoraExpiracaoToken=?, tipoUsuario_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getSenha());
        ps.setString(++i, entity.getSituacao());
        ps.setString(++i, entity.getMotivo());
        ps.setString(++i, entity.getTokenRedeSocial());
        ps.setString(++i, entity.getTokenAutenticacao());
        ps.setTimestamp(++i, entity.getDataHoraExpiracaoToken());
        ps.setLong(++i, entity.getTipoUsuario().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();

        if (entity.getTipoUsuario().getId() == 3) {
            this.updateIntegrante(entity, conn);
        }

        if (entity.getTipoUsuario().getId() == 4) {
            this.updateJurado(entity, conn);
        }
    }

    private void updateIntegrante(Usuario entity, Connection conn) throws SQLException {
        String sql = "UPDATE integranteEquipe SET responsavel=?, usuario_fk=?, estande_fk=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setBoolean(++i, entity.getIntegranteEquipe().getResponsavel());
        ps.setLong(++i, entity.getId());
        ps.setLong(++i, entity.getIntegranteEquipe().getEstande().getId());

        ps.execute();
        ps.close();
    }

    private void updateJurado(Usuario entity, Connection conn) throws SQLException {
        String sql = "DELETE FROM criterioJurado WHERE usuario_fk = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, entity.getId());

        ps.execute();
        ps.close();

        for (CriterioAvaliacao criterio : entity.getCriterioAvaliacaoList()) {
            sql = "INSERT INTO criterioJurado (usuario_fk, criterioAvaliacao_fk) VALUES (?, ?);";
            PreparedStatement psUpdate = conn.prepareStatement(sql);

            int i = 0;
            psUpdate.setLong(++i, entity.getId());
            psUpdate.setLong(++i, criterio.getId());

            ps.execute();
            ps.close();
        }
    }

    @Override
    public Usuario readById(Connection conn, Long id) throws Exception {
        Usuario usuario = null;
        String sql = "select u.*, tp.nome tipoUsuario, cj.criterioavaliacao_fk, ca.nome criterio, ca.peso, ie.estande_fk, ie.responsavel from usuario u join tipousuario tp on u.tipousuario_fk = tp.id join criteriojurado cj on u.id = cj.usuario_fk join criterioavaliacao ca on cj.criterioavaliacao_fk = ca.id join integranteequipe ie on u.id = ie.usuario_fk where u.id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (usuario == null) {
                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSituacao(rs.getString("situacao"));
                usuario.setDataHoraExpiracaoToken(rs.getTimestamp("dataHoraExpiracaoToken"));
                usuario.setMotivo(rs.getString("situacao"));
                usuario.setId(rs.getLong("id"));
                usuario.setTokenAutenticacao(rs.getString("tokenAutenticacao"));
                usuario.setTokenRedeSocial(rs.getString("tokenRedeSocial"));

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(rs.getLong("tipoUsuario_fk"));
                tipoUsuario.setNome(rs.getString("tipousuario"));

                usuario.setTipoUsuario(tipoUsuario);
            }

            if (usuario.getTipoUsuario().getId() == 4) {
                if (usuario.getCriterioAvaliacaoList().isEmpty()) {
                    usuario.setCriterioAvaliacaoList(new ArrayList<>());
                }
                
                CriterioAvaliacao criterio = new CriterioAvaliacao();
                criterio.setId(rs.getLong("criterioavaliacao_fk"));
                criterio.setNome(rs.getString("criterio"));
                criterio.setPeso(rs.getLong("peso"));
                
                usuario.getCriterioAvaliacaoList().add(criterio);
            }

        }
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        if (criteria == null) {
            criteria = new HashMap<>();
        }
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "select u.*, tp.nome tipoUsuario, cj.criterioavaliacao_fk, ca.nome criterio, ca.peso, ie.estande_fk, ie.responsavel from usuario u join tipousuario tp on u.tipousuario_fk = tp.id join criteriojurado cj on u.id = cj.usuario_fk join criterioavaliacao ca on cj.criterioavaliacao_fk = ca.id join integranteequipe ie on u.id = ie.usuario_fk WHERE 1=1";

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
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setSituacao(rs.getString("situacao"));
            usuario.setDataHoraExpiracaoToken(rs.getTimestamp("dataHoraExpiracaoToken"));
            usuario.setMotivo(rs.getString("motivo"));
            usuario.setId(rs.getLong("id"));
            usuario.setTokenAutenticacao(rs.getString("tokenAutenticacao"));
            usuario.setTokenRedeSocial(rs.getString("tokenRedeSocial"));

            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId(rs.getLong("tipoUsuario_fk"));

            usuario.setTipoUsuario(tipoUsuario);

            usuarioList.add(usuario);
        }
        return usuarioList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";

        String nome = (String) criteria.get(UsuarioCriteria.NOME_EQ);
        if (nome != null && !nome.isEmpty()) {
            sql += " AND nome ILIKE ?";
            nome = "%" + nome + "%";
            args.add(nome);
        }

        String email = (String) criteria.get(UsuarioCriteria.EMAIL_EQ);
        if (email != null && !email.isEmpty()) {
            sql += " AND email ILIKE ?";
            email = "%" + email + "%";
            args.add(email);
        }

        String senha = (String) criteria.get(UsuarioCriteria.SENHA_EQ);
        if (senha != null && !senha.isEmpty()) {
            sql += " AND senha ILIKE ?";
            senha = "%" + senha + "%";
            args.add(senha);
        }

//        boolean is_visitante = (boolean)criteria.get(UsuarioCriteria.IS_VISITANTE);
//        if(is_visitante != false){
//            sql = " AND tipoUsuario_fk = ?";
//            args.add(2);
//        }
//        
//        boolean is_administrador = (boolean)criteria.get(UsuarioCriteria.IS_ADMINISTRADOR);
//        if(is_administrador != false){
//            sql = " AND tipoUsuario_fk = ?";
//            args.add(1);
//        }
//        
//        boolean is_expositor = (boolean)criteria.get(UsuarioCriteria.IS_EXPOSITOR);
//        if(is_expositor != false){
//            sql = " AND tipoUsuario_fk = ?";
//            args.add(3);
//        }
//        
//        boolean is_jurado = (boolean)criteria.get(UsuarioCriteria.IS_JURADO);
//        if(is_jurado != false){
//            sql = " AND tipo_usuario_fk = ?";
//            args.add(4);
//        }
//        
//        boolean is_bloqueado = (boolean)criteria.get(UsuarioCriteria.IS_BLOQUEADO);
//        if(is_bloqueado != false){
//            sql = " AND bloqueado is ?";
//            args.add(true);
//        }
        return sql;
    }
}
