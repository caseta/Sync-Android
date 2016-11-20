package com.sync.taylorcase.sync;

import java.util.ArrayList;

public class Sync {

    public ArrayList<String> matchingItems;
    public String personSyncedWith;

    public Sync() {

    }

    public Sync(ArrayList<String> matchingItems, String personSyncedWith) {
        this.matchingItems = matchingItems;
        this.personSyncedWith = personSyncedWith;
    }

}
