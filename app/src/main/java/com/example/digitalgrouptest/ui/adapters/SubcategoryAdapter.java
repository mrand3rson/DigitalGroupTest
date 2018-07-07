package com.example.digitalgrouptest.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalgrouptest.R;
import com.example.digitalgrouptest.tools.Constraints;
import com.example.digitalgrouptest.ui.activities.CategoryActivity;
import com.example.digitalgrouptest.ui.activities.ContentActivity;
import com.example.digitalgrouptest.ui.activities.SubcategoryActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.digitalgrouptest.tools.Constraints.ARG_CATEGORY;
import static com.example.digitalgrouptest.tools.Constraints.ARG_SUBCATEGORY;

/**
 * Created by Andrei on 07.07.2018.
 */

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> {

    private final String PATTERN_NUMBERS = "%d digits";
    private final String PATTERN_STRINGS = "Length %d";
    private final Context mContext;
    private final ArrayList<Integer> mData;

    private RewardedVideoAd mRewardedVideoAd;


    public SubcategoryAdapter(Context context, ArrayList<Integer> data) {
        mContext = context;
        mData = data;

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.loadAd(CategoryActivity.REWARDED_TEST_KEY,
                new AdRequest.Builder().build());
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

        void bind(Integer item) {
            String category = ((SubcategoryActivity)mContext).getCategory();

            String itemString = String.format(Locale.getDefault(),
                    getPattern(category), item);
            mTitleView.setText(itemString);

            mParent.setOnClickListener(v -> {
                mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                    @Override
                    public void onRewardedVideoAdLoaded() {
                        Log.d("debug_ok", "videoadloaded");
                    }

                    @Override
                    public void onRewardedVideoAdOpened() {
                        Log.d("debug_ok", "videoadopened");
                    }

                    @Override
                    public void onRewardedVideoStarted() {
                        Log.d("debug_ok", "videostarted");
                    }

                    @Override
                    public void onRewardedVideoAdClosed() {
                        Log.d("debug_ok", "adclosed");
                    }

                    @Override
                    public void onRewarded(RewardItem rewardItem) {
                        Log.d("debug_ok", "onrewarded");
                    }

                    @Override
                    public void onRewardedVideoAdLeftApplication() {
                        Log.d("debug_ok", "leftapplication");
                    }

                    @Override
                    public void onRewardedVideoAdFailedToLoad(int i) {
                        Log.d("debug_ok", "failedtoload");
                    }

                    @Override
                    public void onRewardedVideoCompleted() {
                        Log.d("debug_ok", "videocompleted");
                        //go to next window
                        Intent intent = new Intent(mContext, ContentActivity.class);
                        intent.putExtra(ARG_CATEGORY, category);
                        intent.putExtra(ARG_SUBCATEGORY, item);
                        mContext.startActivity(intent);
                    }
                });

                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                } else {
                    Toast.makeText(mContext, "Loading...", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private String getPattern(String category) {
            if (category.equals(Constraints.CATEGORY_NUMBERS)) {
                return SubcategoryAdapter.this.PATTERN_NUMBERS;
            } else { //if (category.equals(Constraints.CATEGORY_STRINGS)) {
                return SubcategoryAdapter.this.PATTERN_STRINGS;
            }
        }
    }
}
