package com.social.network.dao;

import com.social.network.connection.ConnectionPool;
import com.social.network.exceptions.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public abstract class AbstractJdbcDAO<T extends Identifiable> implements GenericDao<T> {

    private static final String WHERE_ID = " WHERE ID = ?";
    private Connective connective;

    public AbstractJdbcDAO(Connective connective) {
        this.connective = connective;
    }
    public abstract String getTableName();
    public abstract String getInsertQuery();
    public abstract String getUpdateQuery();
    public abstract PreparedStatement prepareStatementForUpdate (PreparedStatement st, T entity) throws SQLException, PersistException;
    public abstract PreparedStatement prepareStatementForInsert (PreparedStatement st, T entity) throws PersistException;
    public abstract List<T> parseResultSet (ResultSet rs) throws PersistException;

    private String getSelectQuery() {
        return "SELECT * FROM " + getTableName();
    }
    private String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + WHERE_ID;
    }

    public T get(int id) throws PersistException {
        String sql = getSelectQuery() + WHERE_ID;
        List<T> list;
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0 || list.size() > 1) {
            throw new PersistException("Can't find unique object in the database");
        }
        return list.get(0);
    }

    public void insert(T entity) throws PersistException {
        String sql = getInsertQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            prepareStatementForInsert(stm, entity);
            int i = stm.executeUpdate();
            if (i != 1) {
                throw new PersistException("On insert more than 1 record returned: " + i);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void update(T entity) throws PersistException {
        String sql = getUpdateQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            prepareStatementForUpdate(stm, entity);
            int i = stm.executeUpdate();
            if (i != 1) {
                throw new PersistException("On insert more than 1 record returned: " + i);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void delete(T entity) throws PersistException {
        String sql = getDeleteQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setInt(1, entity.getId());
            int i = stm.executeUpdate();
            if (i != 1) {
                throw new PersistException("On delete more than 1 record returned: " + i);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        String sql = getSelectQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e.getMessage());
        }
    }

}
