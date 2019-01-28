package com.franlopez.androidcertification.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.franlopez.androidcertification.CustomApplication;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.db.dao.GithubRepoDao;

import static com.franlopez.androidcertification.commons.Constants.DB_NAME;

@Database(entities = {GithubRepoDomain.class}, exportSchema = false, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase sInstance;

    public static synchronized MyDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(CustomApplication.getInstance(), MyDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public abstract GithubRepoDao reposDao();
}
