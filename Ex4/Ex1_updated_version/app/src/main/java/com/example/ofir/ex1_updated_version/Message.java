package com.example.ofir.ex1_updated_version;

import java.util.Random;

class Message {
    /**
     * This class represents a Message object, each message contains a content, ID and it
     * creation timestamp
     */

    private String content;
    private String id;
    private String timestamp;

    Message(String content, String id, String timestamp)
    {
        this.content = content;
        this.id = id;
        this.timestamp = timestamp;
    }

    static String getRandomNumberString() {
        /**
         * This function will generate 9 digit random Number.
         */
        Random rnd = new Random();
        int number = rnd.nextInt(999999999);

        return String.format("%09d", number);
    }


    // Getters

    String getContent(){
        return this.content;
    }

    String getId(){
        return this.id;
    }

    String getTimestamp(){
        return this.timestamp;
    }

}
