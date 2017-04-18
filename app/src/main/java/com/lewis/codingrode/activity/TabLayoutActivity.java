package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActTabLayoutBinding;

/**
 * TabLayout作为底部布局
 * Created by lewis on 2017/4/18.
 */

public class TabLayoutActivity extends BaseActivity{

    private ActTabLayoutBinding mBinding;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_tab_layout);
        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;
    }
}
