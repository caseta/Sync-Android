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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.GroupUser;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.MySyncs.MySyncsActivity;
import com.sync.taylorcase.sync.R;
import com.sync.taylorcase.sync.Sync;

import java.util.ArrayList;
import java.util.HashMap;

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
                findViewById(R.id.choose_loading).setVisibility(View.VISIBLE);

                joinTheGroup(groupNames.get(position));

                calculateSyncs(groupNames.get(position));

                findViewById(R.id.choose_loading).setVisibility(View.GONE);
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
        Toast.makeText(context, "Success! \nCheck the My Syncs Tab!", Toast.LENGTH_SHORT).show();
        // home for now
        Intent intent = new Intent(this, HomeActivity.class);
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

    public void calculateSyncs(final String groupName) {

        database.addListenerForSingleValueEvent(new ValueEventListener() {

            //        database.child("groups").child(groupName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> matchingItems = new ArrayList<String>();
                ArrayList<String> myItems = new ArrayList<String>();
                ArrayList<String> theirItems = new ArrayList<String>();

                final String currentUserId = auth.getCurrentUser().getUid();

                userIdsInGroup = new ArrayList<String>();

                for (DataSnapshot user : dataSnapshot.child("groups").child(groupName).getChildren()) {
                    String userId = user.getKey();
                    if (!(userId.equals(currentUserId)) && (!(userId.equals("show")))) {
                        userIdsInGroup.add(userId);
                    }
                }

                // We have all user IDs, time to match
                for (int i = 0; i < userIdsInGroup.size(); i++) {

                    myItems = getItemsForUser(currentUserId, dataSnapshot);
                    theirItems = getItemsForUser(userIdsInGroup.get(i), dataSnapshot);

                    matchingItems = matchItems(myItems, theirItems);

                    HashMap<String, String> matchingItemsHash = new HashMap<String, String>();

                    // Fill up the HashMap

                    Log.e("YEET", "matching items: " + matchingItems);

                    if (matchingItems.size() > 0) {


                        for (int k = 0; k < matchingItems.size(); k++) {
                            matchingItemsHash.put(matchingItems.get(k), "love");
                        }


                        String myFirstName = dataSnapshot.child("users").child(currentUserId).child("firstName").getValue().toString();
                        String theirFirstName = dataSnapshot.child("users").child(userIdsInGroup.get(i)).child("firstName").getValue().toString();

                        Sync mySync = new Sync(matchingItemsHash, theirFirstName, userIdsInGroup.get(i), groupName);
                        Sync theirSync = new Sync(matchingItemsHash, myFirstName, currentUserId, groupName);

//                        database.child("users").child(currentUserId).child("mySyncs").push().setValue(mySync);
                        database.child("users").child(currentUserId).child("mySyncs").child(userIdsInGroup.get(i)).setValue(mySync);
//                        database.child("users").child(userIdsInGroup.get(i)).child("mySyncs").push().setValue(theirSync);
                        database.child("users").child(userIdsInGroup.get(i)).child("mySyncs").child(currentUserId).setValue(theirSync);

                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // TODO: error
            }
        });

    }

    public ArrayList<String> getItemsForUser(String userId, DataSnapshot snapshot) {

        final ArrayList<String> listOfItems = new ArrayList<>();

        for (DataSnapshot item : snapshot.child("users").child(userId).child("myItems").getChildren()) {
            String itemName = item.getKey();
            Log.e("YEET", "adding: " + itemName);
            listOfItems.add(itemName);
        }
        Log.e("YEET", "about to return this listOfItems: " + listOfItems);
        return listOfItems;
    }

    public ArrayList<String> matchItems(ArrayList<String> myItems, ArrayList<String> theirItems) {

        ArrayList<String> matchingItems = new ArrayList<>();

        // for each of MY items
        for (int i = 0; i < myItems.size(); i++) {

            String myItem = myItems.get(i);

            // check in the inner for look against each of THEIR items
            for (int j = 0; j < theirItems.size(); j++) {

                String theirItem = theirItems.get(j);

                if (myItem.equals(theirItem)) {
                    Log.e("YEET", "These are the same!!: " + myItem + " " + theirItem);
                    matchingItems.add(myItem);
                }

            }
        }
        Log.e("YEET", "About to return this: " + matchingItems);
        return matchingItems;
    }


}
