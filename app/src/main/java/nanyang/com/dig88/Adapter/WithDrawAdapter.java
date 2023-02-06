package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.WithDrawListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/22.
 */
public class WithDrawAdapter extends BaseAdapter {
    private List<WithDrawListBean> mlist;
    private Context context;
    private BaseActivity aty;
    private CancelResponse cancelResponse;


    public WithDrawAdapter(Context mcontext, List<WithDrawListBean> list) {

        this.context = mcontext;
        aty = (BaseActivity) context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_withdrawmoney_list,
                    null);
            holder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
            holder.tv_fenlei = (TextView) convertView.findViewById(R.id.tv_fenlei);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_jine = (TextView) convertView.findViewById(R.id.tv_jine);
            holder.tv_zhuangtai = (TextView) convertView.findViewById(R.id.tv_zhuangtai);
            holder.tv_xuanxiang = (TextView) convertView.findViewById(R.id.tv_xuanxiang);
            holder.tv_note = (TextView) convertView.findViewById(R.id.tv_note);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final WithDrawListBean depositListBean = mlist.get(position);
        holder.tv_bianhao.setText(context.getString(R.string.bianhao1) + (position + 1));
        if (WebSiteUrl.WebId.equals("46")) {
            holder.tv_fenlei.setText("Id：" + depositListBean.getId_mod_deposit_withdraw());
        } else {
            holder.tv_fenlei.setText(R.string.feileiqukuan);
        }
        holder.tv_time.setText(context.getString(R.string.shijian1) + depositListBean.getDate_time());
        holder.tv_jine.setText(context.getString(R.string.honglijine1) + depositListBean.getAmount());
        if (WebSiteUrl.WebId.equals("995") || WebSiteUrl.WebId.equals("49")) {
            if (depositListBean.getStatus().equals("0")) {
                holder.tv_xuanxiang.setText(context.getString(R.string.xuanxiang) + context.getString(R.string.cancel));
                holder.tv_xuanxiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://app.info.dig88api.com/index.php?page=with_cancel_submitter";
                        String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + aty.getUserInfoBean().getUser_id() + "&session_id=" + aty.getUserInfoBean().getSession_id();
                        params += "&with_id=" + depositListBean.getId_mod_deposit_withdraw();
                        HttpUtils.httpPost(url, params, new HttpUtils.RequestCallBack() {
                            @Override
                            public void onRequestSucceed(String s) {
                                if (cancelResponse != null) {
                                    cancelResponse.onCancelResponseLisener();
                                }
                            }

                            @Override
                            public void onRequestFailed(String s) {
                                if (cancelResponse != null) {
                                    cancelResponse.onCancelResponseLisener();
                                }
                            }
                        });
                    }
                });
                holder.tv_xuanxiang.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                holder.tv_xuanxiang.setText(context.getString(R.string.xuanxiang));
                holder.tv_xuanxiang.setTextColor(context.getResources().getColor(R.color.gray));
            }
        } else {
            holder.tv_xuanxiang.setText(context.getString(R.string.xuanxiang));
            holder.tv_xuanxiang.setTextColor(context.getResources().getColor(R.color.gray));
            if (WebSiteUrl.WebId.equals("77") || WebSiteUrl.WebId.equals("78")) {
                holder.tv_xuanxiang.setText(context.getString(R.string.remak) + ":");
            }
        }
//        holder.tv_bianhao .setText(position+1);
//        0：新申请，1：后台处理成功，2：处理中，3：超时
//        4：管理员终止，6：其他异常类型
        String s = mlist.get(position).getStatus();
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
        holder.tv_note.setText(context.getString(R.string.note) + " : " + depositListBean.getTrs_id());
        if (WebSiteUrl.WebId.equals("46")) {
            holder.tv_xuanxiang.setVisibility(View.GONE);
        } else {
            holder.tv_xuanxiang.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public void setCancelResponse(CancelResponse cancelResponse) {
        this.cancelResponse = cancelResponse;
    }

    public void setData(List<WithDrawListBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public interface CancelResponse {
        void onCancelResponseLisener();
    }

    static class ViewHolder {

        TextView tv_xuanxiang;
        TextView tv_zhuangtai;
        TextView tv_jine;
        TextView tv_time;
        TextView tv_fenlei;
        TextView tv_bianhao;
        TextView tv_note;

    }
}
