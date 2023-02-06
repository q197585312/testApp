package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.ApplyDividendListBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/12/22.
 */
public class ApplyDividendListAdapter extends BaseAdapter {
    private List<ApplyDividendListBean> mlist;
    private Context context;
    public ApplyDividendListAdapter(Context mcontext, List<ApplyDividendListBean> list) {

        this.context = mcontext;
        this.mlist = list;

    }

    public void setMlist(List<ApplyDividendListBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_apply_dividend,
                    null);
            holder.tv_no = (TextView) convertView.findViewById(R.id.tv_no);
            holder.tv_leixing = (TextView) convertView.findViewById(R.id.tv_leixing);
            holder.tv_end_time = (TextView) convertView.findViewById(R.id.tv_end_time);
            holder.tv_zhuantai = (TextView) convertView.findViewById(R.id.tv_zhuantai);
            holder.tv_shenqing_hongli = (TextView) convertView.findViewById(R.id.tv_shenqing_hongli);
            holder.tv_shenqing_tiaoshu = (TextView) convertView.findViewById(R.id.tv_shenqing_tiaoshu);
            holder.tv_no_data = (TextView) convertView.findViewById(R.id.tv_no_data);
            holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ApplyDividendListBean depositListBean = mlist.get(position);
        if (depositListBean.getEnd_time().equals("-1")) {
            holder.rl_content.setVisibility(View.GONE);
            holder.tv_no_data.setVisibility(View.VISIBLE);
        } else {
            holder.rl_content.setVisibility(View.VISIBLE);
            holder.tv_no_data.setVisibility(View.GONE);
            holder.tv_no.setText(context.getString(R.string.no) + (position + 1));
            if (depositListBean.getProvider().equals("1")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.gaomianyx));
            } else if (depositListBean.getProvider().equals("3")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.mgyx));
            } else if (depositListBean.getProvider().equals("4")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.zhenrenyule4));
            } else if (depositListBean.getProvider().equals("5")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.tiyubocai1));
            } else if (depositListBean.getProvider().equals("6")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.tiyubocai2));
            } else if (depositListBean.getProvider().equals("7")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.zhenrenyule3));
            } else if (depositListBean.getProvider().equals("8")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.zhenrenyule));
            } else if (depositListBean.getProvider().equals("9")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.qihuo));
            } else if (depositListBean.getProvider().equals("10")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.zhenrenyule2));
            } else if (depositListBean.getProvider().equals("11")) {
                holder.tv_leixing.setText(context.getString(R.string.aleixing) + context.getString(R.string.casion2));
            }

            holder.tv_end_time.setText(context.getString(R.string.jieshutime) + depositListBean.getEnd_time());
            if (depositListBean.getStatus().equals("0")) {
                holder.tv_zhuantai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.chulizhong));
            } else if (depositListBean.getStatus().equals("1")) {
                holder.tv_zhuantai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.chuliwancen));
            } else if (depositListBean.getStatus().equals("2")) {
                holder.tv_zhuantai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.shengqingqx));
            }
            double aa = Double.parseDouble(depositListBean.getRef_amount());
            String result = String.format("%.2f", aa);
            if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("1")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "USD" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("2")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "SGD" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("3")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "MYR" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("4")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "THB" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("5")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "CNY" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("6")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "HKD" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("8")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "VND" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("9")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "TEST" + ")");
            } else if (((BaseActivity) context).getUserInfoBean().getId_mod_currency().equals("11")) {
                holder.tv_shenqing_hongli.setText(context.getString(R.string.shenqts) + result + "(" + "IDR" + ")");
            }
            holder.tv_shenqing_tiaoshu.setText(context.getString(R.string.shengqinghl1) + depositListBean.getTransfer_count());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_no;
        TextView tv_leixing;
        TextView tv_end_time;
        TextView tv_zhuantai;
        TextView tv_shenqing_hongli;
        TextView tv_shenqing_tiaoshu;
        RelativeLayout rl_content;
        TextView tv_no_data;


    }
}
