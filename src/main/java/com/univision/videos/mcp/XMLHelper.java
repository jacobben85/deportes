package com.univision.videos.mcp;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 *
 * @author jbjohn
 */
public class XMLHelper {

    public static Document xmlFromString(String xml) {
        Document doc;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            System.out.println("XML parse error: " + e.getMessage());
            return null;
        } catch (SAXException e) {
            System.out.println("Wrong XML file structure: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("I/O exeption: " + e.getMessage());
            return null;
        }

        return doc;

    }

    public static String xmlParser(String filepath) {
        try {
            StringBuilder sb;
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
                String line;
                sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line.trim());
                }
            } finally {

            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return "";
        }

    }

}
