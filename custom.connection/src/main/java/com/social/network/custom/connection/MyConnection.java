package com.social.network.custom.connection;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public abstract class MyConnection implements Connection {

    private static final Logger logger = Logger.getLogger(MyConnection.class);

    static MyConnection create(Connection connection,
                               Queue<Connection> freeConnections,
                               Queue<Connection> usedConnections) throws SQLException {

        connection.setAutoCommit(true);

        return new MyConnection() {

            @Override
            public Connection get() {
                return connection;
            }

            @Override
            public void close() throws SQLException {
                if (!usedConnections.remove(this)) {
                    logger.error("Can't remove free connection from the used pool");
                }
                if (this.isClosed()) {
                    logger.error("Trying to add closed connection to the free pool");
                }
                if (!freeConnections.offer(this)) {
                    logger.error("Can't add free connection to the pool");
                }

            }
        };
    }

    abstract Connection get();

    public void reallyClose() throws SQLException {
        get().close();
    }

    @Override
    public Statement createStatement() throws SQLException {
        return get().createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return get().prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return get().prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return get().nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        get().setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return get().getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        get().commit();
    }

    @Override
    public void rollback() throws SQLException {
        get().rollback();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return get().isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return get().getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        get().setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return get().isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        get().setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return get().getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        get().setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return get().getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return get().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        get().clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return get().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return get().getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        get().setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        get().setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return get().getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return get().setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return get().setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        get().rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        get().releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return get().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return get().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return get().prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return get().prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return get().createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return get().createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return get().createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return get().createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return get().isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        get().setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        get().setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return get().getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return get().getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return get().createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return get().createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        get().setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return get().getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        get().abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        get().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return get().getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return get().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return get().isWrapperFor(iface);
    }
}
