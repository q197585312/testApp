package nanyang.com.dig88.Table;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BetOrderBean;
import nanyang.com.dig88.Table.entity.BetStateThirdBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/3.
 */
public class BetSettlementThirdActivity extends GameBaseActivity {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindString(R.string.amount)
    String amount;
    @BindString(R.string.amount_of_effective)
    String amountEffective;
    @BindString(R.string.commission)
    String commission;
    @BindString(R.string.win_or_lose)
    String winLose;
    @BindString(R.string.result)
    String result;
    private AdapterViewContent<BetStateThirdBean> listContent;
    private TableHttpHelper<List<BetStateThirdBean>> helper;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_list_content;
    }

    /*Statement：
    第一层:
    http://mobilesport.dig88api.com/_norm/AccMatchWL_App.aspx?userName=t@dc2stest0002&to=2015/12/07&from=2015/12/07
    */
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initAdapter();
        setTitle(getString(R.string.detail));
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        String url = getIntent().getStringExtra(AppConfig.ACTION_KEY_INITENT_STRING);
        helper = new TableHttpHelper<>(mContext, listContentPtrlv, new ThreadEndT<List<BetStateThirdBean>>(new TypeToken<List<BetStateThirdBean>>() {
        }.getType()) {
            @Override
            public void endT(List<BetStateThirdBean> result,int model) {
                if (result != null) {
                    setThirdListData(result);
                }
            }

            @Override
            public void endString(String result,int model) {

            }

            @Override
            public void endError(String error) {
                Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
            }
        });
        //"http://mobilesport.dig88api.com/_norm/"
        helper.getData(WebSiteUrl.SportUrl+"_norm/" + url, "", TableDataHelper.ModelType.Running);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    private void initAdapter() {
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        listContent = new AdapterViewContent<BetStateThirdBean>(mContext, listContentPtrlv.getRefreshableView());
        listContent.setBaseAdapter(new QuickAdapterImp<BetStateThirdBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_statement_third_floor;
            }

            @Override
            public void convert(ViewHolder helper, final BetStateThirdBean item, int position) {
                if (position==listContent.getDataCount()-1){
                    helper.setVisible(R.id.ll_date,false);
                    helper.setVisible(R.id.ll_math,false);
                    helper.setVisible(R.id.ll_state,false);
                }else {
                    helper.setVisible(R.id.ll_date,true);
                    helper.setVisible(R.id.ll_math,true);
                    helper.setVisible(R.id.ll_state,true);
                }
                helper.setText(R.id.tv_date,getString(R.string.date)+":");
                helper.setText(R.id.tv_math,getString(R.string.match)+":");
                helper.setText(R.id.tv_state,getString(R.string.state)+":");
                helper.setText(R.id.tv_amount,getString(R.string.amount)+":");
                helper.setText(R.id.tv_amount_of_effective,getString(R.string.amount_of_effective)+":");
                helper.setText(R.id.tv_win_lose,getString(R.string.win_or_lose)+":");
                helper.setText(R.id.tv_commission,getString(R.string.commission)+":");
                helper.setText(R.id.statement_third_tv11, item.getRefNo());

                helper.setText(R.id.statement_third_tv12, item.getDate());

                helper.setText(R.id.statement_third_tv21, item.getModuleTitle());
                if (!item.getHome().equals("") || !item.getAway().equals(""))
                    helper.setText(R.id.statement_third_tv22, item.getHome() + " VS " + item.getAway());
                else
                    helper.setText(R.id.statement_third_tv22, "");
                if (!item.getResult().equals(""))
                    helper.setText(R.id.statement_third_tv23, item.getWorkingDate() + "   " + result + ":" + item.getResult());
                else
                    helper.setText(R.id.statement_third_tv23, item.getWorkingDate());

                helper.setText(R.id.statement_third_tv31, item.getBetType()+item.getFirstHalf());
                if (!item.getHdpOdds().equals("") || !item.getOdds().equals(""))
                    helper.setText(R.id.statement_third_tv32, item.getHdpOdds() + "@" + item.getOdds()+"(Inet)");
                else
                    helper.setText(R.id.statement_third_tv32, "");
                helper.setText(R.id.statement_third_tv33, item.getStatus());
                helper.setVisible(R.id.statement_third_tv33, TextUtils.isEmpty(item.getStatus())?false:true);
                helper.setText(R.id.statement_third_tv4, item.getAmount());
                helper.setText(R.id.statement_third_tv5, item.getValidAmount());
                if (item.getAmount().startsWith("-")){
                    helper.setTextColor(R.id.statement_third_tv4, Color.RED);
                }else {
                    helper.setTextColor(R.id.statement_third_tv4, Color.BLUE);
                }
                if (item.getValidAmount().startsWith("-")){
                    helper.setTextColor(R.id.statement_third_tv5, Color.RED);
                }else {
                    helper.setTextColor(R.id.statement_third_tv5, Color.BLUE);
                }
                helper.setText(R.id.statement_third_tv6, item.getWinLose());
                helper.setText(R.id.statement_third_tv7, item.getCom());
            }
        });
        listContent.setItemClick(new ItemCLickImp<BetStateThirdBean>() {
            @Override
            public void itemCLick(View view, BetStateThirdBean betStateThirdBean, int position) {

                if (betStateThirdBean.getParUrl()!=null&&!betStateThirdBean.getParUrl().equals("")) {
                    Bundle bundle = new Bundle();
                    BetOrderBean.DicAllEntity dicBean=new BetOrderBean.DicAllEntity();
                    dicBean.setPar_Url(betStateThirdBean.getParUrl());
                    bundle.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, dicBean);
                    AppTool.activiyJump(mContext, BetParDetailsActivity.class, bundle);
                }
            }
        });
    }

    private void setThirdListData(List<BetStateThirdBean> result) {
        listContent.setData(result);
    }
}
