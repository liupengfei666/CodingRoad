package com.lewis.codingrode.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lewis.codingrode.recyclerview.base.ItemViewDelegate;
import com.lewis.codingrode.recyclerview.base.ItemViewDelegateManager;
import com.lewis.codingrode.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Author: lewis
 * Date: 2017/6/7.
 * Description:
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<T> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;
    private OnItemClickListener mOnItemClickListener;

    public MultiItemTypeAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        this.mDatas = data;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (!userItemViewDelegateManager())
            return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(i);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder viewHolder = ViewHolder.create(mContext, viewGroup, layoutId);
        onViewHolderCreated(viewHolder, viewHolder.getConvertView());
        setListener(viewGroup, viewHolder, i);
        return viewHolder;
    }

    private void onViewHolderCreated(ViewHolder viewHolder, View convertView) {
    }

    public void convert(ViewHolder holder, T t){
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    private void setListener(ViewGroup parent, final ViewHolder holder, int viewType){
        if (!isEnabled(viewType)) return;
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                {
                    int position = holder.getAdapterPosition();
                    mOnItemClickListener.OnItemClick(v, holder, position);
                }
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemClickListener.OnItemLongClick(v, holder, position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        convert(viewHolder, mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public List<T> getDatas(){
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    private boolean userItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemDelegateCount() > 0;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, RecyclerView.ViewHolder viewHolder, int position);

        boolean OnItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
