package com.ninositsolution.inveleapp.search_everywhere;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFilterTwoViewBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;

public class FilterTwoViewAdapter extends RecyclerView.Adapter<FilterTwoViewAdapter.FilterTwoViewViewHolder> {

    private Context context;
    private ArrayList<HomeArrayLists> homeArrayLists;
    private LayoutInflater layoutInflater;
    private int mode;

    public FilterTwoViewAdapter(Context context, ArrayList<HomeArrayLists> homeArrayLists, int mode) {
        this.context = context;
        this.homeArrayLists = homeArrayLists;
        this.mode = mode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilterTwoViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterFilterTwoViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_filter_two_view, viewGroup, false);

        return new FilterTwoViewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterTwoViewViewHolder filterTwoViewViewholder, int i) {

        SearchEverywhereVM searchEverywhereVM;

        if (mode == Constants.FILTER_SHOW_LESS)
        {
            searchEverywhereVM = new SearchEverywhereVM(homeArrayLists.get(i), Constants.SEARCH_EVERYWHERE_CATEGORIES);
            filterTwoViewViewholder.setBinding(searchEverywhereVM);

        } else
        {
            if (i > 3)
            {
                searchEverywhereVM = new SearchEverywhereVM(homeArrayLists.get(i), Constants.SEARCH_EVERYWHERE_CATEGORIES);
                filterTwoViewViewholder.setBinding(searchEverywhereVM);
            } else
            {
                filterTwoViewViewholder.binding.twoViewLayout.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mode == Constants.FILTER_SHOW_LESS)
        {
            if (homeArrayLists.size() > 4)
                return 4;
            else
                return homeArrayLists.size();
        } else
        {
            return homeArrayLists.size();
        }
    }

    public class FilterTwoViewViewHolder extends RecyclerView.ViewHolder{

        AdapterFilterTwoViewBinding binding;

        public FilterTwoViewViewHolder(@NonNull AdapterFilterTwoViewBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void setBinding(SearchEverywhereVM searchEverywhereVM)
        {
            this.binding.setAdapterTwoView(searchEverywhereVM);
            binding.executePendingBindings();
        }

        public AdapterFilterTwoViewBinding getBinding()
        {
            return binding;
        }
    }
}