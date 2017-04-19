package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActRadioButtonBinding;
import com.lewis.codingrode.fragment.ChatFragment;
import com.lewis.codingrode.fragment.ContractFragment;
import com.lewis.codingrode.fragment.DiscoveryFragment;
import com.lewis.codingrode.fragment.MeFragment;

/**
 * Created by lewis on 2017/4/19.
 */

public class RadioButtonActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    private ActRadioButtonBinding mBinding;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_radio_button);
        mViewPager = mBinding.viewPager;
        mRadioGroup = mBinding.radioGroup;
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId)
        {
            case R.id.rb_chat:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_contacts:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_discovery:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_me:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    private PagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position){
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0:
                mRadioGroup.check(R.id.rb_chat);
                break;
            case 1:
                mRadioGroup.check(R.id.rb_contacts);
                break;
            case 2:
                mRadioGroup.check(R.id.rb_discovery);
                break;
            case 3:
                mRadioGroup.check(R.id.rb_me);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
