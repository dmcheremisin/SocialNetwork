package com.social.network.utils;

import static com.social.network.utils.ServerUtils.isBlank;

public class Validation {

    public static void main(String[] args) {
        System.out.println(validatePassword("aaa123"));
    }

    public static boolean validateEmail(String email){
        if(isBlank(email)) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_]+\\.(.*[a-zA-Z0-9-_]+)+$");
    }

    public static boolean validatePassword(String password){
        if(isBlank(password)) {
            return false;
        }
        return password.matches("(?=.*[0-9].*)(?=.*[a-zA-Z].*)[0-9a-zA-Z]{6,}");
    }
}
