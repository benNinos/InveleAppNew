package com.ninositsolution.inveleapp.coupon;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCouponBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

import java.util.List;

public class CouponAdapter  extends RecyclerView.Adapter<CouponAdapter.MyViewHolder>{
    Context context;
    List<HomeArrayLists> couponLists;
    public static final String TAG = CouponAdapter.class.getSimpleName();
    LayoutInflater layoutInflater;
    ICoupon iCoupon;


public CouponAdapter(Context context, List<HomeArrayLists> couponLists, ICoupon iCoupon){
    this.context = context;
    this.couponLists = couponLists;
    this.iCoupon = iCoupon;
}

    @NonNull
    @Override
    public CouponAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }

        AdapterCouponBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_coupon, parent, false);

        return new MyViewHolder(binding, iCoupon);
    }

    @Override
    public void onBindViewHolder(final  CouponAdapter.MyViewHolder holder, final int position) {

        holder.setBinding(new CouponVM(couponLists.get(position)));
    }

    @Override
    public int getItemCount() {
        return couponLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AdapterCouponBinding binding;
        ICoupon iCoupon;

        public MyViewHolder(@NonNull AdapterCouponBinding binding, ICoupon iCoupon) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCoupon = iCoupon;

            binding.moreDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   MyViewHolder.this.iCoupon.onMoreDetailsClicked(couponLists.get(getAdapterPosition()).description);
                }
            });
        }

        public void setBinding(CouponVM couponVM)
        {
            binding.setAdapterCoupon(couponVM);
            binding.executePendingBindings();
        }

        public AdapterCouponBinding getBinding()
        {
            return binding;
        }
    }
}