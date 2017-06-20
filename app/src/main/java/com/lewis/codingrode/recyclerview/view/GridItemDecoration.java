package com.lewis.codingrode.recyclerview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Author: lewis
 * Date: 2017/6/19.
 * Description:
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public GridItemDecoration(Context context) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    /**
     * 画竖直分割线 |
     *
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int top = child.getTop() - params.topMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int bottom = child.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画水平分割线 —
     *
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            /*注意+ -的区别，之前用的+没理解getLeft（）*/
            int left = child.getLeft() - params.leftMargin;
            int top = child.getBottom() + params.bottomMargin;
            int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount)) { // 如果是最后一行则不需要绘制底部
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) { // 如果是最后一列，则不需要绘制右边
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }

    private boolean isLastRaw(RecyclerView parent, int itemPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (itemPosition >= childCount) { // 如果是最后一行，则不需要绘制底部
                return true;
            }
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) { // StaggeredGridLayoutManager 且纵向滚动
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (itemPosition >= childCount) {
                    return true;
                }
            } else {// StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((itemPosition + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastColum(RecyclerView parent, int itemPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            if ((itemPosition + 1) % spanCount == 0) { // 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((itemPosition + 1) % spanCount == 0) { // 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (itemPosition >= childCount) { // 如果是最后一列，则不需要绘制右边
                    return true;
                }
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        //列数
        int spanCount = -1;
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) manager).getSpanCount();
        } else if (manager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) manager).getSpanCount();
        }
        return spanCount;
    }
}
