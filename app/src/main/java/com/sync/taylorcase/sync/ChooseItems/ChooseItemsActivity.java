package com.sync.taylorcase.sync.ChooseItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sync.taylorcase.sync.CreateAccount.CreateAccountActivity;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.Item;
import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseItemsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.choose_items_done_button) Button doneButton;

    ListView listView;
    DatabaseReference database;
    FirebaseAuth auth;
    ArrayList<String> itemNames; // to populate to show them
    Context context;

    ArrayList<String> userPicksItemNames; // selected names
    ArrayList<String> userPicksLoveSelected;
    ArrayList<String> userPicksLikeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_choose_items);
        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.choose_items_list_view);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        doneButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        findViewById(R.id.choose_loading).setVisibility(View.VISIBLE);

        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.choose_items_done_button) {

            populateCheckBoxesChecked();

        } else {
            // TODO: error
        }
    }

    public void populateCheckBoxesChecked() {

        View view;
        CheckBox loveCheckbox;
        CheckBox likeCheckbox;

        userPicksItemNames = new ArrayList<>();
        userPicksLikeSelected = new ArrayList<>();
        userPicksLoveSelected = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            view = listView.getChildAt(i);
            loveCheckbox = (CheckBox) view.findViewById(R.id.love_checkbox);
            likeCheckbox = (CheckBox) view.findViewById(R.id.like_checkbox);

            if (loveCheckbox.isChecked() && !(likeCheckbox.isChecked())) {
                userPicksItemNames.add(itemNames.get(i));
                userPicksLoveSelected.add("1");
                userPicksLikeSelected.add("0");
            } else if (likeCheckbox.isChecked() && !(loveCheckbox.isChecked())) {
                userPicksItemNames.add(itemNames.get(i));
                userPicksLoveSelected.add("0");
                userPicksLikeSelected.add("1");
            } else if (loveCheckbox.isChecked() && likeCheckbox.isChecked()) {
                userPicksItemNames.add(itemNames.get(i));
                userPicksLoveSelected.add("1");
                userPicksLikeSelected.add("1");
            } else {
                // NO CHECK BOXES SELECTED FOR THIS ONE

                // OR ERROR LOL
            }

        }

        inputUserItems();

    }

    public void inputUserItems() {

        String userId = auth.getCurrentUser().getUid();

        for (int i = 0; i < userPicksItemNames.size(); i++) {
            String name = userPicksItemNames.get(i);
            Item item = new Item(userPicksLoveSelected.get(i), userPicksLikeSelected.get(i));

            database.child("users").child(userId).child("myItems").child(name).setValue(item);
        }

        findViewById(R.id.choose_loading).setVisibility(View.GONE);

        Toast.makeText(context, "Preferences Updated!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);

    }

}

//    private void ButtonClick() {
//        /** get all values of the EditText-Fields */
//        View v;
//        ArrayList<String> mannschaftsnamen = new ArrayList<String>();
//        EditText et;
//        for (int i = 0; i < myList.getCount(); i++) {
//            v = myList.getAdapter().getView(i, null, null);
//            et = (EditText) v.findViewById(i);
//            mannschaftsnamen.add(et.getText().toString());
//        }
//        ....
//    }


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