package com.ninositsolution.inveleapp.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.generated.callback.OnClickListener;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private static int currentPosition = -1;
    private ICart iCart;

    public CartAdapter(Context context, ICart iCart) {
        this.context = context;
        this.iCart = iCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_cart, viewGroup, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, final int i) {
/*
        if (cartViewHolder.edit_layout.getVisibility() == View.VISIBLE)
        {
            Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            cartViewHolder.edit_layout.setVisibility(View.GONE);
            cartViewHolder.edit_layout.startAnimation(slideUp);

        }

        if (currentPosition == i)
        {
            if (cartViewHolder.edit_layout.getVisibility() == View.VISIBLE)
            {
                Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
                cartViewHolder.edit_layout.setVisibility(View.GONE);
                cartViewHolder.edit_layout.startAnimation(slideUp);
            }

            else {
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
                cartViewHolder.edit_layout.setVisibility(View.VISIBLE);
                cartViewHolder.edit_layout.startAnimation(slideDown);
                cartViewHolder.edit_layout.requestFocus();

            }

        }*/

        cartViewHolder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCart.onEditClicked(i);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView delete;
        //LinearLayout edit_layout;
        RelativeLayout edit_button;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.cart_delete_rate);
            //edit_layout = itemView.findViewById(R.id.cart_edit);
            edit_button = itemView.findViewById(R.id.cart_edit_button1);
            delete.setPaintFlags(delete.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);

        }

    }

}