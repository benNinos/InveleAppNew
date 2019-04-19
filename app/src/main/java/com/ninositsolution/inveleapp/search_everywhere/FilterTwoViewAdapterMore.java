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
    private IFilter iFilter;

    public FilterTwoViewAdapterMore(Context context, ArrayList<HomeArrayLists> homeArrayLists, int mode, IFilter iFilter) {
        this.context = context;
        this.homeArrayLists = homeArrayLists;
        this.mode = mode;
        this.iFilter = iFilter;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilterTwoViewViewHolderMore onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterFilterTwoViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_filter_two_view, viewGroup, false);

        return new FilterTwoViewViewHolderMore(binding, iFilter);
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
        IFilter iFilter;

        public FilterTwoViewViewHolderMore(@NonNull AdapterFilterTwoViewBinding binding, IFilter iFilter) {
            super(binding.getRoot());
            this.binding = binding;
            this.iFilter = iFilter;
        }

        public void setBinding(SearchEverywhereVM searchEverywhereVM)
        {
            this.binding.setAdapterTwoView(searchEverywhereVM);
            binding.executePendingBindings();

            binding.setIAdapterTwoView(new FilterTwoViewAdapter.FilterTwoViewListener() {

                @Override
                public void onButtonClicked() {

                    if (binding.twoViewButton.getCurrentTextColor() == context.getResources().getColor(R.color.text_color))
                    {
                        binding.twoViewButton.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        binding.twoViewButton.setTextColor(context.getResources().getColor(R.color.white));

                    } else {
                        binding.twoViewButton.setBackground(context.getResources().getDrawable(R.drawable.altered_button_bground));
                        binding.twoViewButton.setTextColor(context.getResources().getColor(R.color.text_color));
                    }

                    if (mode == Constants.SEARCH_EVERYWHERE_CATEGORIES)
                        iFilter.onCategoriesClicked(homeArrayLists.get(getAdapterPosition()).slug, homeArrayLists.get(getAdapterPosition()).name);

                    if (mode == Constants.SEARCH_EVERYWHERE_BRANDS || mode == Constants.SEARCH_EVERYWHERE_SHIPPING)
                        iFilter.onFilterTwoViewClicked(mode, homeArrayLists.get(getAdapterPosition()).id);

                    if (mode == Constants.SEARCH_EVERYWHERE_LOCATIONS)
                        iFilter.onFilterTwoViewClicked(mode, homeArrayLists.get(getAdapterPosition()).seller_store_location_id);
                }
            });
        }

        public AdapterFilterTwoViewBinding getBinding()
        {
            return binding;
        }
    }
}
