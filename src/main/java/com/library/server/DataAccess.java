package com.library.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by allan06 on 8/10/2015.
 */
public class DataAccess {
    /**
     * The name of the MySQL account to use (or empty for anonymous)
     */
    private final String userName = Privado.USERNAME;

    /**
     * The password for the MySQL account (or empty for anonymous)
     */
    private final String password = Privado.PASSWORD;

    /**
     * The name of the computer running MySQL
     */
    private final String serverName = "localhost";

    /**
     * The port of the MySQL server (default is 3306)
     */
    private final int portNumber = 3306;

    /**
     * The name of the database we are testing with (this default is installed with MySQL)
     */
    private final String dbName = "lms";

    /**
     * Constant for the title
     * @param TITLE = "Title"
     */
    public final static String TITLE = "Title";

    /**
     * Get a new database connection
     *
     * @return Connection conn - an SQL connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        conn = DriverManager.getConnection("jdbc:mysql://"
                        + serverName + ":" + portNumber + "/" + dbName,
                connectionProps);
        return conn;
    }

    public boolean isValidQuery(Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(command); // This will throw a SQLException if it fails
            return true;
        }
        finally {

            // This will run whether we throw an exception or not
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public ResultSet executeQuery(Connection conn, String command, Statement stmt) throws Exception {
            stmt.executeQuery(command); // This will throw a SQLException if it fails
            ResultSet rs = stmt.executeQuery(command);
            return rs;
    }

    public StringBuilder buildSelectQuery(String columns, String tables, String whereClause, String orderByClause) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(columns);
        sb.append(" FROM ");
        sb.append(tables);
        if(whereClause != null)
        {
            sb.append(" WHERE " + whereClause);
        }
        if(orderByClause != null)
        {
            sb.append(" ORDER BY " + orderByClause);
        }
        sb.append(";");

        return sb;
    }

    public ArrayList<String> getLibraryItems() throws Exception {
        ArrayList<String> libraryItems = new ArrayList<>();
//        Connection cn = getConnection();
//        try {
//
//            Statement stmt = cn.createStatement();
//            ResultSet rs = executeQuery(cn, "SHOW TABLES;", stmt);
//            while (rs.next()) {
//                libraryItems.add(rs.getString(1));
//            }
//            return libraryItems;
//        }
//        finally {
//            cn.close();
//        }
        libraryItems.add("Books");
        libraryItems.add("Newspapers");
        libraryItems.add("Magazines");
        libraryItems.add("Audio");
        libraryItems.add("Visual");
        return libraryItems;
    }

    public ResultSet getSelectQuery(String columns, String tables, String whereClause, String orderByClause) throws Exception {
        Connection cn = getConnection();
        try {
            Statement stmt = cn.createStatement();
            String query = buildSelectQuery(columns, tables, whereClause, orderByClause).toString();
            ResultSet rs = executeQuery(cn, query, stmt);
            return rs;
        }
        finally {
            cn.close();
        }
    }

    public ArrayList<String> getColumnNames(String table) throws Exception {
//        ArrayList<String> columns = new ArrayList<>();
//        ResultSet rs = getSelectQuery("COLUMN_NAME", "INFORMATION_SCHEMA.COLUMNS", "TABLE_NAME = '" + table + "'", null);
//        while(rs.next())
//        {
//            columns.add(rs.getString("COLUMN_NAME"));
//        }
//        return columns;
//
    ArrayList<String> columns = new ArrayList<>();
        columns.add("ID");
        columns.add("Title");
        columns.add("Author");
        columns.add("Year");
        columns.add("Genre");
        columns.add("Publisher");
        return columns;
    }
}

