package com.social.network.initialization;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class InitializationBatchFilter {
    private static final Logger logger = Logger.getLogger(InitializationBatchFilter.class);
    private static final String CAN_T_ADD_BATCH_TO_STATEMENT = "Batch filter: can't add batch to statement";

    /**
     * custom.connection module is supposed to be used in dev environment, so it should load dump batch.
     *
     * @param stmt   - Statement
     * @param schema - database schema
     * @param dump   - dump data
     */
    public static void addBatchToStatement(Statement stmt, StringBuilder schema, StringBuilder dump) {
        try {
            splitAndAddSql(stmt, schema);
            splitAndAddSql(stmt, dump);
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
