package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.DigGameTypeBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/10/20.  game adapter（游戏类型适配器）
 */
public class GameTypeAdapter extends BaseAdapter {
     private List<DigGameTypeBean> mlist;
    private Context  context;

    public GameTypeAdapter(Context mcontext, List<DigGameTypeBean> list) {

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dig_game_type,
                    null);
            holder.image = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.name = (TextView) convertView.findViewById(R.id.game_name);
            holder.induce = (TextView) convertView.findViewById(R.id.game_induce);
            holder.comein=(Button)convertView.findViewById(R.id.comein);
            convertView.setTag(holder);

        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.image .setImageResource(mlist.get(position).getImage());
        holder.name.setText(mlist.get(position).getName());
        holder.induce.setText(mlist.get(position).getIntrduce());
        holder.comein.setText(mlist.get(position).getComein());
        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView induce;
        Button comein;

    }
}

