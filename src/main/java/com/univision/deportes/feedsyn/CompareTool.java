package com.univision.deportes.feedsyn;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by jbjohn on 9/30/15.
 */
public class CompareTool {

    FeedProcessor fp = new FeedProcessor();
    HashMap<String, Object> tracker = new HashMap<>();

    public CompareTool() {
    }

    public void compareJson(String eventId, String type) {

        System.out.println("running test eventId/type : " + eventId + "/" + type + " at : " + new Date());
        String key = eventId + type;
        JsonParser parser = new JsonParser();
        try {
            String json = fp.processFeed(type, eventId);
            String json2 = json;

            if (tracker.containsKey(key)) {
                String jsonOld = (String) tracker.get(key);

                JsonElement jsonObject = parser.parse(json);
                JsonElement jsonObject2 = parser.parse(json2);
                if (jsonObject.getAsJsonObject().equals(jsonObject2.getAsJsonObject())) {
                    System.out.println("Json not updated");
                } else {
                    System.out.println("Json updated");
                }
                tracker.put(key, json);
            } else {
                tracker.put(key, json);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
