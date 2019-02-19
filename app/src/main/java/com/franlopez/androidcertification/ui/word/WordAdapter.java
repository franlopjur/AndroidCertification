package com.franlopez.androidcertification.ui.word;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.model.domain.WordDomain;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<WordDomain> wordList;

    public WordAdapter(List<WordDomain> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_word, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        wordViewHolder.bind();
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    private List<WordDomain> getWordList() {
        return wordList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private TextView nameLabel;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.row_word__label__name);
        }

        public void bind() {
            if (getWordList() != null &&
                    !getWordList().isEmpty()) {
                WordDomain item = getWordList().get(getLayoutPosition());

                if (item != null) {
                    nameLabel.setText(item.getName());
                }
            }
        }
    }
}
