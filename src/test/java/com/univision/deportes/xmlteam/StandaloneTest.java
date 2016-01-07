package com.univision.deportes.xmlteam;

import org.junit.Test;

import java.io.IOException;

/**
 */
public class StandaloneTest {

    @Test
    public void testProcess() {
        Standalone standalone = new Standalone();
        try {

            String folder = "processed";
            String eventId = "787816"; //EFBO787816
            String startTime = "PT20M";

            //String fixtureKeys = "event-stats,event-stats-progressive";
            String url = "http://sw5staging.xmlteam.com/api/feeds?" +
                    "start=" + startTime +
                    "&publisher-keys=optasports.com" +
                    "&sport-keys=15054000" +
                    //"&fixture-keys=" + fixtureKeys +
                    //"&event-keys=EFBO" + eventId;
                    "&format=xml";

            System.out.println(url);
            standalone.fetchLinksAndProcess(url, folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}