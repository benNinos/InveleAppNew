package com.ninositsolution.inveleapp.fitme_list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeListBinding;
import com.ninositsolution.inveleapp.fitme.FitmeActivity;
import com.ninositsolution.inveleapp.pojo.FitmeLists;
import com.ninositsolution.inveleapp.utils.Session;

public class FitmeListActivity extends AppCompatActivity implements IFitmeList{

    ActivityFitmeListBinding binding;
    FitmeListVM fitmeListVM;
    Context context;
    IFitmeList iFitmeList;
    FitmeListAdapter fitmeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fitme_list);

        context = this;
        iFitmeList = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fitme_list);
        fitmeListVM = ViewModelProviders.of(this).get(FitmeListVM.class);
        binding.setFitmeList(fitmeListVM);
        binding.setLifecycleOwner(this);
        binding.setIFitmeList(iFitmeList);

        binding.fitmeListRecyclerView.setHasFixedSize(true);
        binding.fitmeListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        binding.viewAllRecyclerView.setHasFixedSize(true);
        binding.viewAllRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        fitmeListVM.getFitmeListsApi(Session.getUserId(context));

        fitmeListVM.getFitmeListVMMutableLiveData().observe(this, new Observer<FitmeListVM>() {
            @Override
            public void onChanged(@Nullable FitmeListVM fitmeListVM) {

                if (fitmeListVM.getStatus() != null)
                {
                    if (fitmeListVM.getStatus().equalsIgnoreCase("success"))
                    {
                        fitmeListAdapter = new FitmeListAdapter(fitmeListVM.getUserMeasurements(), context, iFitmeList);
                        binding.fitmeListRecyclerView.setAdapter(fitmeListAdapter);
                    }
                }
            }
        });
    }

    @Override
    public void onBackClicked() {

        if (binding.viewAllLayout.getVisibility() == View.VISIBLE)
        {
            binding.viewAllLayout.setVisibility(View.GONE);
            binding.fitmeListRecyclerView.setVisibility(View.VISIBLE);
            fitmeListVM.toolBarHeader.set("Fitme List");
        } else
        {
            super.onBackPressed();
        }

    }

    @Override
    public void onCheckboxClicked(int userMeasurementId) {


    }

    @Override
    public void onViewAllClicked(FitmeLists fitmeLists) {

        binding.fitmeListRecyclerView.setVisibility(View.GONE);
        binding.viewAllLayout.setVisibility(View.VISIBLE);
        fitmeListVM.toolBarHeader.set("Fitme View Details");

        fitmeListVM.viewAllName.set(fitmeLists.getName());
        fitmeListVM.viewAllgender.set(fitmeLists.getGender());
        fitmeListVM.viewAllMeasurement.set(fitmeLists.getMeasurement());

        binding.viewAllRecyclerView.setAdapter(new FitmeViewAllAdapter(context, fitmeLists.getMeasureDetails(), fitmeLists.getMeasurement()));

    }

    @Override
    public void onEditClicked(int userMeasurementId) {

        Intent intent = new Intent(this, FitmeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("userMeasurementId", userMeasurementId);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onDeleteClicked(int userMeasurementId) {

    }

    @Override
    public void onAddLayoutClicked() {

        Intent intent = new Intent(this, FitmeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("userMeasurementId", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
