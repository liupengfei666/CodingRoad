package com.lewis.codingrode.recyclerview.base;

/**
 * Author: lewis
 * Date: 2017/6/7.
 * Description:
 */

public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
