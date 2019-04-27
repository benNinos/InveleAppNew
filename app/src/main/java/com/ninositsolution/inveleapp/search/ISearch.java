package com.ninositsolution.inveleapp.search;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public interface ISearch {

    void onBackClicked();
    void onCameraClicked();
    void onMicrophoneClicked();
    void onEverywhereClicked();
    void onNearbyClicked();
    void onDeleteHistoryClicked();
    void searchClicked(String slug);
    void onKeywordCloseClicked(int keyword);
}
