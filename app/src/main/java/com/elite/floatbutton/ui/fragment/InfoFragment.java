package com.elite.floatbutton.ui.fragment;


import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

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

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_info;
    }

    @Override
    public void findView(View view) {
        mUsernameTI=(TextInputLayout)view.findViewById(R.id.textinput_username);
        mPasswordTI=(TextInputLayout)view.findViewById(R.id.textinput_password);
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
                if (s.length()>6){
                    mUsernameTI.setErrorEnabled(true);
                    mUsernameTI.setError("输入内容长度不能超过6个字符");
                }else {
                    mUsernameTI.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPasswordTI.setHint(getResources().getString(R.string.frag_info_pass_hint));
    }
}
