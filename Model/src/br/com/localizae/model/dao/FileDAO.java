/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.criteria.FileCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.File;
import br.com.localizae.model.entity.Promocao;
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
public class FileDAO implements BaseDAO<File>{

    @Override
    public void create(Connection conn, File entity) throws Exception {
        String sql = "INSERT INTO imagem (uri, estande_fk, promocao_fk) VALUES (?) RETURNING id;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setString(++i, entity.getUri());
        ps.setLong(++i, entity.getEstande().getId());
        ps.setLong(++i, entity.getPromocao().getId());
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            entity.setId(rs.getLong("id"));
        }
        
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM imagem WHERE id = ?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, File entity) throws Exception {
        String sql = "UPDATE imagem SET uri=? WHERE id=?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setString(++i, entity.getUri());
        ps.setLong(++i, entity.getId());
        
        ps.execute();
        ps.close();
    }

    @Override
    public void updatePartial(Connection conn, File entity) throws Exception {
        throw new UnsupportedOperationException("Método não suportado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File readById(Connection conn, Long id) throws Exception {
        File file = null;
        
        String sql = "SELECT i.*, e.titulo estande, p.nome promocao FROM imagem i JOIN estande e ON e.id = i.estande_fk JOIN promocao p ON p.id = i.promocao_fk WHERE id = ?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        ps.setLong(++i, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            file = new File();
            file.setId(id);
            file.setUri(rs.getString("uri"));
            
            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setTitulo(rs.getString("estande"));
            
            Promocao promocao = new Promocao();
            promocao.setId(rs.getLong("promocao_fk"));
            promocao.setNome("promocao");
            
            file.setEstande(estande);
            file.setPromocao(promocao);
        }
        
        return file;
    }

    @Override
    public List<File> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<File> fileList = null;
        
        String sql = "SELECT i.*, e.titulo estande, p.nome promocao FROM imagem i JOIN estande e ON e.id = i.estande_fk JOIN promocao p ON p.id = i.promocao_fk WHERE 1=1";
        
        List<Object> args = new ArrayList<>();
        sql += this.applyCriteria(criteria, args);
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int i = 0;
        for(Object arg : args){
            ps.setObject(++i, arg);
        }
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            File file = new File();
            file.setId(rs.getLong("id"));
            file.setUri(rs.getString("uri"));
            
            Estande estande = new Estande();
            estande.setId(rs.getLong("estande_fk"));
            estande.setTitulo(rs.getString("estande"));
            
            Promocao promocao = new Promocao();
            promocao.setId(rs.getLong("promocao_fk"));
            promocao.setNome("promocao");
            
            file.setEstande(estande);
            file.setPromocao(promocao);
            
            fileList.add(file);
        }
        
        return fileList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        String sql = "";
        
        String uri = (String) criteria.get(FileCriteria.URI_ILIKE);
        if(uri != null && !uri.isEmpty()){
            sql += " AND uri ILIKE ?";
            uri = "%"+uri+"%";
            args.add(uri);
        }
        
        Long estande = (Long) criteria.get(FileCriteria.ESTANDE_EQ);
        if(estande != null){
            sql += " AND estande_fk = ?";
            args.add(estande);
        }
        
        Long promocao = (Long) criteria.get(FileCriteria.PROMO_EQ);
        if(promocao != null){
            sql += " AND promocao_fk = ?";
            args.add(promocao);
        }
        
        return sql;
    }
    
}
