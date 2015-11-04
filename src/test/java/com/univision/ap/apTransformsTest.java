package com.univision.ap;

import org.junit.Test;

import java.io.InputStream;

/**
 * Ap tests
 */
public class ApTransformsTest {

    @Test
    public void testProcess() throws Exception {

        InputStream xml = this.getClass().getClassLoader().getResourceAsStream("xml/ap/feed.xml");
        ApTransforms apTransforms = new ApTransforms();
        apTransforms.process(xml);
    }
}