package com.franlopez.androidcertification.data.word;

import android.arch.lifecycle.LiveData;

import com.franlopez.androidcertification.model.domain.WordDomain;

import java.util.List;

public class WordRepository {

    private WordCacheDataSource cacheDataSource;

    public WordRepository() {
        this.cacheDataSource = new WordCacheDataSource();
    }

    public LiveData<List<WordDomain>> searchWordList() {
        return cacheDataSource.getWordListAsync();
    }

    public void insertWord(String name) {
        cacheDataSource.insertWord(name);
    }
}
