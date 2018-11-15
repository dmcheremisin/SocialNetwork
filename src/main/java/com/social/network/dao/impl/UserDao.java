package com.social.network.dao.impl;

import com.social.network.dao.AbstractJdbcDAO;
import com.social.network.dao.Connective;
import com.social.network.models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return "INSERT INTO " + getTableName() + " VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, 2, 'false');";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE USERS SET firstname=?, lastname=?, dob=?, sex=?, phone=? WHERE id=?";
    }

    @Override
    public PreparedStatement prepareStatementForUpdate(PreparedStatement st, User entity) throws SQLException {
        st.setString(1, entity.getFirstName());
        st.setString(2, entity.getLastName());
        Date date = new Date(entity.getDob().getTime());
        st.setDate(3, date);
        st.setInt(4, entity.getSex());
        st.setString(5, entity.getPhone());
        st.setInt(6, entity.getId());
        return st;
    }

    @Override
    public PreparedStatement prepareStatementForInsert(PreparedStatement st, User entity) throws SQLException {
        st.setString(1, entity.getFirstName());
        st.setString(2, entity.getLastName());
        Date date = null;
        if(entity.getDob() != null) {
            date = new Date(entity.getDob().getTime());
        }
        st.setDate(3, date);
        if(entity.getSex() != null) {
            st.setInt(4, entity.getSex());
        } else {
            st.setInt(4, 1);
        }
        st.setString(5, entity.getPhone());
        st.setString(6, entity.getEmail());
        st.setString(7, entity.getPassword());
        return st;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                Date dob = rs.getDate("dob");
                int sex = rs.getInt("sex");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Integer role = rs.getInt("role");
                Boolean blocked = rs.getBoolean("blocked");

                User user = new User();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if(dob != null){
                    user.setDob(new Date(dob.getTime()));
                }
                user.setSex(sex);
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
                user.setBlocked(blocked);

                users.add(user);
            }
        }catch (Exception e) {
            logger.error("Can't parse result set");
        }
        return users;
    }

}
