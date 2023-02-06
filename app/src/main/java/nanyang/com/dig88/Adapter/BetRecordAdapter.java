package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.ReportFormBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/12/22.
 */
public class BetRecordAdapter extends BaseAdapter {
    private List<ReportFormBean> mlist;
    private Context context;

    public BetRecordAdapter(Context mcontext, List<ReportFormBean> list) {

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bet_record,
                    null);
            holder.tv_no = (TextView) convertView.findViewById(R.id.tv_no);
            holder.tv_leixing = (TextView) convertView.findViewById(R.id.tv_leixing);
            holder.tv_youxi = (TextView) convertView.findViewById(R.id.tv_youxi);
            holder.tv_wanfa=(TextView)convertView.findViewById(R.id.tv_wanfa);
            holder.tv_haoma=(TextView)convertView.findViewById(R.id.tv_haoma);
            holder.tv_peilv=(TextView)convertView.findViewById(R.id.tv_peilv);
            holder.tv_youhui=(TextView)convertView.findViewById(R.id.tv_youhui);
            holder.tv_xiazhujine=(TextView)convertView.findViewById(R.id.tv_xiazhujine);
            holder.tv_qihao=(TextView)convertView.findViewById(R.id.tv_qihao);
            holder.tv_xiazhushijian=(TextView)convertView.findViewById(R.id.tv_xiazhushijian);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        ReportFormBean depositListBean=mlist.get(position);
        holder.tv_no .setText(context.getString(R.string.no)+(position+1));
        holder.tv_leixing .setText(context.getString(R.string.youxitype)+depositListBean.getGame());
        holder.tv_youxi .setText(context.getString(R.string.youxi1)+depositListBean.getPool());
        holder.tv_wanfa .setText(context.getString(R.string.wanfa1)+depositListBean.getType());
        holder.tv_haoma .setText(context.getString(R.string.haoma1)+depositListBean.getNumber());
        holder.tv_peilv .setText(context.getString(R.string.peilv1)+depositListBean.getFactor());
        holder.tv_youhui .setText(context.getString(R.string.youhui1)+depositListBean.getDiscount());
        holder.tv_xiazhujine .setText(context.getString(R.string.xiazhujinee)+depositListBean.getBet_amount());
        holder.tv_qihao .setText(context.getString(R.string.qihao1)+depositListBean.getPeriod());
        holder.tv_xiazhushijian .setText(context.getString(R.string.xiazhushijian)+depositListBean.getBet_time());
        return convertView;
    }

    static class ViewHolder {

        TextView tv_no;
        TextView tv_leixing;
        TextView tv_youxi;
        TextView tv_wanfa;
        TextView tv_haoma;
        TextView tv_peilv;
        TextView tv_youhui;
        TextView tv_xiazhujine;
        TextView tv_qihao;
        TextView tv_xiazhushijian;


    }
}
