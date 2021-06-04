package com.steve.realmmultithreads.utils;

import com.steve.realmmultithreads.models.DataObj;
import com.steve.realmmultithreads.models.DataQuery;

public class DataUtil {

    // Set initial value when create a new data object.
    public static DataObj getNewData() {
        DataObj data = new DataObj();
        data.setId(1L);
        data.setA(2);
        data.setB(2);
        return data;
    }

    public static DataObj safeLoadData() {
        // If you call RealmUtil().loadData() is not thread safe.
        return RealmUtil.instance.loadData();
    }

    public static void safeSaveData(DataObj data) {
        RealmUtil.instance.saveData(data);
    }

    public static void updateDataQuery(DataQuery query) {
        DataObj newData = safeLoadData();
        query.updateQuery(newData);
        safeSaveData(newData);
    }
}
