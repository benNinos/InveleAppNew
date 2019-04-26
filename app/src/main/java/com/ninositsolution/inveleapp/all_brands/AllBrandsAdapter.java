package com.ninositsolution.inveleapp.all_brands;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterAllBrandsBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

public class AllBrandsAdapter extends RecyclerView.Adapter<AllBrandsAdapter.MyViewHolder> {

    private Context context;
    private List<HomeArrayLists> lists;
    private LayoutInflater layoutInflater;

    public static final String TAG = AllBrandsAdapter.class.getSimpleName();

    public AllBrandsAdapter(Context context, List<HomeArrayLists> lists) {
        this.context = context;
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllBrandsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       if (layoutInflater == null)
       {
           layoutInflater = LayoutInflater.from(context);
       }

        AdapterAllBrandsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_all_brands, parent, false);

       return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBrandsAdapter.MyViewHolder holder,final int position) {

        holder.setBinding(new AllBrandsVM(lists.get(position).name));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        AdapterAllBrandsBinding binding;

        public MyViewHolder(@NonNull AdapterAllBrandsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.allBrandAdapterRecyclerView.setHasFixedSize(true);
            binding.allBrandAdapterRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));

            Log.i(TAG, "position Y-> "+binding.brandTitle.getY());
            Log.i(TAG, "position X-> "+binding.brandTitle.getX());
            Log.i(TAG, "position TOP-> "+binding.brandTitle.getTop());
        }

        public void setBinding(AllBrandsVM allBrandsVM)
        {
            binding.setAdapterAllBrand(allBrandsVM);
            binding.executePendingBindings();

            binding.allBrandAdapterRecyclerView.setAdapter(new AllBrandsChildAdapter(context, lists.get(getAdapterPosition()).brands));
        }
    }
}


