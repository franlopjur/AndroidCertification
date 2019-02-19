package com.franlopez.androidcertification.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.franlopez.androidcertification.model.domain.WordDomain;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WordDomain word);

    @Query("DELETE FROM Word")
    void deleteAll();

    @Query("SELECT * FROM Word WHERE name == :nameToSearch")
    LiveData<WordDomain> getWordByName(String nameToSearch);

    @Query("SELECT * FROM Word")
    LiveData<List<WordDomain>> getWordList();
}
