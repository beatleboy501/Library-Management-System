package com.library.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by beatleboy501 on 8/11/2015.
 */
public class TestDataAccessService {
    private DataAccessService service = new DataAccessService();
    private String [] headers = {"id", "Title", "Author", "Genre", "Year"};

    @Test
    public void testPopulateTableHeader() throws Exception {
        service = new DataAccessService();
        String[] array = service.populateTableHeader("audio");
        Assert.assertNotNull(array);
        for(String s : array)
        {
            System.out.println(s);
        }
    }

    @Test
    public void testPopulateTableData() throws Exception {
        service = new DataAccessService();
        List<Object[]> td = service.populateTableData("audio", headers, "", "");
        Assert.assertNotNull(td);
    }
}
