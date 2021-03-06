package com.univision.deportes.feedsyn;

import org.junit.BeforeClass;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;

/**
 */
//@RunWith(Parameterized.class)
public class FeedProcessorTest {

    private static FeedProcessor fp;
    private static String expectedStats;
    private static String expectedComments;
    private static String scheduleResults;
    private static String eventId = "832696"; // Roma v Barcelona
    private static String season = "2015";
    private static String league = "5"; // Champions League
    private static String team = "178"; // Barcelona
    private static CompareTool ct;

    public FeedProcessorTest(String eventId) {
        this.eventId = eventId;
    }

    @BeforeClass
    public static void setUp() throws IOException, URISyntaxException {
        fp = new FeedProcessor();
        expectedStats = fp.processFeed("stats", eventId);
        expectedComments = fp.processFeed("commentary", eventId);
        scheduleResults = fp.processFeed("schedule", eventId);
        ct = new CompareTool();
    }

//    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"832696"},
                {"832669"},
                {"832637"},
                {"832695"},
                {"832674"},
                {"832663"},
                {"832627"},
                {"832657"},
                {"803172"},
                {"803173"},
                {"803174"},
                {"803175"},
                {"803176"},
                {"803177"},
                {"803178"},
                {"803179"},
                {"803180"},
                {"803181"}
        });
//        return Arrays.asList(new Object[1000][0]);
    }

    public void testProcessFeedStats() throws InterruptedException {
        String response = null;
        try {
            response = fp.processFeed("stats", eventId);
            JSONAssert.assertEquals(expectedStats, response, false);
        } catch (AssertionError e) {
            if (response != null) {
                expectedStats = response;
            }
            throw new AssertionError(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        await().until(() ->
        {
            return delayMethod();
        });
    }

    public void testProcessFeedComments() throws InterruptedException {
        String response = null;
        try {
            response = fp.processFeed("commentary", eventId);
            JSONAssert.assertEquals(expectedComments, response, false);
        } catch (AssertionError e) {
            if (response != null) {
                expectedComments = response;
            }
            throw new AssertionError(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        await().until(() ->
        {
            return delayMethod();
        });
    }

    public void testProcessFeedScheduleResults() throws InterruptedException {
        String response = null;
        try {
            response = fp.processFeed("schedule", eventId);
            JSONAssert.assertEquals(scheduleResults, response, false);
        } catch (AssertionError e) {
            if (response != null) {
                scheduleResults = response;
            }
            throw new AssertionError(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        await().until(() ->
        {
            return delayMethod();
        });
    }

    private boolean delayMethod() throws InterruptedException {
        Thread.sleep(10);
        return true;
    }

    public void testCompareJson() {
        ct.compareJson(eventId, "stats");
        ct.compareJson(eventId, "commentary");
        await().until(() ->
        {
            return delayMethod();
        });
    }
}