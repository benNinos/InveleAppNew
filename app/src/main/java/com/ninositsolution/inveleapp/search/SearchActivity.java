package com.ninositsolution.inveleapp.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivitySearchBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.search_everywhere.SearchEverywhereActivity;

public class SearchActivity extends AppCompatActivity implements ISearch{

    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setSearch(new SearchVM(getApplicationContext(),this));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onBackClicked() {

        startActivity(new Intent(this, HomeActivity.class));

    }

    @Override
    public void onCameraClicked() {

        Toast.makeText(this, "Camera work under production", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMicrophoneClicked() {

        Toast.makeText(this, "Microphone work under production", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEverywhereClicked() {

       /* binding.searchEverywhereLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.searchEverywhere.setTextColor(getResources().getColor(R.color.white));
        binding.everywhereCheck.setVisibility(View.VISIBLE);

        binding.searchNearbyLayout.setBackgroundColor(getResources().getColor(R.color.colorProject));
        binding.searchNearby.setTextColor(getResources().getColor(R.color.textColor));
        binding.nearbyCheck.setVisibility(View.INVISIBLE);*/

    }

    @Override
    public void onNearbyClicked() {

       /* binding.searchNearbyLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.searchNearby.setTextColor(getResources().getColor(R.color.white));
        binding.nearbyCheck.setVisibility(View.VISIBLE);

        binding.searchEverywhereLayout.setBackgroundColor(getResources().getColor(R.color.colorProject));
        binding.searchEverywhere.setTextColor(getResources().getColor(R.color.textColor));
        binding.everywhereCheck.setVisibility(View.INVISIBLE);*/

    }

    @Override
    public void loadSearchAdapter(SearchAdapter searchAdapter) {

        binding.searchRecyclerview.setHasFixedSize(true);
        binding.searchRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.searchRecyclerview.setAdapter(searchAdapter);

    }

    @Override
    public void searchClicked() {
        startActivity(new Intent(this, SearchEverywhereActivity.class));
    }
}
