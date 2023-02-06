package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.RecommendedListBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/12/22.
 */
public class RecommendListAdapter extends BaseAdapter {
    private List<RecommendedListBean> mlist;
    private Context context;

    public RecommendListAdapter(Context mcontext, List<RecommendedListBean> list) {
        this.context = mcontext;
        this.mlist = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tuijianren,
                    null);
            holder.tv_no = (TextView) convertView.findViewById(R.id.tv_no);
            holder.tv_zhanghao = (TextView) convertView.findViewById(R.id.tv_zhanghao);
            holder.tv_bibie = (TextView) convertView.findViewById(R.id.tv_bibie);
            holder.tv_licheng = (TextView) convertView.findViewById(R.id.tv_licheng);
            holder.tv_kaihutime = (TextView) convertView.findViewById(R.id.tv_kaihutime);
            holder.tv_no_data = (TextView) convertView.findViewById(R.id.tv_no_data);
            holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecommendedListBean depositListBean = mlist.get(position);
        if (depositListBean.getUsername().equals("-1")) {
            holder.rl_content.setVisibility(View.GONE);
            holder.tv_no_data.setVisibility(View.VISIBLE);
        } else {
            holder.rl_content.setVisibility(View.VISIBLE);
            holder.tv_no_data.setVisibility(View.GONE);
            holder.tv_no.setText(context.getString(R.string.no) + (position + 1));
            holder.tv_zhanghao.setText(context.getString(R.string.beituijianzh) + depositListBean.getUsername());
            holder.tv_bibie.setText(context.getString(R.string.tuijianbibie) + depositListBean.getCurrency());
            holder.tv_licheng.setText(context.getString(R.string.tuijianlic) + depositListBean.getUsername());
            holder.tv_kaihutime.setText(context.getString(R.string.kaihutime) + depositListBean.getOpen_time());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_no;
        TextView tv_zhanghao;
        TextView tv_bibie;
        TextView tv_licheng;
        TextView tv_kaihutime;
        RelativeLayout rl_content;
        TextView tv_no_data;
    }
}
