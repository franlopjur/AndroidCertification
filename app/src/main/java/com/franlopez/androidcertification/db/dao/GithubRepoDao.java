package com.franlopez.androidcertification.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

import java.util.List;

@Dao
public interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GithubRepoDomain> repoList);

    @Query("SELECT * FROM GithubRepos WHERE (name LIKE :query) OR (description LIKE :query)")
    LiveData<List<GithubRepoDomain>> getReposAsync(String query);

    @Query("SELECT * FROM GithubRepos WHERE (name LIKE :query) OR (description LIKE :query)")
    List<GithubRepoDomain> getRepos(String query);
}
