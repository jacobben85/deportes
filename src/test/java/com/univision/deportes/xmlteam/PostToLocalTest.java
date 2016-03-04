package com.univision.deportes.xmlteam;

import org.junit.Test;

/**
 * Local post test
 */
public class PostToLocalTest {

    @Test
    public void testFetchLinksAndProcess() throws Exception {
        String eventId = "840461";
        String startTime = "20151201T000000-0500";
        startTime = "P2M";
        String endTime = "20151230T000000-0500";

        String fixtureKeys = "event-stats,event-stats-progressive";
        String url = "http://staging.xmlteam.com/api/feeds?" +
                "start=" + startTime +
                //"&end=" + endTime +
                "&publisher-keys=optasports.com" +
                "&sport-keys=15054000" +
                "&fixture-keys=" + fixtureKeys +
                //"&last-files-by=event-key&fixture-keys=event-stats,event-commentary,event-reports" +
                "&format=xml" +
                "&event-keys=EFBO" + eventId;
        System.out.println(url);

        PostToLocal postToLocal = new PostToLocal();
        postToLocal.fetchLinksAndProcess(url);
    }
}