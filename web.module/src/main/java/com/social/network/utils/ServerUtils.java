package com.social.network.utils;

import com.social.network.constants.Role;
import com.social.network.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerUtils {

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isInteger(String str) {
        return isNotBlank(str) && str.matches("\\d+");
    }

    public static String getRequestedUrl(String str) {
        Pattern pattern = Pattern.compile("^.*/([a-zA-Z0-9\\-]*)(\\?.*)?$");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            return matcher.group(1);
        }
        return "";
    }

    public static List<String> getConfigUrls(String str) {
        String[] split = str.split(",");
        List<String> urls = new ArrayList<>();
        Collections.addAll(urls, split);
        return urls;
    }

    public static void setRoleToRequest(HttpServletRequest request, User user) {
        int role = user.getRole();
        Role roleModel = Role.getRoleByKey(role);
        request.setAttribute("role", roleModel.getRoleString());
    }

    public static User getUserFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (User) session.getAttribute("user");
    }
}
