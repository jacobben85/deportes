package com.univision.deportes.xmlteam;

import java.io.InputStream;

/**
 */
public class SingleNormalizer {

    public void process() {

        InputStream xml1 = this.getClass().getClassLoader().getResourceAsStream("xml/xsl/test.xml");

        InputStream xsl1 = this.getClass().getClassLoader().getResourceAsStream("xml/xsl/test.xsl");
        String response1 = XmlTeamNormalize.transformer(xml1, xsl1, true);
    }
}
