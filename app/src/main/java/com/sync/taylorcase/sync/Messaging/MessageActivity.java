package com.sync.taylorcase.sync.Messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sync.taylorcase.sync.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.input) EditText input;

    Context context;
    DatabaseReference database;
    FirebaseAuth auth;
    String id;
    String currentUserId;

    private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_chat_window);
        ButterKnife.bind(this);

        fab.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        id = getIntent().getExtras().getString("ID");
        currentUserId = auth.getCurrentUser().getUid();

        displayChatMessages();

    }

    @Override
    public void onClick(View v) {
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.fab) {
            // post to MINE
            database.child("users").child(currentUserId).child("mySyncs").child(id).child("messages").push()
                    .setValue(new ChatMessage(input.getText().toString(),
                            FirebaseAuth.getInstance().getCurrentUser().getDisplayName()
                    ));

            // post to THEIRS
            database.child("users").child(id).child("mySyncs").child(currentUserId).child("messages").push()
                    .setValue(new ChatMessage(input.getText().toString(),
                            FirebaseAuth.getInstance().getCurrentUser().getDisplayName()
                    ));

            input.setText("");
        } else {
            // TODO: error
        }
    }

    private void displayChatMessages() {
        Log.e("YEETxxxxx", "1");
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
        Log.e("YEETxxxxx", "2");

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, database.child("users").child(currentUserId).child("mySyncs").child(id).child("messages")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                 Log.e("YEETxxxxx", "1111");
                messageText.setText(model.getMessageText());
                Log.e("YEETxxxxx", "22222");
                messageUser.setText(model.getMessageUser());
                Log.e("YEETxxxxx", "33333");
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
                Log.e("YEETxxxxx", "44444");

            }
        };
        Log.e("YEETxxxxx", "3");

        listOfMessages.setAdapter(adapter);
        Log.e("YEETxxxxx", "4");

    }


}
