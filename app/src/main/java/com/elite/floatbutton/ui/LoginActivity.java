package com.elite.floatbutton.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.elite.floatbutton.R;
import com.elite.floatbutton.utils.NavigationUtils;

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
    private ImageButton loginBtn;
    private ImageButton btn_red;
    private TextView btn_blue;
    private CheckBox check_auto, check_save;
    private TextView loginText;
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
        View view1 = inflater.inflate(R.layout.tab_login_info, null);
        View view2 = inflater.inflate(R.layout.tab_login_checkable, null);
        viewContainer.add(view1);
        viewContainer.add(view2);

        configChildView();
        viewPager.setAdapter(mAdapter);
        viewPager.setPageTransformer(true, mTransformer);
    }

    private void configChildView() {
        userInput = (TextInputLayout) viewContainer.get(0).findViewById(R.id.edit_user);
        passInput = (TextInputLayout) viewContainer.get(0).findViewById(R.id.edit_pass);
        loginBtn = (ImageButton) viewContainer.get(0).findViewById(R.id.login_ok);
        loginText = (TextView) viewContainer.get(0).findViewById(R.id.login_title);

        btn_blue = (TextView) viewContainer.get(1).findViewById(R.id.login_checkable);
        btn_red = (ImageButton) viewContainer.get(1).findViewById(R.id.login_red);
        check_auto = (CheckBox) viewContainer.get(1).findViewById(R.id.check_auto);
        check_save = (CheckBox) viewContainer.get(1).findViewById(R.id.check_save);

        userInput.setHint("用户名");
        passInput.setHint("密码");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=userInput.getEditText().getText().toString().trim();
                String password=passInput.getEditText().getText().toString().trim();
                if (username.equals("123")&&password.equals("123")){
                    NavigationUtils.startActivity(LoginActivity.this,MainActivity.class);
                    finish();
                }
            }
        });
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
            if (position < -1) {
                page.setAlpha(0f);
            }else if (position <= 1) {
                float scaleFactor = -(1 - position) * pageWidth;
                loginBtn.setTranslationX((float) (scaleFactor * 1.4));
                loginText.setTranslationX((float) (scaleFactor * 1.2));
                userInput.setTranslationX((float) (scaleFactor * 1.7));
                passInput.setTranslationX((float) (scaleFactor * 1.9));

                btn_blue.setTranslationX((position) * (pageWidth / 3));
                btn_red.setTranslationX((position) * pageWidth);
                check_auto.setTranslationX((position) * (pageWidth / 2));
                check_save.setTranslationX((position) * pageWidth);

                page.findViewById(R.id.login_background).setAlpha(1 - Math.abs(position));
//                page.findViewById(R.id.login_background).setTranslationX(pageWidth*(-position));
                page.setTranslationX(pageWidth * (-position));
            } else {
                page.setAlpha(0f);
            }
        }
    }
}
