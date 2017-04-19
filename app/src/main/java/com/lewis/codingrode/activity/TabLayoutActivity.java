package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActTabLayoutBinding;
import com.lewis.codingrode.fragment.AboutFragment;
import com.lewis.codingrode.fragment.CategoryFragment;
import com.lewis.codingrode.fragment.GankFragment;

/**
 * TabLayout可以用作底部或者顶部布局，看情况而定，用作底部布局的时候记得设置app:tabIndicatorHeight="0dp"
 * app:tabMode="fixed"让他固定不滑动
 * <p>
 * TabLayout作为底部布局
 * Created by lewis on 2017/4/18.
 */

public class TabLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActTabLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.act_tab_layout);
        ViewPager mViewPager = mBinding.viewPager;
        TabLayout mTabLayout = mBinding.tabLayout;
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.icon_main);
        mTabLayout.getTabAt(1).setIcon(R.drawable.icon_category);
        mTabLayout.getTabAt(2).setIcon(R.drawable.icon_about);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private PagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        String titles[] = new String[]{"Home", "Category", "Person"};

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return CategoryFragment.newInstance();
            } else if (position == 2) {
                return AboutFragment.newInstance();
            }
            return GankFragment.newInstance(null);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    };
}
