package com.library.server;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by allan06 on 8/11/2015.
 */
public class TestAudioDataAccess {
    AudioDataAccess ada = new AudioDataAccess();

    @Test
    public void testGetTitles() throws Exception {
        ada = new AudioDataAccess();
        ArrayList<String> titles = ada.getTitles();
        Assert.assertNotNull(titles);
        Assert.assertTrue(titles.contains("AM"));
    }
}
