package com.sync.taylorcase.sync.ChooseItems;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

public class ChooseItemsActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference database;
    ArrayList<String> itemNames;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_choose_items);
        listView = (ListView) findViewById(R.id.choose_items_list_view);

        database = FirebaseDatabase.getInstance().getReference();


        populateDataArray();
    }

    public void populateDataArray() {

        database.child("items").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemNames = new ArrayList<String>();

                for (DataSnapshot item: dataSnapshot.getChildren()) {
                    String name = item.getKey();
                    itemNames.add(name);
                }

                callAdapter(itemNames);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: error
            }
        });
    }

    public void callAdapter(ArrayList<String> itemNames) {
        context = getApplicationContext();
        listView.setAdapter(new ChooseItemsAdapter(this, itemNames));
    }

}


//    ListView listview;
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        listview = (ListView) findViewById(R.id.listview);
//        listview.setAdapter(new yourAdapter(this, new String[] { "data1",
//                "data2" }));
//    }