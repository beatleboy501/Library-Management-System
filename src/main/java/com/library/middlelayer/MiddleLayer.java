package com.library.middlelayer;

import com.library.server.DataAccess;

/**
 * Created by allan06 on 8/11/2015.
 */
public class MiddleLayer {
    DataAccess da = new DataAccess();

    public String[] populateTableHeader(String item) throws Exception {
//        da = new DataAccess();
////        // TODO: add switch case here to find the item server class
//        ArrayList<String> header = da.getColumnNames(item);
        String[] headerArray = new String[6];
        headerArray[0] = "ID";
        headerArray[1] = "Title";
        headerArray[2] = "Author";
        headerArray[3] = "Year";
        headerArray[4] = "Genre";
        headerArray[5] = "Publisher";
////        for(int i = 0; i <= header.size() - 1; i++)
////        {
////            headerArray[i] = header.get(i);
////        }
        return headerArray;
    }

    public Object[][] populateTableData(String item) throws Exception {
        Object[][] tableData = new Object[3][6];
        tableData[0][0] = "1";
        tableData[0][1] = "Library Management Systems";
        tableData[0][2] = "Andrew Allison";
        tableData[0][3] = "2014";
        tableData[0][4] = "Non Fiction";
        tableData[0][5] = "PenguinS";
        tableData[1][0] = "2";
        tableData[1][1] = "Deep Sea Diving";
        tableData[1][2] = "Scuba Steve";
        tableData[1][3] = "1999";
        tableData[1][4] = "Science Fiction";
        tableData[1][5] = "Randall House";
        tableData[2][0] = "3";
        tableData[2][1] = "Eating";
        tableData[2][2] = "Archie Bunker";
        tableData[2][3] = "1960";
        tableData[2][4] = "Fiction";
        tableData[2][5] = "Royal";
        return tableData;
    }

}
