package com.sync.taylorcase.sync;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SyncSummaryActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savtedInstanceState) {
        super.onCreate(savtedInstanceState);
        setContentView(R.layout.view_sync_summary);

        id = getIntent().getExtras().getString("ID");
    }
}
