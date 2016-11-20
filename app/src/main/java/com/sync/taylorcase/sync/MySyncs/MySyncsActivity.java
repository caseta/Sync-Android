package com.sync.taylorcase.sync.MySyncs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.R;
import com.sync.taylorcase.sync.SyncSummaryActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MySyncsActivity extends AppCompatActivity {

    @Bind(R.id.choose_items_done_button) Button button;

    ListView listView;
    Context context;
    DatabaseReference database;
    FirebaseAuth auth;
    ArrayList<String> groups;
    ArrayList<String> names;
    ArrayList<String> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_choose_items);
        listView = (ListView) findViewById(R.id.choose_items_list_view);
        ButterKnife.bind(this);

        button.setEnabled(false);
        button.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        populateDataHashMap();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                findViewById(R.id.choose_loading).setVisibility(View.VISIBLE);

//                findViewById(R.id.choose_loading).setVisibility(View.GONE);

                navigate(position);

            }
        });

    }

    public void navigate(int position) {
        Intent intent = new Intent(this, SyncSummaryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.putExtra("ID", ids.get(position));

        startActivity(intent);
    }

    public void populateDataHashMap() {

        String currentUserId = auth.getCurrentUser().getUid();

        database.child("users").child(currentUserId).child("mySyncs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                groups = new ArrayList<String>();
                names = new ArrayList<String>();
                ids = new ArrayList<String>();

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.e("YEET", "data is: " + child);
                    String synchedName = child.child("personSyncedWith").getValue().toString();
                    names.add(synchedName);

                    String id = child.child("personsUserId").getValue().toString();
                    ids.add(id);

                    String groupName = child.child("groupName").getValue().toString();
                    groups.add(groupName);

                    // use this later
//                    HashMap<String, String> matchingItemsHash = (HashMap<String, String>) child.child("matchingItems").getValue();

//                    for (String key: matchingItemsHash.keySet()) {
//                        groups.add(key);
//                    }

                }

                callAdapter(groups, names);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void callAdapter(ArrayList<String> groups, ArrayList<String> names) {
        context = getApplication();
        listView.setAdapter(new MySyncsAdapter(this, groups, names));
    }

}
