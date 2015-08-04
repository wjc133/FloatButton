package com.elite.floatbutton.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.elite.floatbutton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/4.
 */
public class LoginActivity extends Activity {
    private List<View> viewContainer;
    private ViewPager viewPager;
    private LoginAdapter mAdapter;
    private TextInputLayout userInput, passInput;
    private ImageButton loginBtn, loginBtn2;
    private TextView loginText, loginText2;
    private LoginTransformer mTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        configView();
    }

    private void configView() {
        mAdapter = new LoginAdapter();
        mTransformer = new LoginTransformer();
        configViewPager();
    }

    private void findView() {
        viewPager = (ViewPager) findViewById(R.id.login_content);
    }

    private void configViewPager() {
        viewContainer = new ArrayList<>();

        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.tab_welcome, null);
        View view2 = inflater.inflate(R.layout.tab_login_info, null);
        viewContainer.add(view1);
        viewContainer.add(view2);

        configChildView();
        viewPager.setAdapter(mAdapter);
        viewPager.setPageTransformer(true, mTransformer);
    }

    private void configChildView() {
        loginText = (TextView) viewContainer.get(0).findViewById(R.id.login_title);
        loginBtn = (ImageButton) viewContainer.get(0).findViewById(R.id.login_ok);
        userInput = (TextInputLayout) viewContainer.get(1).findViewById(R.id.edit_user);
        passInput = (TextInputLayout) viewContainer.get(1).findViewById(R.id.edit_pass);
        loginBtn2 = (ImageButton) viewContainer.get(1).findViewById(R.id.login_ok);
        loginText2 = (TextView) viewContainer.get(1).findViewById(R.id.login_title);

        userInput.setHint("用户名");
        passInput.setHint("密码");
    }

    private class LoginAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewContainer.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainer.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainer.get(position));
            return viewContainer.get(position);
        }
    }

    private class LoginTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if (position > 1 || position < -1) {
                page.setAlpha(0f);
            } else if (position <= 1) {
                loginBtn.setTranslationY((float)((1-position)*0.5*pageWidth));
                loginText.setTranslationY((float)(-(1-position)*0.5*pageWidth));
                loginText.setTranslationX((1-position)*pageWidth);
            }
        }
    }
}
