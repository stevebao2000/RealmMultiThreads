package com.steve.realmmultithreads.utils;

import com.steve.realmmultithreads.Project;
import com.steve.realmmultithreads.models.DataObj;

import io.realm.Realm;

public class RealmUtil {
    private static final String TAG = "RealmUtil";
    static volatile RealmUtil instance = null;

    public static RealmUtil getInstance() {
        if (instance == null) {
            instance = new RealmUtil();
        }
        return instance;
    }

    public synchronized DataObj loadData() {
        try (Realm realm = Project.getInstance().getRealm()) {
            DataObj data = realm.where(DataObj.class).findFirst();
            if (data == null) {
                return DataUtil.createNewData();
            }
            return realm.copyFromRealm(data);
        }
    }

    public synchronized void saveData(final DataObj data) {
        try (Realm realm = Project.getInstance().getRealm()) {
            realm.refresh();
            realm.executeTransaction((realm1) -> {
                realm1.copyToRealmOrUpdate(data);
            });
        }
    }

    public synchronized void deleteData() {
        try (Realm realm = Project.getInstance().getRealm()) {
            DataObj data = realm.where(DataObj.class).findFirst();
            if (data != null) {
                realm.executeTransaction((realm1) -> {
                    realm1.deleteAll();
                });
            }
        }
    }
}
