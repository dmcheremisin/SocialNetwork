package com.social.network.init;

import com.social.network.connective.Connective;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.social.network.initialization.InitializationBatchFilter.addBatchToStatement;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class Initialization {
    private static final Logger logger = Logger.getLogger(Initialization.class);
    private static final String SOMETHING_WENT_WRONG_WITH_INITIALIZATION = "Something went wrong with initialization of schema and dump of the data base";
    private static final String CAN_T_READ_SQL_BATCH = "Can't read sql batch";

    private Connective connective;
    private final File sqlSchema;
    private final File sqlDump;

    public Initialization(Connective connective) {
        this.connective = connective;
        ClassLoader classLoader = getClass().getClassLoader();
        sqlSchema = new File(classLoader.getResource("schema.sql").getFile());
        sqlDump = new File(classLoader.getResource("dump.sql").getFile());
    }

    public void initializeStubData() {
        try (Connection con = connective.getConnection();
             Statement stmt = con.createStatement();) {

            StringBuilder schemaBatch = fileToSqlBatch(sqlSchema);
            StringBuilder dumpBatch = fileToSqlBatch(sqlDump);
            addBatchToStatement(stmt, schemaBatch, dumpBatch);

            stmt.executeBatch();
            logger.info("All data is initialized successfully");
        } catch (SQLException e) {
            logger.error(SOMETHING_WENT_WRONG_WITH_INITIALIZATION);
            throw new RuntimeException(e);
        }
    }

    private StringBuilder fileToSqlBatch(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            StringBuilder sb = new StringBuilder();
            lines.forEach(sb::append);
            return sb;
        } catch (IOException e) {
            logger.error(CAN_T_READ_SQL_BATCH);
            throw new RuntimeException(e);
        }
    }


}
