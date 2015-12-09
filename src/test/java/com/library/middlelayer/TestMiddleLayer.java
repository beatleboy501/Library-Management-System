package com.library.middlelayer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by allan06 on 8/11/2015.
 */
public class TestMiddleLayer {
    MiddleLayer ml = new MiddleLayer();

    @Test
    public void testPopulateTableHeader() throws Exception {
        ml = new MiddleLayer();
        String[] array = ml.populateTableHeader("audio");
        Assert.assertNotNull(array);
        for(String s : array)
        {
            System.out.println(s);
        }
    }

    @Test
    public void testPopulateTableData() throws Exception {
        ml = new MiddleLayer();
        Object[][] td = ml.populateTableData("audio");
        Assert.assertNotNull(td);
    }
}
