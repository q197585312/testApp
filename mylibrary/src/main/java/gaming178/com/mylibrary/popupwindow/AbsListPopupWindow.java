package gaming178.com.mylibrary.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import java.util.List;

import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;

/**
 *
 */
public abstract class AbsListPopupWindow<T> extends BasePopupWindow {
    AdapterViewContent<T> adapterContent;

    /**
     * 基ViewGrounp为LinearLayout
     *
     * @param context
     * @param v
     */
    public AbsListPopupWindow(Context context, View v) {
        this(context, v, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    public AbsListPopupWindow(Context context, View v, int width, int height) {
        super(context, v, width, height);
        this.context = context;
        this.v = v;
        this.width = width;
        this.height = height;
        initAdapterView();
    }

    public void setView(View v) {
        this.v = v;
    }

    public void setlayout(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public void setData(List<T> data) {
        adapterContent.setData(data);
    }

    private void initAdapterView() {
        adapterContent = new AdapterViewContent<T>(context,
                getAbListViewRes(view));
        adapterContent.setBaseAdapter(new QuickAdapterImp<T>() {

            @Override
            public int getBaseItemResource() {
                return getItemLayoutRes();
            }

            @Override
            public void convert(ViewHolder helper, T item, int position) {
                convertItem(helper, item, position);
            }
        });
        adapterContent.setItemClick(new ItemCLickImp<T>() {

            @Override
            public void itemCLick(View v,T t, int position) {
                closePopupWindow();
                popItemCLick(t, position);
            }
        });
    }

    /**
     * 点击item
     *
     * @param t
     * @param position
     */
    protected abstract void popItemCLick(T t, int position);

    protected AbsListView getAbListViewRes(View view){
        return (AbsListView) view.findViewById(getListViewId());
    }

    protected abstract void convertItem(ViewHolder helper, T item, int position);

    protected abstract int getItemLayoutRes();

    /**
     * @return
     */
    protected abstract int getListViewId();

}
