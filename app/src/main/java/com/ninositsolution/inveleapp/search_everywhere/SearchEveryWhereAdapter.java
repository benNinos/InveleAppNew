package com.ninositsolution.inveleapp.search_everywhere;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.search.SearchAdapter;

public class SearchEveryWhereAdapter extends RecyclerView.Adapter<SearchEveryWhereAdapter.SearchEveryWhereViewHolder> {


    @NonNull
    @Override
    public SearchEveryWhereViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchEveryWhereViewHolder searchEveryWhereViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchEveryWhereViewHolder extends RecyclerView.ViewHolder {


        public SearchEveryWhereViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
