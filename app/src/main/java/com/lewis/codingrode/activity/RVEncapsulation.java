package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActRvEncapsulationBinding;

/**
 * Author: lewis
 * Date: 2017/6/7.
 * Description:
 */

public class RVEncapsulation extends BaseActivity{

    ActRvEncapsulationBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_rv_encapsulation);

    }
}
