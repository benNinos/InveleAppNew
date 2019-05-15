package com.ninositsolution.inveleapp.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCartChildBinding;
import com.ninositsolution.inveleapp.pojo.CartDetails;

import java.util.List;

public class CartChildAdapter extends RecyclerView.Adapter<CartChildAdapter.CartChildViewHolder> {

    private Context context;
    private List<CartDetails> cartDetailsList;
    private ICart iCart;
    private LayoutInflater layoutInflater;
    private boolean status;

    public CartChildAdapter(Context context, List<CartDetails> cartDetailsList, ICart iCart, boolean status) {
        this.context = context;
        this.cartDetailsList = cartDetailsList;
        this.iCart = iCart;
        this.status = status;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(context);

        AdapterCartChildBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_cart_child, viewGroup, false);

        return new CartChildViewHolder(binding, iCart);

    }

    @Override
    public void onBindViewHolder(@NonNull CartChildViewHolder cartChildViewHolder, int i) {

        cartChildViewHolder.setBinding(new CartVM(cartDetailsList.get(i)));

        cartChildViewHolder.binding.cartCheckBox.setChecked(status);
    }

    @Override
    public int getItemCount() {
        return cartDetailsList.size();
    }

    public class CartChildViewHolder extends RecyclerView.ViewHolder{

        private AdapterCartChildBinding binding;
        private ICart iCart;

        public CartChildViewHolder(@NonNull AdapterCartChildBinding binding, ICart iCart) {
            super(binding.getRoot());

            this.binding = binding;
            this.iCart = iCart;

            binding.cartDeleteRate.setPaintFlags(binding.cartDeleteRate.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);

            binding.cartCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {

                    }else {

                    }
                }
            });
        }

        public void setBinding(CartVM cartVM)
        {
            binding.setAdapterCartChild(cartVM);
            binding.executePendingBindings();
        }
    }
}
