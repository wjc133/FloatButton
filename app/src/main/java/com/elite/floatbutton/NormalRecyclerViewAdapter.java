package com.elite.floatbutton;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Elite Group
 * Created by wjc133 on 2015/7/30.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private static final String TAG = "ViewAdapter";
    private final Context mContext;
    private final LayoutInflater mInflater;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mTitles = context.getResources().getStringArray(R.array.devices);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new NormalTextViewHolder(mInflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mTitles[i]);
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
