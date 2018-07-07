package com.example.digitalgrouptest.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.digitalgrouptest.R;
import com.example.digitalgrouptest.models.DataGenerator;
import com.example.digitalgrouptest.tools.Constraints;
import com.example.digitalgrouptest.ui.adapters.ContentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    ContentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ButterKnife.bind(this);

        if (getIntent() != null) {
            String category = getIntent().getStringExtra(Constraints.ARG_CATEGORY);
            int subcategory = getIntent().getIntExtra(Constraints.ARG_SUBCATEGORY, 0);

            initRecycler(category, subcategory);
        }
    }

    private void initRecycler(String category, int subcategory) {

        switch (category) {
            case Constraints.CATEGORY_NUMBERS: {
                mAdapter = new ContentAdapter(this, DataGenerator.makeNumbersList(subcategory, 100));
                break;
            }
            default: { //Constraints.CATEGORY_STRINGS: {
                mAdapter = new ContentAdapter(this, DataGenerator.makeStringsList(subcategory, 26));
                break;
            }
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
    }
}
