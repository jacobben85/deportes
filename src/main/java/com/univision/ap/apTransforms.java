package com.univision.ap;

import com.univision.deportes.xmlteam.XmlTeamNormalize;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Ap Transforms
 */
public class ApTransforms {

    public void process(InputStream xml) throws IOException {

        InputStream xsl1 = this.getClass().getClassLoader().getResourceAsStream("xsl/ap/univision.xsl");
        String response1 = XmlTeamNormalize.transformer(xml, xsl1, true);

        System.out.println(response1);
        String json = "";
        try {
            json = XmlTeamNormalize.xmlToJson(response1, true);
            if (true) {
                System.out.println(json);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
