/**
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.unkonw.testapp.libs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.unkonw.testapp.R;


/**
 */
public class ViewHolder  {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;

    private final Context context;

    private View convertView;


    /**
     * For multiple types
     *
     * @param context
     * @param parent
     * @param layoutId
     * @param typeHashCode
     */
    @Deprecated
    private ViewHolder(Context context, ViewGroup parent, int layoutId, int typeHashCode) {
        this.context = context;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context) //
                .inflate(layoutId, parent, false);
        convertView.setTag(typeHashCode+ R.id.btn_test, this);

    }

    public ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.context = context;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context) //
                .inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public ViewHolder(Context context, ViewGroup parent, View convertView) {
        this.context = context;
        this.views = new SparseArray<View>();
        this.convertView = convertView;
        this.convertView.setTag(this);
    }

    /**
     * @return A ViewHolder instance.
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int typeHashCode) {

        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, typeHashCode);
        }
        ViewHolder holder=null;
        try {
            holder = (ViewHolder) convertView.getTag(typeHashCode + R.id.btn_test);
        }catch ( ClassCastException e){

        }
        if(holder==null)
            return new ViewHolder(context, parent, layoutId, typeHashCode);
        return holder;


    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    public static ViewHolder newHolder(Context context, View convertView,
                                       ViewGroup parent) {
        return new ViewHolder(context, parent, convertView);
    }

    /**
     * @param viewId The id of the contentView you want to retrieve.
     * @deprecated retrieveView(viewId) instead
     */
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    /**
     * @param viewId The contentView id.
     * @param value  The text to put in the text contentView.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }
    public ViewHolder setTextSize(int viewId, float size) {
        TextView view = retrieveView(viewId);
        view.setTextSize(size);
        return this;
    }
    public ViewHolder setTextColor(int viewId, int value) {
        TextView view = retrieveView(viewId);
        view.setTextColor(value);
        return this;
    }
    public ViewHolder setTextColorRes(int viewId, int value) {
        TextView view = retrieveView(viewId);
        view.setTextColor(context.getResources().getColor(value));
        return this;
    }
    public ViewHolder setTextColor(int viewId, ColorStateList value) {
        TextView view = retrieveView(viewId);
        view.setTextColor(value);
        return this;
    }

    /**
     * @param viewId The contentView id.
     * @param value  visibility One of VISIBLE, INVISIBLE, or GONE.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setVisibility(int viewId, int value) {
        retrieveView(viewId).setVisibility(value);
        return this;
    }
    public void setEnable(int viewId, boolean value) {
        retrieveView(viewId).setEnabled(value);
    }
    /**
     * @param viewId     The contentView id.
     * @param imageResId The image resource id.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * @param viewId   The contentView id.
     * @param drawable The image drawable.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }



    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Sets the background color for this contentView.
     *
     * @param viewId
     * @param colorRes
     * @return
     */
    public ViewHolder setBackgroundColorRes(int viewId, int colorRes) {
        View view = retrieveView(viewId);
        int color = context.getResources().getColor(colorRes);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Set the background to a given resource. The resource should refer to a
     * Drawable object or 0 to remove the background. Parameters:
     *
     * @param viewId
     * @param drawableRes
     * @return
     */
    public ViewHolder setBackgroundRes(int viewId, int drawableRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(drawableRes);
        return this;
    }

    /**
     * Register a callback to be invoked when this contentView is clicked. If this contentView
     * is not clickable, it becomes clickable.
     */
    public ViewHolder setClickLisenter(int viewId, OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Alpha 0-1.
     */

    @SuppressLint("NewApi")
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else { // Pre-honeycomb hack toset Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * @param viewId  The contentView id.
     * @param visible True for VISIBLE, false for GONE.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setVisible(int viewId, boolean visible) {
        retrieveView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Retrieve the convertView
     */
    public View getView() {
        return convertView;
    }

    public <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

}
