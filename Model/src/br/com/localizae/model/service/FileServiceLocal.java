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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.IOUtils;

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
            if(file.getUri() == null || file.getUri().isEmpty()){
                file.setUri(String.valueOf(System.currentTimeMillis()));
            }
            dao.create(conn, file);

            if (!file.getBase64().isEmpty()) {
                
               // byte[] data = Base64.getDecoder().decode(file.getBase64().replace("\\",""));
                byte[] data = DatatypeConverter.parseBase64Binary(file.getBase64());
                int teste = data.length;
                System.out.println(data);
                try (OutputStream stream = new FileOutputStream(Constantes.PATH_FILE + file.getUri())) {
                    stream.write(data);
                }
            } else if (file.getFile().length != 0) {
                FileOutputStream fos = new FileOutputStream(Constantes.PATH_FILE + file.getUri());
                fos.write(file.getFile());

                fos.close();
            }else{
                throw new IllegalArgumentException("É necessário o envio do arquivo como array de byte ou como base64.");
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            System.out.println(e.getMessage());
            throw e;
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
            FileInputStream fis = new FileInputStream(Constantes.PATH_FILE + arq.getUri());
            arq.setFile(IOUtils.toByteArray(fis));
            file = arq.getFile();
            fis.close();
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

            File arq = dao.readById(conn, id);
            
            FileInputStream fis = new FileInputStream(Constantes.PATH_FILE + arq.getUri());
            
            arq.setFile(IOUtils.toByteArray(fis));
            file = arq.getFile();
            fis.close();
        } catch (Exception ex) {
            throw ex;
        }

        return file;
    }

}
