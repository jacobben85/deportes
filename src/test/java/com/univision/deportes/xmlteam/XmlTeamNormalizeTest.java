package com.univision.deportes.xmlteam;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jbjohn
 */
public class XmlTeamNormalizeTest {

    public XmlTeamNormalizeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class XmlTeamNormalize.
     */
    @Test
    public void testProcess() {
        XmlTeamNormalize instance = new XmlTeamNormalize();
        boolean status = instance.process();
        Assert.assertEquals(true, status);
    }

}