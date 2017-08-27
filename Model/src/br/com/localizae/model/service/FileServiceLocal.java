/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.base.service.FileServiceBase;
import br.com.localizae.model.dao.FileDAO;
import br.com.localizae.model.entity.File;
import br.com.localizae.model.utils.Constantes;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marca
 */
public class FileServiceLocal implements FileServiceBase {

    @Override
    public void upload(File file) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            FileDAO dao = new FileDAO();

            dao.create(conn, file);

            FileOutputStream fos = new FileOutputStream(Constantes.PATH_FILE + file.getUri());
            fos.write(file.getFile());
            fos.close();

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    @Override
    public byte[] download(String uri) throws Exception {
        byte[] file = null;

        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            FileDAO dao = new FileDAO();

            Map<Enum, Object> criteria = new HashMap<>();
            File arq = dao.readByCriteria(conn, criteria, 0l, 0l).get(0);
            file = arq.getFile();
        } catch (Exception ex) {
            throw ex;
        }

        return file;
    }

    @Override
    public byte[] download(Long id) throws Exception {
        byte[] file = null;

        Connection conn;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            FileDAO dao = new FileDAO();

            file = dao.readById(conn, id).getFile();
        } catch (Exception ex) {
            throw ex;
        }

        return file;
    }

}
