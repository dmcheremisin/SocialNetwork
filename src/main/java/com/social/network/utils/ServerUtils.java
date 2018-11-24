package com.social.network.utils;

import com.social.network.constants.Role;
import com.social.network.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isInteger(String str) {
        return isNotEmpty(str) && str.matches("\\d+");
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

    public static boolean notEmpty(Collection collection){
        return !(collection == null || collection.size() == 0);
    }
}
