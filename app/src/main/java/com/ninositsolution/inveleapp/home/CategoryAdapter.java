package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCategoriesBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private HomeVM homeVM;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, HomeVM homeVM) {
        this.context = context;
        this.homeVM = homeVM;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterCategoriesBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_categories, viewGroup, false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {

        HomeVM homeVM = new HomeVM(this.homeVM.menus.get().get(i), 1);

        categoryViewHolder.bind(homeVM);

    }

    @Override
    public int getItemCount() {
        return homeVM.menus.get().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private AdapterCategoriesBinding binding;

        public CategoryViewHolder(@NonNull AdapterCategoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HomeVM homeVM)
        {
            this.binding.setAdapterCategories(homeVM);
            binding.executePendingBindings();
        }

        public AdapterCategoriesBinding getBinding()
        {
            return binding;
        }

    }
}
