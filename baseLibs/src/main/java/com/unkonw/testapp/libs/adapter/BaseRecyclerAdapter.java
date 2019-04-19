package com.unkonw.testapp.libs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzho on 2015/12/30.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<MyRecyclerViewHolder> implements View.OnLongClickListener, View.OnClickListener {
    private static final String TAG = "BaseRecyclerAdapter";
    protected List<T> mDatas = new ArrayList<T>();
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;
    protected Context mContext;


    public BaseRecyclerAdapter(Context context, List<T> mDatas, int layoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }

    private int getBodySize() {
        return mDatas == null ? 0 : mDatas.size();
    }

    private boolean isHead(int position) {
        return headers.size() > 0 && position < headers.size();
    }

    private boolean isFoot(int position) {
        return footers.size() > 0 && (position >= (getBodySize() + footers.size()));
    }

    @Override
    public int getItemViewType(int position) {
        if (isFoot(position) || isHead(position)) {
            return position;
        } else {
            return -1;
        }
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecyclerViewHolder holder = null;
        if (viewType == -1) {
            View view = mLayoutInflater.inflate(mLayoutId, parent, false);
            holder = new BodyViewHolder(view);
            if (mOnItemClickListener != null)
                view.setOnClickListener(this);
            if (mOnItemLongClickListener != null)
                view.setOnLongClickListener(this);
        } else if (isHead(viewType)) {
            holder = new HeadViewHolder(headers.get(viewType));
        } else if (isFoot(viewType)) {
            holder = new FootViewHolder(footers.get(viewType - headers.size() - getBodySize()));
        } else {
            holder = null;
        }

        return holder;
    }


    List<View> headers = new ArrayList<>();

    public void addHeader(View header) {
        headers.add(header);
    }

    List<View> footers = new ArrayList<>();

    public void addFooter(View footer) {
        footers.add(footer);
    }

    @Override
    public final void onBindViewHolder(MyRecyclerViewHolder holder, int position) {

        if (holder instanceof HeadViewHolder) {

        } else if (holder instanceof BodyViewHolder) {
//            ((BodyViewHolder) holder).body.setText((CharSequence) listData.get());

            holder.getHolderView().setTag(position - headers.size());
            T item = getItem(position - headers.size());
            Log.d(TAG, "onBindViewHolder: BodyViewHolder" + ",position:" + position + "headers.size:" + headers.size() + ",item" + item);
            if (item == null)
                return;
            convert(holder, position - headers.size(), item);

        } else if (holder instanceof FootViewHolder) {

        }

    }

    public abstract void convert(MyRecyclerViewHolder holder, int position, T item);

    @Override
    public int getItemCount() {

        return mDatas == null ? 0 : (headers.size() + getBodySize() + footers.size());
    }

    public T getItem(int position) {

        return mDatas != null && mDatas.size() > (position) ? mDatas.get(position) : null;
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
        T item = getItem(position);
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view, item, position);
    }

    @Override
    public boolean onLongClick(View view) {
        int position = (Integer) view.getTag();
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(view, getItem(position), position);
            return true;
        }
        return false;
    }

    public void removeHeadAndFoot() {
        headers = new ArrayList<>();
        footers = new ArrayList<>();
        notifyDataSetChanged();
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

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    private static class HeadViewHolder extends MyRecyclerViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class BodyViewHolder extends MyRecyclerViewHolder {
        public BodyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class FootViewHolder extends MyRecyclerViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

}
