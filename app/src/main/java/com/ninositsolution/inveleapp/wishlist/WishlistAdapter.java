package com.ninositsolution.inveleapp.wishlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private Context context;

    public WishlistAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_wishlist, viewGroup, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder wishlistViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder{

        TextView delete_1, delete_2;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);

            delete_1 = itemView.findViewById(R.id.delete_rate_1);
            delete_2 = itemView.findViewById(R.id.delete_rate_2);

            delete_1.setPaintFlags(delete_1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            delete_2.setPaintFlags(delete_2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
