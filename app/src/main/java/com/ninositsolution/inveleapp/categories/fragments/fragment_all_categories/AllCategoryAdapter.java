package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.all.All;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.categories.CategoryAdapter;
import com.ninositsolution.inveleapp.categories.CategoryVM;
import com.ninositsolution.inveleapp.categories.ICategory;
import com.ninositsolution.inveleapp.databinding.AllCategoriesAdapterBinding;
import com.ninositsolution.inveleapp.databinding.CategoryAdapterBinding;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.MainViewHolder> {

    public Context context;
    private AllCategoryFragmentVM allCategoryFragmentVM;
    private List<AllCategoryFragmentVM> arrayList;
    private LayoutInflater layoutInflater;
    IAllCategories iAllCategories;

    public static final String TAG = CategoryAdapter.class.getSimpleName();

    public AllCategoryAdapter(Context context, List<AllCategoryFragmentVM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public AllCategoryAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        AllCategoriesAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.all_category_adapter,viewGroup,false);

        return new MainViewHolder(binding,iAllCategories);

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {

        allCategoryFragmentVM = arrayList.get(position);
        mainViewHolder.binding.setAdapterAllCategoryFragment(allCategoryFragmentVM);

        Log.e(TAG,"LIST_SIZE==>"+arrayList.size());

        mainViewHolder.bind(allCategoryFragmentVM,iAllCategories);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        AllCategoriesAdapterBinding binding;
        IAllCategories iAllCategories;

        public MainViewHolder(@NonNull AllCategoriesAdapterBinding binding, IAllCategories iAllCategories) {
            super(binding.getRoot());
            this.binding = binding;
            this.iAllCategories = iAllCategories;
        }
        public void bind(final AllCategoryFragmentVM allCategoryFragmentVM, IAllCategories iAllCategories)
        {
            this.binding.setAdapterAllCategoryFragment(allCategoryFragmentVM);
            this.binding.setIAllCategories(iAllCategories);
            // this.binding.setIAddressBook(iAddressBook);
            binding.executePendingBindings();

        }

        public AllCategoriesAdapterBinding getBinding()
        {
            return binding;
        }

    }

}
