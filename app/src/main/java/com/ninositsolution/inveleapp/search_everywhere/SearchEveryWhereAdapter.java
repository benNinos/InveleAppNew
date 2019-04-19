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
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.search.SearchAdapter;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.WishListListener;

import java.util.ArrayList;

public class SearchEveryWhereAdapter extends RecyclerView.Adapter<SearchEveryWhereAdapter.SearchEveryWhereViewHolder> {

    private Context context;
    private ArrayList<HomeArrayLists> productList;
    private LayoutInflater layoutInflater;
    private WishListListener wishListListener;

    public SearchEveryWhereAdapter(Context context, ArrayList<HomeArrayLists> productList, WishListListener wishListListener) {
        this.context = context;
        this.productList = productList;
        this.wishListListener = wishListListener;
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

        return new SearchEveryWhereViewHolder(binding, wishListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchEveryWhereViewHolder searchEveryWhereViewHolder, int i) {

        SearchEverywhereVM searchEverywhereVM =
                new SearchEverywhereVM(this.productList.get(i), Constants.SEARCH_EVERYWHERE_PRODUCTS);

        searchEveryWhereViewHolder.setBinding(searchEverywhereVM);

        if (productList.get(i).wishlist == 1)
            searchEveryWhereViewHolder.showWishList();

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class SearchEveryWhereViewHolder extends RecyclerView.ViewHolder {

        private AdapterProductThumbnailBinding binding;
        private WishListListener wishListListener;


        public SearchEveryWhereViewHolder(@NonNull AdapterProductThumbnailBinding binding, WishListListener wishListListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.wishListListener = wishListListener;

            binding.productDeleteRate.setPaintFlags(binding.productDeleteRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void setBinding(SearchEverywhereVM searchEverywhereVM)
        {
            this.binding.setAdapterProductThumbnail(searchEverywhereVM);
            binding.executePendingBindings();

            binding.wishlist0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showWishList();
                    wishListListener.updateWishList(productList.get(getAdapterPosition()).product_id, 1);
                }
            });

            binding.wishlist1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeWishList();
                    wishListListener.updateWishList(productList.get(getAdapterPosition()).product_id, 0);

                }
            });
        }

        public AdapterProductThumbnailBinding getBinding()
        {
            return binding;
        }

        public void showWishList() {

            binding.wishlist0.setVisibility(View.GONE);
            binding.wishlist1.setVisibility(View.VISIBLE);
        }
        public void removeWishList() {

            binding.wishlist1.setVisibility(View.GONE);
            binding.wishlist0.setVisibility(View.VISIBLE);
        }

    }
}
