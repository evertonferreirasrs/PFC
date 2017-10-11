/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseBeaconService;
import br.com.localizae.model.dao.BeaconDAO;
import br.com.localizae.model.entity.Beacon;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class BeaconService implements BaseBeaconService {

    @Override
    public void create(Beacon entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Beacon entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Beacon readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePartial(Beacon entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Beacon> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<Beacon> beaconList = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        if(criteria == null){
            criteria = new HashMap<>();
        }
        try {
            BeaconDAO dao = new BeaconDAO();
            beaconList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }finally{
            conn.close();
        }

        return beaconList;
    }

    @Override
    public void validate(Beacon entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
