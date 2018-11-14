package com.social.network.dao;

import com.social.network.connection.ConnectionPool;
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

    private ConnectionPool cp;
    private final File sqlSchema;
    private final File sqlDump;

    public InitializationDao() {
        try {
            cp = ConnectionPool.getConnectionPool();
            ClassLoader classLoader = getClass().getClassLoader();
            sqlSchema = new File(classLoader.getResource("schema.sql").getFile());
            sqlDump = new File(classLoader.getResource("dump.sql").getFile());
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can't initialize data base");
            throw new RuntimeException("Can't initialize data base");
        }
    }

    public void initializeStubData() {
        try(Connection con = cp.getConnection();
            Statement stmt = con.createStatement();) {

            StringBuilder schemaBatch = fileToSqlBatch(sqlSchema);
            stmt.addBatch(schemaBatch.toString());
            StringBuilder dumpBatch = fileToSqlBatch(sqlDump);
            stmt.addBatch(dumpBatch.toString());

            stmt.executeBatch();
        } catch (SQLException e) {
            logger.error("Something went wrong with initialization of schema and dump of the data base");
            throw new RuntimeException("Something went wrong with initialization of schema and dump of the data base");
        }
    }

    private StringBuilder fileToSqlBatch(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            StringBuilder sb = new StringBuilder();
            lines.forEach(sb::append);
            return sb;
        } catch (IOException e) {
            logger.error("Can't read sql batch");
            throw new RuntimeException("Can't read sql batch");
        }
    }


}
