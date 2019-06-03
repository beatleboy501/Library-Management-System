package com.library.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by beatleboy501 on 8/10/2015.
 */
public class DataAccess {
    /**
     * The name of the MySQL account to use (or empty for anonymous)
     */
    private final String userName = Auth.USERNAME;

    /**
     * The password for the MySQL account (or empty for anonymous)
     */
    private final String password = Auth.PASSWORD;

    /**
     * The name of the computer running MySQL
     */
    private final String serverName = "localhost";

    /**
     * The port of the MySQL dataaccess (default is 3306)
     */
    private final int portNumber = 3306;

    /**
     * The name of the database we are testing with (this default is installed with MySQL)
     */
    private final String dbName = "lms";

    /**
     * Get a new database connection
     *
     * @return Connection conn - an SQL connection
     * @throws SQLException
     */
    private Connection conn;

    public Connection getConnection() throws SQLException {
        if(conn != null) {
            return conn;
        } else {
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.userName);
            connectionProps.put("password", this.password);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Error connecting to database");
                e.printStackTrace();
                return conn;
            }
            conn = DriverManager.getConnection("jdbc:mysql://"
                            + serverName + ":" + portNumber + "/" + dbName,
                    connectionProps);
            return conn;
        }
    }

    public boolean isValidQuery(Connection conn, String command) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery(command); // This will throw a SQLException if it fails
            return true;
        }
    }

    public ResultSet executeQuery(Connection conn, String command, Statement stmt) throws Exception {
        stmt.executeQuery(command); // This will throw a SQLException if it fails
        return stmt.executeQuery(command);
    }

    StringBuilder buildSelectQuery(String columns, String tables, String whereClause, String orderByClause) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(columns);
        sb.append(" FROM ");
        sb.append(tables);
        if (whereClause != null) sb.append(" WHERE ").append(whereClause);
        if (orderByClause != null) sb.append(" ORDER BY ").append(orderByClause);
        sb.append(";");
        return sb;
    }

    public ArrayList<String> getLibraryItems() throws Exception {
        ArrayList<String> libraryItems = new ArrayList<>();
        try (Connection cn = getConnection()) {
            Statement stmt = cn.createStatement();
            ResultSet rs = executeQuery(cn, "SHOW TABLES;", stmt);
            while (rs.next()) libraryItems.add(rs.getString(1));
            return libraryItems;
        }
    }

    public ResultSet getSelectQuery(String columns, String tables, String whereClause, String orderByClause) throws Exception {
        Connection cn = getConnection();
        try {
            Statement stmt = cn.createStatement();
            String query = buildSelectQuery(columns, tables, whereClause, orderByClause).toString();
            return executeQuery(cn, query, stmt);
        } catch(Exception e) {
            cn.close();
            return null;
        }
    }

    public ArrayList<String> getColumnNames(String table) throws Exception {
        ArrayList<String> columns = new ArrayList<>();
        ResultSet rs = getSelectQuery("COLUMN_NAME", "INFORMATION_SCHEMA.COLUMNS", "TABLE_NAME = '" + table + "'", null);
        while (rs.next()) columns.add(rs.getString("COLUMN_NAME"));
        return columns;
    }
}

