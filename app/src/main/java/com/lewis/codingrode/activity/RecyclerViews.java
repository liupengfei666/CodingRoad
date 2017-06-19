package com.lewis.codingrode.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActRecyclerviewsBinding;

/**
 * Author: lewis
 * Date: 2017/5/26.
 * Description: RecyclerView demo list
 */

public class RecyclerViews extends BaseActivity implements View.OnClickListener{

    private ActRecyclerviewsBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_recyclerviews);
        mBinding.btnSimple.setOnClickListener(this);
        mBinding.btnAdvance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simple:
                startActivity(RecyclerViewSimple.class);
                break;
            case R.id.btn_advance:
                startActivity(RVEncapsulation.class);
                break;
        }
    }
}
