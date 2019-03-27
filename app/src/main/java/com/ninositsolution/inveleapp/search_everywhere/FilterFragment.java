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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.FragmentFilterBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.OnSwipeTouchListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

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
        binding.filterCategoriesRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), vm.categories.get(), Constants.FILTER_SHOW_LESS));

        binding.filterCategoriesRecyclerViewMore.setHasFixedSize(true);
        binding.filterCategoriesRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterCategoriesRecyclerViewMore.setAdapter(new FilterTwoViewAdapter(getContext(), vm.categories.get(), Constants.FILTER_SHOW_MORE));

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
        });

        return view;
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