package com.steve.realmmultithreads.utils;

import com.steve.realmmultithreads.models.DataObj;
import com.steve.realmmultithreads.models.DataQuery;

public class DataUtil {

    // Set initial value when create a new data object.
    public static DataObj createNewData() {
        DataObj data = new DataObj();
        data.setId(1L);
        data.setA(0);
        data.setB(0);
        data.increaseAmount(0);
        return data;
    }

    public static DataObj safeLoadData() {
        // If you call RealmUtil().loadData() is not thread safe.
        return RealmUtil.getInstance().loadData();
    }

    public static void safeSaveData(DataObj data) {
        RealmUtil.getInstance().saveData(data);
    }

    public static void safeDeleteData() {
        RealmUtil.getInstance().deleteData();
    }

    public static void updateDataQuery(DataQuery query) {
        DataObj newData = safeLoadData();
        query.updateQuery(newData);
        safeSaveData(newData);
    }
}
