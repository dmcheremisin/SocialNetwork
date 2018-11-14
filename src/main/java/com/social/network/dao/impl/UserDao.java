package com.social.network.dao.impl;

import com.social.network.dao.AbstractJdbcDAO;
import com.social.network.dao.Connective;
import com.social.network.exceptions.PersistException;
import com.social.network.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public class UserDao extends AbstractJdbcDAO<User> {
    public UserDao(Connective connective) {
        super(connective);
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //INSERT INTO USERS (firstname, lastname, dob, sex, phone, email, passwordhash, role, blocked) VALUES ('Дима', 'Ч', '1987-09-28', 1, '+7 999 235 6695', 'admin@adm.ru', '123', 1, 'false');
        //INSERT INTO USERS VALUES (null,'Дима', 'Ч', '1987-09-28', 1, '+7 999 235 6695', 'admin21@adm.ru', '123', 1, 'false');
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public PreparedStatement prepareStatementForUpdate(PreparedStatement st, User entity) throws SQLException, PersistException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatementForInsert(PreparedStatement st, User entity) throws PersistException {
        return null;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws PersistException {
        return null;
    }

}
