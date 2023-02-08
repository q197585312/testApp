package nanyang.com.dig88.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.HashMap;

import butterknife.BindView;
import nanyang.com.dig88.Activity.presenter.CommonDataListPresenter;
import nanyang.com.dig88.Entity.AutoPromotionBean;
import nanyang.com.dig88.Entity.PromotionListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/8/1.
 */

public class CommonDataListActivity extends BaseActivity<CommonDataListPresenter> {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    private int type = 0;//0:promotionData,1:autoPromotionData

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_common_data_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new CommonDataListPresenter(this));
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        setTitle(intent.getStringExtra("title"));
        setleftViewEnable(true);
        getData();
    }

    private String initStr(String s) {
        if (s.contains("(") && s.contains(")")) {
            int i = s.indexOf("(");
            int j = s.indexOf(")");
            if (j > i) {
                return s.substring(i + 1, j);
            } else {
                return s;
            }
        } else {
            return s;
        }
    }

    private String initStr1(String s) {
        if (s.contains("(") && s.contains(")")) {
            int i = s.indexOf("(");
            int j = s.indexOf(")");
            if (j > i) {
                return s.substring(0, i);
            } else {
                return s;
            }
        } else {
            return s;
        }
    }

    public void getData() {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfoBean().getUser_id());
        p.put("session_id", getUserInfoBean().getSession_id());
        switch (type) {
            case 0:
                presenter.getPromotionData(p);
                break;
            default:
                presenter.getAutoPromotionData(p);
                break;
        }
    }

    public void onGetPromotionData(PromotionListBean promotionListBean) {
        BaseRecyclerAdapter<PromotionListBean.DataBean> adapter = new BaseRecyclerAdapter<PromotionListBean.DataBean>(mContext, promotionListBean.getData(), R.layout.item_promotion_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PromotionListBean.DataBean item) {
                LinearLayout ll_content = holder.getView(R.id.ll_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBonus_amount().equals("-1")) {
                    ll_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    ll_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_num = holder.getView(R.id.tv_num);
                    tv_num.setText(getString(R.string.No) + (position + 1));
                    TextView tv_code = holder.getView(R.id.tv_code);
                    tv_code.setText(getString(R.string.Code) + item.getPromosi_code());
                    TextView tv_title = holder.getView(R.id.tv_title);
                    tv_title.setText(getString(R.string.Title) + item.getInfo());
                    TextView tv_join_date = holder.getView(R.id.tv_join_date);
                    tv_join_date.setText(getString(R.string.Join_Date) + item.getDate_time());
                    TextView tv_start_date = holder.getView(R.id.tv_start_date);
                    tv_start_date.setText(getString(R.string.Start_Date) + item.getStart_time());
                    TextView tv_end_date = holder.getView(R.id.tv_end_date);
                    tv_end_date.setText(getString(R.string.End_Date) + item.getEnd_time());
                    TextView tv_bonus = holder.getView(R.id.tv_bonus);
                    tv_bonus.setText(getString(R.string.Bonus) + item.getBonus_amount());
                    TextView tv_turnover = holder.getView(R.id.tv_turnover);
                    tv_turnover.setText(getString(R.string.Turnover) + item.getTurnover_amount());
                    TextView tv_status = holder.getView(R.id.tv_status);
                    String status_text = item.getStatus_text();
                    tv_status.setText(getString(R.string.Status) + status_text);
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }

    public void onGetAutoPromotionData(AutoPromotionBean autoPromotionBean) {
        BaseRecyclerAdapter<AutoPromotionBean.DataBean> adapter = new BaseRecyclerAdapter<AutoPromotionBean.DataBean>(mContext, autoPromotionBean.getData(), R.layout.item_promotion_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, AutoPromotionBean.DataBean item) {
                LinearLayout ll_content = holder.getView(R.id.ll_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getType2().equals("-1")) {
                    ll_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    ll_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_num = holder.getView(R.id.tv_num);
                    tv_num.setText(getString(R.string.No) + (position + 1));
                    TextView tv_code = holder.getView(R.id.tv_code);
                    tv_code.setText(getString(R.string.type) + initStr1(item.getRemark()));
                    TextView tv_title = holder.getView(R.id.tv_title);
                    tv_title.setText(getString(R.string.game_name) + initStr(item.getRemark()));
                    TextView tv_join_date = holder.getView(R.id.tv_join_date);
                    tv_join_date.setText(getString(R.string.shijian) + item.getDate_time());
                    TextView tv_start_date = holder.getView(R.id.tv_start_date);
                    tv_start_date.setText(getString(R.string.honglijine1) + item.getType2());
                    TextView tv_end_date = holder.getView(R.id.tv_end_date);
                    tv_end_date.setText(getString(R.string.tuijianbibie) + getCurrency());
                    TextView tv_bonus = holder.getView(R.id.tv_bonus);
                    TextView tv_turnover = holder.getView(R.id.tv_turnover);
                    TextView tv_status = holder.getView(R.id.tv_status);
                    tv_bonus.setVisibility(View.GONE);
                    tv_turnover.setVisibility(View.GONE);
                    tv_status.setVisibility(View.GONE);
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
