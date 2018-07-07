package com.example.digitalgrouptest.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalgrouptest.R;
import com.example.digitalgrouptest.ui.activities.CategoryActivity;
import com.example.digitalgrouptest.ui.activities.SubcategoryActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.digitalgrouptest.tools.Constraints.ARG_CATEGORY;

/**
 * Created by Andrei on 07.07.2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    private final Context mContext;
    private final ArrayList<String> mData;

    private InterstitialAd mInterstitialAd;


    public CategoriesAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_simple, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView mTitleView;

        View mParent;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mParent = itemView;
        }

        void bind(String item) {
            mTitleView.setText(item);
            mParent.setOnClickListener(v -> {
                showAdAndGo(item);
            });
        }

        void showAdAndGo(String item) {
            if (mInterstitialAd == null) {
                mInterstitialAd = new InterstitialAd(mContext);
                mInterstitialAd.setAdUnitId(CategoryActivity.INTERSTETIAL_TEST_KEY);
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        mInterstitialAd.show();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        Intent intent = new Intent(mContext, SubcategoryActivity.class);
                        intent.putExtra(ARG_CATEGORY, item);
                        mContext.startActivity(intent);
                    }
                });
            }
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }
}
