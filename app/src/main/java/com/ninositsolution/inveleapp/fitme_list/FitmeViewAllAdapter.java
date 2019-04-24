package com.ninositsolution.inveleapp.fitme_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFitmeViewallBinding;
import com.ninositsolution.inveleapp.pojo.FitmeLists;

import java.util.List;

public class FitmeViewAllAdapter extends RecyclerView.Adapter<FitmeViewAllAdapter.FitmeViewAllViewHolder> {

    private Context context;
    private List<FitmeLists> fitmeLists;
    private String measurementUnit;
    private LayoutInflater layoutInflater;

    public FitmeViewAllAdapter(Context context, List<FitmeLists> fitmeLists, String measurementUnit) {
        this.context = context;
        this.fitmeLists = fitmeLists;
        this.measurementUnit = measurementUnit;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FitmeViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }

        AdapterFitmeViewallBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_fitme_viewall, viewGroup, false);


        return new FitmeViewAllViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FitmeViewAllViewHolder fitmeViewAllViewHolder, int i) {


        fitmeViewAllViewHolder.setBinding(new FitmeListVM(
                fitmeLists.get(i).getLabel(),
                fitmeLists.get(i).getValue(),
                measurementUnit
        ));

    }


    @Override
    public int getItemCount() {
        return fitmeLists.size();
    }

    public class FitmeViewAllViewHolder extends RecyclerView.ViewHolder{

        private AdapterFitmeViewallBinding binding;

        public FitmeViewAllViewHolder(@NonNull AdapterFitmeViewallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(FitmeListVM fitmeListVM)
        {
            binding.setAdapterFitmeViewAll(fitmeListVM);
            binding.executePendingBindings();
        }
    }
}
