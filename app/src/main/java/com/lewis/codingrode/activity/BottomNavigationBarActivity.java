package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActBottomNavigationBinding;

/**
 * Created by lewis on 17-4-17.
 * 底部导航系列
 */

public class BottomNavigationBarActivity extends BaseActivity implements View.OnClickListener {

    private ActBottomNavigationBinding mBinding;
    private TextView mBottomNavigationView, mTabLayout, mRadioButton, mNormal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_bottom_navigation);
        initView();
        setListener();
    }

    private void initView() {
        mBottomNavigationView = mBinding.tvBottomNavigationView;
        mTabLayout = mBinding.tvTabLayout;
        mRadioButton = mBinding.tvRadioButton;
        mNormal = mBinding.tvNormal;
    }

    private void setListener() {
        mBottomNavigationView.setOnClickListener(this);
        mTabLayout.setOnClickListener(this);
        mRadioButton.setOnClickListener(this);
        mNormal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_navigation_view:
                startActivity(BottomNavigationViewActivity.class);
                break;
            case R.id.tv_tab_layout:
                startActivity(TabLayoutActivity.class);
                break;
            case R.id.tv_radio_button:

                break;
            case R.id.tv_normal:

                break;
        }
    }
}
