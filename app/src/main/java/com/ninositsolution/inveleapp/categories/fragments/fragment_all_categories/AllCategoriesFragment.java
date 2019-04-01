package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;

import com.ninositsolution.inveleapp.all_brands.all_brands_fragments.AllFragmentsVM;
import com.ninositsolution.inveleapp.categories.CategoryActivity;
import com.ninositsolution.inveleapp.categories.CategoryAdapter;
import com.ninositsolution.inveleapp.categories.CategoryVM;
import com.ninositsolution.inveleapp.databinding.FragmentAllBinding;
import com.ninositsolution.inveleapp.databinding.FragmentAllCategoriesBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCategoriesFragment extends Fragment implements IAllCategories{

    private FragmentAllCategoriesBinding binding;
    private  IAllCategories iAllCategories;
    AllCategoryFragmentVM allCategoryFragmentVM;
    AllCategoryAdapter allCategoryAdapter;
    public static final String TAG = AllCategoriesFragment.class.getSimpleName();


    public AllCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_categories,container,false);
        final View view = binding.getRoot();

        binding.setAllCategoryFragment(allCategoryFragmentVM);
        binding.setLifecycleOwner(this);

        binding.allCategoryRecyclerview.setHasFixedSize(true);
        binding.allCategoryRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));

        allCategoryFragmentVM = ViewModelProviders.of(this).get(AllCategoryFragmentVM.class);

      //  showProgressBar();
        allCategoryFragmentVM.getAllCategoryList();
       // allCategoryFragmentVM.parent_categories.get().clear();

        allCategoryFragmentVM.getAllcategoryVMMutableLiveData().observe(this, new Observer<List<AllCategoryFragmentVM>>() {
            @Override
            public void onChanged(@Nullable List<AllCategoryFragmentVM> allCategoryFragmentVMS) {
                hideProgressBar();

                if(!allCategoryFragmentVMS.isEmpty()) {
                    hideProgressBar();

                    Log.e(TAG,"LIST_SIZE==>"+allCategoryFragmentVMS.size());

                    allCategoryAdapter = new AllCategoryAdapter(view.getContext(), allCategoryFragmentVMS);
                   binding.allCategoryRecyclerview.setAdapter(allCategoryAdapter);
                   //allCategoryFragmentVMS.clear();
                }else {
                    Toast.makeText(getActivity(),"List Empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    @Override
    public void categoryClicked() {

    }

    private void showProgressBar()
    {
        if (binding.progressBar.getVisibility() == View.GONE)
            binding.progressBar.setVisibility(View.VISIBLE);
    }



    private void hideProgressBar()
    {
        if (binding.progressBar.getVisibility() == View.VISIBLE)
            binding.progressBar.setVisibility(View.GONE);
    }
}
