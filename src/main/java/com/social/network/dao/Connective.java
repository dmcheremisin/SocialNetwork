package com.social.network.dao;

import java.sql.Connection;

/**
 * Created by Dmitrii on 14.11.2018.
 */
@FunctionalInterface
public interface Connective {
    Connection getConnection();
}
