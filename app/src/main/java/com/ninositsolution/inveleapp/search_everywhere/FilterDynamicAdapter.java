package com.ninositsolution.inveleapp.search_everywhere;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFilterDynamicBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;

public class FilterDynamicAdapter extends RecyclerView.Adapter<FilterDynamicAdapter.FilterDynamicViewHolder> {

    private Context context;
    private ArrayList<HomeArrayLists> homeArrayLists;
    private int mode;
    private LayoutInflater layoutInflater;
    private IFilter iFilter;

    public FilterDynamicAdapter(Context context, ArrayList<HomeArrayLists> homeArrayLists, int mode, IFilter iFilter) {
        this.context = context;
        this.homeArrayLists = homeArrayLists;
        this.mode = mode;
        this.iFilter = iFilter;

    }

    @NonNull
    @Override
    public FilterDynamicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterFilterDynamicBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_filter_dynamic, viewGroup, false);

        return new FilterDynamicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterDynamicViewHolder filterDynamicViewHolder, int i) {

        SearchEverywhereVM searchEverywhereVM;

        searchEverywhereVM = new SearchEverywhereVM(homeArrayLists.get(i), mode);

        filterDynamicViewHolder.setBinding(searchEverywhereVM);

        filterDynamicViewHolder.binding.filterDynamicRecyclerView.setAdapter(new FilterFourViewAdapter(context,
                homeArrayLists.get(i).attribute_values, homeArrayLists.get(i).is_color.equalsIgnoreCase("1"), iFilter,
                null, Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD));

    }

    @Override
    public int getItemCount() {
        return homeArrayLists.size();
    }

    public class FilterDynamicViewHolder extends RecyclerView.ViewHolder{

        AdapterFilterDynamicBinding binding;

        public FilterDynamicViewHolder(@NonNull AdapterFilterDynamicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.filterDynamicRecyclerView.setHasFixedSize(true) ;
            this.binding.filterDynamicRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        }

        public void setBinding(SearchEverywhereVM searchEverywhereVM)
        {
            this.binding.setAdapterDynamic(searchEverywhereVM);
            binding.executePendingBindings();
        }

        public AdapterFilterDynamicBinding getBinding()
        {
            return binding;
        }
    }
}
