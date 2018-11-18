package com.social.network.constants;

/**
 * Created by Dmitrii on 18.11.2018.
 */
public enum Role {
    ADMIN("admin", 1), MEMBER("member", 2), UNKNOWN("unknown", 3);

    private String role;
    private int key;

    Role(String role, int key) {
        this.role = role;
        this.key = key;
    }

    public String getRoleString() {
        return role;
    }

    public static Role getRoleByKey(int key){
        for (Role value : Role.values()) {
            if(value.key == key){
                return value;
            }
        }
        return UNKNOWN;
    }
}
