package com.ninositsolution.inveleapp.search_everywhere;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.MarkerOptions;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFilterTwoViewBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;

public class FilterTwoViewAdapterMore extends RecyclerView.Adapter<FilterTwoViewAdapterMore.FilterTwoViewViewHolderMore> {

    private Context context;
    private ArrayList<HomeArrayLists> homeArrayLists;
    private LayoutInflater layoutInflater;
    private int mode;

    public FilterTwoViewAdapterMore(Context context, ArrayList<HomeArrayLists> homeArrayLists, int mode) {
        this.context = context;
        this.homeArrayLists = homeArrayLists;
        this.mode = mode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilterTwoViewViewHolderMore onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterFilterTwoViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_filter_two_view, viewGroup, false);

        return new FilterTwoViewViewHolderMore(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterTwoViewViewHolderMore filterTwoViewViewHolderMore, int i) {

        SearchEverywhereVM searchEverywhereVM;

        searchEverywhereVM = new SearchEverywhereVM(homeArrayLists.get(i), mode);
        filterTwoViewViewHolderMore.setBinding(searchEverywhereVM);

    }

    @Override
    public int getItemCount() {
        return homeArrayLists.size();
    }

    public class FilterTwoViewViewHolderMore extends RecyclerView.ViewHolder{

        AdapterFilterTwoViewBinding binding;

        public FilterTwoViewViewHolderMore(@NonNull AdapterFilterTwoViewBinding binding) {
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
