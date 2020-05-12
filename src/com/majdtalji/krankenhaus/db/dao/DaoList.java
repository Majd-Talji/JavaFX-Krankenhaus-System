package com.majdtalji.krankenhaus.db.dao;

import java.util.List;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public interface DaoList<T> {

    public List<T> loadAll() throws Exception;

    public int insert(T t) throws Exception;

    public int update(T t) throws Exception;

    public int delete(T t) throws Exception;

    public T getData(T t) throws Exception;

    public T getDataById(int id) throws Exception;

}
