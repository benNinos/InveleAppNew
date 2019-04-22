package com.ninositsolution.inveleapp.wishlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterWishlistsBinding;
import com.ninositsolution.inveleapp.generated.callback.OnClickListener;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.WishListListener;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private Context context;
    private List<HomeArrayLists> wishList;
    private LayoutInflater layoutInflater;
    private WishListListener wishListListener;

    public WishlistAdapter(Context context, List<HomeArrayLists> wishList, WishListListener wishListListener) {
        this.context = context;
        this.wishList = wishList;
        this.wishListListener = wishListListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterWishlistsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_wishlists, viewGroup, false);

        return new WishlistViewHolder(binding, wishListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder wishlistViewHolder, int i) {

        wishlistViewHolder.setBinding(new WishlistVM(wishList.get(i)));
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder{

        private WishListListener wishListListener;
        private AdapterWishlistsBinding binding;


        public WishlistViewHolder(@NonNull AdapterWishlistsBinding binding, final WishListListener wishListListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.wishListListener = wishListListener;

            binding.productDeleteRate.setPaintFlags(binding.productDeleteRate.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

            binding.wishlist1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wishListListener.updateWishList(wishList.get(getAdapterPosition()).product_id, 0);
                }
            });
        }

        public void setBinding(WishlistVM wishlistVM)
        {
            binding.setAdapterWishlist(wishlistVM);
            binding.executePendingBindings();
        }

        public AdapterWishlistsBinding getBinding()
        {
            return binding;
        }
    }
}
