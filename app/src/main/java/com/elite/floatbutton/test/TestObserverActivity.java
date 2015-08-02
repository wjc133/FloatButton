package com.elite.floatbutton.test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.elite.floatbutton.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/2.
 */
public class TestObserverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        TestDataObserver observer = new TestDataObserver(new Handler(), this);
        getContentResolver().registerContentObserver(Uri.parse("content://sms/"),true,observer);
    }
}
