package com.unkonw.testapp.libs.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
    private static final String TAG = "BaseRecyclerAdapter";
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private View mHeaderView, mFooterView;

    public View getHeader() {
        return mHeaderView;
    }

    public void setHeader(View mHeader) {

        if (mHeaderView == null) {
            this.mHeaderView = mHeader;
            notifyItemInserted(0);
        } else {
            this.mHeaderView = mHeader;
            notifyDataSetChanged();
        }

    }

    public View getFooter() {
        return mFooterView;
    }

    public void setFooter(View mFooter) {
        if (mFooterView == null) {
            this.mFooterView = mFooter;
            if (mHeaderView == null) {
                notifyItemInserted(mDatas.size());
            } else {
                notifyItemInserted(mDatas.size() + 1);
            }
        } else {
            this.mFooterView = mFooter;
            notifyDataSetChanged();
        }

    }

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

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            if (mFooterView == null) {
                return TYPE_NORMAL;
            } else {
                if (position == mDatas.size()) {
                    return TYPE_FOOTER;
                } else {
                    return TYPE_NORMAL;
                }
            }
        } else {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                if (mFooterView == null) {
                    return TYPE_NORMAL;
                } else {
                    if (position != mDatas.size() + 1) {
                        return TYPE_NORMAL;
                    } else {
                        return TYPE_FOOTER;
                    }
                }
            }
        }
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecyclerViewHolder holder = null;
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyRecyclerViewHolder(mHeaderView);
        } else if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new MyRecyclerViewHolder(mFooterView);
        }
        View view = mLayoutInflater.inflate(mLayoutId, parent, false);
        holder = new MyRecyclerViewHolder(view);
        if (mOnItemClickListener != null)
            view.setOnClickListener(this);
        if (mOnItemLongClickListener != null)
            view.setOnLongClickListener(this);
        return holder;

    }


    @Override
    public final void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER)
            return;
        final int realPosition = getRealPosition(holder);
        holder.getHolderView().setTag(realPosition);
        final T item = mDatas.get(realPosition);
        if (item == null)
            return;
        convert(holder, realPosition, item);

    }

    private int getRealPosition(RecyclerView.ViewHolder viewHolder) {
        int layoutPosition = viewHolder.getLayoutPosition();
        return mHeaderView == null ? layoutPosition : layoutPosition - 1;
    }

    public abstract void convert(MyRecyclerViewHolder holder, int position, T item);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (mDatas == null)
            return 0;
        if (mHeaderView == null) {
            if (mFooterView == null) {
                return mDatas.size();
            } else {
                return mDatas.size() + 1;
            }
        } else {
            if (mFooterView == null) {
                return mDatas.size() + 1;
            } else {
                return mDatas.size() + 2;
            }
        }
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


    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position) ;
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

}
