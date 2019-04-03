package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.OtherFragmentBrandAdapterBinding;


import java.util.List;


public class BrandCategoryAdapter extends RecyclerView.Adapter<BrandCategoryAdapter.MainViewHolder>  {

    public Context context;
    private OtherFragmentVM otherFragmentVM;
    private List<OtherFragmentVM> arrayList;
    private LayoutInflater layoutInflater;
    IOtherCategory iOtherCategory;

    public static final String TAG = BrandCategoryAdapter.class.getSimpleName();

    public interface ClickEvent{
        void setClickEventItem(int position,String menu_id,String name);

    }
    ClickEvent clickEvent;

    public void setClikEvent(ClickEvent clikEvent){
        this.clickEvent = clikEvent;
    }
    public BrandCategoryAdapter(Context context, List<OtherFragmentVM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public BrandCategoryAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        OtherFragmentBrandAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.brand_adapter,viewGroup,false);
        return new MainViewHolder(binding,iOtherCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandCategoryAdapter.MainViewHolder mainViewHolder, int position) {

        otherFragmentVM = arrayList.get(position);
        mainViewHolder.binding.setOtherFragment(otherFragmentVM);
        mainViewHolder.binding.setIOtherCategory(iOtherCategory);

        Log.e(TAG,"LIST_SIZE==>"+arrayList.size());

        mainViewHolder.bind(otherFragmentVM,iOtherCategory);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

       private OtherFragmentBrandAdapterBinding binding;
        public IOtherCategory iOtherCategory;

        public MainViewHolder(@NonNull OtherFragmentBrandAdapterBinding binding, IOtherCategory iOtherCategory) {
            super(binding.getRoot());
            this.binding = binding;
            this.iOtherCategory = iOtherCategory;
        }
        public void bind(final OtherFragmentVM otherFragmentVM, IOtherCategory iOtherCategory)
        {
            this.binding.setOtherFragment(otherFragmentVM);
            this.binding.setIOtherCategory(iOtherCategory);
            binding.executePendingBindings();
        }

        public OtherFragmentBrandAdapterBinding getBinding()
        {
            return binding;
        }
    }
}
