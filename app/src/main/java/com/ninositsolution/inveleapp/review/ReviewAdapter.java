package com.ninositsolution.inveleapp.review;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;

    public ReviewAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReviewViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_review, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
