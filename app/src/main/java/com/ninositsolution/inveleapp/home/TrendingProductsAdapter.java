package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterTrendingProductsBinding;

public class TrendingProductsAdapter extends RecyclerView.Adapter<TrendingProductsAdapter.TrendingProductsViewHolder> {

    private Context context;
    private HomeVM homeVM;
    private LayoutInflater layoutInflater;

    public TrendingProductsAdapter(Context context, HomeVM homeVM) {
        this.context = context;
        this.homeVM = homeVM;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrendingProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterTrendingProductsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_trending_products, viewGroup, false);

        return new TrendingProductsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingProductsViewHolder trendingProductsViewHolder, int i) {

        trendingProductsViewHolder.bind(homeVM, i);
    }

    @Override
    public int getItemCount() {
        if (homeVM.product_trendings.get().size() > 4)
        {
            return 4;
        } else
        {
            return homeVM.product_trendings.get().size();
        }
    }

    public class TrendingProductsViewHolder extends RecyclerView.ViewHolder{

        private AdapterTrendingProductsBinding binding;


        public TrendingProductsViewHolder(@NonNull AdapterTrendingProductsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.dealDeleteRate.setPaintFlags(binding.dealDeleteRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void bind(HomeVM homeVM, int position)
        {
            HomeVM homeVM1 = new HomeVM(homeVM, position);
            this.binding.setAdapterTrendingProducts(homeVM1);
            binding.executePendingBindings();
        }

        public AdapterTrendingProductsBinding getBinding()
        {
            return binding;
        }
    }
}