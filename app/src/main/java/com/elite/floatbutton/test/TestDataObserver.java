package com.elite.floatbutton.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/2.
 */
public class TestDataObserver extends ContentObserver {
    private Context context;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public TestDataObserver(Handler handler, Context context) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor c = context.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "read"}, "address=? and read=?", new String[]{"12345678900", "0"}, "date desc");
        if (c != null) {
            ContentValues values = new ContentValues();
            values.put("read", "1");
            c.moveToFirst();
            while (c.isLast()) {
                context.getContentResolver().update(Uri.parse("content://sms/inbox"), values, "_id=?", new String[]{"" + c.getLong(c.getColumnIndex("_id:"))});
                c.moveToNext();
            }
        }
    }
}
