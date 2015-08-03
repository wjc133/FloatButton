package com.elite.floatbutton.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.elite.floatbutton.R;
import com.elite.floatbutton.adapter.MutlipleItemAdapter;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public class DevicesFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_devices;
    }

    @Override
    public void findView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cyc_devices);
    }

    @Override
    protected void configView() {
    //        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
        mRecyclerView.setAdapter(new MutlipleItemAdapter(getActivity()));
    }
}
