package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.DividendCenterListBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/12/22.
 */
public class DividendRecordListAdapter extends BaseAdapter {
    BaseActivity activity;
    private List<DividendCenterListBean> mlist;
    private Context context;

    public DividendRecordListAdapter(Context mcontext, List<DividendCenterListBean> list) {

        this.context = mcontext;
        this.mlist = list;
        activity = (BaseActivity) context;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_deposit_list,
                    null);
            holder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
            holder.tv_leixing = (TextView) convertView.findViewById(R.id.tv_leixing);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_jine = (TextView) convertView.findViewById(R.id.tv_jine);
            holder.tv_fenlei = (TextView) convertView.findViewById(R.id.tv_fenlei);
            holder.tv_zhuangtai = (TextView) convertView.findViewById(R.id.tv_zhuangtai);
            holder.tv_xuanxiang = (TextView) convertView.findViewById(R.id.tv_xuanxiang);
            holder.tv_shanchu = (TextView) convertView.findViewById(R.id.tv_shanchu);
            holder.tv_no_data = (TextView) convertView.findViewById(R.id.tv_no_data);
            holder.tv_note = (TextView) convertView.findViewById(R.id.tv_note);
            holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DividendCenterListBean depositListBean = mlist.get(position);
        if (depositListBean.getTime().equals("-1")) {
            holder.rl_content.setVisibility(View.GONE);
            holder.tv_no_data.setVisibility(View.VISIBLE);
        } else {
            holder.rl_content.setVisibility(View.VISIBLE);
            holder.tv_no_data.setVisibility(View.GONE);
            holder.tv_bianhao.setText(context.getString(R.string.bianhao1) + (position + 1));
            String bonus_type = depositListBean.getBonus_type();
            holder.tv_leixing.setText(context.getString(R.string.leixing) + bonus_type);
            holder.tv_time.setText(context.getString(R.string.shijian) + depositListBean.getTime());

            holder.tv_jine.setText(context.getString(R.string.honglijine1) + depositListBean.getAmount());
//        if (depositListBean.getBonus_type().equals("4")){
//            holder.tv_fenlei.setText(context.getString(R.string.fenlei) + context.getString(R.string.cunkuanhongli));
//        }else {
//            holder.tv_fenlei.setText(context.getString(R.string.fenlei)+context.getString(R.string.zhuankuanhl));
//        }
            holder.tv_note.setVisibility(View.GONE);
            holder.tv_fenlei.setText(context.getString(R.string.fenlei) + context.getString(R.string.zhuankuanhl));
            String status = depositListBean.getStatus();
            if (!TextUtils.isEmpty(status)) {
                if (status.equals("Finish")) {
                    String success = context.getString(R.string.Success);
                    holder.tv_zhuangtai.setText(context.getString(R.string.honglizhongxin) + success);
                    holder.tv_zhuangtai.setTextColor(Color.parseColor("#2DCB67"));
                } else if (status.equals("Submitting")) {
                    holder.tv_zhuangtai.setText(context.getString(R.string.honglizhongxin) + status);
                    holder.tv_zhuangtai.setTextColor(Color.parseColor("#FF8000"));
                } else {
                    holder.tv_zhuangtai.setText(context.getString(R.string.honglizhongxin) + status);
                    holder.tv_zhuangtai.setTextColor(Color.parseColor("#FC5885"));
                }
            } else {
                holder.tv_zhuangtai.setText(context.getString(R.string.honglizhongxin) + "");
            }
            holder.tv_shanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {

        TextView tv_bianhao;
        TextView tv_leixing;
        TextView tv_time;
        TextView tv_jine;
        TextView tv_fenlei;
        TextView tv_zhuangtai;
        TextView tv_xuanxiang;
        TextView tv_shanchu;
        RelativeLayout rl_content;
        TextView tv_no_data;
        TextView tv_note;


    }
}
