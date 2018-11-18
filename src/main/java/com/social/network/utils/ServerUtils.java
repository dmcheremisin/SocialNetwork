package com.social.network.utils;

import javax.servlet.ServletContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerUtils {

    public static boolean stringIsNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isInteger(String str) {
        return stringIsNotEmpty(str) && str.matches("\\d+");
    }

    public static String getRequestedUrl(String str) {
        Pattern pattern = Pattern.compile("^.*\\/([a-zA-Z0-9\\-]*)(\\?.*)?$");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            return matcher.group(1);
        }
        return null;
    }
}
