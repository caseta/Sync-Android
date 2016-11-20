package com.sync.taylorcase.sync;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SyncSummaryActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.sync_summary_1) TextView one;
    @Bind(R.id.sync_summary_2) TextView two;
    @Bind(R.id.sync_summary_3) TextView three;
    @Bind(R.id.sync_summary_4) TextView four;
    @Bind(R.id.sync_summary_name) TextView name;
    @Bind(R.id.sync_summary_group_name) TextView groupName;
    @Bind(R.id.sync_summary_message_button) Button messageButton;

    Context context;
    DatabaseReference database;
    FirebaseAuth auth;
    String id;
    String currentUserId;

    String userName;
    String userGroupName;
    ArrayList<String> itemNames;

    @Override
    protected void onCreate(Bundle savtedInstanceState) {
        super.onCreate(savtedInstanceState);
        setContentView(R.layout.view_sync_summary);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        currentUserId = auth.getCurrentUser().getUid();

        id = getIntent().getExtras().getString("ID");

        setValues();

        messageButton.setOnClickListener(this);
    }

    public void setValues() {

        database.child("users").child(currentUserId).child("mySyncs").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemNames = new ArrayList<String>();

                userName = dataSnapshot.child("personSyncedWith").getValue().toString();
                userGroupName = dataSnapshot.child("groupName").getValue().toString();

                HashMap<String, String> matchingItemsHash = (HashMap<String, String>) dataSnapshot.child("matchingItems").getValue();

                for (String key : matchingItemsHash.keySet()) {
                    itemNames.add(key);
                }

                populateTextViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void populateTextViews() {

        name.setText(userName);
        groupName.setText(userGroupName);

        if (itemNames.size() == 1) {

            one.setText(itemNames.get(0));
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);

        } else if (itemNames.size() == 2) {
            one.setText(itemNames.get(0));
            two.setText(itemNames.get(1));
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
        } else if (itemNames.size() == 3) {
            one.setText(itemNames.get(0));
            two.setText(itemNames.get(1));
            three.setText(itemNames.get(2));
            four.setVisibility(View.GONE);
        } else if (itemNames.size() == 4) {
            one.setText(itemNames.get(0));
            two.setText(itemNames.get(1));
            three.setText(itemNames.get(2));
            four.setText(itemNames.get(3));

        } else {
            // TODO: error
        }

    }

    @Override
    public void onClick(View v) {
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.sync_summary_message_button) {
            // OPEN CHAT WINDOW
        } else {
            // TODO: error
        }
    }
}
