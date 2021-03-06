package com.univision.deportes.xmlteam;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 *
 * @author jbjohn
 */
public class XmlTeamNormalize {

    public boolean process() {

        InputStream xml1 = this.getClass().getClassLoader().getResourceAsStream("xml/sample/809979.xml");

        InputStream xsl1 = this.getClass().getClassLoader().getResourceAsStream("xsl/xmlteam/bbc-to-xts.xsl");
        String response1 = transformer(xml1, xsl1, false);

        InputStream xml2 = new ByteArrayInputStream(response1.getBytes());
        InputStream xsl2 = this.getClass().getClassLoader().getResourceAsStream("xsl/xmlteam/xts-to-2.2.xsl");
        String response2 = transformer(xml2, xsl2, false);

        InputStream xml3 = new ByteArrayInputStream(response2.getBytes());
        InputStream xsl3 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/event-reports.xsl");
        String response3 = transformer(xml3, xsl3, true);

        InputStream xml4 = new ByteArrayInputStream(response3.getBytes());
        InputStream xsl4 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/normalize.xsl");
        String response4 = transformer(xml4, xsl4, false);

        InputStream xml5 = new ByteArrayInputStream(response4.getBytes());
        InputStream xsl5 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/processingInstructions.xsl");
        String response5 = transformer(xml5, xsl5, false);

        InputStream xml6 = new ByteArrayInputStream(response5.getBytes());
        InputStream xsl6 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/sports-data-wrapper.xsl");
        String response6 = transformer(xml6, xsl6, false);

        System.out.println(response6);
        String json = "";
        try {
            json = xmlToJson(response6, true);
            if (false) {
                System.out.println(json);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return !response1.isEmpty() && !response2.isEmpty() && !response3.isEmpty() &&
                !response4.isEmpty() && !response5.isEmpty() &&
                !json.isEmpty();
    }

    public static String transformer(InputStream xml, InputStream xsl, boolean print) {
        StreamSource stylesource = new StreamSource(xsl);
        TransformerFactory factory = TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer;

        try {

            StreamSource source = new StreamSource(xml);
            transformer = factory.newTransformer(stylesource);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String response = writer.toString();
            if (print) {
                System.out.println(response);
            }
            return response;

        } catch (TransformerConfigurationException ex) {
        } catch (TransformerException ex) {
        }
        return "";
    }

    public static String xmlToJson(String xml, boolean formatted) throws XMLStreamException {
        InputStream input = new ByteArrayInputStream(xml.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        JsonXMLConfig config = new JsonXMLConfigBuilder()
                .autoArray(true)
                .autoPrimitive(true)
                .prettyPrint(formatted)
                .build();

        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
            try {
                return output.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new XMLStreamException(e.getMessage());
            }
        } finally {
            // dp nothing
        }
    }
}
