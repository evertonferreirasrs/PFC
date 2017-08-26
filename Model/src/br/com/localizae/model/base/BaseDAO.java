/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public interface BaseDAO <E extends BaseEntity>{
    public void create(Connection conn, E entity) throws Exception;
    public void delete(Connection conn, Long id) throws Exception;
    public void update(Connection conn, E entity) throws Exception;
    public void updatePartial(Connection conn, E entity) throws Exception;
    public E readById(Connection conn, Long id) throws Exception;
    public List<E> readByCriteria(Connection conn, Map<Enum, Object> criteria, Long limit, Long offset) throws Exception;
    public String applyCriteria(Map<Enum, Object> criteria, List<Object> args) throws Exception;
}
