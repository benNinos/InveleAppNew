package com.ninositsolution.inveleapp.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCartBinding;
import com.ninositsolution.inveleapp.pojo.CartDetails;
import com.ninositsolution.inveleapp.utils.CartDatabase;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ICart iCart;
    private LayoutInflater layoutInflater;
    private List<CartDetails> cartDetailsList;
    private CartDatabase cartDatabase;

    private static final String TAG = CartAdapter.class.getSimpleName();

    public CartAdapter(Context context, List<CartDetails> cartDetailsList, ICart iCart) {
        this.context = context;
        this.iCart = iCart;
        this.cartDetailsList = cartDetailsList;
        notifyDataSetChanged();

        cartDatabase = new CartDatabase(context);

        //Log.i(TAG, "size -> "+cartDetailsList.size());

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(context);

        AdapterCartBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_cart, viewGroup, false);

        return new CartViewHolder(binding, iCart);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

        cartViewHolder.setBinding(new CartVM(cartDetailsList.get(i).getStoreName()));

        if (cartDatabase.getParentCheckbox(i))
        {
            cartViewHolder.binding.headCheckBox.setChecked(true);
            cartViewHolder.binding.cartDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {

     return cartDetailsList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private AdapterCartBinding binding;
        private ICart iCart;

        public CartViewHolder(@NonNull AdapterCartBinding binding, ICart iCart) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCart = iCart;

            binding.cartAdapterRecyclerView.setHasFixedSize(true);
            binding.cartAdapterRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        public void setBinding(CartVM cartVM)
        {
            binding.setAdapterCart(cartVM);
            binding.executePendingBindings();

            binding.cartAdapterRecyclerView.setAdapter(new CartChildAdapter(context, cartDetailsList
                    .get(getAdapterPosition()).getProductlists(),iCart, getAdapterPosition()));

            binding.headCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            if (binding.headCheckBox.isChecked())
                            {
                               // binding.headCheckBox.setChecked(true);
                                iCart.onParentBoxChecked(getAdapterPosition());

                            } else {

                               // binding.headCheckBox.setChecked(false);
                                iCart.onParentBoxUnChecked(getAdapterPosition());
                            }
                }
            });

        }
    }

}