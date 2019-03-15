package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherCategoriesFragment extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_other_categories, container, false);

        recyclerView = view.findViewById(R.id.category_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        loadExpandableRecycler();

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
