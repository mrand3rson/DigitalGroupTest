package com.example.digitalgrouptest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digitalgrouptest.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrei on 07.07.2018.
 */

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final static int TYPE_STRING = 0;
    private final static int TYPE_INTEGER = 1;
    private final Context mContext;
    private final ArrayList mData;


    public ContentAdapter(Context context, ArrayList data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof Integer) {
            return TYPE_INTEGER;
        } //else if (mData.get(position) instanceof String) {
            return TYPE_STRING;
        //}
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_INTEGER: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_simple, parent, false);
                return new NumberViewHolder(v);
            }
            default: { //case TYPE_STRING: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_strings, parent, false);
                return new StringViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_INTEGER) {
            ((NumberViewHolder)holder).bind(
                    (int) mData.get(position)
            );
        } else { //if (holder.getItemViewType() == TYPE_STRING) {
            ((StringViewHolder)holder).bind(
                    (String) mData.get(position)
            );
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class StringViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView mTitleView;

        View mParent;


        StringViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mParent = itemView;
        }

        void bind(String item) {
            mTitleView.setText(item);
        }
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView mTitleView;

        View mParent;


        NumberViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mParent = itemView;
        }

        void bind(int item) {
            mTitleView.setText(String.valueOf(item));
        }
    }
}
