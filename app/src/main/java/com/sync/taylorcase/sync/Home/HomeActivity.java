package com.sync.taylorcase.sync.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sync.taylorcase.sync.R;

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

    @Override
    protected void onCreate(Bundle savedInstsanceState) {
        super.onCreate(savedInstsanceState);
        setContentView(R.layout.view_home);
        ButterKnife.bind(this);
    }
}
