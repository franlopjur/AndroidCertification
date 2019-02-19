package com.franlopez.androidcertification.ui.word;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.franlopez.androidcertification.data.word.WordRepository;
import com.franlopez.androidcertification.model.domain.WordDomain;

import java.util.List;

public class WordViewModel extends ViewModel {
    private WordRepository wordRepository;

    private LiveData<List<WordDomain>> wordLiveData;

    public WordViewModel() {
        this.wordRepository = new WordRepository();
        this.wordLiveData = wordRepository.searchWordList();
    }

    public LiveData<List<WordDomain>> getWordLiveData() {
        return wordLiveData;
    }

    public void insertNewWord(String name) {
        wordRepository.insertWord(name);
    }
}
