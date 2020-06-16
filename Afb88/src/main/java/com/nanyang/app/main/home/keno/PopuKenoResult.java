package com.nanyang.app.main.home.keno;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.nanyang.app.main.home.keno.bean.KenoResultBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class PopuKenoResult extends BasePopupWindow {
    private List<KenoDataBean.PublicDataBean.CompanyDataBean.ResultIdBean> resultIdList;
    private List<KenoResultBean> resultList;
    private List<String> numList;
    private BaseRecyclerAdapter<String> adapter;
    private Context context;

    public PopuKenoResult(Context context, View v) {
        super(context, v);
        this.context = context;
    }

    public PopuKenoResult(Context context, View v, int width, int height) {
        super(context, v, width, height);
        this.context = context;
    }

    public PopuKenoResult(List<KenoDataBean.PublicDataBean.CompanyDataBean.ResultIdBean> resultIdList,
                          Context context, View v, int width, int height) {
        super(context, v, width, height);
        this.resultIdList = resultIdList;
        resultList = new ArrayList<>();
        numList = new ArrayList<>();
        this.context = context;
        adapter = new BaseRecyclerAdapter<String>(context, numList, R.layout.item_keno_result) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv = holder.getView(R.id.tv_content);
                tv.setText(item);
            }
        };
        GridLayoutManager layoutManager = new GridLayoutManager(context, 10);
        keno_result_rc.setLayoutManager(layoutManager);
        keno_result_rc.setAdapter(adapter);
        refreshData(resultIdList);
    }

    public void refreshData(List<KenoDataBean.PublicDataBean.CompanyDataBean.ResultIdBean> list) {
        resultIdList = list;
        if (resultIdList == null || resultIdList.size() == 0) {
            ToastUtils.showShort("result data null");
            return;
        }
        parseData();
        showUi(resultList.get(index));
        img_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index > resultList.size() - 1) {
                    index = resultList.size() - 1;
                    return;
                }
                showUi(resultList.get(index));
            }
        });
        img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0) {
                    index = 0;
                    return;
                }
                showUi(resultList.get(index));
            }
        });
    }

    private int index = 0;

    private void showUi(KenoResultBean bean) {
        tv_draw_num.setText(bean.getDrawNum());
        int total = 0;
        for (int i = 0; i < bean.getNumList().size(); i++) {
            total += Integer.parseInt(bean.getNumList().get(i));
        }
        tv_total.setText(total + "");
        String[] type = bean.getType().split(" ");
        for (int i = 0; i < type.length; i++) {
            if (type[i].equals("A")) {
                type[i] = "W";
            }
        }
        tv1.setText(type[0]);
        tv2.setText(type[1]);
        tv3.setText(type[2]);
        tv4.setText(type[3]);
        tv5.setText(type[4]);
        adapter.setData(bean.getNumList());
    }

    ImageView img_left;
    ImageView img_right;
    TextView tv_draw_num;
    RecyclerView keno_result_rc;
    TextView tv_total;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv;
    ImageView img;

    @Override
    protected void initView(View view) {
        super.initView(view);
        img_left = (ImageView) view.findViewById(R.id.img_left);
        img_right = (ImageView) view.findViewById(R.id.img_right);
        tv_draw_num = (TextView) view.findViewById(R.id.tv_draw_num);
        keno_result_rc = (RecyclerView) view.findViewById(R.id.keno_result_rc);
        tv_total = (TextView) view.findViewById(R.id.tv_total);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv = (TextView) v.findViewById(R.id.tv_result);
        img = (ImageView) v.findViewById(R.id.img_result);
    }

    private void parseData() {
        for (int i = 0; i < resultIdList.size(); i++) {
            String drawNum = resultIdList.get(i).getId();
            String[] numAndType = resultIdList.get(i).getValue().split("\\|");
            List<String> numList = new ArrayList<>();
            String type = numAndType[1];
            String[] chars = numAndType[0].split(" ");
            for (int j = 0; j < chars.length; j++) {
                numList.add(chars[j] + "");
            }
            resultList.add(new KenoResultBean(drawNum, type, numList));
        }
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popu_keno_resule;
    }

    @Override
    protected void onClose() {
        v.setBackgroundColor(0x00000000);
        tv.setTextColor(Color.WHITE);
        AfbUtils.GildLoadResForImg(context, img, R.mipmap.keno_result_white);
    }
}
