package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterDealProductsBinding;
import com.ninositsolution.inveleapp.product_detail_page.ProductDetailActivity;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    private Context context;
    private HomeVM homeVM;
    private LayoutInflater layoutInflater;

    public DealAdapter(Context context, HomeVM homeVM) {
        this.context = context;
        this.homeVM = homeVM;
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterDealProductsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_deal_products, viewGroup, false);

        return new DealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder dealViewHolder, int i) {

        HomeVM homeVM = new HomeVM(this.homeVM.deal_products.get().get(i));
        dealViewHolder.bind(homeVM);

    }

    @Override
    public int getItemCount() {

        if (homeVM.deal_products.get().size() > 4)
        {
            return 4;
        } else
        {
            return homeVM.deal_products.get().size();
        }
    }

    public class DealViewHolder extends RecyclerView.ViewHolder{

        private AdapterDealProductsBinding binding;


        public DealViewHolder(@NonNull AdapterDealProductsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.dealDeleteRate.setPaintFlags(binding.dealDeleteRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            binding.dealLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductDetailActivity.class));
                }
            });
        }

        public void bind(HomeVM homeVM)
        {
            this.binding.setAdapterDealProducts(homeVM);
            binding.executePendingBindings();
        }

        public AdapterDealProductsBinding getBinding()
        {
            return binding;
        }
    }
}
