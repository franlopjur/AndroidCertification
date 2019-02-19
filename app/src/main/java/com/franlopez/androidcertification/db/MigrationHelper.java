package com.franlopez.androidcertification.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class MigrationHelper {
    public static Migration getMigrationFromVersion1ToVersion2() {
        return new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE 'Word' ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                                         + "'name' TEXT NOT NULL)");
            }
        };
    }
}
