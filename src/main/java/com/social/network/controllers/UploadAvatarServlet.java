package com.social.network.controllers;

import com.social.network.dao.UserDao;
import com.social.network.models.User;
import com.social.network.utils.ServerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitrii on 20.11.2018.
 */
public class UploadAvatarServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UploadAvatarServlet.class);

    private static final String FILE_EXTENSION_IS_NOT_ALLOWED = "File extension is not allowed: ";
    private static final String FILE_EXTENSION_IS_NOT_RECOGNIZED = "File extension is not recognized with content-disposition: ";

    private List<String> allowedImageExtensions = new LinkedList<>();
    private UserDao userDao;
    private String savePath;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        savePath = getServletContext().getRealPath("") + getInitParameter("uploadDir");

        String allowedExtensions = getInitParameter("allowedExtensions");
        String[] split = allowedExtensions.split(",");
        for (String ext : split) {
            allowedImageExtensions.add(ext.toLowerCase());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part part = request.getParts().iterator().next();
        String extension = extractFileExtension(part);
        if (allowedImageExtensions.contains(extension.toLowerCase())) {
            String fileName = user.getId() + "." + extension;
            fileName = new File(fileName).getName().toLowerCase();
            part.write(savePath + File.separator + fileName);

            user.setImage(fileName);
            userDao.updateImage(user);
        } else {
            String message = FILE_EXTENSION_IS_NOT_ALLOWED + extension;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    private String extractFileExtension(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                String fileExtension = getFileExtension(fileName);
                if (ServerUtils.isNotBlank(fileExtension)){
                    return fileExtension;
                }
            }
        }
        String message = FILE_EXTENSION_IS_NOT_RECOGNIZED + contentDisp;
        logger.error(message);
        throw new RuntimeException(message);
    }

    private String getFileExtension(String image) {
        Pattern pattern = Pattern.compile("(.+)\\.([A-Za-z]+)");
        Matcher matcher = pattern.matcher(image);
        if(matcher.matches()) {
            return matcher.group(2);
        }
        return null;
    }
}
