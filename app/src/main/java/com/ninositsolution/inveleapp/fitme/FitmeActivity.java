package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeBinding;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.ninositsolution.inveleapp.utils.Constants.FITME_MEN;
import static com.ninositsolution.inveleapp.utils.Constants.FITME_WOMEN;

public class FitmeActivity extends AppCompatActivity implements FitmeRecyclerAdapter.FitmeDetailsListener {
    ActivityFitmeBinding binding;

    public static final String TAG = "FitmeActivity";
    FitmeVM fitmeVMGlobal, fitmeInstance;
    Context context;
    FitmeRecyclerAdapter adapter;
    FitmeRecyclerAdapter.FitmeDetailsListener fitmeDetailsListener;
    private HashMap<Integer, String> fitme_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fitmeDetailsListener = this;

        binding = DataBindingUtil.setContentView(this,R.layout.activity_fitme);
        fitmeVMGlobal = ViewModelProviders.of(this).get(FitmeVM.class);
        binding.setFitme(fitmeVMGlobal);
        binding.setLifecycleOwner(this);
        //fitmeVMGlobal.currentSize.set("0");
        context = FitmeActivity.this;
        Log.e(TAG, "Flow will show progress dialog");

        showProgressBar();
        Log.e(TAG, "Flow entered into next line");

        binding.fitMeRecyclerView.setHasFixedSize(true);
        binding.fitMeRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        fitme_details = new HashMap<Integer, String>();


        fitmeVMGlobal.getFitmeVMMutableLiveData().observe(this, new Observer<FitmeVM>() {
            @Override
            public void onChanged(@Nullable FitmeVM fitmeVM) {

                hideProgressBar();

                String status = fitmeVM.status.get();
                String msg = fitmeVM.msg.get();

                if (!status.isEmpty())
                {
                    if (status.equalsIgnoreCase("success"))
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();

                        fitmeInstance = fitmeVM;

                        adapter = new FitmeRecyclerAdapter(context,fitmeVM, FITME_MEN, fitmeDetailsListener);
                        binding.fitMeRecyclerView.setAdapter(adapter);

                    } else
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        binding.setIFitme(new IFitme() {
            @Override
            public void onDisabledMenClicked() {

                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.black));
                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.menTextView.setTextColor(getResources().getColor(R.color.white));

                fitme_details.clear();

                adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_MEN, fitmeDetailsListener);
                binding.fitMeRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onDisabledWomenClicked() {

                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.menTextView.setTextColor(getResources().getColor(R.color.black));
                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.white));

                fitme_details.clear();

                adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_WOMEN, fitmeDetailsListener);
                binding.fitMeRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onDisabledCMClicked() {
                binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
                binding.inchesTextView.setTextColor(getResources().getColor(R.color.black));

            }

            @Override
            public void onEnabledInchesClicked()
            {

                binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.black));
                binding.inchesTextView.setTextColor(getResources().getColor(R.color.white));

            }
        });
    }


    private void showProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.GONE)
            binding.fitmeProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.VISIBLE)
            binding.fitmeProgress.setVisibility(View.GONE);
    }

    @Override
    public void onDecreasedSizeClicked(int key, String value)
    {
        fitme_details.put(key, value);
    }

    @Override
    public void onIncreasedSizeClicked(int key, String value)
    {
        fitme_details.put(key, value);

        if (fitme_details.size() >5)
        {
            getFitmeDetails(fitme_details);
        }
    }

    @Override
    public void onQuestionDescClicked()
    {
        Toast.makeText(context, "Hello ! Clicked", Toast.LENGTH_SHORT).show();
    }

    private String getFitmeDetails(HashMap<Integer, String> fitme_details)
    {
        ArrayList<FitmeDetailModel> fitmeDetailModelArrayList = new ArrayList<>();
        FitmeDetailModel fitmeDetailModel = new FitmeDetailModel();



       for (Integer key : fitme_details.keySet())
       {
           fitmeDetailModel.setLabel_id(key);
           fitmeDetailModel.setValue(fitme_details.get(key));

           fitmeDetailModelArrayList.add(fitmeDetailModel);
       }

        JSONArray jsonArray = new JSONArray(Arrays.asList(fitmeDetailModelArrayList));

       Log.i(TAG, "fitme_details -> "+ jsonArray.toString());

       return jsonArray.toString();
    }

    public class FitmeDetailModel
    {
        private Integer label_id;
        private String value;

        public Integer getLabel_id() {
            return label_id;
        }

        public void setLabel_id(Integer label_id) {
            this.label_id = label_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
