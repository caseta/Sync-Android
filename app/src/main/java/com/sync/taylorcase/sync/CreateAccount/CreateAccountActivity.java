package com.sync.taylorcase.sync.CreateAccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.ChooseItems.ChooseItemsActivity;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.HowItWorksActivity;
import com.sync.taylorcase.sync.R;
import com.sync.taylorcase.sync.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.create_account_first_name) EditText firstName;
    @Bind(R.id.create_account_last_name) EditText lastName;
    @Bind(R.id.create_account_email) EditText email;
    @Bind(R.id.create_account_password) EditText password;
    @Bind(R.id.create_account_confirm_password) EditText confirmPassword;
    @Bind(R.id.create_account_submit) Button submitButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private Context context;

//    CreateAccount presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_create_account);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.create_loading).setVisibility(View.VISIBLE);
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.create_account_submit) {
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e("CREATE_ACCOUNT", "Auth successful");
                                createUserInDatabase();

                                findViewById(R.id.create_loading).setVisibility(View.GONE);
                                Intent intent = new Intent(context, HowItWorksActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                context.startActivity(intent);

                            } else {
                                Log.e("CREATE_ACCOUNT", "Error authenticaing user");
                            }
                        }

                    });
        } else {
            //TODO: error
        }
    }

    public void createUserInDatabase() {

        Log.e("CREATE_ACCOUNT", "Creating user in user node");

        User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString());

        String userId = firebaseAuth.getCurrentUser().getUid();

        database.child("users").child(userId).setValue(user);

        // READING
//        database.child("users").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //TODO: error
//            }
//        });
    }

}


//    // 4. GET ALL OF THE EVENT INFO
//    public ArrayList<Event> createEventList(DataSnapshot snapshot) {
//
//        ArrayList<Event> events = new ArrayList<>();
//
//        for (DataSnapshot snapshotChild : snapshot.getChildren()) {
//
////            We could use the line below (very nice and clean) if we didn\'t
////            need to get the event's name (AKA child.getKey();)
//
////            Event event = child.getValue(Event.class);
//
//            String eventName = snapshotChild.getKey();
//            String eventAddress = snapshotChild.child("address").getValue().toString();
//            String eventDate = snapshotChild.child("date").getValue().toString();
//            String eventHalf = snapshotChild.child("half").getValue().toString();
//            String eventMakeAvailable = snapshotChild.child("makeavailable").getValue().toString();
//            String eventTime = snapshotChild.child("time").getValue().toString();
//
//            Event event = new Event(eventName, eventAddress, eventDate, eventHalf, eventMakeAvailable, eventTime);
//
//            events.add(event);
//        }
//
//        return events;
//    }