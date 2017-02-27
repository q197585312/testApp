package com.nanyang.app.main.home.Games.thaiThousand;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.Games.BaseGamesFragment;
import com.nanyang.app.main.home.Games.model.ThaiThousandIntroduceBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ThaiThousandFragment extends BaseGamesFragment<ThaiThousandPresenter> implements ThaiThousandContract.View {
    @Bind(R.id.tv_grade_date)
    TextView tvGradeDate;
    @Bind(R.id.layout_bet1)
    LinearLayout layoutBet1;
    TextView bet1Itmebet1;
    TextView bet1Itmebet2;
    @Bind(R.id.layout_bet2)
    LinearLayout layoutBet2;
    TextView bet2Itmebet1;
    TextView bet2Itmebet2;
    @Bind(R.id.layout_bet3)
    LinearLayout layoutBet3;
    TextView bet3Itmebet1;
    TextView bet3Itmebet2;
    @Bind(R.id.layout_bet4)
    LinearLayout layoutBet4;
    TextView bet4Itmebet1;
    TextView bet4Itmebet2;
    @Bind(R.id.layout_bet5)
    LinearLayout layoutBet5;
    TextView bet5Itmebet1;
    TextView bet5Itmebet2;
    @Bind(R.id.layout_bet6)
    LinearLayout layoutBet6;
    TextView bet6Itmebet1;
    TextView bet6Itmebet2;
    @Bind(R.id.layout_bet7)
    LinearLayout layoutBet7;
    TextView bet7Itmebet1;
    TextView bet7Itmebet2;
    @Bind(R.id.layout_bet8)
    LinearLayout layoutBet8;
    TextView bet8Itmebet1;
    TextView bet8Itmebet2;
    @Bind(R.id.layout_bet9)
    LinearLayout layoutBet9;
    TextView bet9Itmebet1;
    TextView bet9Itmebet2;
    @Bind(R.id.layout_bet10)
    LinearLayout layoutBet10;
    TextView bet10Itmebet1;
    TextView bet10Itmebet2;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.layout_introduce)
    LinearLayout layoutIntroduce;
    TextView tvIntroduceNmae;
    TextView tvIntroduceContent;
    TextView tvIntroduceWay;


    public List<String> gradeData = new ArrayList<>();
    public List<ThaiThousandIntroduceBean> introduceData = new ArrayList<>();
    public LayoutInflater inflater;
    String date;

    @Override
    public void initData() {
        super.initData();
        inflater = LayoutInflater.from(mContext);
        date = getDate(mContext);
        createPresenter(new ThaiThousandPresenter());
        initGradeData();
        tvGradeDate.setText(gradeData.get(0));
        initIntroduceData();
    }

    private void initIntroduceData() {
        introduceData.add(new ThaiThousandIntroduceBean("1D最高奖", "赌上1号预测 其中3个号码能在3D内的抽奖。 假如 抽奖是312，那是顾客能赢的奖品.", "一付三块"));
        introduceData.add(new ThaiThousandIntroduceBean("1D 底部奖", "赌上1号预测 其中2个号码能在2D内的最后抽奖品.", "一付四块"));
        introduceData.add(new ThaiThousandIntroduceBean("1D 最高奖固定 一", "顾客可以赌上个单号预测上3D奖品的第一数字。比如，结果数字是 132，然后顾客将获得奖金.", " 一付九块"));
        introduceData.add(new ThaiThousandIntroduceBean("1D 最高奖固定 二", "顾客可以赌上个单号预测上3D奖品的第二数字。比如，结果数字是 132，然后顾客将获得奖金.", " 一付九块"));
        introduceData.add(new ThaiThousandIntroduceBean("1D 最高奖固定 三", "顾客可以赌上个单号预测上3D奖品的第二数字。比如，结果数字是 132，然后顾客将获得奖金.", " 一付九块"));
        loadIntroduceData();
    }

    private void loadIntroduceData() {
        if (introduceData.size() != 0) {
            for (int i = 0; i < introduceData.size(); i++) {
                ThaiThousandIntroduceBean introduceBean = introduceData.get(i);
                View layout = inflater.inflate(R.layout.layout_thaithousand_introduce, null);
                tvIntroduceNmae = (TextView) layout.findViewById(R.id.tv_Introduce_name);
                tvIntroduceNmae.setText(introduceBean.getName());
                tvIntroduceContent = (TextView) layout.findViewById(R.id.tv_Introduce_content);
                tvIntroduceContent.setText(introduceBean.getIntroduce());
                tvIntroduceWay = (TextView) layout.findViewById(R.id.tv_Introduce_way);
                tvIntroduceWay.setText(introduceBean.getWay());
                layoutIntroduce.addView(layout);
            }
        }
    }

    private void initGradeData() {
        gradeData.add(date + "-泰式千字最高奖 1D");
        gradeData.add(date + "-泰式千字底部奖 1D");
        gradeData.add(date + "-泰式千字最高奖固定1 1D");
        gradeData.add(date + "-泰式千字最高奖固定2 1D");
        gradeData.add(date + "-泰式千字最高奖固定3 1D");
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_thai_thousand;
    }

    @Override
    public void initView() {
        super.initView();
        initBet(layoutBet1, bet1Itmebet1, bet1Itmebet2);
        initBet(layoutBet2, bet2Itmebet1, bet2Itmebet2);
        initBet(layoutBet3, bet3Itmebet1, bet3Itmebet2);
        initBet(layoutBet4, bet4Itmebet1, bet4Itmebet2);
        initBet(layoutBet5, bet5Itmebet1, bet5Itmebet2);
        initBet(layoutBet6, bet6Itmebet1, bet6Itmebet2);
        initBet(layoutBet7, bet7Itmebet1, bet7Itmebet2);
        initBet(layoutBet8, bet8Itmebet1, bet8Itmebet2);
        initBet(layoutBet9, bet9Itmebet1, bet9Itmebet2);
        initBet(layoutBet10, bet10Itmebet1, bet10Itmebet2);
        layoutBet1.findViewById(R.id.item_atm).setVisibility(View.VISIBLE);


    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getString(R.string.Thai_game1), getString(R.string.Thai_game1)));
        types.add(new MenuItemInfo(0, getString(R.string.Thai_game2), getString(R.string.Thai_game2)));
        types.add(new MenuItemInfo(0, getString(R.string.Thai_game3), getString(R.string.Thai_game3)));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
//                presenter.refresh(item.getType());
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    public void toolbarRightClick(View v) {
        createPopupWindow(
                new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, 300) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_ball_type;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setChooseTypeAdapter(rv_list);
                    }
                });
        popWindow.showPopupDownWindow();
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onGetData(Object data) {

    }

    @OnClick({R.id.tv_grade_date, R.id.tv_refresh, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_grade_date:
                BaseListPopupWindow<String> popupWindow = new BaseListPopupWindow<String>(mContext, v, v.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, tvGradeDate) {
                    @Override
                    public int getRecyclerViewId() {
                        return R.id.base_rc;
                    }

                    @Override
                    public List<String> getData() {
                        return gradeData;
                    }
                };
                popupWindow.showPopupDownWindow();
                break;
            case R.id.tv_refresh:
                ToastUtils.showShort("refresh");
                //TODO
                break;
            case R.id.tv_submit:
                ToastUtils.showShort("submit");
                //TODO
                break;
        }
    }

    public void initBet(LinearLayout layout, TextView bet1, TextView bet2) {
        bet1 = (TextView) layout.findViewById(R.id.item_bet1);
        bet2 = (TextView) layout.findViewById(R.id.item_bet2);
    }
}