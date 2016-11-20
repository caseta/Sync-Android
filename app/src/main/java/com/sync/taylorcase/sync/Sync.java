package com.sync.taylorcase.sync;

import java.util.ArrayList;
import java.util.HashMap;

public class Sync {

//    public ArrayList<String> matchingItems;
    public HashMap<String, String> matchingItems;
    public String personSyncedWith;
    public String personsUserId;
    public String groupName;

    public Sync() {

    }

    public Sync(HashMap<String, String> matchingItems, String personSyncedWith, String personsUserId, String groupName) {
        this.matchingItems = matchingItems;
        this.personSyncedWith = personSyncedWith;
        this.personsUserId = personsUserId;
        this.groupName = groupName;
    }

}
