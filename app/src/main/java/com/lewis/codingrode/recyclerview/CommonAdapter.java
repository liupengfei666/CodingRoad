package com.lewis.codingrode.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import com.lewis.codingrode.recyclerview.base.ItemViewDelegate;
import com.lewis.codingrode.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Author: lewis
 * Date: 2017/6/7.
 * Description:
 */

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    private Context mContext;
    private int layoutId;
    private List<T> mDatas;
    private LayoutInflater mInflater;

    public CommonAdapter(Context mContext, final int layoutId, List<T> data) {
        super(mContext, data);
        this.mContext = mContext;
        this.layoutId = layoutId;
        this.mDatas = data;
        mInflater = LayoutInflater.from(mContext);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
}
