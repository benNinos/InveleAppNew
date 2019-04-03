package com.ninositsolution.inveleapp.cancel_order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;

public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.CancelViewHolder> {

    private Context context;

    public CancelAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CancelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CancelViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.adapter_cancel, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CancelViewHolder cancelViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CancelViewHolder extends RecyclerView.ViewHolder{
        public CancelViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
