package com.social.network.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dmitrii on 05.12.2018.
 */
public class LocaleServlet extends HttpServlet {

    private static final String RU_CODE = "ru";
    private static final String EN_CODE = "en";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String lang = request.getParameter("lang");

        if (session != null && lang != null) {
            if (RU_CODE.equals(lang)) {
                session.setAttribute("language", RU_CODE);
            } else {
                session.setAttribute("language", EN_CODE);
            }
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}

