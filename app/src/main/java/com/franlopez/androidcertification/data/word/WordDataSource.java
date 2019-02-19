package com.franlopez.androidcertification.data.word;

import android.arch.lifecycle.LiveData;

public abstract class WordDataSource<T> {
    protected abstract T getWordListSync();
    protected abstract LiveData<T> getWordListAsync();
}
