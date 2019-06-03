package com.library.dataaccess;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by beatleboy501 on 8/10/2015.
 */
public class TestDataAccess {
    DataAccess da = new DataAccess();

    @Test
    public void testConnection() throws Exception
    {
        da = new DataAccess();
        Assert.assertNotNull(da.getConnection());
    }

    @Test
    public void testExecuteQuery() throws Exception
    {
        da = new DataAccess();
        Connection cn = da.getConnection();
        boolean query = da.isValidQuery(cn, "SHOW TABLES");
        Assert.assertTrue(query);
    }

    @Test
    public void testResultSet() throws Exception{
        da = new DataAccess();

            Connection cn = da.getConnection();
            Statement stmt = cn.createStatement();
            ResultSet rs = da.executeQuery(cn, "SELECT * FROM audio;", stmt);
            ResultSetMetaData rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                try {
                    if (rs.next()) {
                        for (int i = 1; i <= numberOfColumns; i++) {
                            if (i > 1) System.out.print(",  ");
                            String columnName = rsmd.getColumnName(i);
                            String columnValue = rs.getString(i);
                            System.out.print(columnName + ":" + columnValue);
                        }
                        System.out.println("\n");
            }
        }
        finally {
            stmt.close();
            rs.close();
        }
        Assert.assertNotNull(rs);
        Assert.assertEquals(10, numberOfColumns);
    }

    @Test
    public void testGetSelectQuery() throws Exception {
        da = new DataAccess();
        String columns = "Title";
        String tables = "audio";
        String whereClause = "Year = 2013";
        String orderByClause = "Title";
        ResultSet rs = da.getSelectQuery(columns, tables, whereClause, orderByClause);
        Assert.assertNotNull(rs);
    }

    @Test
    public void testGetLibraryItems() throws Exception {
        da = new DataAccess();
        ArrayList<String> items = da.getLibraryItems();
        Assert.assertTrue(items.contains("audio"));
        Assert.assertTrue(items.contains("books"));
        Assert.assertTrue(items.contains("journals"));
        Assert.assertTrue(items.contains("magazines"));
        Assert.assertTrue(items.contains("newspapers"));
        Assert.assertTrue(items.contains("visual"));
    }

    @Test
    public void testGetColumnNames() throws Exception {
        da = new DataAccess();
        ArrayList<String> list = da.getColumnNames("audio");
        Assert.assertNotNull(list);
        for(String s : list)
        {
            System.out.println(s);
        }
    }
}
