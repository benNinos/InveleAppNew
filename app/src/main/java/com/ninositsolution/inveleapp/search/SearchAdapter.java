package com.ninositsolution.inveleapp.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private List<String> searchTextList = new ArrayList<>();
    private ISearch iSearch;

    public SearchAdapter(Context context, List<String> searchTextList, ISearch iSearch) {
        this.context = context;
        this.searchTextList = searchTextList;
        this.iSearch = iSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, final int i) {

        searchViewHolder.textView.setText(searchTextList.get(i));

        searchViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSearch.searchClicked(searchTextList.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchTextList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.adapter_search_text);
        }

    }
}
