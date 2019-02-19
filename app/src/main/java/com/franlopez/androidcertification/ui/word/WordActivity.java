package com.franlopez.androidcertification.ui.word;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.model.domain.WordDomain;
import com.franlopez.androidcertification.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class WordActivity extends BaseActivity {

    private RecyclerView wordList;
    private WordViewModel wordViewModel;
    private WordAdapter wordAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.word_container;
    }

    @Override
    protected void initializeViews() {
        wordList = findViewById(R.id.word__list__elements);
        wordAdapter = new WordAdapter(new ArrayList<WordDomain>());
        wordList.setAdapter(wordAdapter);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getWordLiveData().observe(this, new Observer<List<WordDomain>>() {
            @Override
            public void onChanged(@Nullable List<WordDomain> wordDomains) {
                boolean needScroll = wordAdapter.getItemCount() != 0;
                wordAdapter.setWordList(wordDomains);
                int positionToScroll = needScroll ?
                        wordAdapter.getItemCount() - 1 : 0;
                wordList.smoothScrollToPosition(positionToScroll);
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
