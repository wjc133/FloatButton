package com.elite.floatbutton.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import com.elite.floatbutton.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public class AppBarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("新闻"));
        tabLayout.addTab(tabLayout.newTab().setText("行情"));
        tabLayout.addTab(tabLayout.newTab().setText("论坛"));
        tabLayout.addTab(tabLayout.newTab().setText("规划"));
        tabLayout.addTab(tabLayout.newTab().setText("水电"));
        tabLayout.addTab(tabLayout.newTab().setText("智控"));
        tabLayout.addTab(tabLayout.newTab().setText("生活"));

        toolbar.setTitle("资讯");
    }
}
