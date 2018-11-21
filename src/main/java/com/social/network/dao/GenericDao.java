package com.social.network.dao;

import java.util.List;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public interface GenericDao<T> {

    public T get(int key);

    public T insert(T object);

    public T update(T object);

    public void delete(T object);

    public List<T> getAll();
}
