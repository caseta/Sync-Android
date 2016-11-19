package com.sync.taylorcase.sync.CreateAccount;

import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sync.taylorcase.sync.R;

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
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.login_create_account_button) {
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.e("CREATE_ACCOUNT", "Auth successful");
                            Log.e("CREATE_ACCOUNT", "Creating user in user node");
                            createUserInDatabase();
                        }

                    });
        } else {
            //TODO: error
        }
    }

    public void createUserInDatabase() {
        
    }
}


//if (errorCode.length() == 1) {
//        authObject.createUserWithEmailAndPassword(emailText, passwordText)
//        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//@Override
//public void onComplete(@NonNull Task<AuthResult> task) {
//        if (task.isSuccessful()) {
//        presenter.createNewUserInDatabase(task.getResult().getUser().getUid(), emailText, firstNameText, lastNameText, licenseText);
//        presenter.goToHomeView(context);
//        } else {
//        // Error creating user
//        }
//        }
//        });
//        } else {
//        presenter.displayError(context, errorCode);
//        }