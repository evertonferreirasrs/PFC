/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.base;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipe LocalizaÊ
 */
public interface BaseService <E> {

    public void create(E entity) throws Exception;
    public void update(E entity) throws Exception;
    public void delete(Long id) throws Exception;
    public E readById(Long id) throws Exception;
    public void updatePartial(E entity) throws Exception;
    public List<E> readByCriteria(Map<Enum, Object> criteria, Long limit, Long offset) throws Exception;
}
