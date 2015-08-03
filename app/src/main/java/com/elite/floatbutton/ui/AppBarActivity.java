package com.elite.floatbutton.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elite.floatbutton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public class AppBarActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<View> viewContainer;
    private List<String> titleContainer;
    private LayoutInflater inflater;
    private TabLayout tabLayout;

    private PagerAdapter mAdapter=new PagerAdapter() {
        @Override
        public int getCount() {
            return titleContainer.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleContainer.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainer.get(position));
            return viewContainer.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainer.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        viewPager=(ViewPager)findViewById(R.id.tab_pager);
        inflater=getLayoutInflater();
        setViewPager();

        toolbar.setTitle("资讯");

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager() {

        viewContainer= new ArrayList<>();
        titleContainer=new ArrayList<>();
        View view1=inflater.inflate(R.layout.fragment_tab1,null);
        View view2=inflater.inflate(R.layout.fragment_tab2,null);
        View view3=inflater.inflate(R.layout.fragment_tab3,null);
        View view4=inflater.inflate(R.layout.fragment_tab4,null);
        View view5=inflater.inflate(R.layout.fragment_tab5,null);
        viewContainer.add(view1);
        viewContainer.add(view2);
        viewContainer.add(view3);
        viewContainer.add(view4);
        viewContainer.add(view5);

        titleContainer.add("新闻");
        titleContainer.add("娱乐");
        titleContainer.add("体育");
        titleContainer.add("财经");
        titleContainer.add("论坛");


        tabLayout.setTabsFromPagerAdapter(mAdapter);
        viewPager.setAdapter(mAdapter);
    }
}
