/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.base.service;

import br.com.localizae.model.entity.File;

/**
 *
 * @author marca
 */
public interface FileServiceBase {
    public void upload(File file) throws Exception;
    public byte[] download(String uri) throws Exception ;
    public byte[] download(Long id) throws Exception ;
}
