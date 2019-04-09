package com.ninositsolution.inveleapp.search_everywhere;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFilterTwoViewBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;

public class FilterFourViewAdapter extends RecyclerView.Adapter<FilterFourViewAdapter.FilterFourViewHolder> {

    private Context context;
    private ArrayList<HomeArrayLists> homeArrayLists;
    private boolean flag;
    private LayoutInflater layoutInflater;
    private IFilter iFilter;
    private ArrayList<String> fitme_sizes;
    private int mode;

    public FilterFourViewAdapter(Context context, ArrayList<HomeArrayLists> homeArrayLists, boolean flag, IFilter iFilter, ArrayList<String> filtme_sizes, int mode) {
        this.context = context;
        this.iFilter = iFilter;
        this.mode = mode;
        notifyDataSetChanged();

        if (mode == Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD)
        {
            this.homeArrayLists = homeArrayLists;
            this.flag = flag;
        } else
        {
            this.fitme_sizes = filtme_sizes;
        }
    }

    @NonNull
    @Override
    public FilterFourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterFilterTwoViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_filter_two_view, viewGroup, false);

        return new FilterFourViewHolder(binding, iFilter);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterFourViewHolder filterFourViewHolder, int i) {

        SearchEverywhereVM searchEverywhereVM;

        if (mode == Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD)
        {
            searchEverywhereVM = new SearchEverywhereVM(homeArrayLists.get(i), flag);
            filterFourViewHolder.setBinding(searchEverywhereVM);
        } else
        {
            searchEverywhereVM = new SearchEverywhereVM(fitme_sizes.get(i));
            filterFourViewHolder.setBinding(searchEverywhereVM);
        }

    }

    @Override
    public int getItemCount() {

        if (mode == Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD)
        {
            return homeArrayLists.size();
        } else
        {
            try {
                return fitme_sizes.size();
            } catch (NullPointerException e)
            {

            }

        }

        return 0;
    }

    public class FilterFourViewHolder extends RecyclerView.ViewHolder{

        AdapterFilterTwoViewBinding binding;
        IFilter iFilter;

        public FilterFourViewHolder(@NonNull AdapterFilterTwoViewBinding binding, IFilter iFilter) {
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

                    if (mode == Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD)
                        iFilter.onFilterTwoViewClicked(Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD, homeArrayLists.get(getAdapterPosition()).id);
                    else
                        iFilter.onFitmeSizeClicked(fitme_sizes.get(getAdapterPosition()));

                }
            });
        }

        public AdapterFilterTwoViewBinding getBinding()
        {
            return binding;
        }
    }
}
