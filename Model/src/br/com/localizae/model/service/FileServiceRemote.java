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
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import javax.xml.bind.DatatypeConverter;



/**
 *
 * @author marca
 */
public class FileServiceRemote implements FileServiceBase {

    @Override
    public void upload(File file) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        if(file == null){
            return ;
        }
        try {
            if(file.getUri() == null || file.getUri().isEmpty()){
                file.setUri(String.valueOf(System.currentTimeMillis()));
            }
            FileDAO dao = new FileDAO();
            dao.create(conn, file);

            if (!file.getBase64().isEmpty()) {
                byte[] data = DatatypeConverter.parseBase64Binary(file.getBase64());
                InputStream fis = new ByteArrayInputStream(data);

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(data.length);
                metadata.setContentType("image/png");
                metadata.setCacheControl("public, max-age=31536000");

                BasicAWSCredentials credentials = new BasicAWSCredentials(Constantes.ACCESSKEYAWS, Constantes.SECRETKEYAWS);

                AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.US_EAST_2).build();
                
                PutObjectRequest putObjectRequest = new PutObjectRequest(Constantes.BUCKET_NAME, file.getUri(), fis, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
                
                s3.putObject(putObjectRequest);
            } else if (file.getFile().length != 0) {
                byte[] data = file.getFile();
                InputStream fis = new ByteArrayInputStream(data);

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(data.length);
                metadata.setContentType("image/png");
                metadata.setCacheControl("public, max-age=31536000");

                BasicAWSCredentials credentials = new BasicAWSCredentials(Constantes.ACCESSKEYAWS, Constantes.SECRETKEYAWS);

                AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.SA_EAST_1).build();
                
                PutObjectRequest putObjectRequest = new PutObjectRequest(Constantes.BUCKET_NAME, file.getUri(), fis, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
                
                s3.putObject(putObjectRequest);
            } else {
                throw new IllegalArgumentException("É necessário o envio do arquivo como array de byte ou como base64.");
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();

            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public byte[] download(String uri) throws Exception {
        return null;
    }

    @Override
    public byte[] download(Long id) throws Exception {
        return null;
    }

}
