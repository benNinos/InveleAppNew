package com.ninositsolution.inveleapp.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterSearchHistoryBinding;

import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    private static final String TAG = SearchHistoryAdapter.class.getSimpleName();
    private Context context;
    private List<SearchKeywordModel> historyLists;
    private ISearch iSearch;
    private LayoutInflater layoutInflater;

    public SearchHistoryAdapter(Context context, List<SearchKeywordModel> historyLists, ISearch iSearch) {
        this.context = context;
        this.historyLists = historyLists;
        this.iSearch = iSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(context);

        AdapterSearchHistoryBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_search_history, viewGroup, false);

        return new SearchHistoryViewHolder(binding, iSearch);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder searchHistoryViewHolder, int i) {

        searchHistoryViewHolder.setBinding(new SearchVM(historyLists.get(i).getName()));
        Log.i(TAG, "value on bindviewholder -> "+historyLists.get(i));
    }

    @Override
    public int getItemCount() {
        return historyLists.size();
    }

    public class SearchHistoryViewHolder extends RecyclerView.ViewHolder{

        AdapterSearchHistoryBinding binding;
        ISearch iSearch;

        public SearchHistoryViewHolder(@NonNull AdapterSearchHistoryBinding binding, final ISearch iSearch) {
            super(binding.getRoot());

            this.binding = binding;
            this.iSearch = iSearch;

            binding.setIAdapterSearchHistory(new SearchHistoryClickListeners() {
                @Override
                public void onTextClicked() {
                    iSearch.searchClicked(historyLists.get(getAdapterPosition()).getName());
                }

                @Override
                public void onCloseClicked() {
                    iSearch.onKeywordCloseClicked(getAdapterPosition());
                }
            });
        }

        public void setBinding(SearchVM searchVM)
        {
            binding.setAdapterSearchHistory(searchVM);
            binding.executePendingBindings();
        }
    }

    public interface SearchHistoryClickListeners
    {
        void onTextClicked();
        void onCloseClicked();
    }
}
