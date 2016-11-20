package com.sync.taylorcase.sync.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sync.taylorcase.sync.ChooseItems.ChooseItemsActivity;
import com.sync.taylorcase.sync.CreateAccount.CreateAccountActivity;
import com.sync.taylorcase.sync.Home.HomeActivity;
import com.sync.taylorcase.sync.HowItWorksActivity;
import com.sync.taylorcase.sync.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, View.OnClickListener {
//        , MvpDelegateCallback<LoginContract.LoginView, LoginContract.LoginPresenter> {

    @Bind(R.id.login_username_textview) EditText usernameTextView;
    @Bind(R.id.login_password_textview) EditText passwordTextView;
    @Bind(R.id.login_login_button) Button loginButton;
    @Bind(R.id.login_create_account_button) Button createAccountButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private Context context;

    LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        ButterKnife.bind(this);
        findViewById(R.id.login_loading).setVisibility(View.GONE);

        //////////////////
        // SKIP LOGIN   //
        //////////////////

//        context = getApplicationContext();
//        Intent intent = new Intent(context, HowItWorksActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        context.startActivity(intent);

        //////////////////
        // END  //////////
        //////////////////

        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new LoginPresenterImpl();

        database = FirebaseDatabase.getInstance().getReference();

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.login_loading).setVisibility(View.VISIBLE);
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.login_login_button) {

            String emailText = usernameTextView.getText().toString().replaceAll("\\s+","");
            String passwordText = passwordTextView.getText().toString().replaceAll("\\s+","");

            firebaseAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                findViewById(R.id.login_loading).setVisibility(View.GONE);
                                Intent intent = new Intent(context, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                context.startActivity(intent);
                            } else {

                            }
                        }
                    });
        } else if (i == R.id.login_create_account_button) {
            Intent intent = new Intent(context, CreateAccountActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        } else {
            //TODO: error
        }
    }

//    hEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
////                            findViewById(R.id.login_loading).setVisibility(View.GONE);
//                presenter.goToHomeView(context);
//            } else {
//                Log.e(LOGIN_ACTIVITY_TAG, task.getException().getMessage());
//                presenter.displayError(context, "Please enter the correct password");
//            }
//
//        }
//    });

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