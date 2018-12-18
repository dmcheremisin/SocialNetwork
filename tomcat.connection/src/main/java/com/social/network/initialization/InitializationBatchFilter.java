package com.social.network.initialization;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class InitializationBatchFilter {
    private static final Logger logger = Logger.getLogger(InitializationBatchFilter.class);
    private static final String CAN_T_ADD_BATCH_TO_STATEMENT = "Batch filter: can't add batch to statement";

    public static void addBatchToStatement(Statement stmt, StringBuilder schema, @Ignore StringBuilder dump) {
        try {
            stmt.addBatch(schema.toString());
        } catch (SQLException e) {
            logger.error(CAN_T_ADD_BATCH_TO_STATEMENT);
            throw new RuntimeException(CAN_T_ADD_BATCH_TO_STATEMENT);
        }
    }
}
