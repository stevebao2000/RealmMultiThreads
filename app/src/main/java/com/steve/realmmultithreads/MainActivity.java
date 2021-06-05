package com.steve.realmmultithreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestData.testRealmDatabaseAsync();
            }
        });
        // Initialize the Realm.
        RealmSetup.init(this.button.getContext().getApplicationContext());

//        testRealmDatabaseAsync(); // Can not test here. It take sometime to init the realm.
    }
}