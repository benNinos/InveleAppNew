package com.ninositsolution.inveleapp.all_brands.all_brands_fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.all_brands.AllBrandsAdapter;
import com.ninositsolution.inveleapp.all_brands.AllBrandsModel;
import com.ninositsolution.inveleapp.databinding.FragmentAllBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements IAllFragment{

    FragmentAllBinding binding;


    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all,container,false);

       View view = binding.getRoot();

       binding.setAllFragment(new AllFragmentsVM(view.getContext(), this));

       return view;
    }


    @Override
    public void setRecyclerAdapter(AllBrandsAdapter adapter) {

        binding.allBrandsRecyclerView.setHasFixedSize(true);
        binding.allBrandsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.allBrandsRecyclerView.setAdapter(adapter);

    }
}


