package com.ninositsolution.inveleapp.fitme;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.FitmeRecyclerAdapterBinding;

import java.util.ArrayList;

import static com.ninositsolution.inveleapp.utils.Constants.FITME_MEN;
import static com.ninositsolution.inveleapp.utils.Constants.FITME_WOMEN;

public class FitmeRecyclerAdapter extends RecyclerView.Adapter<FitmeRecyclerAdapter.MyViewHolder>{
    private Context context;
    private FitmeVM fitmeVM;
    private LayoutInflater layoutInflater;
    private int gender;


    public FitmeRecyclerAdapter(Context context, FitmeVM fitmeVM, int gender)
    {
        this.fitmeVM = fitmeVM;
        this.context = context;
        this.gender = gender;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());

        }

FitmeRecyclerAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fitme_recycler_adapter,viewGroup,false);


        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (gender == FITME_MEN)
        {
            FitmeVM fitmeVM = new FitmeVM(this.fitmeVM.men.get().get(i));

            myViewHolder.setBinding(fitmeVM);

        }

        if (gender == FITME_WOMEN)
        {
            FitmeVM fitmeVM1 = new FitmeVM(this.fitmeVM.women.get().get(i));

            myViewHolder.setBinding(fitmeVM1);
        }



    }

    @Override
    public int getItemCount() {

        if (gender == FITME_MEN)
        {
            return fitmeVM.men.get().size();
        }

        if (gender == FITME_WOMEN)
        {
            return fitmeVM.women.get().size();
        }

        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        FitmeRecyclerAdapterBinding binding;

        public MyViewHolder(@NonNull FitmeRecyclerAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(FitmeVM fitmeVM)
        {
            binding.setAdapterFitme(fitmeVM);
            binding.executePendingBindings();
        }

        public FitmeRecyclerAdapterBinding getBinding()
        {
            return binding;
        }
    }
}
