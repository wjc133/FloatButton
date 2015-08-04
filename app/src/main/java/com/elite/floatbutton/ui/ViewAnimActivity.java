package com.elite.floatbutton.ui;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.elite.floatbutton.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/4.
 */
public class ViewAnimActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ScrollView scrollView;
    private ProgressBar progressBar;

    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewanim);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        scrollView = (ScrollView) findViewById(R.id.viewanim_content);
        progressBar = (ProgressBar) findViewById(R.id.process);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        scrollView.setVisibility(View.GONE);
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change:
                swapView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void swapView() {
        scrollView.setAlpha(0f);
        scrollView.setVisibility(View.VISIBLE);

        scrollView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);

        progressBar.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
