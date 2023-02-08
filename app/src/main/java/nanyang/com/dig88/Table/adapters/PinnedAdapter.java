package nanyang.com.dig88.Table.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.myview.mylistview.PinnedSectionListView;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.MatchBean;

/**
 * Created by Administrator on 2015/11/17.
 */
public abstract class PinnedAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    List<MatchBean> datas;
    Context mContext;

    public PinnedAdapter(Context mContext,List<MatchBean> datas) {
        this.datas = datas;
        this.mContext = mContext;

    }

    public PinnedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType==0;
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public MatchBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder helper=null;
        if(getItemViewType(position)==0) {
            helper = get(mContext, convertView, parent,
                    R.layout.header_table_module_layout);
        }
        else{
            helper = get(mContext, convertView, parent,
                    R.layout.item_table_module);
        }
        convert(helper, getItem(position), position);
        return helper.getView();
    }

    protected abstract void convert(ViewHolder helper, MatchBean item, int position);

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType()== MatchBean.Type.HEAD?0:1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public ViewHolder get(Context context, View convertView, ViewGroup parent,
                          int layoutId) {
        if (convertView == null) {
            return ViewHolder.get(context, convertView, parent, layoutId);
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        return holder;

    }

    public void setDatas(List<MatchBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
    public void setItemHomeOdds(int position ,String odds){
        datas.get(position).getHandicapBeans().get(0).setHomeHdpOdds(odds);

    }
    public void setItemAwayOdds(int position ,String s){
        datas.get(position).getHandicapBeans().get(0).setAwayHdpOdds(s);

    }
    public void setItemHomeOdds_FH(int position ,String s){
        datas.get(position).getHandicapBeans().get(1).setHomeHdpOdds(s);
    }
    public void setItemAwayOdds_FH(int position ,String s){
        datas.get(position).getHandicapBeans().get(1).setAwayHdpOdds(s);
    }
    public void setItemOverOdds(int position ,String s){
        datas.get(position).getHandicapBeans().get(0).setOverOdds(s);
    }
    public void setItemUnderOdds(int position ,String s){
        datas.get(position).getHandicapBeans().get(0).setUnderOdds(s);
    }
    public void setItemOverOdds_FH(int position ,String s){
        datas.get(position).getHandicapBeans().get(1).setOverOdds(s);
    }
    public void setItemUnderOdds_FH(int position ,String s){
        datas.get(position).getHandicapBeans().get(1).setUnderOdds(s);
    }
    public void clear(){
        datas=new ArrayList<>();
        notifyDataSetChanged();
    }
}
