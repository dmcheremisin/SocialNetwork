package com.social.network.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private static final Logger logger = Logger.getLogger(Encryption.class);
    private static final String CAN_T_ENCRYPT_PASSWORD = "Can't encrypt password";

    public static String encryptPassword(String passwordToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(CAN_T_ENCRYPT_PASSWORD);
            throw new RuntimeException(e);
        }
    }
}
