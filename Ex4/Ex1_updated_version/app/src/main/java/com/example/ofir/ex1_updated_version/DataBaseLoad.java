package com.example.ofir.ex1_updated_version;

import android.os.AsyncTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.ofir.ex1_updated_version.MainActivity.recyclerViewAdapter;

/**
 * This class is responsible to load all the messages from FireBase DB
 */

public class DataBaseLoad extends AsyncTask<Void, Void, Void> {

    private final String CONTENT_TAG = "content";
    private final String ID_TAG = "id";
    private final String TIMESTAMP_TAG = "timestamp";

    @Override
    protected Void doInBackground(Void... voids) {
        MainActivity.messagesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.getDocuments().size() > 0) {
                    ArrayList<Message> msg_from_db = new ArrayList<>();
                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                        String content =
                                queryDocumentSnapshots.getDocuments().get(i).getString(CONTENT_TAG);
                        String id = queryDocumentSnapshots.getDocuments().get(i).getString(ID_TAG);
                        String timestamp =
                                queryDocumentSnapshots.getDocuments().get(i).getString(TIMESTAMP_TAG);
                        Message msg = new Message(content, id, timestamp);
                        msg_from_db.add(msg);
                    }

                    // sort messages by timestamp
                    Comparator<Message> compareByTs= new Comparator<Message>() {
                        @Override
                        public int compare(Message o1, Message o2) {
                            return o1.getTimestamp().compareTo(o2.getTimestamp());
                        }
                    };
                    Collections.sort(msg_from_db, compareByTs);

                    // update messages list and update recycler view adapter
                    MainActivity.messages = msg_from_db;
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
        return null;
    }
}
