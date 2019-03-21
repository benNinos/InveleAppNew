package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryFragmentVM;
import com.ninositsolution.inveleapp.databinding.FragmentOtherCategoriesBinding;
import com.ninositsolution.inveleapp.utils.Constants;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherCategoriesFragment extends Fragment {

    FragmentOtherCategoriesBinding binding;
    OtherFragmentVM otherFragmentVM;
    IOtherCategory iOtherCategory;
    BrandCategoryAdapter brandCategoryAdapter;

    private RecyclerView recyclerView;
    private ExpandableCategoriesAdapter expandableCategoriesAdapter;
    private int [] images = {R.drawable.mb1, R.drawable.mb2, R.drawable.mb3,
            R.drawable.mb1, R.drawable.mb1, R.drawable.mb2};
    private String header = "Women's Fashion";
    private List<ExpandableCategoriesPOJO> expandableCategoriesPOJOList;
    ExpandableCategoriesPOJO expandableCategoriesPOJO;


    public OtherCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_other_categories,container,false);
        final View view = binding.getRoot();

        binding.setOtherFragment(otherFragmentVM);
        binding.setIOtherCategory(iOtherCategory);

        binding.brandRecyclerview.setHasFixedSize(true);
        binding.brandRecyclerview.setLayoutManager(new GridLayoutManager(view.getContext(),3));

        otherFragmentVM = ViewModelProviders.of(this).get(OtherFragmentVM.class);

       // otherFragmentVM.brandUrl.set(String.valueOf(new OtherFragmentVM(Constants.select_banner)));




       // otherFragmentVM.brandUrl.set(Constants.select_banner);
        binding.setOtherFragment(new OtherFragmentVM(Constants.select_banner));
       // otherFragmentVM.bannerImage().set(Constants.select_banner);



        otherFragmentVM.getBrandCategoryList();
        otherFragmentVM.getBrandVMMutableLiveData().observe(this, new Observer<List<OtherFragmentVM>>() {
            @Override
            public void onChanged(@Nullable List<OtherFragmentVM> otherFragmentVMS) {
                try {

                    if (!otherFragmentVMS.isEmpty()) {

                        brandCategoryAdapter = new BrandCategoryAdapter(view.getContext(), otherFragmentVMS);
                        binding.brandRecyclerview.setAdapter(brandCategoryAdapter);

                    } else {
                        Toast.makeText(getActivity(), "List Empty", Toast.LENGTH_SHORT).show();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
        });

        // loadExpandableRecycler();

        return view;
    }

    private void loadExpandableRecycler() {

        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);


        expandableCategoriesAdapter = new ExpandableCategoriesAdapter(expandableCategoriesPOJOList, getContext());
        recyclerView.setAdapter(expandableCategoriesAdapter);
    }

}
