package com.univision.deportes.xmlteam;

import org.junit.Test;

/**
 * Local post test
 */
public class PostToLocalTest {

    @Test
    public void testFetchLinksAndProcess() throws Exception {
        //String eventId = "803840";
        String startTime = "PT5M";

        String fixtureKeys = "event-reports";
        String url = "http://staging.xmlteam.com/api/feeds?" +
                "start=" + startTime +
                "&publisher-keys=optasports.com" +
                "&sport-keys=15054000" +
                //"&fixture-keys=" + fixtureKeys +
                //"&last-files-by=event-key&fixture-keys=event-stats,event-commentary,event-reports" +
                "&format=xml";
                //"&event-keys=EFBO" + eventId;
        System.out.println(url);

        PostToLocal postToLocal = new PostToLocal();
        postToLocal.fetchLinksAndProcess(url);
    }
}