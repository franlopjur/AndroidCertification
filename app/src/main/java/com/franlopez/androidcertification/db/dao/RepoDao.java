package com.franlopez.androidcertification.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.franlopez.androidcertification.data.domain.RepoDomain;

import java.util.List;

@Dao
public interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RepoDomain> repoList);

    @Query("SELECT * FROM Repos")
    List<RepoDomain> getRepos();
}
