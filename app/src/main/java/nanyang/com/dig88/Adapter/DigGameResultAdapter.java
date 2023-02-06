package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.DigGameResultBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/11/18. (数字结果列表适配器)
 */
public class DigGameResultAdapter extends BaseAdapter {
    private List<DigGameResultBean> mlist;
    private Context mcontext;
    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
    public DigGameResultAdapter(List<DigGameResultBean> list, Context context) {
       this.mlist=list;
        this.mcontext=context;
        this.mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 4;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        DigGameResultBean digGameResultBean=mlist.get(i);
        if (view==null){
           view=mInflater.inflate(R.layout.item_dig_game_result,null);
            holder = new ViewHolder();
            holder.tv_qihao=(TextView)view.findViewById(R.id.tv_qishu);
            holder.tv_qinumber=(TextView)view.findViewById(R.id.tv_qinumber);
            view.setTag(holder);
        }else {
          holder=(ViewHolder)view.getTag();
        }
        holder.tv_qihao.setText(digGameResultBean.getPeriod());
        holder.tv_qinumber.setText(digGameResultBean.getResult());

        Log.i("toby","============期号"+digGameResultBean.getPeriod());


        return view;
    }
    /*存放控件 的ViewHolder*/
    public final class ViewHolder {
        public TextView tv_qihao;
        public TextView tv_qinumber;

    }
}
