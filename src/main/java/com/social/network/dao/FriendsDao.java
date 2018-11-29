package com.social.network.dao;

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

import static com.social.network.dao.MessagesDao.getUserFromRow;

public class FriendsDao {
    private static final Logger logger = Logger.getLogger(MessagesDao.class);
    private static final String CAN_T_GET_ALL_USER_FRIENDS_ENTITIES = "Can't get all user friends entities from the database with id = ";
    private static final String CAN_T_GET_ALL_USER_FRIENDS_REQUESTS = "Can't get all user friends requests from the database with id";
    private static final String CAN_T_CHECK_FRIENDSHIP = "Can't check friendship for the users with ids: %s, %s";
    private static final String CAN_T_INSERT_ADD_TO_FRIEND_REQUEST_USER_S_FRIEND_S = "Can't insert add to friend request -> user: %s, friend %s";
    private static final String CAN_T_ACCEPT_FRIEND_REQUEST_USER_S_FRIEND_S = "Can't accept friend request -> user: %s, friend %s";
    private static final String CAN_T_REMOVE_FRIEND_REQUEST_USER_S_FRIEND_S = "Can't remove friend request -> user: %s, friend %s";

    private static final String FRIENDS_OF_USER = "SELECT * FROM user_friends_requests where (sid = ? OR rid = ?) AND accepted = TRUE";
    private static final String FRIENDS_REQUESTS_OF_USER = "SELECT * FROM user_friends_requests WHERE (sid = ? OR rid = ?) AND accepted = FALSE";
    private static final String FRIENDSHIP = "SELECT * FROM user_friends_requests WHERE (sid = ? AND rid = ?) or (sid = ? AND rid = ?)";
    private static final String ADD_FRIEND_REQUEST = "INSERT INTO friendship VALUES(NULL, ?, ?, false)";
    private static final String ACCEPT_FRIEND_REQUEST =
            "UPDATE friendship set accepted = TRUE WHERE (usersender = ? AND userreceiver = ?) OR (usersender = ? AND userreceiver = ?)";
    private static final String DELETE_FRIEND_REQUEST =
            "DELETE FROM friendship WHERE (usersender = ? AND userreceiver = ?) OR (usersender = ? AND userreceiver = ?)";

    private final Connective connective;

    public FriendsDao(Connective connective) {
        this.connective = connective;
    }

    public List<UserFriend> getFriends(int userId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(FRIENDS_OF_USER)) {
            stm.setInt(1, userId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            List<UserFriend> friends = parseResultSet(rs);
            friends.forEach(f -> f.setFriend(f.getUserSender().getId() == userId ? f.getUserReceiver() : f.getUserSender()));
            return friends;
        } catch (SQLException e) {
            String message = CAN_T_GET_ALL_USER_FRIENDS_ENTITIES + userId;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    public List<UserFriend> getFriendsRequests(int userId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(FRIENDS_REQUESTS_OF_USER)) {
            stm.setInt(1, userId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            List<UserFriend> friends = parseResultSet(rs);
            friends.forEach(f -> f.setFriend(f.getUserSender().getId() == userId ? f.getUserReceiver() : f.getUserSender()));
            return friends;
        } catch (SQLException e) {
            String message = CAN_T_GET_ALL_USER_FRIENDS_REQUESTS + userId;
            logger.error(message);
            throw new RuntimeException(message);
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
            String message = String.format(CAN_T_CHECK_FRIENDSHIP, user, friend);
            logger.error(message);
            throw new RuntimeException(message);
        }
    }
    
    public void addToFriends(int userId, int friendId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(ADD_FRIEND_REQUEST)) {
            stm.setInt(1, userId);
            stm.setInt(2, friendId);
            stm.executeUpdate();
        } catch (SQLException e) {
            String message = String.format(CAN_T_INSERT_ADD_TO_FRIEND_REQUEST_USER_S_FRIEND_S, userId, friendId);
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    public void acceptFriendRequest(int userId, int friendId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(ACCEPT_FRIEND_REQUEST)) {
            stm.setInt(1, userId);
            stm.setInt(2, friendId);
            stm.setInt(3, friendId);
            stm.setInt(4, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            String message = String.format(CAN_T_ACCEPT_FRIEND_REQUEST_USER_S_FRIEND_S, userId, friendId);
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    public void declineFriendRequest(int userId, int friendId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(DELETE_FRIEND_REQUEST)) {
            stm.setInt(1, userId);
            stm.setInt(2, friendId);
            stm.setInt(3, friendId);
            stm.setInt(4, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            String message = String.format(CAN_T_REMOVE_FRIEND_REQUEST_USER_S_FRIEND_S, userId, friendId);
            logger.error(message);
            throw new RuntimeException(message);
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
