package com.library.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by allan06 on 8/10/2015.
 */
public class AudioDataAccess extends DataAccess {

    private static final String TABLE = "audio";

    public enum Column {
        Title, Call_Id, Genre, Year, Keywords, Artist, Format, Runtime, Audio_Id, Copies
    }

    public ResultSet getAllFromAudio() throws Exception
    {
        Connection cn = getConnection();
        Statement stmt = cn.createStatement();
        String query = buildSelectQuery("*", TABLE, null, null).toString();
        ResultSet rs = executeQuery(cn, query, stmt);
        return rs;
    }

    public ArrayList<String> getTitles() throws Exception {
        ArrayList<String> titleList = new ArrayList<String>();
        String col = Column.Title.toString();
        ResultSet rs = getSelectQuery(col, TABLE, null, col);
        while (rs.next()) {
            titleList.add(rs.getString(1));
        }
        return titleList;
    }

    public ArrayList<String> getCallId() throws Exception {
        ArrayList<String> callIdList = new ArrayList<String>();
        String col = Column.Call_Id.toString();
        ResultSet rs = getSelectQuery(col, TABLE, null, col);
        while (rs.next()) {
            callIdList.add(rs.getString(1));
        }
        return callIdList;
    }

    public ArrayList<String> getGenre() throws Exception {
        ArrayList<String> genreList = new ArrayList<String>();
        String col = Column.Genre.toString();
        ResultSet rs = getSelectQuery(col, TABLE, null, col);
        while (rs.next()) {
            genreList.add(rs.getString(1));
        }
        return genreList;
    }

    public ResultSet getSelectQuery(String columns, String whereClause, String orderByClause) throws Exception {
        // TODO: validate "columns" match one of the elements in the enum above
        ResultSet rs = super.getSelectQuery(columns, TABLE, whereClause, orderByClause);
        return rs;
    }

    public ArrayList<String> getColumnNames() throws Exception {
        ArrayList<String> columns = super.getColumnNames(TABLE);
        return columns;
    }
}
