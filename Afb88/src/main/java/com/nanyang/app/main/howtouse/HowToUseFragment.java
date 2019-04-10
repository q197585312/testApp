package com.nanyang.app.main.howtouse;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BetCenter.Bean.HowToUse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 47184 on 2019/4/4.
 */

public class HowToUseFragment extends BaseMoreFragment {

    @Bind(R.id.howtouse_content_layout)
    LinearLayout contentLayout;
    @Bind(R.id.howtouse_redio_layout)
    LinearLayout redioLayout;
    private List<HowToUse> dataList;
    @Bind(R.id.howtouse_text1)
    TextView howtouseText1;
    @Bind(R.id.howtouse_text2)
    TextView howtouseText2;
    @Bind(R.id.howtouse_text3)
    TextView howtouseText3;
    @Bind(R.id.howtosue_img)
    ImageView img;
    @Bind(R.id.howtouse_title)
    TextView titleText;
    @Bind(R.id.img_left)
    ImageView leftImg;
    @Bind(R.id.img_right)
    ImageView rightImg;
    @Bind(R.id.howtouse_btn)
    TextView hwotouseBtn;

    List<TextView> textList;
    List<TextView> redioList;
    private int index;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_howtouse;
    }

    @Override
    public void initView() {
        super.initView();
        setBackTitle(getString(R.string.setting));
        index = 0;
        textList = new ArrayList<>();
        redioList = new ArrayList<>();
        textList.add(howtouseText1);
        textList.add(howtouseText2);
        textList.add(howtouseText3);
        initDateList();
        for (int i = 0; i < dataList.size(); i++) {
            TextView t = new TextView(mContext);
            LinearLayout.LayoutParams lp;
            // 设置margin

            if (i == 0) {
                lp = new LinearLayout.LayoutParams(25, 25);
                t.setBackgroundResource(R.drawable.shape_how_to_use);
            } else {
                lp = new LinearLayout.LayoutParams(20, 20);
                t.setBackgroundResource(R.drawable.shape_how_to_use_notselect);
            }
            lp.leftMargin = 3;
            t.setLayoutParams(lp);
            redioLayout.addView(t);
            redioList.add(t);
        }
        hwotouseBtn.setText(getString(R.string.howtouse_btn));
        //默认为选择第一条数据
        changeContent(dataList.get(index));
        //第一页隐藏左箭头
        leftImg.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.img_left, R.id.img_right, R.id.howtouse_btn})
    public void clickImg(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                if (index > 0) {
                    index--;
                    if (index == 0) {
                        leftImg.setVisibility(View.INVISIBLE);
                        hwotouseBtn.setText(getString(R.string.howtouse_btn));
                    }
                    if (index == dataList.size() - 2) {
                        rightImg.setVisibility(View.VISIBLE);
                    }
                    changeContent(dataList.get(index));
                }
                break;
            case R.id.img_right:
                if (index < dataList.size()) {
                    index++;
                    if (index == dataList.size() - 1) {
                        rightImg.setVisibility(View.INVISIBLE);
                        hwotouseBtn.setText(getString(R.string.howtouse_btn2));
                    }
                    if (index == 1) {
                        leftImg.setVisibility(View.VISIBLE);
                    }
                    changeContent(dataList.get(index));
                }
                break;
            case R.id.howtouse_btn:
                holder.isBack(false);
                break;

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setToolbarVisibility(View.VISIBLE);
            index = 0;
            hwotouseBtn.setText(getString(R.string.howtouse_btn));
            //默认为选择第一条数据
            changeContent(dataList.get(index));
            //第一页隐藏左箭头
            leftImg.setVisibility(View.INVISIBLE);
            rightImg.setVisibility(View.VISIBLE);
        }
    }


    public void changeContent(HowToUse htu) {
        howtouseText1.setText("");
        howtouseText2.setText("");
        howtouseText3.setText("");
        img.setImageResource(htu.getImg());
        titleText.setText(htu.getTitle());
        for (int i = 0; i < htu.getContent().size(); i++) {
            textList.get(i).setText(htu.getContent().get(i));
        }
        for (TextView textView : redioList) {
            textView.setBackgroundResource(R.drawable.shape_how_to_use_notselect);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(25, 25);
        redioList.get(index).setLayoutParams(lp);
        redioList.get(index).setBackgroundResource(R.drawable.shape_how_to_use);
    }

    public void initDateList() {
        dataList = new ArrayList<>();
        //选择产品与时间区
        HowToUse htu1 = new HowToUse();
        htu1.setImg(R.mipmap.howtouse_step_1);
        htu1.setTitle(getString(R.string.howtouse_title1_1));
        List<String> list1 = new ArrayList<>();
        list1.add(getString(R.string.howtouse_content1_1));
        list1.add(getString(R.string.howtouse_content1_2));
        htu1.setContent(list1);
        dataList.add(htu1);
        //选择联赛
        HowToUse htu2 = new HowToUse();
        htu2.setImg(R.mipmap.howtouse_step_2);
        htu2.setTitle(getString(R.string.howtouse_title2_1));
        List<String> list2 = new ArrayList<>();
        list2.add(getString(R.string.howtouse_content2_1));
        list2.add(getString(R.string.howtouse_content2_2));
        htu2.setContent(list2);
        dataList.add(htu2);
        //注单
        HowToUse htu3 = new HowToUse();
        htu3.setImg(R.mipmap.howtouse_step_3);
        htu3.setTitle(getString(R.string.howtouse_title3_1));
        List<String> list3 = new ArrayList<>();
        list3.add(getString(R.string.howtouse_content3_1));
        list3.add(getString(R.string.howtouse_content3_2));
        htu3.setContent(list3);
        dataList.add(htu3);
        //我的最爱
        HowToUse htu4 = new HowToUse();
        htu4.setImg(R.mipmap.howtouse_step_4);
        htu4.setTitle(getString(R.string.howtouse_title4_1));
        List<String> list4 = new ArrayList<>();
        list4.add(getString(R.string.howtouse_content4_1));
        list4.add(getString(R.string.howtouse_content4_2));
        list4.add(getString(R.string.howtouse_content4_3));
        htu4.setContent(list4);
        dataList.add(htu4);
        //滚球赛事/滚球视讯
        HowToUse htu5 = new HowToUse();
        htu5.setImg(R.mipmap.howtouse_step_5);
        htu5.setTitle(getString(R.string.howtouse_title5_1));
        List<String> list5 = new ArrayList<>();
        list5.add(getString(R.string.howtouse_content5_1));
        list5.add(getString(R.string.howtouse_content5_2));
        htu5.setContent(list5);
        dataList.add(htu5);
    }
}
