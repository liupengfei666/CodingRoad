package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActivityBottomNavigationViewBinding;

/**
 * 网上的一个BottomNavigationView与ViewPager结合的demo,地址：http://www.qingpingshan.com/rjbc/az/177936.html
 * Created by lewis on 2017/4/18.
 */

public class BottomNavigationViewActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBottomNavigationViewBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation_view);
        BottomNavigationView mBottomNavigationView = mBinding.bottomNavigationView;
        mContent = mBinding.textContent;
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mContent.setText(R.string.title_home);
                break;
            case R.id.navigation_dashboard:
                mContent.setText(R.string.title_dashboard);
                break;
            case R.id.navigation_notifications:
                mContent.setText(R.string.title_notifications);
                break;
            case R.id.navigation_share:
                mContent.setText(R.string.Share);
                break;
        }
        return true;
    }
}
