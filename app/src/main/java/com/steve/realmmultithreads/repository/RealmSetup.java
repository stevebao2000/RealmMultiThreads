package com.steve.realmmultithreads.repository;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmSetup {
    private static final String TAG = "Project";
    private static RealmSetup instance = null;
    private Context context = null;
    private Realm realm;
    private final RealmConfiguration realmConfiguration;

    public static RealmSetup getInstance() {
        return instance;
    }

    public static void init(Context mContext) {
        if (null == instance) {
            instance = new RealmSetup(mContext);
        }
    }

    private RealmSetup(Context mcontext) {
        this.context = mcontext;

        Realm.init(context);

        realmConfiguration = new RealmConfiguration.Builder()
                .name("myProject.realm")
                .schemaVersion(1)
                .modules(new ProjectModules())
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public Realm getRealm() {
        realm = Realm.getInstance(realmConfiguration);
        return realm;
    }

    public void startRealm() {
        realm = Realm.getInstance(realmConfiguration);
        realm.init(context);
    }

    public static boolean isInitialized() {
        return null != instance;
    }

    public static Context getContext() {
        return instance.context;
    }
}
