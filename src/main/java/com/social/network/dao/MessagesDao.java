package com.social.network.dao;

import com.social.network.connection.Connective;
import com.social.network.models.Message;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmitrii on 23.11.2018.
 */
public class MessagesDao {
    private static final Logger logger = Logger.getLogger(MessagesDao.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Connective connective;
    private String SELECT_LAST_QUERY = "SELECT * FROM last_user_message WHERE sid = ? OR rid = ? ";
    private String SELECT_BOTH_QUERY = "SELECT * FROM user_message WHERE (sid = ? AND rid = ?) OR (sid = ? AND rid = ?);";
    private String INSERT_QUERY = "INSERT INTO messages VALUES(NULL, ?, ?, ?, ?);";

    public MessagesDao(Connective connective) {
        this.connective = connective;
    }

    public List<Message> getRecentMessages(Integer userId) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(SELECT_LAST_QUERY);) {
            stm.setInt(1, userId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Can't get all entities from the database");
            throw new RuntimeException();
        }
    }

    public List<Message> getBothMessages(Integer sender, Integer receiver) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(SELECT_BOTH_QUERY);) {
            stm.setInt(1, sender);
            stm.setInt(2, receiver);
            stm.setInt(3, receiver);
            stm.setInt(4, sender);
            ResultSet rs = stm.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("Can't get all entities from the database");
            throw new RuntimeException();
        }
    }

    public void addMessage(int sender, int receiver, String message) {
        try (Connection con = connective.getConnection();
             PreparedStatement stm = con.prepareStatement(INSERT_QUERY);) {
            LocalDateTime now = LocalDateTime.now();
            stm.setString(1, DATE_TIME_FORMATTER.format(now));
            stm.setInt(2, sender);
            stm.setInt(3, receiver);
            stm.setString(4, message);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't inset message to the database");
            throw new RuntimeException();
        }
    }

    private List<Message> parseResultSet(ResultSet rs) {
        List<Message> messages = new LinkedList<>();
        try {
            while (rs.next()) {
                Message messageModel = new Message();

                Integer id = rs.getInt("id");
                LocalDateTime dateTime = rs.getTimestamp("dt").toLocalDateTime();
                DateTimeFormatter shortDt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
                String dateString = shortDt.format(dateTime);
                String message = rs.getString("message");

                User userSender = getUserFromRow(rs, "sid", "sfirstname", "slastname", "simage");
                User userReceiver = getUserFromRow(rs, "rid", "rfirstname", "rlastname", "rimage");

                messageModel.setId(id);
                messageModel.setDate(dateString);
                messageModel.setMessage(message);
                messageModel.setSender(userSender);
                messageModel.setReceiver(userReceiver);

                messages.add(messageModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    static User getUserFromRow(ResultSet rs, String id, String firstName, String lastName, String image) throws SQLException {
        Integer userId = rs.getInt(id);
        String userFirstName = rs.getString(firstName);
        String userLastName = rs.getString(lastName);
        String userImage = rs.getString(image);

        User user = new User();
        user.setId(userId);
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);
        user.setImage(userImage);

        return user;
    }
}
