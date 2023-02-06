package nanyang.com.dig88.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.PersonGameResultBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2015/11/18. (真人结果列表适配器)
 */
public class PersonGameResultAdapter extends BaseAdapter {
    private List<PersonGameResultBean> mlist;
    private Context mcontext;
    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
    public PersonGameResultAdapter(List<PersonGameResultBean> list, Context context) {
        this.mlist=list;
        this.mcontext=context;
        this.mInflater=LayoutInflater.from(context);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        PersonGameResultBean digGameResultBean=null;
        if (mlist.size()>0){
             digGameResultBean=mlist.get(i);
        }else {

        }
        if (view==null){
            view=mInflater.inflate(R.layout.item_person_game_result,null);
            holder = new ViewHolder();
            holder.tv_qihao=(TextView)view.findViewById(R.id.tv_qishu);
            holder.tv_qinumber=(TextView)view.findViewById(R.id.tv_qinumber);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        holder.tv_qihao.setText(digGameResultBean.getPeriod());
        holder.tv_qinumber.setText(digGameResultBean.getResult());

        Log.i("toby", "============期号" + digGameResultBean.getPeriod());


        return view;
    }
    /*存放控件 的ViewHolder*/
    public final class ViewHolder {
        public TextView tv_qihao;
        public TextView tv_qinumber;

    }
}
