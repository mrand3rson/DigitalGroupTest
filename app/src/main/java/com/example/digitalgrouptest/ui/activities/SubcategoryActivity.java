package com.example.digitalgrouptest.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.digitalgrouptest.R;
import com.example.digitalgrouptest.models.DataGenerator;
import com.example.digitalgrouptest.ui.adapters.SubcategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.digitalgrouptest.tools.Constraints.ARG_CATEGORY;

public class SubcategoryActivity extends Activity {

    private static final int SPAN_COUNT = 2;

    public String getCategory() {
        return mCategory;
    }

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private SubcategoryAdapter mAdapter;
    private String mCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        ButterKnife.bind(this);

        if (getIntent() != null) {
            mCategory = getIntent().getStringExtra(ARG_CATEGORY);
            initRecycler();
        }
    }

    private void initRecycler() {
        mAdapter = new SubcategoryAdapter(this, DataGenerator.makeSubcategoriesList());
        mRecycler.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mRecycler.setAdapter(mAdapter);
    }
}
