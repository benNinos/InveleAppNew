package com.ninositsolution.inveleapp.coupon;
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
import com.squareup.picasso.Picasso;

public class CouponAdapter  extends RecyclerView.Adapter<CouponAdapter.MyViewHolder>{
Context context;
List<CouponModel> couponModel =new ArrayList<>();
public static final String TAG = CouponAdapter.class.getSimpleName();


public CouponAdapter(Context context){
    this.context = context;
}


    @NonNull
    @Override
    public CouponAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final  CouponAdapter.MyViewHolder holder, final int position) {
       /* Log.e(TAG,"size is:=>"+ couponModel.size());
        holder.couponName.setText(couponModel.get(position).couponName);
        holder.offer_textView.setText(couponModel.get(position).offer);
        holder.discount_textView.setText(couponModel.get(position).discount);


        Picasso
                .get()
                .load(couponModel.get(position).image)

                .into(holder.couponImageView);

        holder.couponImageView.setScaleType(ImageView.ScaleType.FIT_XY);
     */
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       /* TextView couponName,offer_textView,discount_textView;
        ImageView couponImageView;*/


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

          /*  couponName = (TextView)itemView.findViewById(R.id.couponName);
            offer_textView = (TextView)itemView.findViewById(R.id.offer_textView);
            discount_textView = (TextView)itemView.findViewById(R.id.discount_textView);
            couponImageView = (ImageView)itemView.findViewById(R.id.couponImageView);*/
        }
    }



}
