package com.social.network.exceptions;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public class PersistException extends Exception {
    public PersistException(String message) {
        super(message);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String s, Exception e) {
        super(s, e);
    }
}
