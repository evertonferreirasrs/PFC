/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.BaseCriterioJuradoService;
import br.com.localizae.model.dao.CriterioJuradoDAO;
import br.com.localizae.model.entity.CriterioJurado;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class CriterioJuradoService implements BaseCriterioJuradoService {

    @Override
    public void create(CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriterioJurado readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePartial(CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CriterioJurado> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception {
        List<CriterioJurado> criterioList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {    
            CriterioJuradoDAO dao = new CriterioJuradoDAO();
            criterioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
        }catch(Exception ex){
            conn.rollback();
        }finally{
            conn.close();
        }
        
        return criterioList;
    }

    @Override
    public void validate(CriterioJurado entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
