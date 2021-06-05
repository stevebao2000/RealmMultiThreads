package com.steve.realmmultithreads;

import android.util.Log;

import com.steve.realmmultithreads.models.DataObj;
import com.steve.realmmultithreads.models.DataQuery;
import com.steve.realmmultithreads.utils.DataUtil;
import com.steve.realmmultithreads.utils.RealmUtil;

public class TestData {
    private static final String TAG = "TestData";
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
    public static void testRealmDatabaseAsync() {
        DataUtil.safeDeleteData();
        /**
         * case: 2 threads load the DataObj, make change of the data and save at same time:
         */
        // wrong way:
        // thread 1:
        DataObj data1 = DataUtil.safeLoadData();
        // bala bala ....

        // thread 2:
        DataObj data2 = DataUtil.safeLoadData();
        // bala bala ...

        // Back to thread 1:
        data1.setA(2);
        DataUtil.safeSaveData(data1);

        // Thread 2:
        data2.setB(3);
        DataUtil.safeSaveData(data2);
        Log.v(TAG, "After set a = 2, b = 3 : " + DataUtil.safeLoadData().to_string());
        // After set a = 2, b = 3 : a = 0, b = 3, amount = -1
        // you can see that a = 0, so we can wrong result.

        //============================================================
        DataUtil.safeDeleteData();
        Log.v(TAG, "After deleteData(): " + DataUtil.safeLoadData().to_string());

        // Correct way:
        // Thread 2:
        DataQuery query1 = new DataQuery();
        query1.setValueA(2);
        DataUtil.updateDataQuery(query1);
        Log.v(TAG, "set a = 2: "  + DataUtil.safeLoadData().to_string());

        // Thread 1:
        DataQuery query2 = new DataQuery();
        query2.setValueB(3);
        DataUtil.updateDataQuery(query2);
        // the final result should be a = 2 and b = 3.
        Log.v(TAG, "set b = 3: "  + DataUtil.safeLoadData().to_string());

        //============================================================
        DataUtil.safeDeleteData();
        Log.v(TAG, "After deleteData(): " + DataUtil.safeLoadData().to_string());

        // Correct way:
        // Thread 2:
        DataQuery query3 = new DataQuery();
        query3.setIncrement(2);
        DataUtil.updateDataQuery(query3);
        Log.v(TAG, "set a = 2: "  + DataUtil.safeLoadData().to_string());

        // Thread 1:
        DataQuery query4 = new DataQuery();
        query4.setIncrement(3);
        DataUtil.updateDataQuery(query4);
        // the final result should be amount = 5.
        Log.v(TAG, "after increment 2 and 3: "  + DataUtil.safeLoadData().to_string());
    }
}
