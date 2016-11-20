package com.sync.taylorcase.sync.JoinGroup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JoinGroupActivity extends AppCompatActivity {

    @Bind(R.id.choose_items_done_button) Button doneButton;

    ListView listView;
    DatabaseReference database;
    FirebaseAuth auth;
    ArrayList<String> groupNames;
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
    }

}
