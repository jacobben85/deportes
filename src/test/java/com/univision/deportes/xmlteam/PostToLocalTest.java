package com.univision.deportes.xmlteam;

import org.junit.Test;

/**
 * Local post test
 */
public class PostToLocalTest {

    @Test
    public void testFetchLinksAndProcess() throws Exception {
        String eventId = "803840";
        String startTime = "P15D";

        String fixtureKeys = "event-stats,event-stats-progressive";
        String url = "http://sw5staging.xmlteam.com/api/feeds?" +
                "start=" + startTime +
                "&publisher-keys=optasports.com" +
                "&sport-keys=15054000" +
                "&fixture-keys=" + fixtureKeys +
                "&format=xml" +
                "&event-keys=EFBO" + eventId;
        System.out.println(url);

        PostToLocal postToLocal = new PostToLocal();
        postToLocal.fetchLinksAndProcess(url);
    }
}