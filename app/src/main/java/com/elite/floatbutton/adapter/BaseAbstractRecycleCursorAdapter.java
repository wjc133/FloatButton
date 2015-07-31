package com.elite.floatbutton.adapter;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/**
 * Elite Group
 * Created by wjc133 on 2015/7/31.
 */
public abstract class BaseAbstractRecycleCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Filterable, CursorFilter.CursorFilterClient {
    //改用cursor来绑定数据
    public abstract void onBindViewHolder(VH holder, Cursor cursor);

    //数据是否有效
    protected boolean mDataValid;

    protected Cursor mCursor;
    protected Context mContext;
    protected int mRowIdColumn;
    //    protected CursorFilter mCorsorFilter;
    protected FilterQueryProvider mFilterQueryProvider;

    //在使用CursorLoader的时候不需要使用
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 0x20;

    public BaseAbstractRecycleCursorAdapter(Context context, Cursor c) {
        init(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
    }

    protected void init(Context context, Cursor c, int flag) {
        mDataValid = c != null;
        mCursor = c;
        mContext = context;
        mRowIdColumn = mDataValid ? c.getColumnIndexOrThrow("_id") : -1;
        if (flag == FLAG_REGISTER_CONTENT_OBSERVER) {
            //实例化观察者
        } else {
            //null
        }

        if (mDataValid) {
            //数据有效则注册观察者
        }

        //需要将表关联id设置为true
        setHasStableIds(true);
    }

    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (mDataValid) {
            return mCursor.getCount();
        }
        return 0;
    }

    public long getItemId(int position) {
        if (mDataValid && mCursor.moveToPosition(position)) {
            return mCursor.getLong(mRowIdColumn);
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!mDataValid) {
            throw new IllegalStateException("数据都无效了，你还绑定个毛！");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("这个位置(" + position + ")木有数据");
        }
        onBindViewHolder(holder, mCursor);
    }

    public void changeCursor(Cursor cursor) {
        Cursor oldCursor = swapCursor(cursor);
        if (oldCursor != null) {
            oldCursor.close();
        }
    }

    private Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        Cursor oldCursor = mCursor;
        if (mCursor != null) {
            //反注册mCursor的观察者
        }
        mCursor = newCursor;
        if (mCursor != null) {
            //注册新的Cursor的观察者
        } else {
            //将各种状态设为异常
        }
        return oldCursor;
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    private class ChangeObserver extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public ChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            onContentChanged();
        }
    }

    public void onContentChanged() {

    }

    private class MyDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            mDataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            mDataValid = false;
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
            notifyDataSetChanged();
        }
    }
}
