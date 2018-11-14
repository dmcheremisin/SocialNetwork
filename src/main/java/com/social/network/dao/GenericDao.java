package com.social.network.dao;

import com.social.network.exceptions.PersistException;
import java.util.List;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public interface GenericDao<T> {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T get(int key) throws PersistException;

    /** Создает новую запись и соответствующий ей объект */
    public void insert(T object) throws PersistException;

    /** Сохраняет состояние объекта в базе данных */
    public void update(T object) throws PersistException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T object) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws PersistException;
}
