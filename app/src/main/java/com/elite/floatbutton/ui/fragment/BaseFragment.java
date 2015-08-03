package com.elite.floatbutton.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(getFragmentLayout(),container,false);
        findView(view);
        configView();
        return view;
    }

    protected void configView() {

    }

    public abstract int getFragmentLayout();

    public void findView(View view){

    }
}
