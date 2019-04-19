package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.HomeManagementRecyclerBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

public class HomeManagementAdapter extends RecyclerView.Adapter<HomeManagementAdapter.HomeManagementViewHolder> {

    private Context context;
    private HomeVM homeVM;
    private LayoutInflater layoutInflater;
    private IHomeClick iHomeClick;

    public HomeManagementAdapter(Context context, HomeVM homeVM, IHomeClick iHomeClick) {
        this.context = context;
        this.homeVM = homeVM;
        this.iHomeClick = iHomeClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeManagementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        HomeManagementRecyclerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_home_management, viewGroup, false);
        return new HomeManagementViewHolder(binding, iHomeClick);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeManagementViewHolder homeManagementViewHolder, int i) {

        homeManagementViewHolder.bind(homeVM.home_managements.get().get(i), homeVM.home_managements.get().get(i).category_name);

    }

    @Override
    public int getItemCount() {
        return homeVM.home_managements.get().size();
    }

    public class HomeManagementViewHolder extends RecyclerView.ViewHolder{

        private HomeManagementRecyclerBinding binding;
        private IHomeClick iHomeClick;

        public HomeManagementViewHolder(@NonNull HomeManagementRecyclerBinding binding, IHomeClick iHomeClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.iHomeClick = iHomeClick;
        }

        public void bind(HomeArrayLists homeArrayLists, String name)
        {
            this.binding.setAdapterHomeManagement(new HomeVM(name, 1));
            HomeManagementViewPagerAdapter adapter = new HomeManagementViewPagerAdapter(context, homeArrayLists, iHomeClick);
            binding.homeManagementViewPager.setAdapter(adapter);
            binding.executePendingBindings();
        }

        public HomeManagementRecyclerBinding getBinding()
        {
            return binding;
        }

    }
}