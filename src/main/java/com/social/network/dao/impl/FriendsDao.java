package com.social.network.dao.impl;

import com.social.network.connection.Connective;
import com.social.network.models.User;
import com.social.network.models.UserFriend;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.social.network.dao.impl.MessagesDao.getUserFromRow;

public class FriendsDao {
    private static final Logger logger = Logger.getLogger(MessagesDao.class);

    public static final String FRIENDS_OF_USER = "SELECT * FROM user_friends_requests where (sid = ? or rid = ?) and accepted = TRUE";
    public static final String FRIENDS_REQUESTS_OF_USER = "SELECT * FROM user_friends_requests where rid = ? and accepted = FALSE";
    public static final String FRIENDSHIP = "SELECT * FROM user_friends_requests where (sid = ? and rid = ?) or (sid = ? and rid = ?)";

    private final Connective connective;

    public FriendsDao(Connective connective) {
        this.connective = connective;
    }

    public List<UserFriend> getFriends(Integer userId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(FRIENDS_OF_USER)) {
            stm.setInt(1, userId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Can't get all user friends entities from the database");
            throw new RuntimeException();
        }
    }

    public List<UserFriend> getFriendsRequests(Integer userId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(FRIENDS_REQUESTS_OF_USER)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Can't get all user friends entities from the database");
            throw new RuntimeException();
        }
    }

    public boolean checkUsersHaveFriendship(int user, int friend) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(FRIENDSHIP)) {
            stm.setInt(1, user);
            stm.setInt(2, friend);
            stm.setInt(3, friend);
            stm.setInt(4, user);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error("Can't get all user friends entities from the database");
            throw new RuntimeException();
        }
    }

    private List<UserFriend> parseResultSet(ResultSet rs) throws SQLException {
        List<UserFriend> list = new LinkedList<>();
        while(rs.next()) {
            UserFriend userFriend = new UserFriend();

            int id = rs.getInt("id");
            User userSender = getUserFromRow(rs, "sid", "sfirstname", "slastname", "simage");
            User userReceiver = getUserFromRow(rs, "rid", "rfirstname", "rlastname", "rimage");

            userFriend.setId(id);
            userFriend.setUserSender(userSender);
            userFriend.setUserReceiver(userReceiver);

            list.add(userFriend);
        }
        return list;
    }
}
