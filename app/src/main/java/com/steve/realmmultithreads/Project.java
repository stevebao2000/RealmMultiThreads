package com.steve.realmmultithreads;

import android.content.Context;
import android.print.PrintJob;

import com.steve.realmmultithreads.repository.ProjectModules;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Project {
    private static final String TAG = "Project";
    private static Project instance = null;
    private Context context = null;
    private Realm realm;
    private final RealmConfiguration realmConfiguration;

    public static Project getInstance() {
        return instance;
    }

    public static void init(Context mContext) {
        if (null == instance) {
            instance = new Project(mContext);
        }
    }

    private Project(Context mcontext) {
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
