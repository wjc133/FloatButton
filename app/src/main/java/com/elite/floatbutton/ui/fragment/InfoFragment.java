package com.elite.floatbutton.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.elite.floatbutton.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public class InfoFragment extends BaseFragment {

    private TextInputLayout mUsernameTI;
    private TextInputLayout mPasswordTI;
    private EditText mUserEdit;
    private EditText mPassEdit;
    private LinearLayout animLayout;
    private Button btn_add,btn_remove;
    private LayoutInflater inflater;
    private View childView;

    private AnimOnClickListener mOnClickListener;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_info;
    }

    @Override
    public void findView(View view) {
        mUsernameTI=(TextInputLayout)view.findViewById(R.id.textinput_username);
        mPasswordTI=(TextInputLayout)view.findViewById(R.id.textinput_password);
        animLayout=(LinearLayout)view.findViewById(R.id.layout_anim);
        btn_add=(Button)view.findViewById(R.id.btn_anim_add);
        btn_remove=(Button)view.findViewById(R.id.btn_anim_remove);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= super.onCreateView(inflater, container, savedInstanceState);
        this.inflater=inflater;
        return view;
    }

    @Override
    protected void configView() {
        mUsernameTI.setHint(getResources().getString(R.string.frag_info_user_hint));
        mUserEdit=mUsernameTI.getEditText();
        mUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    mUsernameTI.setErrorEnabled(true);
                    mUsernameTI.setError("输入内容长度不能超过6个字符");
                } else {
                    mUsernameTI.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPasswordTI.setHint(getResources().getString(R.string.frag_info_pass_hint));

        mOnClickListener=new AnimOnClickListener();
        btn_add.setOnClickListener(mOnClickListener);
        btn_remove.setOnClickListener(mOnClickListener);
    }

    private class AnimOnClickListener implements View.OnClickListener{

        private int pos=0;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_anim_add:
                    childView=inflater.inflate(R.layout.item_anim,null);
                    childView.setId(pos++);
                    animLayout.addView(childView);
                    break;
                case R.id.btn_anim_remove:
                    if (pos!=0) {
                        childView = animLayout.findViewById(--pos);
                        animLayout.removeView(childView);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
