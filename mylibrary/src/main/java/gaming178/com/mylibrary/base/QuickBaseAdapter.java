/**
 * Copyright 2013 Joan Zapata
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gaming178.com.mylibrary.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need to provide the
 * convert() implementation.<br/>
 * Using the provided ViewHolder, your code is minimalist. support max type 2
 *
 * @param <T> The type of the items in the list.
 */
public abstract class QuickBaseAdapter<T> extends BaseAdapter {

    private static final String TAG = QuickBaseAdapter.class.getSimpleName();

    private  Context context;

    private  int layoutResId;

    private List<T> data;

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public QuickBaseAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public QuickBaseAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.context = context;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        if(data==null)
            return 0;
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = get(context, convertView, parent,
                layoutResId, position);
        convert(holder, getItem(position), position);
        return holder.getView();
    }

    /**
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public ViewHolder get(Context context, View convertView, ViewGroup parent,
                          int layoutId, int position) {
        if (convertView == null) {
            return ViewHolder.get(context, convertView, parent, layoutId);
        }
        return (ViewHolder) convertView.getTag();

    }

    @Override
    public boolean isEnabled(int position) {
        return position < data.size();
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void clear() {
        this.data = new ArrayList<T>();
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return data;
    }

    public void setList(List<T> mList) {
        this.data = mList;
        notifyDataSetChanged();
    }

    public void updateItems(List<T> users) {
        if (users != null) {
            setList(users);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int index) {
        getList().remove(index);
        notifyDataSetChanged();
    }

    public void updateItem(int index, T item) {
        getList().set(index, item);
        notifyDataSetChanged();
    }

    /**
     * Implement this method and use the helper to adapt the view to the given
     * item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(ViewHolder helper, T item, int position);
    public void operateListView(){

    }
}
