package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCategoriesBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private HomeVM homeVM;
    private LayoutInflater layoutInflater;
    private IHomeClick iHomeClick;

    public CategoryAdapter(Context context, HomeVM homeVM, IHomeClick iHomeClick) {
        this.context = context;
        this.homeVM = homeVM;
        this.iHomeClick = iHomeClick;
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

        return new CategoryViewHolder(binding, iHomeClick);
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

        IHomeClick iHomeClick;

        private AdapterCategoriesBinding binding;

        public CategoryViewHolder(@NonNull AdapterCategoriesBinding binding, IHomeClick iHomeClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.iHomeClick = iHomeClick;
        }

        public void bind(final HomeVM homeVMBind)
        {
            this.binding.setAdapterCategories(homeVMBind);
            binding.executePendingBindings();

            binding.categoriesClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        iHomeClick.onCategoriesClicked(homeVM.menus.get().get(getAdapterPosition()).slug, homeVM.menus.get().get(getAdapterPosition()).name);

                }
            });
        }

        public AdapterCategoriesBinding getBinding()
        {
            return binding;
        }

    }
}
