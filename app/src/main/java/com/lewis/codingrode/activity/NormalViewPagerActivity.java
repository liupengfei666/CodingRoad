package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActNormalViewpagerBinding;
import com.lewis.codingrode.fragment.ChatFragment;
import com.lewis.codingrode.fragment.ContractFragment;
import com.lewis.codingrode.fragment.DiscoveryFragment;
import com.lewis.codingrode.fragment.MeFragment;

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
        mBinding.viewPager.setAdapter(mPagerAdapter);
        mBinding.viewPager.setCurrentItem(0);
        mBinding.layoutMessage.setOnClickListener(this);
        mBinding.layoutContracts.setOnClickListener(this);
        mBinding.layoutDiscovery.setOnClickListener(this);
        mBinding.layoutMe.setOnClickListener(this);
        mBinding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //根据ViewPager滑动位置更改透明度
                int diaphaneity_one = (int) (255 * v);
                int diaphaneity_two = (int) (255 * (1 - v));
                switch (i) {
                    case 0:
                        mBinding.tvMessageNormal.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvMessagePress.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvContactsNormal.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvContactsPress.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvMessageTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        mBinding.tvMessageTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        mBinding.tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        mBinding.tvContactsTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                    case 1:
                        mBinding.tvContactsNormal.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvContactsPress.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvDiscoveryPress.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        mBinding.tvContactsTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        mBinding.tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        mBinding.tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                    case 2:
                        mBinding.tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvDiscoveryPress.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvMeNormal.getBackground().setAlpha(diaphaneity_two);
                        mBinding.tvMePress.getBackground().setAlpha(diaphaneity_one);
                        mBinding.tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        mBinding.tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        mBinding.tvMeTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        mBinding.tvMeTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                }
            }

            @Override
            public void onPageSelected(int i) {
                mBinding.viewPager.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        bottomReset();
        switch (v.getId()) {
            case R.id.layout_message:
                mBinding.viewPager.setCurrentItem(0, false);
                mBinding.tvMessagePress.getBackground().setAlpha(255);
                mBinding.tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.layout_contracts:
                mBinding.viewPager.setCurrentItem(1, false);
                mBinding.tvContactsPress.getBackground().setAlpha(255);
                mBinding.tvContactsTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.layout_discovery:
                mBinding.viewPager.setCurrentItem(2, false);
                mBinding.tvDiscoveryPress.getBackground().setAlpha(255);
                mBinding.tvDiscoveryTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.layout_me:
                mBinding.viewPager.setCurrentItem(3, false);
                mBinding.tvMePress.getBackground().setAlpha(255);
                mBinding.tvMeTextPress.setTextColor(Color.argb(255, 69, 192, 26));
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

    private PagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return ChatFragment.newInstance();
                case 1:
                    return ContractFragment.newInstance();
                case 2:
                    return DiscoveryFragment.newInstance();
                case 3:
                    return MeFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
}
