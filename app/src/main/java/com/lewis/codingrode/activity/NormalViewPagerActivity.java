package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActNormalViewpagerBinding;

/**
 * Created by lewis on 2017/4/21.
 */

public class NormalViewPagerActivity extends BaseActivity implements View.OnClickListener{

    private ActNormalViewpagerBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_normal_viewpager);
        bottomReset();
        mBinding.tvMessagePress.getBackground().setAlpha(255);
        mBinding.tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_message:

                break;
            case R.id.layout_contracts:

                break;
            case R.id.layout_discovery:

                break;
            case R.id.layout_me:

                break;
        }
    }

    private void bottomReset(){
        mBinding.tvMessageNormal.getBackground().setAlpha(255);
        mBinding.tvContactsNormal.getBackground().setAlpha(255);
        mBinding.tvDiscoveryNormal.getBackground().setAlpha(255);
        mBinding.tvMeNormal.getBackground().setAlpha(255);
        mBinding.tvMessagePress.getBackground().setAlpha(1);
        mBinding.tvContactsPress.getBackground().setAlpha(1);
        mBinding.tvDiscoveryPress.getBackground().setAlpha(1);
        mBinding.tvMePress.getBackground().setAlpha(1);
        mBinding.tvMessageTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        mBinding.tvContactsTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        mBinding.tvDiscoveryTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        mBinding.tvMeTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        mBinding.tvMessageTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        mBinding.tvContactsTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        mBinding.tvDiscoveryTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        mBinding.tvMeTextPress.setTextColor(Color.argb(0, 69, 192, 26));
    }
}
