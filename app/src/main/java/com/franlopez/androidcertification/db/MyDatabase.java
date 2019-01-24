package com.franlopez.androidcertification.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.franlopez.androidcertification.CustomApplication;
import com.franlopez.androidcertification.data.domain.RepoDomain;
import com.franlopez.androidcertification.db.dao.RepoDao;

@Database(entities = {RepoDomain.class}, exportSchema = true, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase sInstance;

    public static synchronized MyDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(CustomApplication.getInstance(), MyDatabase.class, "database")
                    .build();
        }
        return sInstance;
    }

    public abstract RepoDao reposDao();
}
