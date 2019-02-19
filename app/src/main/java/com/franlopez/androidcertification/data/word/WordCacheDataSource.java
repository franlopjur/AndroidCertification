package com.franlopez.androidcertification.data.word;

import android.arch.lifecycle.LiveData;

import com.franlopez.androidcertification.db.MyDatabase;
import com.franlopez.androidcertification.model.domain.WordDomain;

import java.util.List;

public class WordCacheDataSource extends WordDataSource<List<WordDomain>> {

    public void insertWord(String name) {
        WordDomain word = new WordDomain();
        word.setName(name);
        MyDatabase.getInstance().wordDao().insert(word);
    }

    @Override
    protected List<WordDomain> getWordListSync() {
        return null;
    }

    @Override
    protected LiveData<List<WordDomain>> getWordListAsync() {
        return MyDatabase.getInstance().wordDao().getWordList();
    }
}
