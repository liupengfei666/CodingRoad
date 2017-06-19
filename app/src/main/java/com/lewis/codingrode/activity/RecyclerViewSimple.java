package com.lewis.codingrode.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.recyclerview.view.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lewis
 * Date: 2017/5/26.
 * Description:
 */

public class RecyclerViewSimple extends BaseActivity{

    private List<Integer> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recyclerview_simple);
        initData();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_simple);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 线性布局
        //第一个参数 上下文 第二个参数 方向 第三个参数 是否逆向展示数据（eg:原-从右往左滑动 逆向后-从左向右滑动）
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        //gridview布局
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //参数1-上下文 2-列数或者行数 3-方向（vertical 2就代表列数 horizontal 2就代表行数） 4-是否逆向展示数据
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false));
        //交错排列的布局
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new SimpleAdapter());
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            mData.add(i);
        }
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleViewHolder(LayoutInflater.from(RecyclerViewSimple.this).inflate(R.layout.item_simple, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder simpleViewHolder, int i) {
            simpleViewHolder.mTextView.setText(mData.get(i) + "");
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class SimpleViewHolder extends RecyclerView.ViewHolder{

            TextView mTextView;

            public SimpleViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text_item);
            }
        }
    }
}
