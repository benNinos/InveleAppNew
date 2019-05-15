package com.ninositsolution.inveleapp.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
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

    private static final String TAG = CartAdapter.class.getSimpleName();

    public CartAdapter(Context context, List<CartDetails> cartDetailsList, ICart iCart) {
        this.context = context;
        this.iCart = iCart;
        this.cartDetailsList = cartDetailsList;
        notifyDataSetChanged();

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
                    .get(getAdapterPosition()).getProductlists(),iCart, false));

            binding.headCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked)
                    {
                        checkAllSubitems();
                        getAllSubtotal();
                        binding.cartDelete.setVisibility(View.VISIBLE);
                    } else
                    {
                        removeAllSubitems();
                        binding.cartDelete.setVisibility(View.GONE);
                    }

                }
            });

        }

        private void getAllSubtotal() {
        }

        private void checkAllSubitems() {

            binding.cartAdapterRecyclerView.setAdapter(new CartChildAdapter(context, cartDetailsList
                    .get(getAdapterPosition()).getProductlists(),iCart, true));
        }

        private void removeAllSubitems()
        {
            binding.cartAdapterRecyclerView.setAdapter(new CartChildAdapter(context, cartDetailsList
                    .get(getAdapterPosition()).getProductlists(),iCart, false));
        }

        public void setTotal(float value)
        {

        }

    }

}