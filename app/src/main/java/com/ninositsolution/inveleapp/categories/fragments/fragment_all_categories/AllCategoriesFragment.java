package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;

import com.ninositsolution.inveleapp.all_brands.all_brands_fragments.AllFragmentsVM;
import com.ninositsolution.inveleapp.categories.CategoryVM;
import com.ninositsolution.inveleapp.databinding.FragmentAllBinding;
import com.ninositsolution.inveleapp.databinding.FragmentAllCategoriesBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCategoriesFragment extends Fragment implements IAllCategories{

    private FragmentAllCategoriesBinding binding;
    private  IAllCategories iAllCategories;
    AllCategoryFragmentVM allCategoryFragmentVM;


    public AllCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_categories,container,false);
        View view = binding.getRoot();

        binding.setAllCategoryFragment(allCategoryFragmentVM);
        binding.setLifecycleOwner(this);


        return view;
    }

}
