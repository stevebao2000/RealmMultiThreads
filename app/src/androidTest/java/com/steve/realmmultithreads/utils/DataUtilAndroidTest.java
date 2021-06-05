package com.steve.realmmultithreads.utils;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;
import com.steve.realmmultithreads.models.DataObj;
import com.steve.realmmultithreads.models.DataQuery;
import com.steve.realmmultithreads.repository.RealmSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DataUtilAndroidTest {
    Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = ApplicationProvider.getApplicationContext();
        RealmSetup.init(mContext);
    }

    @After
    public void tearDown() throws Exception {
        mContext = null;
    }

    @Test
    public void testTwoThreadChangeValues_IncorrectResult() {
        // clean the dataObj first
        DataUtil.safeDeleteData();

        DataObj data1 = DataUtil.safeLoadData();
        DataObj data2 = DataUtil.safeLoadData();

        data1.setA(2);
        DataUtil.safeSaveData(data1);

        data2.setB(3);
        DataUtil.safeSaveData(data2);

        DataObj newData = DataUtil.safeLoadData();
        // The value a = 0 (override by second save).
        assertNotEquals(newData.getA(), 2);
    }

    @Test
    public void testTwoThreadChangeValues_CorrectResult() {
        // clean the dataObj first
        DataUtil.safeDeleteData();

        // thread 1:
        DataQuery query1 = new DataQuery();
        query1.setValueA(2);
        DataUtil.updateDataQuery(query1);

        DataQuery query2 = new DataQuery();
        query2.setValueB(3);
        DataUtil.updateDataQuery(query2);

        DataObj newData = DataUtil.safeLoadData();
        // The value a = 2.
        assertEquals(newData.getA(), 2);
        assertEquals(newData.getB(), 3);
    }

    @Test
    public void testTwoThreadIncrementAmount_CorrectResult() {
        // clean the dataObj first
        DataUtil.safeDeleteData();

        // thread 1:
        DataQuery query1 = new DataQuery();
        query1.setIncrement(2);
        DataUtil.updateDataQuery(query1);

        DataQuery query2 = new DataQuery();
        query2.setIncrement(3);
        DataUtil.updateDataQuery(query2);

        DataObj newData = DataUtil.safeLoadData();
        // The amount = 2 + 3.
        assertEquals(newData.getAmount(), 5);
    }
}