package com.library.service;

import com.library.dataaccess.DataAccess;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beatleboy501 on 8/11/2015.
 */
public class DataAccessService {
    private final DataAccess da = new DataAccess();

    public String[] populateTableHeader(String item) throws Exception {
        ArrayList<String> header = da.getColumnNames(item);
        System.out.println(header);
        String[] headerArray = new String[header.size()];
        for(int i = 0; i <= header.size() - 1; i++) headerArray[i] = header.get(i);
        return headerArray;
    }

    public List<Object[]> populateTableData(String item, String[] headers, String facet, String facetValue) throws Exception {
        String whereClause = (facet.equals("") && facetValue.equals("")) ? null : facet + " LIKE '%" + facetValue + "%'";
        ResultSet rs = da.getSelectQuery("*", item, whereClause, null);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnsNumber = rsMetaData.getColumnCount();
        List<Object[]> tableData = new ArrayList<>();
        while (rs.next()) {
            Object[] rowData = new Object[columnsNumber];
            for (int j = 0; j < headers.length; j++) {
                String columnVal = rs.getString(headers[j]);
                rowData[j] = columnVal;
            }
            tableData.add(rowData);
        }
        return tableData;
    }

}
