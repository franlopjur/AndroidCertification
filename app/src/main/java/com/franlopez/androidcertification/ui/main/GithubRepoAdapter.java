package com.franlopez.androidcertification.ui.main;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franlopez.androidcertification.R;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;

public class GithubRepoAdapter extends PagedListAdapter<GithubRepoDomain, GithubRepoAdapter.GithubRepoViewHolder> {

    public GithubRepoAdapter(@NonNull DiffUtil.ItemCallback<GithubRepoDomain> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public GithubRepoAdapter.GithubRepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GithubRepoViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_github_repo, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GithubRepoAdapter.GithubRepoViewHolder viewHolder, int position) {
        GithubRepoDomain item = getItem(position);
        if (item != null) {
            viewHolder.bind(item, position);
        }
    }

    class GithubRepoViewHolder extends RecyclerView.ViewHolder {

        private TextView nameLabel;

        GithubRepoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.row_github_repo__label__name);
        }

        void bind(GithubRepoDomain repo, int position) {
            if (repo != null) {
                nameLabel.setText(position + " - " + repo.getName());
            }
        }
    }
}
