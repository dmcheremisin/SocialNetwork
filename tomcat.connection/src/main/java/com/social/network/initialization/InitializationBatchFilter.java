package com.social.network.initialization;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class InitializationBatchFilter {
    private static final Logger logger = Logger.getLogger(InitializationBatchFilter.class);
    private static final String CAN_T_ADD_BATCH_TO_STATEMENT = "Batch filter: can't add batch to statement";

    /**
     * tomcat.connection module is supposed to be used in production environment, so it should not load dump batch.
     *
     * @param stmt   - Statement
     * @param schema - database schema
     * @param dump   - dump data
     */
    public static void addBatchToStatement(Statement stmt, StringBuilder schema, @Ignore StringBuilder dump) {
        try {
            splitAndAddSql(stmt, schema);
        } catch (SQLException e) {
            logger.error(CAN_T_ADD_BATCH_TO_STATEMENT);
            throw new RuntimeException(e);
        }
    }

    private static void splitAndAddSql(Statement stmt, StringBuilder sqlBatch) throws SQLException {
        String[] schemaSql = sqlBatch.toString().split(";");
        for (String sql : schemaSql) {
            stmt.addBatch(sql);
        }
    }
}
