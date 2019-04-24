package com.ninositsolution.inveleapp.fitme_list;

import com.ninositsolution.inveleapp.pojo.FitmeLists;

public interface IFitmeList {

    void onBackClicked();
    void onCheckboxClicked(int userMeasurementId);
    void onViewAllClicked(FitmeLists fitmeLists);
    void onEditClicked(int userMeasurementId);
    void onDeleteClicked(int userMeasurementId);
    void onAddLayoutClicked();
}
