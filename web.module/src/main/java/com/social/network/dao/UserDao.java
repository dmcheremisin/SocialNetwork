package com.social.network.dao;

import com.social.network.connective.Connective;
import com.social.network.constants.Role;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static com.social.network.utils.ServerUtils.isNotBlank;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public class UserDao{
    private static final Logger logger = Logger.getLogger(UserDao.class);
    private static final String CREATION_OF_USER_FAILED_NO_ID_OBTAINED = "Creation of user failed, no id obtained.";
    private static final String CAN_T_INSERT_USER_IN_THE_DATABASE = "Can't insert user in the database: ";
    private static final String CAN_T_UPDATE_USER_WITH_ID_S_IN_THE_DATABASE = "Can't update user with id=%s in the database";
    private static final String CAN_T_GET_ALL_USERS_FROM_THE_DATABASE = "Can't get all users from the database";
    private static final String CAN_T_DELETE_USER_WITH_ID_S_FROM_THE_DATABASE = "Can't delete user with id=%s from the database";
    private static final String CAN_T_UPDATE_IMAGE = "Can't update image of user with id=%s in the database";
    private static final String CAN_T_UPDATE_USER_PASSWORD = "Can't update user with id=%s with new password in the database";
    private static final String CAN_T_BLOCK_UNBLOCK_USER = "Can't block user with id=% and blocked flag=%s";
    private static final String CAN_T_SET_PRIVILEGES_USER = "Can't set privileges to user with id=% and role=%s";
    private static final String CAN_T_PARSE_USER_RESULT_SET = "Can't parse user result set";
    
    private static final String SELECT_ALL_USERS = "SELECT * FROM users WHERE id != ? LIMIT 10 OFFSET ?";
    private static final String SEARCH_USERS = "SELECT * FROM users WHERE id != ? AND LOWER(concat((firstname), ' ', lastname)) LIKE ? LIMIT 10 OFFSET ?;";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET firstname=?, lastname=?, dob=?, sex=?, phone=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String INSERT_USER = "INSERT INTO users VALUES(NULL, NULL, NULL, NULL, 1, NULL, ?, ?, 2, 'false', NULL);";
    private static final String SELECT_FROM_USERS_WHERE_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";
    private static final String UPDATE_USERS_SET_IMAGE_WHERE_ID = "UPDATE USERS SET image=? WHERE id=?";
    private static final String UPDATE_USERS_SET_PASSWORD_WHERE_ID = "UPDATE USERS SET password=? WHERE id=?";
    private static final String BLOCK_UNBLOCK_USER = "UPDATE USERS SET blocked=? WHERE id=?";
    private static final String SET_PRIVILEGES_USER = "UPDATE USERS SET role=? WHERE id=?";


    private final Connective connective;
    public static final DateTimeFormatter SHORT_DT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public UserDao(Connective connective) {
        this.connective = connective;
    }

    public User get(int id) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(SELECT_USER);) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            List<User> list  = parseResultSet(rs);
            return list.get(0);
        } catch (SQLException e) {
            logger.error(String.format("Can't get object with id=%s in the database", id));
            throw new RuntimeException(e);
        }
    }

    public User insert(User entity) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);) {
            stm.setString(1, entity.getEmail());
            stm.setString(2, entity.getPassword());
            stm.executeUpdate();
            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return get(id);
                } else {
                    throw new SQLException(CREATION_OF_USER_FAILED_NO_ID_OBTAINED);
                }
            }
        } catch (SQLException e) {
            logger.error(CAN_T_INSERT_USER_IN_THE_DATABASE + entity);
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void update(User entity) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(UPDATE_USER);) {
            prepareStatementForUpdate(stm, entity);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format(CAN_T_UPDATE_USER_WITH_ID_S_IN_THE_DATABASE, entity.getId()));
            throw new RuntimeException(e);
        }
    }

    public void delete(User entity) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(DELETE_USER);) {
            stm.setInt(1, entity.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error( String.format(CAN_T_DELETE_USER_WITH_ID_S_FROM_THE_DATABASE, entity.getId()));
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll(int userId, int page) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(SELECT_ALL_USERS);) {
            stm.setInt(1, userId);
            stm.setInt(2, page * 10);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error(CAN_T_GET_ALL_USERS_FROM_THE_DATABASE);
            throw new RuntimeException(e);
        }
    }

    public List<User> searchAll(int userId, int page, String search) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(SEARCH_USERS);) {
            stm.setInt(1, userId);
            stm.setString(2, '%' + search + '%');
            stm.setInt(3, page);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error(CAN_T_GET_ALL_USERS_FROM_THE_DATABASE);
            throw new RuntimeException(e);
        }
    }

    public User getUserByCredentials(String email, String password) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL_AND_PASSWORD);) {
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            List<User> list  = parseResultSet(rs);
            if(list.size() == 1) {
                return list.get(0);
            }
        } catch (SQLException e) {
            logger.error(String.format("No user found with such credentials email = %s and password = ****", email));
        }
        return null;
    }

    public User updateImage(User user) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(UPDATE_USERS_SET_IMAGE_WHERE_ID)) {
            stm.setString(1, user.getImage());
            stm.setInt(2, user.getId());
            stm.executeUpdate();
            return get(user.getId());
        } catch (SQLException e) {
            logger.error(String.format(CAN_T_UPDATE_IMAGE, user.getId()));
            throw new RuntimeException(e);
        }
    }

    public User updatePassword(User user) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(UPDATE_USERS_SET_PASSWORD_WHERE_ID)) {
            stm.setString(1, user.getPassword());
            stm.setInt(2, user.getId());
            stm.executeUpdate();
            return get(user.getId());
        } catch (SQLException e) {
            logger.error(String.format(CAN_T_UPDATE_USER_PASSWORD, user.getId()));
            throw new RuntimeException(e);
        }
    }

    public void blockUnblock(Integer userId, boolean blocked) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(BLOCK_UNBLOCK_USER)) {
            stm.setBoolean(1, blocked);
            stm.setInt(2, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format(CAN_T_BLOCK_UNBLOCK_USER, userId, blocked));
            throw new RuntimeException(e);
        }
    }

    public void setPrivileges(Integer userId, Role role) {
        try(Connection con = connective.getConnection();
            PreparedStatement stm = con.prepareStatement(SET_PRIVILEGES_USER)) {
            stm.setInt(1, role.getKey());
            stm.setInt(2, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format(CAN_T_SET_PRIVILEGES_USER, userId, role.getRoleString()));
            throw new RuntimeException(e);
        }
    }

    private List<User> parseResultSet(ResultSet rs) {
        List<User> users = new LinkedList<>();
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
                String image = rs.getString("image");

                User user = new User();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if(dob != null){
                    LocalDateTime dateTime = rs.getTimestamp("dob").toLocalDateTime();
                    String dateString = SHORT_DT.format(dateTime);
                    user.setDob(dateString);
                }
                user.setSex(sex);
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
                user.setBlocked(blocked);
                user.setImage(image);

                users.add(user);
            }
        }catch (Exception e) {
            logger.error(CAN_T_PARSE_USER_RESULT_SET);
        }
        return users;
    }

    private PreparedStatement prepareStatementForUpdate(PreparedStatement st, User entity) throws SQLException {
        st.setString(1, entity.getFirstName());
        st.setString(2, entity.getLastName());
        String dob = entity.getDob();
        if(isNotBlank(dob)) {
            try {
                java.util.Date dateParsed = FORMAT.parse(dob);
                Date date = new Date(dateParsed.getTime());
                st.setDate(3, date);
            } catch (ParseException e) {
                logger.error("Can't parse date string = " + dob);
            }
        }
        st.setInt(4, entity.getSex());
        st.setString(5, entity.getPhone());
        st.setInt(6, entity.getId());
        return st;
    }
}
