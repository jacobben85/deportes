package com.univision.deportes.feedsyn;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;
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

        String key = eventId + type;
        JsonParser parser = new JsonParser();
        try {
            String json = fp.processFeed(type, eventId);

            if (tracker.containsKey(key)) {
                String jsonOld = (String) tracker.get(key);

                JsonElement jsonObject = parser.parse(json);
                JsonElement jsonObject2 = parser.parse(jsonOld);
                if (jsonObject.getAsJsonObject().equals(jsonObject2.getAsJsonObject())) {
                } else {
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
