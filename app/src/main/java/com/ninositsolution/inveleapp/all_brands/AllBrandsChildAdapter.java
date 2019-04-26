package com.ninositsolution.inveleapp.all_brands;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterAllBrandsChildBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

import java.util.List;

public class AllBrandsChildAdapter extends RecyclerView.Adapter<AllBrandsChildAdapter.AllBrandsChildViewHolder> {

    private Context context;
    private List<HomeArrayLists> lists;
    private LayoutInflater layoutInflater;

    public AllBrandsChildAdapter(Context context, List<HomeArrayLists> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public AllBrandsChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(context);

        AdapterAllBrandsChildBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_all_brands_child, viewGroup, false);

        return new AllBrandsChildViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBrandsChildViewHolder allBrandsChildViewHolder, int i) {

        allBrandsChildViewHolder.setBinding(new AllBrandsVM(lists.get(i).name, lists.get(i).image_path));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class AllBrandsChildViewHolder extends RecyclerView.ViewHolder{

        AdapterAllBrandsChildBinding binding;

        public AllBrandsChildViewHolder(@NonNull AdapterAllBrandsChildBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(AllBrandsVM allBrandsVM)
        {
            binding.setAdapterAllBrandChild(allBrandsVM);
            binding.executePendingBindings();
        }
    }
}
