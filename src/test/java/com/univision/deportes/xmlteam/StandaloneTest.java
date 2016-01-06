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
            //String startTime = "20150922T010000-0400";
            String startTime = "PT3H";
            //String endTime = "20150924T000000-0400";

            //String fixtureKeys = "event-stats,event-stats-progressive";
            String url = "http://sw5staging.xmlteam.com/api/feeds?" +
                    "start=" + startTime +
                    "&publisher-keys=optasports.com" +
                    "&sport-keys=15054000" +
                    //"&fixture-keys=" + fixtureKeys +
                    "&format=xml";
                    //"&event-keys=EFBO" + eventId;
            System.out.println(url);
            standalone.fetchLinksAndProcess(url, folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    @Test
//    public void testProcessOther() {
//        Standalone standalone = new Standalone();
//        try {
//
//            String eventId = "803172";
//            String period = "start=P3M";
//
//            String url = "http://feed5.xmlteam.com/api/feeds?" +
//                    "&publisher-keys=optasports.com" +
//                    "&format=xml" +
//                    period +
//                    "&event-keys=EFBO";
//            standalone.fetchLinksAndProcess(url + eventId, eventId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}