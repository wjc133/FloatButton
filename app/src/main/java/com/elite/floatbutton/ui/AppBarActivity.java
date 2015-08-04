package com.elite.floatbutton.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return titleContainer.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
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
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.tab_pager);
        inflater = getLayoutInflater();
        setViewPager();

        toolbar.setTitle("资讯");

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager() {

        viewContainer = new ArrayList<>();
        titleContainer = new ArrayList<>();
        View view1 = inflater.inflate(R.layout.fragment_tab1, null);
        View view2 = inflater.inflate(R.layout.fragment_tab2, null);
        View view3 = inflater.inflate(R.layout.fragment_tab3, null);
        View view4 = inflater.inflate(R.layout.fragment_tab4, null);
        View view5 = inflater.inflate(R.layout.fragment_tab5, null);

        configTabView(view1);

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
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void configTabView(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.cyc_appbar);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            private String[] mTitles = {"苹果", "西瓜", "菠萝", "主板", "CPU", "内存条", "Google", "Apple", "Mircosoft", "Lenovo", "Haier"};

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ImageViewHolder(AppBarActivity.this.getLayoutInflater().inflate(R.layout.list_item_image, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof ImageViewHolder) {
                    ((ImageViewHolder) holder).textView.setText(mTitles[position]);
                }
            }

            @Override
            public int getItemCount() {
                return mTitles == null ? 0 : mTitles.length;
            }

            class ImageViewHolder extends RecyclerView.ViewHolder {

                ImageView imageView;
                TextView textView;

                public ImageViewHolder(View itemView) {
                    super(itemView);
                    imageView = (ImageView) itemView.findViewById(R.id.item_image);
                    textView = (TextView) itemView.findViewById(R.id.item_text);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.55f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageHeight=view.getHeight();
            int pageWidth=view.getWidth();
            float scaleFactor;
            if (position > 1 || position < -1) {
                view.setAlpha(0f);
            } else {
                scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position));
//                view.setTranslationX(-position * view.getWidth());
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setAlpha(scaleFactor);
                view.setScaleX(scaleFactor / 2 + 0.5f);
                view.setScaleY(scaleFactor / 2 + 0.5f);
            }
        }
    }
}
