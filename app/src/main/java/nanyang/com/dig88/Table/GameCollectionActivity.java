package nanyang.com.dig88.Table;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import gaming178.com.mylibrary.myview.mylistview.PinnedSectionListView;
import gaming178.com.mylibrary.myview.mylistview.PullToRefreshLayout;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.LinkedViewPager.ViewPager;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.utils.TableAdapterHelper;
import nanyang.com.dig88.Table.utils.TableDataHelper;

/**
 * Created by Administrator on 2015/11/26.
 */
public class GameCollectionActivity extends GameBaseActivity {
    @BindView(R.id.table_number_tv)
    TextView tableNumberTv;
    @BindView(R.id.table_match_mark_tv)
    TextView tableMatchMarkTv;
    @BindView(R.id.ballgame_left_tv)
    TextView ballgameLeftTv;
    @BindView(R.id.ballgame_center_vp)
    ViewPager ballgameCenterVp;
    @BindView(R.id.ballgame_mark_tv)
    TextView ballgameMarkTv;
    @BindView(R.id.pull_icon)
    ImageView pullIcon;
    @BindView(R.id.refreshing_icon)
    ImageView refreshingIcon;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.state_iv)
    ImageView stateIv;
    @BindView(R.id.head_view)
    RelativeLayout headView;
    @BindView(R.id.ballgame_table_content_explv)
    PinnedSectionListView ballgameTableContentExplv;
    @BindView(R.id.pullup_icon)
    ImageView pullupIcon;
    @BindView(R.id.loading_icon)
    ImageView loadingIcon;
    @BindView(R.id.loadstate_tv)
    TextView loadstateTv;
    @BindView(R.id.loadstate_iv)
    ImageView loadstateIv;
    @BindView(R.id.loadmore_view)
    RelativeLayout loadmoreView;
    @BindView(R.id.ballgame_table_refresh_view)
    PullToRefreshLayout ballgameTableRefreshView;
    TableDataHelper helper;
    Handler updateHandler=new Handler();
    private String modle;
    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            updateNet();
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };
    private TableAdapterHelper adapterHelper;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_ballgame_layout1;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        modle = getIntent().getStringExtra(AppConfig.ACTION_KEY_INITENT_STRING);
        tableMatchMarkTv.setText(modle);
        helper = new TableDataHelper(ballgameTableRefreshView,this, 1, new ThreadEndT<ArrayList<TableModuleBean>>(new TypeToken<ArrayList<TableModuleBean>>() {
        }.getType()) {
            @Override
            public void endT(ArrayList<TableModuleBean> result,int model) {
                if (result != null && result.size() > 0) {
                    adapterHelper = new TableAdapterHelper(mContext, modle, ballgameTableContentExplv, helper);
                    adapterHelper.setAllListData(result);
                    adapterHelper.setTypeMarkView(tableMatchMarkTv);
                    adapterHelper.setHeaderPager(ballgameCenterVp);
                    adapterHelper.setContentSv(ballgameTableRefreshView);
                    adapterHelper.setCountView(tableNumberTv);
                    adapterHelper.getData();
                } else {
                    Toast.makeText(mContext,getString(R.string.no_data),Toast.LENGTH_LONG).show();
                    if(adapterHelper!=null)
                        adapterHelper.clear();
                }

            }

            @Override
            public void endString(String result,int model) {

            }

            @Override
            public void endError(String error) {
                Toast.makeText(mContext,getString(R.string.connect_server_fail),Toast.LENGTH_LONG).show();
            }
        });
        helper.getData(modle, "",modle);
    }

    private void updateNet() {
        helper.updateNet(modle);
    }

    public void updateTableData() {
        removeTableUpdate();
        updateHandler.postDelayed(dataUpdateRunnable, 10000);// 打开定时器，执行操作
    }

    private void removeTableUpdate() {
        updateHandler.removeCallbacks(dataUpdateRunnable);// 50是延时时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTableData();
    }
    @Override
    protected void onStop() {
        super.onStop();
        removeTableUpdate();
    }
}
