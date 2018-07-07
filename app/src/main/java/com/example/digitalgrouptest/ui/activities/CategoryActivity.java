package com.example.digitalgrouptest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.digitalgrouptest.R;
import com.example.digitalgrouptest.models.DataGenerator;
import com.example.digitalgrouptest.ui.adapters.CategoriesAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    public final static String REWARDED_TEST_KEY = "ca-app-pub-3940256099942544/5224354917";
    public final static String INTERSTETIAL_TEST_KEY = "ca-app-pub-3940256099942544/1033173712";


    @BindView(R.id.adView1)
    AdView mAdView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private CategoriesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        initRecycler();
    }

    private void initRecycler() {
        mAdapter = new CategoriesAdapter(this, DataGenerator.makeCategoriesList());
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecycler.setAdapter(mAdapter);
    }
}
