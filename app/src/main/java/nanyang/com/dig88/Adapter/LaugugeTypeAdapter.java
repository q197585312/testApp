package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.LaugugeTypeBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/10/20.  game adapter（游戏类型适配器）
 */
public class LaugugeTypeAdapter extends BaseAdapter {
     private List<LaugugeTypeBean> mlist;
    private Context  context;
    public LaugugeTypeAdapter(Context mcontext, List<LaugugeTypeBean> list) {

        this.context=mcontext;
        this.mlist=list;

    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lauguge_type,
                    null);

            holder.name = (TextView) convertView.findViewById(R.id.tv_yuyan);

            convertView.setTag(holder);

        }else{
            holder=(ViewHolder) convertView.getTag();
        }


        holder.name.setText(mlist.get(position).getName());
        return convertView;
    }

    static class ViewHolder {

        TextView name;


    }
}

