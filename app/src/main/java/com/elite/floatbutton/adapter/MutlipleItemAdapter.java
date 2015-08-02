package com.elite.floatbutton.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elite.floatbutton.R;
import com.elite.floatbutton.test.TestObserverActivity;

/**
 * Elite Group
 * Created by wjc133 on 2015/7/31.
 */
public class MutlipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static enum ITEM_TYPE{
        TYPE_IMAGE,
        TYPE_TEXT
    }

    private final LayoutInflater mInflater;
    private ViewGroup mParent;
    private final String[] mTitles;
    public MutlipleItemAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        mTitles=context.getResources().getStringArray(R.array.devices);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mParent=parent;
        RecyclerView.ViewHolder holder;
        if (viewType==ITEM_TYPE.TYPE_IMAGE.ordinal()) {
            holder = new ImageViewHolder(mInflater.inflate(R.layout.list_item_image, parent, false));
        }else {
            holder = new TextViewHolder(mInflater.inflate(R.layout.list_item_text, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder){
            ((ImageViewHolder) holder).textView.setText(mTitles[position]);
        }else if (holder instanceof TextViewHolder){
            ((TextViewHolder) holder).textView.setText(mTitles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mTitles==null?0:mTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0?ITEM_TYPE.TYPE_IMAGE.ordinal():ITEM_TYPE.TYPE_TEXT.ordinal();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public TextViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.item_text);
            imageView=(ImageView)itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLayoutPosition()==0){
                        Snackbar.make(mParent,"您点击了第"+getLayoutPosition()+"个位置",
                        Snackbar.LENGTH_SHORT)
                                .setAction(R.string.action_notify, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }
                }
            });
        }
    }
}
