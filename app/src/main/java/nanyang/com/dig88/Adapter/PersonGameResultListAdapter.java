package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Activity.PersonResultListActivity;
import nanyang.com.dig88.Activity.ResultListActivity;
import nanyang.com.dig88.Entity.DigGameResultBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/10/20. dig game adapter
 */
public class PersonGameResultListAdapter extends BaseAdapter {
    public Handler handler = new Handler();
     private List<DigGameResultBean> mlist;
    private Context  context;
    private String gameName;
    public PersonGameResultListAdapter(Context mcontext, List<DigGameResultBean> list) {
        this.context=mcontext;
        this.mlist=list;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_game_result_list,
                    null);
            holder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
            holder.tv_youxi_type = (TextView) convertView.findViewById(R.id.tv_youxi_type);
            holder.tv_youxi_name = (TextView) convertView.findViewById(R.id.tv_youxi_name);
            holder.tv_youxiid = (TextView) convertView.findViewById(R.id.tv_youxiid);
            holder.tv_kaijiangtime = (TextView) convertView.findViewById(R.id.tv_kaijiangtime);
            holder.tv_result = (TextView) convertView.findViewById(R.id.tv_result);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        DigGameResultBean resultListBean=mlist.get(position);
        holder.tv_bianhao.setText((position + 1) + "");
        holder.tv_youxi_type.setText(context.getString(R.string.shuziyouxi1));


        switch(gameName)
        {
            case "42_Balls":
                holder.tv_youxi_name.setText("42 "+context.getString(R.string.qiu));
                break;
            case "36_Balls":
                holder.tv_youxi_name.setText("36 "+context.getString(R.string.qiu));
                break;
            case "48_Balls":
                holder.tv_youxi_name.setText("48 "+context.getString(R.string.qiu));
                break;
            case "12_Balls":
                holder.tv_youxi_name.setText("12 "+context.getString(R.string.qiu));
                break;
            case "Sicbo_Balls":
                holder.tv_youxi_name.setText("Sicbo "+context.getString(R.string.qiu));
                break;
            case "Roulette_Balls":
                holder.tv_youxi_name.setText("Roulette "+context.getString(R.string.qiu));
                break;
            case "Multiple_36Balls":
                holder.tv_youxi_name.setText("Multiple 36Balls");
                break;
        }
        holder.tv_youxiid.setText(resultListBean.getPeriod());
        holder.tv_kaijiangtime.setText(resultListBean.getOpen_time());
        holder.tv_result.setText(resultListBean.getResult());
        return convertView;
    }

    static class ViewHolder {
        TextView tv_bianhao;
        TextView tv_youxi_type;
        TextView tv_youxi_name;
        TextView tv_youxiid;
        TextView tv_kaijiangtime;
        TextView tv_result;

    }

}
