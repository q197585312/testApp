package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.CompleteReportFormBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/22.
 */
public class CompleteReportFormAdapter extends BaseAdapter {
    private List<CompleteReportFormBean> mlist;
    private Context context;
    private String number = "";

    public CompleteReportFormAdapter(Context mcontext, List<CompleteReportFormBean> list) {

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_complete_record,
                    null);
            holder.tv_no = (TextView) convertView.findViewById(R.id.tv_no);
            holder.tv_leixing = (TextView) convertView.findViewById(R.id.tv_leixing);
            holder.tv_youxi = (TextView) convertView.findViewById(R.id.tv_youxi);
            holder.tv_wanfa = (TextView) convertView.findViewById(R.id.tv_wanfa);
            holder.tv_haoma = (TextView) convertView.findViewById(R.id.tv_haoma);

            holder.tv_xiazhujie = (TextView) convertView.findViewById(R.id.tv_xiazhujie);
            holder.tv_shuying = (TextView) convertView.findViewById(R.id.tv_shuying);
            holder.tv_jieguo = (TextView) convertView.findViewById(R.id.tv_jieguo);
            holder.tv_qihao = (TextView) convertView.findViewById(R.id.tv_qihao);
            holder.tv_peilv = (TextView) convertView.findViewById(R.id.tv_peilv);
            holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
            holder.tv_no_data = (TextView) convertView.findViewById(R.id.tv_no_data);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CompleteReportFormBean depositListBean = mlist.get(position);
        if (depositListBean.getBet_amount().equals("-1")) {
            holder.rl_content.setVisibility(View.GONE);
            holder.tv_no_data.setVisibility(View.VISIBLE);
        } else {
            holder.rl_content.setVisibility(View.VISIBLE);
            holder.tv_no_data.setVisibility(View.GONE);
            number = "" + (position + 1);
            if (number.length() < 2) {
                number = "0" + (position + 1);
            }
            holder.tv_no.setText(context.getString(R.string.no) + number);
            holder.tv_leixing.setText(context.getString(R.string.youxitype) + depositListBean.getGame());
            holder.tv_youxi.setText(context.getString(R.string.youxi1) + depositListBean.getPool());
            holder.tv_wanfa.setText(context.getString(R.string.wanfa1) + depositListBean.getType());
            holder.tv_haoma.setText(context.getString(R.string.haoma1) + depositListBean.getNumber());
            holder.tv_xiazhujie.setText(context.getString(R.string.xiazhujine) + ":" + depositListBean.getBet_amount());
            holder.tv_shuying.setText(context.getString(R.string.shuying) + depositListBean.getWin_loss());
            holder.tv_jieguo.setText(context.getString(R.string.jieguo) + ":" + depositListBean.getResult());
            holder.tv_qihao.setText(context.getString(R.string.qihao1) + depositListBean.getPeriod());
            holder.tv_peilv.setText(context.getString(R.string.wanpeilv) + depositListBean.getFactor());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_no;
        TextView tv_leixing;
        TextView tv_youxi;
        TextView tv_wanfa;
        TextView tv_haoma;
        TextView tv_xiazhujie;
        TextView tv_shuying;
        TextView tv_jieguo;
        TextView tv_qihao;
        TextView tv_peilv;
        RelativeLayout rl_content;
        TextView tv_no_data;


    }
}
