package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCategoryRecyclerBinding;
import com.ninositsolution.inveleapp.databinding.ChildCategoryAdapterBinding;

import java.util.List;

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.MainViewHolder>  {
    private List<ChildCategoriesPOJO> childCategoriesPOJOList;
    private Context context;
    private static int currentPosition = -1;
    private static int previousPosition = -1;

    private OtherFragmentVM otherFragmentVM;
    private List<OtherFragmentVM> arrayList;
    private LayoutInflater layoutInflater;
    IOtherCategory iOtherCategory;

    public static final String TAG = ChildCategoryAdapter.class.getSimpleName();

    public ChildCategoryAdapter(List<OtherFragmentVM> childCategoriesPOJOList, Context context) {
        this.arrayList = childCategoriesPOJOList;
        this.context = context;
        // notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ChildCategoryAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(layoutInflater ==null){
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }


        ChildCategoryAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.child_category_adapter,viewGroup,false);
        return new ChildCategoryAdapter.MainViewHolder(binding,iOtherCategory);

    }

    @Override
    public void onBindViewHolder(@NonNull ChildCategoryAdapter.MainViewHolder mainViewHolder, int position) {

//        Log.e(TAG,"size==>"+arrayList.get(position).child_categories.get());

        otherFragmentVM = new OtherFragmentVM(arrayList.get(0).child_categories.get().get(position));
        mainViewHolder.bind(otherFragmentVM,iOtherCategory);
      //  mainViewHolder.binding.setOtherFragment(otherFragmentVM);
       // mainViewHolder.binding.setIOtherCategory(iOtherCategory);

    }

    @Override
    public int getItemCount() {
        return arrayList.get(0).child_categories.get().size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        private ChildCategoryAdapterBinding binding;
        public IOtherCategory iOtherCategory;

        public MainViewHolder(@NonNull ChildCategoryAdapterBinding binding, IOtherCategory iOtherCategory) {
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

        public ChildCategoryAdapterBinding getBinding()
        {
            return binding;
        }
    }
}
