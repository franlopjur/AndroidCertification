package com.franlopez.androidcertification.ui.word;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.model.domain.WordDomain;
import com.franlopez.androidcertification.ui.base.BaseActivity;

import java.util.List;

public class WordActivity extends BaseActivity {

    private RecyclerView wordList;
    private WordViewModel wordViewModel;

    @Override
    public int getContentLayout() {
        return R.layout.word_container;
    }

    @Override
    protected void initializeViews() {
        wordList = findViewById(R.id.word__list__elements);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getWordLiveData().observe(this, new Observer<List<WordDomain>>() {
            @Override
            public void onChanged(@Nullable List<WordDomain> wordDomains) {
                wordList.setAdapter(new WordAdapter(wordDomains));
            }
        });
    }

    @Override
    protected void fabActionBehaviour() {
        wordViewModel.insertNewWord("Word " + wordList.getAdapter().getItemCount());
    }

    @Override
    protected boolean isFabVisible() {
        return true;
    }
}
