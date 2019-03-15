package com.ninositsolution.inveleapp.search;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchVM {

    private SearchModel searchModel;
    private Context context;
    private ISearch iSearch;

    public SearchVM(Context context, ISearch iSearch) {
        this.context = context;
        this.iSearch = iSearch;
        searchModel = new SearchModel();
        loadSearchAdapter();
    }

    private void loadSearchAdapter() {

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList = searchModel.populateSearchList();

        SearchAdapter searchAdapter = new SearchAdapter(context, arrayList, iSearch);

        iSearch.loadSearchAdapter(searchAdapter);
    }

    //TextWatchers

    public void afterPostalCodeChanged(CharSequence sequence)
    {
        searchModel.setPostalCode(sequence.toString());
    }

    //ClickListeners

    public void onBackClicked()
    {
        iSearch.onBackClicked();
    }

    public void onCameraClicked()
    {
        iSearch.onCameraClicked();
    }

    public void onMicrophoneClicked()
    {
        iSearch.onMicrophoneClicked();
    }

    public void onEverywhereClicked()
    {
        iSearch.onEverywhereClicked();
    }

    public void onNearbyClicked()
    {
        iSearch.onNearbyClicked();
    }
}
