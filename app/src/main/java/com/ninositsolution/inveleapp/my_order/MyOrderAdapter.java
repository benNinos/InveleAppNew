package com.ninositsolution.inveleapp.my_order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder> {

    private Context context;
    private static int currentPosition = -1;
    private IMyOrder iMyOrder;

    public MyOrderAdapter(Context context, IMyOrder iMyOrder) {
        this.context = context;
        this.iMyOrder = iMyOrder;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_my_order, viewGroup, false);
        return new MyOrderViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder myOrderViewHolder, final int i) {

        if (i%2 == 0)
        {
            myOrderViewHolder.status_value.setText(context.getString(R.string.store_pick));
        }
            
        myOrderViewHolder.track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iMyOrder.onTrackClicked(i);
               notifyDataSetChanged();
            }
        });

        myOrderViewHolder.review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMyOrder.reviewClicked(i);
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder{

        TextView track;
        TextView review;
        TextView status_value;
        RelativeLayout shipmentLayout;

        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            track = itemView.findViewById(R.id.track);
            shipmentLayout = itemView.findViewById(R.id.delivered_details_layout);
            review = itemView.findViewById(R.id.review);
            
            status_value = itemView.findViewById(R.id.cancel_status_value);

        }
    }
}
