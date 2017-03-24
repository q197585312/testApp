package com.unkonw.testapp.libs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzho on 2015/12/30.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<MyRecyclerViewHolder> implements View.OnLongClickListener, View.OnClickListener {
    protected List<T> mDatas = new ArrayList<T>();
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;
    protected Context mContext;

    public BaseRecyclerAdapter(Context context, List<T> mDatas, int layoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext=context;
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(mLayoutId, parent, false);
        if (mOnItemClickListener != null)
            view.setOnClickListener(this);
        if (mOnItemLongClickListener != null)
            view.setOnLongClickListener(this);
        MyRecyclerViewHolder holder = new MyRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public final void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.getHolderView().setTag(position);
        T item = getItem(position);
        convert(holder, position,item);
    }

    public abstract void convert(MyRecyclerViewHolder holder, int position, T item);

    public T getItem(int positon) {
        return mDatas != null && mDatas.size() > positon ? mDatas.get(positon) : null;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void addItem(T item, boolean isNotify) {
        mDatas.add(item);
        if (isNotify)
            notifyDataSetChanged();
    }

    public void addItem(T item) {
        addItem(item, true);
    }

    public void addAllItem(List<T> items, boolean isNotify) {
        mDatas.addAll(items);
        if (isNotify)
            notifyDataSetChanged();
    }

    public void addAllItem(List<T> items) {
        addAllItem(items, true);
    }

    public void clearItems(boolean isNotify) {
        mDatas.clear();
        if (isNotify)
            notifyDataSetChanged();
    }

    public void addAllAndClear(List<T> items) {
        clearItems(false);
        addAllItem(items);
    }

    public void destroyAdapter() {
        mDatas.clear();
    }


    /**
     * 点击事件的 处理   start
     */

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        T item=getItem(position);
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view,item, position);
    }

    @Override
    public boolean onLongClick(View view) {
        int position = (Integer) view.getTag();
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(view,getItem(position), position);
            return true;
        }
        return false;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T item, int position);
    }

    /**
     * 该方法需要在setAdapter之前调用
     */
    public BaseRecyclerAdapter<T> setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        return this;
    }

    /**
     * 该方法需要在setAdapter之前调用
     */
    public BaseRecyclerAdapter<T> setOnLongItemClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
        return this;
    }
}
