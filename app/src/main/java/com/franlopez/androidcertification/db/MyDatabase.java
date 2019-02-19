package com.franlopez.androidcertification.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.franlopez.androidcertification.CustomApplication;
import com.franlopez.androidcertification.db.dao.WordDao;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.db.dao.GithubRepoDao;
import com.franlopez.androidcertification.model.domain.WordDomain;

import static com.franlopez.androidcertification.commons.Constants.DB_NAME;

@Database(entities = {GithubRepoDomain.class, WordDomain.class}, exportSchema = false, version = 2)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase sInstance;

    public static synchronized MyDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(CustomApplication.getInstance(), MyDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(MigrationHelper.getMigrationFromVersion1ToVersion2())
                    .build();
        }
        return sInstance;
    }

    public abstract GithubRepoDao reposDao();
    public abstract WordDao wordDao();
}
