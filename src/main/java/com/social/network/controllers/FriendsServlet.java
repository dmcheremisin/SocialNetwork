package com.social.network.controllers;

import com.social.network.dao.impl.FriendsDao;
import com.social.network.models.User;
import com.social.network.models.UserFriend;

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

    private FriendsDao friendsDao;

    @Override
    public void init() throws ServletException {
        friendsDao = (FriendsDao) getServletContext().getAttribute("friendsDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        int userId = user.getId();

        List<UserFriend> friendsRequests = friendsDao.getFriendsRequests(userId);
        Map<Boolean, List<UserFriend>> groupBySender = friendsRequests.stream()
                .collect(Collectors.partitioningBy(f -> f.getUserSender().getId() == userId));
        List<UserFriend> friends = friendsDao.getFriends(userId);

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
        }
        doGet(req, resp);
    }
}
