package com.sync.taylorcase.sync.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sync.taylorcase.sync.R;

import java.util.List;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

//    @Bind(R.id.login_create_account_button) Button createAccountButton;
//
//    private FirebaseAuth firebaseAuth;
//    private DatabaseReference database;
//    private Context context;
//
//    LoginPresenterImpl presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_login);
//        ButterKnife.bind(this);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        presenter = new LoginPresenterImpl();
//
//        database = FirebaseDatabase.getInstance().getReference();
//
//        loginButton.setOnClickListener(this);
//        createAccountButton.setOnClickListener(this);
//    }

    private Context context;
    private ListView List;
    private ArrayAdapter<String> Adapter;

    @Override
    protected void onCreate(Bundle savedInstsanceState) {
        super.onCreate(savedInstsanceState);
        setContentView(R.layout.view_home);
        ButterKnife.bind(this);

        List = (ListView) findViewById(R.id.navList);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navBarItemClicked(position);
            }
        });

        createNavMenu();
    }

    //////////////////
    // NAV BAR MENU //
    //////////////////

    private void createNavMenu() {
        String[] options = {"Home", "Account Settings", "Postings"};
        Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        List.setAdapter(Adapter);
    }

    public void navBarItemClicked(int position) {
        context = getApplicationContext();

        if (position == 0) {
            Toast.makeText(context, "WORKS!", Toast.LENGTH_SHORT).show();
        } else if (position == 1) {

        } else if (position == 2) {

        } else {
            //TODO: error
        }
    }

}
//        if(position == 0){
//            Toast.makeText(context, "HOME selected!", Toast.LENGTH_SHORT).show();
//        } else if (position == 1){
//            //Toast.makeText(context, "ACCOUNT SETTINGS selected!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(context, EditAccountSettingsActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(intent);
//        } else if (position == 2){
//            Toast.makeText(context, "POSTINGS selected!", Toast.LENGTH_SHORT).show();
//        } else if (position == 3){
//            //Toast.makeText(context, "RESERV
//    }
