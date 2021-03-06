package com.univision.deportes.xmlteam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uvn.maputils.MapUtil;
import com.uvn.maputils.model.Caster;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Standalone sandbox for validation
 */
public class Standalone {

    public String process(InputStream xml) throws IOException {

        InputStream xsl1 = this.getClass().getClassLoader().getResourceAsStream("xsl/xmlteam/bbc-to-xts.xsl");
        String response1 = XmlTeamNormalize.transformer(xml, xsl1, false);

        InputStream xml2 = new ByteArrayInputStream(response1.getBytes());
        InputStream xsl2 = this.getClass().getClassLoader().getResourceAsStream("xsl/xmlteam/xts-to-2.2.xsl");
        String response2 = XmlTeamNormalize.transformer(xml2, xsl2, false);

        InputStream xml3 = new ByteArrayInputStream(response2.getBytes());
        InputStream xsl3 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/event-reports.xsl");
        String response3 = XmlTeamNormalize.transformer(xml3, xsl3, false);

        InputStream xml4 = new ByteArrayInputStream(response3.getBytes());
        InputStream xsl4 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/normalize.xsl");
        String response4 = XmlTeamNormalize.transformer(xml4, xsl4, false);

        InputStream xml5 = new ByteArrayInputStream(response4.getBytes());
        InputStream xsl5 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/processingInstructions.xsl");
        String response5 = XmlTeamNormalize.transformer(xml5, xsl5, false);

        InputStream xml6 = new ByteArrayInputStream(response5.getBytes());
        InputStream xsl6 = this.getClass().getClassLoader().getResourceAsStream("xsl/univision/sports-data-wrapper.xsl");
        String response6 = XmlTeamNormalize.transformer(xml6, xsl6, false);

        String json = "";
        try {
            json = XmlTeamNormalize.xmlToJson(response6, true);
            if (false) {
                System.out.println(json);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);

        MapUtil.parse(result, "$.sports-content.sports-event.team.[*].player.[*].player-metadata.player-metadata-soccer.@line-formation-position", Caster.Type.INTEGER);
        MapUtil.parse(result, "$.sports-content.sports-event.team.[*].player.[*].player-metadata.player-metadata-soccer.@line-formation", Caster.Type.INTEGER);
        MapUtil.parse(result, "$.sports-content.sports-event.team.[*].player.[*].player-metadata.player-metadata-soccer.@time-entered-event", Caster.Type.INTEGER);
        MapUtil.parse(result, "$.sports-content.sports-event.team.[*].player.[*].player-metadata.player-metadata-soccer.@time-exited-event", Caster.Type.INTEGER);

        Gson gson = new Gson();
        String processedJson = gson.toJson(fsynSimulator(result));
        return processedJson;
    }

    /**
     * Go through the manifest and recursively call next page if more items exist.
     * @param manifest the XML team manifest
     * @return a listing of relative document paths for SportsML content.
     */
    private List<String> getSportsMLDocURLs(String manifest) {

        List<String> urls = new ArrayList<String>();
        try {
            // Convert to a document.
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(manifest.getBytes()));

            // Pull all the file paths from the manifest.
            XPath xPath =  XPathFactory.newInstance().newXPath();
            String pathExpression = "//document-listing/file-path";
            NodeList nodeList = null;
            nodeList = (NodeList) xPath.compile(pathExpression).evaluate(document, XPathConstants.NODESET);

            // Go through the manifest and add paths to the list.
            for (int index = 0; index < nodeList.getLength(); index++) {
                Node node = nodeList.item(index);
                urls.add(node.getTextContent());
            }

            // Page through the manifest if there is more.
            String nextExpression = "//metadata/next/text()";
            String next = (String) xPath.compile(nextExpression).evaluate(document, XPathConstants.STRING);

            // Recursively call next page.
            if (next != null && !next.isEmpty()) {
                urls.addAll( getSportsMLDocURLs(getXMLTeamURL(next)) );
            }

        } catch (XPathExpressionException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        } catch (ParserConfigurationException e) {
        }

        return urls;
    }

    /**
     * Fetch an XML Team URL with authorization.
     * @param url the URL to fetch.
     * @return a server response body as a string.
     */
    public String getXMLTeamURL(String url) throws IOException {
        URI uri = URI.create(url);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet();
        httpGet.setHeader("Authorization", "Basic dW5pdmlzaW9uOmM0Ymwz");
        httpGet.setURI(uri);
        HttpResponse response = client.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private HashMap<String, Object> fsynSimulator(HashMap<String, Object> result) {
        HashMap<String, Object> response = new HashMap<>();
        result.put("ttl", 90);
        response.put("data", result);
        response.put("status", "success");

        return response;
    }

    private String generateFileName(String feedUrl) {
        List<String> filename = Arrays.asList(feedUrl.split("/"));
        String xmlname = filename.get(6);
        xmlname.substring(0, xmlname.length() - 4);
        return xmlname.substring(0, xmlname.length() - 4) + ".json";
    }

    public void fetchLinksAndProcess(String url, String folder) throws IOException {
        String response = getXMLTeamURL(url);
        List<String> feedUrls = getSportsMLDocURLs(response);

        for (String feedUrl : feedUrls) {
            System.out.println("processing : " + feedUrl);
            String feedResponse = getXMLTeamURL("http://sw5staging.xmlteam.com/sportsml/files/" + feedUrl);
            InputStream stream = new ByteArrayInputStream(feedResponse.getBytes(StandardCharsets.UTF_8));
            String filename = generateFileName(feedUrl);
            String json = process(stream);

            File file = new File("json/" + folder + "/" + filename);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(json);
            writer.close();
        }
    }
}