package com.ninositsolution.inveleapp.categories;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.address_book.IAddressBook;
import com.ninositsolution.inveleapp.databinding.CategoryAdapterBinding;
import com.ninositsolution.inveleapp.databinding.MainAdapterBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MainViewHolder> {

    public Context context;
    private CategoryVM categoryVM;
    private List<CategoryVM> arrayList;
    private LayoutInflater layoutInflater;
    ICategory iCategory;

    public static final String TAG = CategoryAdapter.class.getSimpleName();

    public CategoryAdapter(Context context, List<CategoryVM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public CategoryAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        CategoryAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.parent_categories_adapter,viewGroup,false);

        return new MainViewHolder(binding,iCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MainViewHolder mainViewHolder, int position) {

        categoryVM = arrayList.get(position);
        mainViewHolder.binding.setAdapterCategory(categoryVM);

        Log.e(TAG,"LIST_SIZE==>"+arrayList.size());

        mainViewHolder.bind(categoryVM,iCategory);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private CategoryAdapterBinding binding;
        public ICategory iCategory;


        public MainViewHolder(@NonNull CategoryAdapterBinding binding,ICategory iCategory) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCategory = iCategory;
        }
        public void bind(final CategoryVM categoryVM, ICategory iCategory)
        {
            this.binding.setAdapterCategory(categoryVM);
            this.binding.setICategory(iCategory);
            // this.binding.setIAddressBook(iAddressBook);
            binding.executePendingBindings();

        }

        public CategoryAdapterBinding getBinding()
        {
            return binding;
        }

    }
}
