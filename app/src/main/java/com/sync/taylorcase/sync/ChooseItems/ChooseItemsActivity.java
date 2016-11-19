package com.sync.taylorcase.sync.ChooseItems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sync.taylorcase.sync.R;

public class ChooseItemsActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_choose_items);
        listView = (ListView) findViewById(R.id.choose_items_list_view);
        listView.setAdapter(new ChooseItemsAdapter(this, new String[]{"one", "two"}));
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