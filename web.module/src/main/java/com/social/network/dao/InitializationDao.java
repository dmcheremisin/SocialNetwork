package com.social.network.dao;

import com.social.network.connection.Connective;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class InitializationDao {
    private static final Logger logger = Logger.getLogger(InitializationDao.class);
    private static final String SOMETHING_WENT_WRONG_WITH_INITIALIZATION = "Something went wrong with initialization of schema and dump of the data base";
    private static final String CAN_T_READ_SQL_BATCH = "Can't read sql batch";

    private Connective connective;
    private final File sqlSchema;
    private final File sqlDump;

    public InitializationDao(Connective connective) {
        this.connective = connective;
        ClassLoader classLoader = getClass().getClassLoader();
        sqlSchema = new File(classLoader.getResource("schema.sql").getFile());
        sqlDump = new File(classLoader.getResource("dump.sql").getFile());
    }

    public void initializeStubData() {
        try (Connection con = connective.getConnection();
             Statement stmt = con.createStatement();) {

            StringBuilder schemaBatch = fileToSqlBatch(sqlSchema);
            stmt.addBatch(schemaBatch.toString());
            StringBuilder dumpBatch = fileToSqlBatch(sqlDump);
            stmt.addBatch(dumpBatch.toString());

            stmt.executeBatch();
            logger.info("All data is initialized successfully");
        } catch (SQLException e) {
            logger.error(SOMETHING_WENT_WRONG_WITH_INITIALIZATION);
            throw new RuntimeException(SOMETHING_WENT_WRONG_WITH_INITIALIZATION);
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
            throw new RuntimeException(CAN_T_READ_SQL_BATCH);
        }
    }


}
