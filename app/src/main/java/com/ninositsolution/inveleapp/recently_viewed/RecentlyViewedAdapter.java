package com.ninositsolution.inveleapp.recently_viewed;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

/**
 * Created by Parthasarathy D on 1/31/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {

    private Context context;

    public RecentlyViewedAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecentlyViewedViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_recently_viewed, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedViewHolder recentlyViewedViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{

        TextView delete_1, delete_2;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            delete_1 = itemView.findViewById(R.id.delete_rate_1);
            delete_2 = itemView.findViewById(R.id.delete_rate_2);

            delete_1.setPaintFlags(delete_1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            delete_2.setPaintFlags(delete_2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
