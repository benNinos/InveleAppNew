package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterCategoryRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parthasarathy D on 1/21/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ExpandableCategoriesAdapter extends RecyclerView.Adapter<ExpandableCategoriesAdapter.ExpandableCategoriesViewHolder> {

    private List<ExpandableCategoriesPOJO> expandableCategoriesPOJOList;
    private Context context;
    private static int currentPosition = -1;
    private static int previousPosition = -1;

    private OtherFragmentVM otherFragmentVM;
    private List<OtherFragmentVM> arrayList,childList;
    private LayoutInflater layoutInflater;
    IOtherCategory iOtherCategory;
    ChildCategoryAdapter childCategoryAdapter;
    int category_position=0;

    public static final String TAG = BrandCategoryAdapter.class.getSimpleName();

    public ExpandableCategoriesAdapter(List<OtherFragmentVM>otherFragmentVM, Context context,int category_position) {
        this.arrayList = otherFragmentVM;
        this.context = context;
        this.category_position = category_position;
       // notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpandableCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(layoutInflater ==null){
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        AdapterCategoryRecyclerBinding binding  = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_category_recycler,viewGroup,false);
        return new ExpandableCategoriesViewHolder(binding,iOtherCategory);

    }

    @Override
    public void onBindViewHolder(@NonNull final ExpandableCategoriesViewHolder expandableCategoriesViewHolder, final int position) {

        if(arrayList.size()>0) {

            otherFragmentVM = arrayList.get(position);
            expandableCategoriesViewHolder.bind(otherFragmentVM, iOtherCategory);
        }else {
            Toast.makeText(context,"No Child",Toast.LENGTH_SHORT).show();
        }
      //  expandableCategoriesViewHolder.binding.setOtherFragment(otherFragmentVM);
      //  expandableCategoriesViewHolder.binding.setIOtherCategory(iOtherCategory);
//        expandableCategoriesViewHolder.binding.executePendingBindings();




        if (expandableCategoriesViewHolder.binding.categoriesChildlayout.getVisibility() == View.VISIBLE)
        {
            Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            expandableCategoriesViewHolder.binding.categoriesChildlayout.startAnimation(slideUp);
            expandableCategoriesViewHolder.binding.downArrow.setVisibility(View.VISIBLE);
            expandableCategoriesViewHolder.binding.upArrow.setVisibility(View.GONE);
            expandableCategoriesViewHolder.binding.categoriesChildlayout.setVisibility(View.GONE);


        }


       // expandableCategoriesViewHolder.binding.categoriesHeader.setText("Women's Fashion");


        if (currentPosition == position)
        {
            if (expandableCategoriesViewHolder.binding.categoriesChildlayout.getVisibility() == View.VISIBLE)
            {
                Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
                expandableCategoriesViewHolder.binding.categoriesChildlayout.startAnimation(slideUp);
                expandableCategoriesViewHolder.binding.downArrow.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.binding.upArrow.setVisibility(View.GONE);
                expandableCategoriesViewHolder.binding.categoriesChildlayout.setVisibility(View.GONE);


            }

            else {
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
                expandableCategoriesViewHolder.binding.categoriesChildlayout.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.binding.categoriesChildlayout.startAnimation(slideDown);
                expandableCategoriesViewHolder.binding.downArrow.setVisibility(View.GONE);
                expandableCategoriesViewHolder.binding.upArrow.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.binding.categoriesChildlayout.requestFocus();

                //set the child recyclerview list
                expandableCategoriesViewHolder.binding.childRecyclerview.setHasFixedSize(true);
                expandableCategoriesViewHolder.binding.childRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
                if(arrayList.get(position).child_categories.get().size()>0) {
                childList = new ArrayList<>();
                otherFragmentVM = new OtherFragmentVM(arrayList.get(position).child_categories.get());
                childList.add(otherFragmentVM);
                childCategoryAdapter = new ChildCategoryAdapter(childList,context);
                expandableCategoriesViewHolder.binding.childRecyclerview.setAdapter(childCategoryAdapter);
                }else {
                    Toast.makeText(context,"No Child",Toast.LENGTH_SHORT).show();
                }

            }
        }

        expandableCategoriesViewHolder.binding.categoriesHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG,"position_selected_list_size==>"+arrayList.get(position).child_categories.get().size()+"\nsize==>"+
                        otherFragmentVM.child_categories.get().size());
                currentPosition = position;
                notifyDataSetChanged();

                //set the child recyclerview list
                if(arrayList.get(position).child_categories.get().size()>0) {
                    childList = new ArrayList<>();
                    otherFragmentVM = new OtherFragmentVM(arrayList.get(position).child_categories.get());
                    childList.add(otherFragmentVM);
                    childCategoryAdapter = new ChildCategoryAdapter(childList, context);
                    expandableCategoriesViewHolder.binding.childRecyclerview.setAdapter(childCategoryAdapter);
                }else {
                    Toast.makeText(context,"No Child",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ExpandableCategoriesViewHolder extends RecyclerView.ViewHolder {

        private AdapterCategoryRecyclerBinding binding;
        public IOtherCategory iOtherCategory;

        public ExpandableCategoriesViewHolder(@NonNull AdapterCategoryRecyclerBinding binding, IOtherCategory iOtherCategory) {
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

        public AdapterCategoryRecyclerBinding getBinding()
        {
            return binding;
        }
    }



}
