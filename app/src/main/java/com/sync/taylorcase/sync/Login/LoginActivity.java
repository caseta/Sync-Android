package com.sync.taylorcase.sync.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sync.taylorcase.sync.R;
import com.sync.taylorcase.sync.mvp.MvpDelegateCallback;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {
//        , MvpDelegateCallback<LoginContract.LoginView, LoginContract.LoginPresenter> {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);

        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new LoginPresenterImpl();

        database = FirebaseDatabase.getInstance().getReference();
    }

//    @Override
//    public LoginContract.LoginPresenter createPresenter() {
//        presenter = new LoginPresenterImpl();
//        return presenter;
//    }
//
//    @Override
//    public LoginContract.LoginView getMvpView() {
//        return this;
//    }
//
//    @Override
//    public boolean shouldRetainInstance() {
//        return false;
//    }
}

//public class ParkActivity extends AppCompatActivity implements ParkScreenContract.ParkScreenView,
//        MvpDelegateCallback<ParkScreenContract.ParkScreenView, ParkScreenContract.ParkScreenPresenter> {