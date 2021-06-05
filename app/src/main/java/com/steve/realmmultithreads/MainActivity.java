package com.steve.realmmultithreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.steve.realmmultithreads.repository.RealmSetup;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Realm - Multithreads";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(v -> TestData.testRealmDatabaseMultiThreads());
        // Initialize the Realm.
        RealmSetup.init(button.getContext().getApplicationContext());
    }
}