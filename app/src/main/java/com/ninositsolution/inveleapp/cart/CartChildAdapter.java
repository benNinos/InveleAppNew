package com.ninositsolution.inveleapp.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCartChildBinding;
import com.ninositsolution.inveleapp.pojo.CartDetails;
import com.ninositsolution.inveleapp.utils.CartDatabase;

import java.util.List;

public class CartChildAdapter extends RecyclerView.Adapter<CartChildAdapter.CartChildViewHolder> {

    private Context context;
    private List<CartDetails> cartDetailsList;
    private ICart iCart;
    private LayoutInflater layoutInflater;
    private int parentPosition;
    private CartDatabase cartDatabase;

    public CartChildAdapter(Context context, List<CartDetails> cartDetailsList, ICart iCart, int parentPosition) {
        this.context = context;
        this.cartDetailsList = cartDetailsList;
        this.iCart = iCart;
        this.parentPosition = parentPosition;

        cartDatabase = new CartDatabase(context);

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

            cartChildViewHolder.binding.cartCheckBox.setChecked(cartDatabase.getChildCheckBox(parentPosition, i));
    }

    @Override
    public int getItemCount() {
        return cartDetailsList.size();
    }

    public class CartChildViewHolder extends RecyclerView.ViewHolder{

        private AdapterCartChildBinding binding;
        private ICart iCart;

        public CartChildViewHolder(@NonNull final AdapterCartChildBinding binding, final ICart iCart) {
            super(binding.getRoot());

            this.binding = binding;
            this.iCart = iCart;

            binding.cartDeleteRate.setPaintFlags(binding.cartDeleteRate.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);

            binding.cartCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        cartDatabase.checkSubItem(parentPosition, getAdapterPosition());
                    }else {
                        cartDatabase.unCheckSubItem(parentPosition, getAdapterPosition());
                    }
                }
            });
            binding.cartCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (binding.cartCheckBox.isChecked())
                    {
                        iCart.onChildBoxChecked(parentPosition, getAdapterPosition());
                    } else
                    {
                        iCart.onChildBoxUnChecked(parentPosition, getAdapterPosition());
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
