package com.ninositsolution.inveleapp.fitme_list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeListBinding;
import com.ninositsolution.inveleapp.utils.Session;

public class FitmeListActivity extends AppCompatActivity implements IFitmeList{

    ActivityFitmeListBinding binding;
    FitmeListVM fitmeListVM;
    Context context;
    IFitmeList iFitmeList;

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


        fitmeListVM.getFitmeListsApi(Session.getUserId(context));

        fitmeListVM.getFitmeListVMMutableLiveData().observe(this, new Observer<FitmeListVM>() {
            @Override
            public void onChanged(@Nullable FitmeListVM fitmeListVM) {

                if (fitmeListVM.getStatus() != null)
                {
                    if (fitmeListVM.getStatus().equalsIgnoreCase("success"))
                    {
                        binding.fitmeListRecyclerView.setAdapter(new FitmeListAdapter(fitmeListVM.getUserMeasurements(), context, iFitmeList));
                    }
                }
            }
        });

    }

    @Override
    public void onBackClicked() {

    }
}
