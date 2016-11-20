package com.sync.taylorcase.sync.JoinGroup;

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
import com.sync.taylorcase.sync.GroupUser;
import com.sync.taylorcase.sync.MySyncs.MySyncsActivity;
import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JoinGroupActivity extends AppCompatActivity {

    @Bind(R.id.choose_items_done_button)
    Button doneButton;

    ListView listView;
    DatabaseReference database;
    FirebaseAuth auth;
    ArrayList<String> groupNames;
    ArrayList<String> userIdsInGroup;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_choose_items);
        ButterKnife.bind(this);

        doneButton.setEnabled(false);
        doneButton.setVisibility(View.GONE);

        listView = (ListView) findViewById(R.id.choose_items_list_view);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        populateDataArray();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                findViewById(R.id.choose_loading).setVisibility(View.VISIBLE);

                joinTheGroup(groupNames.get(position));

                calculateSyncs(groupNames.get(position));

//                findViewById(R.id.create_loading).setVisibility(View.GONE);
                navigateToSyncs();
            }
        });
    }

    public void populateDataArray() {

        database.child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groupNames = new ArrayList<String>();

                for (DataSnapshot group : dataSnapshot.getChildren()) {
                    String groupName = group.getKey();
                    groupNames.add(groupName);
                }

                callAdapter(groupNames);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void callAdapter(ArrayList<String> groupNames) {
        context = getApplicationContext();
        listView.setAdapter(new JoinGroupAdapter(this, groupNames));
    }

    public void navigateToSyncs() {
        Intent intent = new Intent(this, MySyncsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
    }

    public void joinTheGroup(final String groupName) {
        final String userId = auth.getCurrentUser().getUid();

        database.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firstName = dataSnapshot.child("firstName").getValue().toString();

                GroupUser groupUser = new GroupUser(firstName);

                database.child("groups").child(groupName).child(userId).setValue(groupUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void calculateSyncs(String groupName) {

        database.child("groups").child(groupName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String currentUserId = auth.getCurrentUser().getUid();

                userIdsInGroup = new ArrayList<String>();

                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    String userId = user.getKey();
                    if (userId != currentUserId && currentUserId != "show") {
                        userIdsInGroup.add(userId);
                    }
                }

                Log.e("YEET_TAG", "userIds: in grou: " + userIdsInGroup);
                // We have all user IDs, time to match

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // TODO: error
            }
        });

    }


}
