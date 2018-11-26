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

import static com.social.network.utils.ServerUtils.getUserFromSession;

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
        List<UserFriend> friends = friendsDao.getFriends(userId);

        req.setAttribute("friendsRequests", friendsRequests);
        req.setAttribute("friends", friends);

        friends.forEach(f -> f.setFriend(f.getUserSender().getId() == userId ? f.getUserReceiver() : f.getUserSender()));

        req.getRequestDispatcher("friends.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("friends.jsp").forward(req, resp);
    }
}
