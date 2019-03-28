package com.ninositsolution.inveleapp.search_everywhere;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.FragmentFilterBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.OnSwipeTouchListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    private static final String TAG = "FilterFragment";
    private FragmentFilterBinding binding;
    private SearchEverywhereVM searchEverywhereVM, vm;
    private static final String SEARCHEVERYWHEREVM_KEY = "searcheverywherevm_key";

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment fragmentInstance(SearchEverywhereVM vmInstance)
    {
        FilterFragment filterFragment = new FilterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SEARCHEVERYWHEREVM_KEY, vmInstance);
        filterFragment.setArguments(bundle);

        return filterFragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false);

        final View view = binding.getRoot();

        searchEverywhereVM = ViewModelProviders.of(this).get(SearchEverywhereVM.class);

        binding.setFilter(searchEverywhereVM);

        binding.setLifecycleOwner(this);

        vm = (SearchEverywhereVM) getArguments().getSerializable(SEARCHEVERYWHEREVM_KEY);

        Toast.makeText(getContext(), ""+vm.msg.get(), Toast.LENGTH_SHORT).show();

        binding.filterCategoriesRecyclerView.setHasFixedSize(true);
        binding.filterCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterCategoriesRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.categories.get()), Constants.SEARCH_EVERYWHERE_CATEGORIES));

        if (vm.categories.get().size() > 4)
        {
            binding.filterCategoriesRecyclerViewMore.setHasFixedSize(true);
            binding.filterCategoriesRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterCategoriesRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.categories.get()), Constants.SEARCH_EVERYWHERE_CATEGORIES));

        }

        Log.i(TAG, "brandlist size -> "+ vm.brands.get().size());

        binding.filterBrandRecyclerView.setHasFixedSize(true);
        binding.filterBrandRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterBrandRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.brands.get()), Constants.SEARCH_EVERYWHERE_BRANDS));

        if (vm.brands.get().size() > 4)
        {
            binding.filterBrandRecyclerViewMore.setHasFixedSize(true);
            binding.filterBrandRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterBrandRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.brands.get()), Constants.SEARCH_EVERYWHERE_BRANDS));
        }

        binding.filterShippingRecyclerView.setHasFixedSize(true);
        binding.filterShippingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterShippingRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.shippings.get()),Constants.SEARCH_EVERYWHERE_SHIPPING));

        if (vm.shippings.get().size() > 4)
        {
            binding.filterShippingRecyclerViewMore.setHasFixedSize(true);
            binding.filterShippingRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterShippingRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.shippings.get()), Constants.SEARCH_EVERYWHERE_SHIPPING));
        }

        binding.filterContainer.setOnTouchListener(new OnSwipeTouchListener(view.getContext()) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onSwipeRight() {

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .remove(Objects.requireNonNull(getFragmentManager()
                                .findFragmentById(R.id.filter_container)))
                        .commit();
            }

        });

        binding.filterScrollView.setOnTouchListener(new OnSwipeTouchListener(view.getContext()) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onSwipeRight() {

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .remove(Objects.requireNonNull(getFragmentManager()
                                .findFragmentById(R.id.filter_container)))
                        .commit();
            }


        });

        binding.setIFilter(new IFilter() {
            @Override
            public void onCloseClicked() {

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .remove(Objects.requireNonNull(getFragmentManager()
                                .findFragmentById(R.id.filter_container)))
                        .commit();
            }

            @Override
            public void onCategoriesShowClicked() {

                if (binding.categoriesShowText.getText().toString().equalsIgnoreCase("show more"))
                {
                    if (binding.filterCategoriesRecyclerViewMore.getVisibility() == View.GONE)
                        binding.filterCategoriesRecyclerViewMore.setVisibility(View.VISIBLE);

                    binding.categoriesShowText.setText(R.string.show_less);
                    binding.categoriesShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

                } else
                {
                    if (binding.filterCategoriesRecyclerViewMore.getVisibility() == View.VISIBLE)
                        binding.filterCategoriesRecyclerViewMore.setVisibility(View.GONE);

                    binding.categoriesShowText.setText(R.string.show_more);
                    binding.categoriesShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);

                }

            }

            @Override
            public void onBrandsShowClicked() {

                if (binding.brandsShowText.getText().toString().equalsIgnoreCase("show more"))
                {
                    if (binding.filterBrandRecyclerViewMore.getVisibility() == View.GONE)
                        binding.filterBrandRecyclerViewMore.setVisibility(View.VISIBLE);

                    binding.brandsShowText.setText(R.string.show_less);
                    binding.brandsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

                } else
                {
                    if (binding.filterBrandRecyclerViewMore.getVisibility() == View.VISIBLE)
                        binding.filterBrandRecyclerViewMore.setVisibility(View.GONE);

                    binding.brandsShowText.setText(R.string.show_more);
                    binding.brandsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);

                }

            }

            @Override
            public void onShippingShowClicked() {

                if (binding.shippingShowText.getText().toString().equalsIgnoreCase("show more"))
                {
                    if (binding.filterShippingRecyclerViewMore.getVisibility() == View.GONE)
                        binding.filterShippingRecyclerViewMore.setVisibility(View.VISIBLE);

                    binding.shippingShowText.setText(R.string.show_less);
                    binding.shippingShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

                } else
                {
                    if (binding.filterShippingRecyclerViewMore.getVisibility() == View.VISIBLE)
                        binding.filterShippingRecyclerViewMore.setVisibility(View.GONE);

                    binding.shippingShowText.setText(R.string.show_more);
                    binding.shippingShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);

                }

            }
        });

        return view;
    }

    private ArrayList<HomeArrayLists> getArraylistLess(ArrayList<HomeArrayLists> lists)
    {
        if (lists.size() > 4)
        {
            ArrayList<HomeArrayLists> arrayLists = new ArrayList<>();
            arrayLists.add(lists.get(0));
            arrayLists.add(lists.get(1));
            arrayLists.add(lists.get(2));
            arrayLists.add(lists.get(3));

            return arrayLists;
        }

        else
        {
            return lists;
        }
    }

    private ArrayList<HomeArrayLists> getArraylistMore(ArrayList<HomeArrayLists> lists)
    {
            for (int i = 0; i<4; i++)
            {
                lists.remove(0);
            }

            return lists;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().findViewById(R.id.search_appbar_layout).setForeground(null);
            getActivity().findViewById(R.id.search_body_layout).setForeground(null);
        }
    }
}