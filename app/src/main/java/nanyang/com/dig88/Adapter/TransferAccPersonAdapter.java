package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.TransferAccPersonBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/22.
 */
public class TransferAccPersonAdapter extends BaseAdapter {
    private List<TransferAccPersonBean.DataBean> mlist;
    private Context context;
    private String masterAcc;
    private String saba;
    private String sb;
    private String scr;

    public TransferAccPersonAdapter(Context mcontext, List<TransferAccPersonBean.DataBean> list, String masterAcc, String saba, String sb, String scr) {
        this.context = mcontext;
        this.mlist = list;
        this.masterAcc = masterAcc;
        this.saba = saba;
        this.scr = scr;
        this.sb = sb;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_transferacclist,
                    null);
            holder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
            holder.tv_leixing = (TextView) convertView.findViewById(R.id.tv_leixing);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_cong = (TextView) convertView.findViewById(R.id.tv_cong);
            holder.tv_dao = (TextView) convertView.findViewById(R.id.tv_dao);
            holder.tv_jine = (TextView) convertView.findViewById(R.id.tv_jine);
            holder.tv_zhuangtai = (TextView) convertView.findViewById(R.id.tv_zhuangtai);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TransferAccPersonBean.DataBean dataBean = mlist.get(position);
        holder.tv_bianhao.setText(context.getString(R.string.bianhao1) + (position + 1));
        holder.tv_leixing.setText(context.getString(R.string.leixing) + context.getString(R.string.transferacc));
        holder.tv_time.setText(context.getString(R.string.shijian1) + dataBean.getInsert_time());
        String statusFrom = dataBean.getFrom_gameid();
        String statusTo = dataBean.getTo_gameid();
        if (statusFrom.equals("1")) {
            holder.tv_cong.setText(context.getString(R.string.from) + masterAcc);
        } else if (statusFrom.equals("2")) {
            holder.tv_cong.setText(context.getString(R.string.from) + saba);
        } else if (statusFrom.equals("3")) {
            holder.tv_cong.setText(context.getString(R.string.from) + sb);
        } else if (statusFrom.equals("5")) {
            holder.tv_cong.setText(context.getString(R.string.from) + scr);
        } else if (statusFrom.equals("8")) {
            if (WebSiteUrl.WebId.equals("72")) {
                holder.tv_cong.setText(context.getString(R.string.from) + context.getString(R.string.afbcasheu_klaspoker_game_money));
            } else if (WebSiteUrl.WebId.equals("54")) {
                holder.tv_cong.setText(context.getString(R.string.from) + "爱发宝扑克");
            } else {
                holder.tv_cong.setText(context.getString(R.string.from) + context.getString(R.string.list5_klaspoker));
            }
        } else if (statusFrom.equals("4")) {
            holder.tv_cong.setText(context.getString(R.string.from) + context.getString(R.string.list4_pt));
        } else if (statusFrom.equals("16")) {
            holder.tv_cong.setText(context.getString(R.string.from) + "RTG " + context.getString(R.string.game));
        } else if (statusFrom.equals("6")) {
            holder.tv_cong.setText(context.getString(R.string.from) + "彩票");
        } else if (statusFrom.equals("17")) {
            holder.tv_cong.setText(context.getString(R.string.from) + context.getString(R.string.NewKeno));
        }
        if (statusTo.equals("1")) {
            holder.tv_dao.setText(context.getString(R.string.to) + masterAcc);
        } else if (statusTo.equals("2")) {
            holder.tv_dao.setText(context.getString(R.string.to) + saba);
        } else if (statusTo.equals("3")) {
            holder.tv_dao.setText(context.getString(R.string.to) + sb);
        } else if (statusTo.equals("5")) {
            holder.tv_dao.setText(context.getString(R.string.to) + scr);
        } else if (statusTo.equals("8")) {
            if (WebSiteUrl.WebId.equals("72")) {
                holder.tv_dao.setText(context.getString(R.string.to) + context.getString(R.string.afbcasheu_klaspoker_game_money));
            } else if (WebSiteUrl.WebId.equals("54")) {
                holder.tv_dao.setText(context.getString(R.string.to) + "爱发宝扑克");
            } else {
                holder.tv_dao.setText(context.getString(R.string.to) + context.getString(R.string.list5_klaspoker));
            }
        } else if (statusTo.equals("4")) {
            holder.tv_dao.setText(context.getString(R.string.to) + context.getString(R.string.list4_pt));
        } else if (statusTo.equals("16")) {
            holder.tv_dao.setText(context.getString(R.string.to) + "RTG " + context.getString(R.string.game));
        } else if (statusTo.equals("6")) {
            holder.tv_dao.setText(context.getString(R.string.to) + "彩票");
        } else if (statusTo.equals("17")) {
            holder.tv_dao.setText(context.getString(R.string.to) + context.getString(R.string.NewKeno));
        }
        holder.tv_jine.setText(context.getString(R.string.honglijine1) + Math.abs(Double.parseDouble(dataBean.getAmount())));
        // holder.tv_bianhao .setText(position+1);
//        0：新申请，1：后台处理成功，2：处理中，3：超时
//        4：管理员终止，6：其他异常类型
        String s = dataBean.getStatus();
        if (s.equals("0")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.newshengqing));
        } else if (s.equals("1")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.houtaichuli));
        } else if (s.equals("2")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.chulizhong));
        } else if (s.equals("3")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.caoshi));
        } else if (s.equals("4")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.gaunliyuan));
        } else if (s.equals("6")) {
            holder.tv_zhuangtai.setText(context.getString(R.string.cunkuanlist1) + context.getString(R.string.qitaleix));
        }
        return convertView;
    }

    static class ViewHolder {

        TextView tv_bianhao;
        TextView tv_leixing;
        TextView tv_time;
        TextView tv_cong;
        TextView tv_dao;
        TextView tv_jine;
        TextView tv_zhuangtai;


    }
}
