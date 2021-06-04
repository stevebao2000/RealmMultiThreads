package com.steve.realmmultithreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.steve.realmmultithreads.models.DataObj;
import com.steve.realmmultithreads.models.DataQuery;
import com.steve.realmmultithreads.utils.DataUtil;

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
                testRealmDatabaseAsync();
            }
        });
        // Initialize the Realm.
        Project.init(getApplicationContext());

        testRealmDatabaseAsync();
    }


    /**
     * Multithreads is a big problem for many people. We can test that in this small project, the problem is resolved.
     * 1. Suppose we have threads: 1 and 2
     *    Thread 1 load DataObj(a = 1 and b = 1) and doing something.
     *    Then thread 2 load DataObj (a = 1 and b = 1).
     * 2. Thread 2 update the value b = 2, then save.
     * 3. Therad 1 finished some work and update value b = 3, then save.
     * 4. You might get a = 2 and b = 1 because the first save(thread 2) has been over written by second save(thread 1).
     * 5. In this project, you will get correct result: a = 2 and b = 3.
     */
    private void testRealmDatabaseAsync() {

//        Project.getInstance().startRealm();
        DataObj data1 = DataUtil.safeLoadData();
        // bala bala

        // Thread 2:
        DataObj data2 = DataUtil.safeLoadData();
        // bala bala

//         wrong way:
//        data2.setB(3);
//        DataUtil.safeSaveData(data2);

        // Right way:
        DataQuery query2 = new DataQuery();
        query2.setValueA(3);
        DataUtil.updateDataQuery(query2);

        // Thread 1:
        DataQuery query1 = new DataQuery();
        query1.setValueA(2);
        DataUtil.updateDataQuery(query1);

        DataObj data = DataUtil.safeLoadData();
        // the final result should be a = 2 and b = 3.
        Log.v(TAG, "Value a = " + data.getA() + ", value b = " + data.getB());
    }
}