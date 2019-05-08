package com.example.ofir.ex1_updated_version;

import android.os.AsyncTask;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will insert the message to the FireStore DB
 */

public class DataBaseInsert extends AsyncTask<String, String, String> {

    private final String CONTENT_TAG = "content";
    private final String ID_TAG = "id";
    private final String TIMESTAMP_TAG = "timestamp";
    private final String COLLECTION_TAG = "ChatMessages";
    private final String FINISHED = "Done";

    @Override
    protected String doInBackground(String... strings) {
        Map<String, String> msg_map = new HashMap<>();
        msg_map.put(CONTENT_TAG, strings[0]);
        msg_map.put(ID_TAG, strings[1]);
        msg_map.put(TIMESTAMP_TAG, strings[2]);
        MainActivity.firestore.collection(COLLECTION_TAG).add(msg_map);

        return FINISHED;
    }

}