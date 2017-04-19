package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;

/**
 * Created by lewis on 2017/4/19.
 */

public class RadioButtonActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.act_radio_button);
    }
}
