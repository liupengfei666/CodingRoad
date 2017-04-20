package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActNormalBinding;
import com.lewis.codingrode.fragment.ChatFragment;
import com.lewis.codingrode.fragment.ContractFragment;
import com.lewis.codingrode.fragment.DiscoveryFragment;
import com.lewis.codingrode.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lewis on 2017/4/20.
 */

public class NormalActivity extends BaseActivity {

    private ActNormalBinding mBinding;
    private FrameLayout mFrameLayout;
    private ImageView iv_weixin, iv_contact, iv_find, iv_profile;
    private TextView tv_weixin, tv_contact, tv_find, tv_profile;
    private ImageView[] mImageViews;
    private TextView[] mTabTexts;
    private List<Fragment> mFragments = new ArrayList<>();
    private int index;
    private int currentTabIndex;// 当前fragment的index

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_normal);
        initView();
        initTabView();
    }

    private void initView() {
        mFrameLayout = mBinding.frameLayout;
        iv_weixin = mBinding.ivWeixin;
        iv_contact = mBinding.ivContact;
        iv_find = mBinding.ivFind;
        iv_profile = mBinding.ivProfile;
        tv_weixin = mBinding.tvWeixin;
        tv_contact = mBinding.tvContact;
        tv_find = mBinding.tvFind;
        tv_profile = mBinding.tvProfile;
    }

    private void initTabView() {
        mImageViews = new ImageView[4];
        mImageViews[0] = iv_weixin;
        mImageViews[1] = iv_contact;
        mImageViews[2] = iv_find;
        mImageViews[3] = iv_profile;
        mTabTexts = new TextView[4];
        mTabTexts[0] = tv_weixin;
        mTabTexts[1] = tv_contact;
        mTabTexts[2] = tv_find;
        mTabTexts[3] = tv_profile;
        ChatFragment mChatFragment = ChatFragment.newInstance();
        ContractFragment mContractFragment = ContractFragment.newInstance();
        DiscoveryFragment mDiscoveryFragment = DiscoveryFragment.newInstance();
        MeFragment mMeFragment = MeFragment.newInstance();
        mFragments.add(mChatFragment);
        mFragments.add(mContractFragment);
        mFragments.add(mDiscoveryFragment);
        mFragments.add(mMeFragment);
        mImageViews[0].setSelected(true);
        mTabTexts[0].setTextColor(0xFF45C01A);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, mChatFragment)
                .add(R.id.frameLayout, mContractFragment)
                .add(R.id.frameLayout, mDiscoveryFragment)
                .add(R.id.frameLayout, mMeFragment)
                .hide(mContractFragment).hide(mDiscoveryFragment)
                .hide(mMeFragment).show(mChatFragment).commit();
    }

    public void onTabClicked(View v) {
        switch (v.getId()) {
            case R.id.weixin:
                index = 0;
                break;
            case R.id.contract:
                index = 1;
                break;
            case R.id.find:
                index = 2;
                break;
            case R.id.profile:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment mFragment = mFragments.get(index);
            ft.hide(mFragments.get(currentTabIndex));
            if (!mFragment.isAdded()) {
                ft.add(R.id.frameLayout, mFragment);
            }
            ft.show(mFragment).commit();
        }
        //把当前tab设置为选中状态
        mImageViews[currentTabIndex].setSelected(false);
        mImageViews[index].setSelected(true);
        mTabTexts[currentTabIndex].setTextColor(0xFF999999);
        mTabTexts[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
    }
}
