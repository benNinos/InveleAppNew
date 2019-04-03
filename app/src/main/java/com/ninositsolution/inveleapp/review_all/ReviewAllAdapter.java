package com.ninositsolution.inveleapp.review_all;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;

public class ReviewAllAdapter extends RecyclerView.Adapter<ReviewAllAdapter.ReviewAllViewHolder> {

    private Context context;

    public ReviewAllAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAllViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReviewAllViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.adapter_review_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAllViewHolder reviewAllViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ReviewAllViewHolder extends RecyclerView.ViewHolder{
        public ReviewAllViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
