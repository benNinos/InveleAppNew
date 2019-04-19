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
    private IHomeClick iHomeClick;

    public TrendingProductsAdapter(Context context, HomeVM homeVM, IHomeClick iHomeClick) {
        this.context = context;
        this.homeVM = homeVM;
        this.iHomeClick = iHomeClick;
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

        return new TrendingProductsViewHolder(binding, iHomeClick);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingProductsViewHolder trendingProductsViewHolder, int i) {

        trendingProductsViewHolder.bind(homeVM, i);

        if (homeVM.product_trendings.get().get(i).wishlist == 1)
            trendingProductsViewHolder.showWishlist();

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
        private IHomeClick iHomeClick;


        public TrendingProductsViewHolder(@NonNull AdapterTrendingProductsBinding binding, final IHomeClick iHomeClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.iHomeClick = iHomeClick;

            binding.dealDeleteRate.setPaintFlags(binding.dealDeleteRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            binding.whishlist0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showWishlist();
                    TrendingProductsViewHolder.this.iHomeClick.updateWishlist(homeVM.product_trendings.get().get(getAdapterPosition()).product_id, 1);
                }
            });

            binding.whishlist1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeWishlist();
                    TrendingProductsViewHolder.this.iHomeClick.updateWishlist(homeVM.product_trendings.get().get(getAdapterPosition()).product_id, 0);
                }
            });
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

        public void showWishlist()
        {
            binding.whishlist0.setVisibility(View.GONE);
            binding.whishlist1.setVisibility(View.VISIBLE);
        }

        public void removeWishlist()
        {
            binding.whishlist0.setVisibility(View.VISIBLE);
            binding.whishlist1.setVisibility(View.GONE);
        }
    }
}