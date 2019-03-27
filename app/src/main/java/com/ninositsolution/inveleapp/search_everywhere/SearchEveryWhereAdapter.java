package com.ninositsolution.inveleapp.search_everywhere;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterProductThumbnailBinding;
import com.ninositsolution.inveleapp.databinding.AdapterTrendingProductsBinding;
import com.ninositsolution.inveleapp.home.HomeVM;
import com.ninositsolution.inveleapp.search.SearchAdapter;
import com.ninositsolution.inveleapp.utils.Constants;

public class SearchEveryWhereAdapter extends RecyclerView.Adapter<SearchEveryWhereAdapter.SearchEveryWhereViewHolder> {

    private Context context;
    private SearchEverywhereVM searchEverywhereVM;
    private LayoutInflater layoutInflater;

    public SearchEveryWhereAdapter(Context context, SearchEverywhereVM searchEverywhereVM) {
        this.context = context;
        this.searchEverywhereVM = searchEverywhereVM;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchEveryWhereViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterProductThumbnailBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_product_thumbnail, viewGroup, false);

        return new SearchEveryWhereViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchEveryWhereViewHolder searchEveryWhereViewHolder, int i) {

        SearchEverywhereVM searchEverywhereVM =
                new SearchEverywhereVM(this.searchEverywhereVM.products.get().get(i), Constants.SEARCH_EVERYWHERE_PRODUCTS);

        searchEveryWhereViewHolder.setBinding(searchEverywhereVM);

    }

    @Override
    public int getItemCount() {
        return searchEverywhereVM.products.get().size();
    }

    public class SearchEveryWhereViewHolder extends RecyclerView.ViewHolder {

        private AdapterProductThumbnailBinding binding;


        public SearchEveryWhereViewHolder(@NonNull AdapterProductThumbnailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.productDeleteRate.setPaintFlags(binding.productDeleteRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void setBinding(SearchEverywhereVM searchEverywhereVM)
        {
            this.binding.setAdapterProductThumbnail(searchEverywhereVM);
            binding.executePendingBindings();
        }

        public AdapterProductThumbnailBinding getBinding()
        {
            return binding;
        }
    }
}
