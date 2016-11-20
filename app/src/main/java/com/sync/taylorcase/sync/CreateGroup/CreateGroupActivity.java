package com.sync.taylorcase.sync.CreateGroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.Group;
import com.sync.taylorcase.sync.GroupUser;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.HowItWorksActivity;
import com.sync.taylorcase.sync.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.create_group_name) EditText groupName;
    @Bind(R.id.create_group_password) EditText password;
    @Bind(R.id.create_group_button) Button submitButton;
    @Bind(R.id.create_group_yes) RadioButton yesButton;
    @Bind(R.id.create_group_no) RadioButton noButton;

    DatabaseReference database;
    FirebaseAuth auth;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_create_group);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.create_group_loading).setVisibility(View.VISIBLE);
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.create_group_button) {

            final String userId = auth.getCurrentUser().getUid();
            Group group = new Group();

            // post group in view spot
            database.child("groups").child(groupName.getText().toString()).setValue(group);

            // post group in myGroups
            database.child("users").child(userId).child("myGroups").child(groupName.getText().toString()).setValue(group);

            // DO YOU WANT TO JOIN THE GROUP?
            if (yesButton.isChecked()) {

                database.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String firstName = dataSnapshot.child("firstName").getValue().toString();

                        GroupUser groupUser = new GroupUser(firstName);

                        database.child("groups").child(groupName.getText().toString()).child(userId).setValue(groupUser);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // TODO: error
                    }
                });

            }



//            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    activeUser.populateUserWithFirebaseDatabaseRef(dataSnapshot);
//
//                    firstNameText.setText(activeUser.getFirstName());
//                    lastNameText.setText(activeUser.getLastName());
//                    licensePlateText.setText(activeUser.getLicense());
//                    emailAddressText.setText(activeUser.getEmail());
//                    Log.d(PLATZ_DEBUG_MSG, "Local user info from firebase: " + this.toString());
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    //TODO: This probably shouldn't crash the app and should be handled more gracefully :)
//                    throw new RuntimeException("Database read failure.");
//                }
//            });
//        }

            findViewById(R.id.create_group_loading).setVisibility(View.GONE);

            Toast.makeText(context, "Group Created!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);

        } else {
            // TODO: ERROR
        }
    }
}
