package com.social.network.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitrii on 14.11.2018.
 */
@FunctionalInterface
public interface Connective {

    Connection getConnection() throws SQLException;;

}
