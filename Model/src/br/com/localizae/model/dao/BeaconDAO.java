/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.base.BaseDAO;
import br.com.localizae.model.entity.Beacon;
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
public class BeaconDAO implements BaseDAO<Beacon>{

    @Override
    public void create(Connection conn, Beacon entity) throws Exception {
        String sql = "INSERT INTO beacon(mac, xCoordinate, yCoordinate) VALUES (?,?,?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getMac());
        ps.setDouble(++i, entity.getyCoordinate());
        ps.setDouble(++i, entity.getyCoordinate());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM beacon WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ps.execute();
        ps.close();
    }

    @Override
    public void update(Connection conn, Beacon entity) throws Exception {
        String sql = "UPDATE beacon SET mac=?, xCoordinate=?, yCoordinate=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setString(++i, entity.getMac());
        ps.setDouble(++i, entity.getxCoordinate());
        ps.setDouble(++i, entity.getyCoordinate());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void updatePartial(Connection conn, Beacon entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Beacon readById(Connection conn, Long id) throws Exception {
        Beacon beacon = null;
        
        String sql = "SELECT * FROM beacon WHERE id = ?;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            beacon = new Beacon();
            beacon.setId(rs.getLong("id"));
            beacon.setMac(rs.getString("mac"));
            beacon.setxCoordinate(rs.getDouble("xCoordinate"));
            beacon.setyCoordinate(rs.getDouble("yCoordinate"));
        }
        
        return beacon;
    }

    @Override
    public List<Beacon> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<Beacon> beaconList = new ArrayList<>();
        
        String sql = "SELECT * FROM beacon";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Beacon beacon = new Beacon();
            beacon.setId(rs.getLong("id"));
            beacon.setMac(rs.getString("mac"));
            beacon.setxCoordinate(rs.getDouble("xCoordinate"));
            beacon.setyCoordinate(rs.getDouble("yCoordinate"));
            
            beaconList.add(beacon);
        }
        
        return beaconList;
    }

    @Override
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
