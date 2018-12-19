package com.social.network.controllers;

import com.social.network.dao.FriendsDao;
import com.social.network.models.User;
import com.social.network.models.UserFriend;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.social.network.utils.ServerUtils.getUserFromSession;
import static com.social.network.utils.ServerUtils.isInteger;
import static com.social.network.utils.ServerUtils.isNotBlank;

public class FriendsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FriendsServlet.class);
    private static final String FRIEND_WRONG_ACTION_REQUEST = "Friends action request with non-valid parameters action=%s , friend=%s";
    private FriendsDao friendsDao;

    @Override
    public void init() throws ServletException {
        friendsDao = (FriendsDao) getServletContext().getAttribute("friendsDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        int userId = user.getId();

        String search = req.getParameter("search");

        List<UserFriend> friendsRequests = friendsDao.getFriendsRequests(userId);
        if(isNotBlank(search)){
            logger.info("Friends search request: " + search);
            String name = search.toLowerCase();
            friendsRequests = friendsRequests.stream()
                    .filter(f -> searchByName(name, f.getFriend()))
                    .collect(Collectors.toList());
        }
        Map<Boolean, List<UserFriend>> groupBySender = friendsRequests.stream()
                .collect(Collectors.partitioningBy(f -> f.getUserSender().getId() == userId));

        List<UserFriend> friends = friendsDao.getFriends(userId);
        if(isNotBlank(search)){
            String name = search.toLowerCase();
            friends = friends.stream()
                    .filter(f -> searchByName(name, f.getFriend()))
                    .collect(Collectors.toList());
        }

        req.setAttribute("usersRequests", groupBySender.get(true));
        req.setAttribute("friendsRequests", groupBySender.get(false));
        req.setAttribute("friends", friends);

        req.getRequestDispatcher("friends.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        Integer userId = user.getId();

        String action = req.getParameter("action");
        String friend = req.getParameter("friend");
        if(isNotBlank(action) && isInteger(friend) && !friend.equals(userId.toString())){
            int friendId = Integer.parseInt(friend);
            switch (action) {
                case "addToFriends":
                    friendsDao.addToFriends(userId, friendId);
                    break;
                case "accept":
                    friendsDao.acceptFriendRequest(userId, friendId);
                    break;
                case "decline":
                case "remove":
                    friendsDao.declineFriendRequest(userId, friendId);
                    break;
            }
        } else {
            logger.error(String.format(FRIEND_WRONG_ACTION_REQUEST, action, friend));
        }
        doGet(req, resp);
    }

    private static boolean searchByName(String name, User u) {
        return (u.getFirstName().toLowerCase() + " " + u.getLastName().toLowerCase()).contains(name) ||
                (u.getLastName().toLowerCase() + " " + u.getFirstName().toLowerCase()).contains(name);
    }
}
