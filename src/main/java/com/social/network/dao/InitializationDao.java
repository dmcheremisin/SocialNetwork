package com.social.network.dao;

import com.social.network.connection.ConnectionPool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class InitializationDao {

    ConnectionPool cp;
    File sqlDump;

    public InitializationDao() {
        try {
            cp = ConnectionPool.getConnectionPool();
            sqlDump = new File(getClass().getClassLoader().getResource("dump.sql").getFile());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initializeStubData() throws IOException {
        try(Connection con = cp.getConnection();
            Statement stmt = con.createStatement();) {
            List<String> lines = Files.readAllLines(sqlDump.toPath());
            for (String line : lines) {
                stmt.execute(line);
            }
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERSCREDS;");
            while (rs.next()) {
                System.out.println("id = " + rs.getString(1) + "; name = " + rs.getString(2) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
