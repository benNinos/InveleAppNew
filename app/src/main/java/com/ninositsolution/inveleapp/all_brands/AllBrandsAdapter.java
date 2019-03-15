package com.ninositsolution.inveleapp.all_brands;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.coupon.CouponAdapter;
import com.squareup.picasso.Picasso;

public class AllBrandsAdapter extends RecyclerView.Adapter<AllBrandsAdapter.MyViewHolder> {
    Context context;
    List<AllBrandsModel> brandsModels = new ArrayList<>();
    public static final String TAG = AllBrandsAdapter.class.getSimpleName();


    public AllBrandsAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public AllBrandsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_all_brands,parent,false);
        return new AllBrandsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBrandsAdapter.MyViewHolder holder,final int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


