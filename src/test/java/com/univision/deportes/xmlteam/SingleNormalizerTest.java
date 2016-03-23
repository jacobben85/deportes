package com.univision.deportes.xmlteam;

import org.junit.Before;
import org.junit.Test;

/**
 */
public class SingleNormalizerTest {

    SingleNormalizer normalizer;

    @Before
    public void setUp() throws Exception {
        normalizer = new SingleNormalizer();
    }

    @Test
    public void process() throws Exception {
        normalizer.process();
    }
}