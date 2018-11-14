package com.social.network.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public abstract class AbstractJdbcDAO<T extends Identifiable> implements GenericDao<T> {
    protected static final Logger logger = Logger.getLogger(AbstractJdbcDAO.class);

    private static final String WHERE_ID = " WHERE ID = ?";
    private Connective connective;

    public AbstractJdbcDAO(Connective connective) {
        this.connective = connective;
    }
    public abstract String getTableName();
    public abstract String getInsertQuery();
    public abstract String getUpdateQuery();
    public abstract PreparedStatement prepareStatementForUpdate (PreparedStatement st, T entity) throws SQLException;
    public abstract PreparedStatement prepareStatementForInsert (PreparedStatement st, T entity) throws SQLException;
    public abstract List<T> parseResultSet (ResultSet rs);

    private String getSelectQuery() {
        return "SELECT * FROM " + getTableName();
    }
    private String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + WHERE_ID;
    }

    public T get(int id) {
        String sql = getSelectQuery() + WHERE_ID;
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            List<T> list  = parseResultSet(rs);
            return list.get(0);
        } catch (SQLException e) {
            logger.error(String.format("Can't get object with id=%s in the database", id));
            throw new RuntimeException();
        }
    }

    public void insert(T entity) {
        String sql = getInsertQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            prepareStatementForInsert(stm, entity);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format("Can't insert object with id=%s in the database", entity.getId()));
            throw new RuntimeException();
        }
    }

    public void update(T entity) {
        String sql = getUpdateQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            prepareStatementForUpdate(stm, entity);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format("Can't update object with id=%s in the database", entity.getId()));
            throw new RuntimeException();
        }
    }

    public void delete(T entity) {
        String sql = getDeleteQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setInt(1, entity.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format("Can't delete object with id=%s from the database", entity.getId()));
            throw new RuntimeException();
        }
    }

    @Override
    public List<T> getAll() {
        String sql = getSelectQuery();
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Can't get all entities from the database");
            throw new RuntimeException();
        }
    }

}
