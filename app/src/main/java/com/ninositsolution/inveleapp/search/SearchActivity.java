package com.ninositsolution.inveleapp.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivitySearchBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.search_everywhere.SearchEverywhereActivity;

public class SearchActivity extends AppCompatActivity implements ISearch{

    private static final String TAG = "SearchActivity";
    ActivitySearchBinding binding;
    SearchVM searchVM;
    Context context;
    Observer<SearchVM> observer;
    ISearch iSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        context = SearchActivity.this;

        iSearch = this;
        searchVM = ViewModelProviders.of(this).get(SearchVM.class);
        binding.setSearch(searchVM);

        binding.setISearch(iSearch);

        binding.setLifecycleOwner(this);

        binding.searchRecyclerview.setHasFixedSize(true);
        binding.searchRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        binding.searchHeader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                searchVM.searchByKeywordApi(s.toString());
                searchVM.getSearchVMMutableLiveData().observe(SearchActivity.this, observer);
                Log.i(TAG, "keyword : "+s.toString());
            }
        });

        binding.searchHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {

                    searchClicked(binding.searchHeader.getText().toString());

                    return true;
                }

                return false;
            }
        });

        observer = new Observer<SearchVM>() {
            @Override
            public void onChanged(@Nullable SearchVM searchVM) {

                if (searchVM.getStatus() != null)
                {
                    if (searchVM.getStatus().equalsIgnoreCase("success"))
                    {
                        binding.searchRecyclerview.setAdapter(new SearchAdapter(context, searchVM.getSearchKeys(), iSearch));

                    } else
                    {

                    }
                }
            }
        };

        }


    @Override
    public void onBackClicked() {

        startActivity(new Intent(context, HomeActivity.class));

    }

    @Override
    public void onCameraClicked() {

        Toast.makeText(context, "Camera work under production", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMicrophoneClicked() {

        Toast.makeText(context, "Microphone work under production", Toast.LENGTH_SHORT).show();

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
    public void searchClicked(String slug) {

        Intent intent = new Intent(context, SearchEverywhereActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("type", "search");
        bundle.putString("slug", slug);
        bundle.putString("name", slug);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
