package com.sync.taylorcase.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sync.taylorcase.sync.ChooseItems.ChooseItemsActivity;
import com.sync.taylorcase.sync.Home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HowToActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.how_it_works_button) Button submitButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_how_it_works);
        ButterKnife.bind(this);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        context = getApplicationContext();
        int i = v.getId();
        if (i == R.id.how_it_works_button) {
            Intent intent = new Intent(context, ChooseItemsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        } else {
            // TODO: error
        }

    }
}
