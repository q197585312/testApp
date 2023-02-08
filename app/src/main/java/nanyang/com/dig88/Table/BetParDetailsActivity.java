package nanyang.com.dig88.Table;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BetOrderBean;
import nanyang.com.dig88.Table.entity.BetParDetalsBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/29.
 */
public class BetParDetailsActivity extends GameBaseActivity {
    BetOrderBean.DicAllEntity orderInfo;
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    private AdapterViewContent<BetParDetalsBean.DetailBean> contentAdapter;
    private TextView header1;
    private TextView header2;
    private TextView header3;
    private TextView footer1;
    private TextView footer2;
    private TextView footer3;
    private View footerLine;
    private View headLine;
    private TextView footerTitle2;
    private TextView footerTitle1;
    private TextView footerTitle3;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_list_content;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initContentAdapter();
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        orderInfo = (BetOrderBean.DicAllEntity) getIntent().getSerializableExtra(AppConfig.ACTION_KEY_INITENT_DATA);
        if (orderInfo == null || orderInfo.getPar_Url().equals("")) {
            return;
        }
        TableHttpHelper<BetParDetalsBean> helper = new TableHttpHelper<>(mContext, listContentPtrlv, new ThreadEndT<BetParDetalsBean>(new TypeToken<BetParDetalsBean>(){}.getType()) {

            @Override
            public void endT(BetParDetalsBean result,int model) {
                header1.setText(":"+result.getRefNo());
                header2.setText(":"+result.getDateTime());
                header3.setText(":"+result.getGameType2());
                footerTitle1.setText(getString(R.string.bet_amount));
                footerTitle2.setText(getString(R.string.odds));
                footerTitle3.setText(getString(R.string.est_payout));
                footer1.setText(":"+result.getAmount());
                footer2.setText(":"+result.getOddsCnt());
                footer3.setText(":"+result.getPayout());
                contentAdapter.setData(result.getDetail());
            }

            @Override
            public void endString(String result,int model) {

            }

            @Override
            public void endError(String error) {
                Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
            }
        });
        helper.getData(WebSiteUrl.SportUrl + orderInfo.getPar_Url(), "", TableDataHelper.ModelType.Running);
    }

    private void initContentAdapter() {
        View header=LayoutInflater.from(mContext).inflate(R.layout.footer_betpar_details,null);
        View footer=LayoutInflater.from(mContext).inflate(R.layout.footer_betpar_details,null);
        header1=(TextView)header.findViewById(R.id.footer_betpar_details_tv1);
        header2=(TextView)header.findViewById(R.id.footer_betpar_details_tv2);
        header3=(TextView)header.findViewById(R.id.footer_betpar_details_tv3);

        footerTitle1=(TextView)footer.findViewById(R.id.betpar_title_footer_tv1);
        footerTitle2=(TextView)footer.findViewById(R.id.betpar_title_footer_tv2);
        footerTitle3=(TextView)footer.findViewById(R.id.betpar_title_footer_tv3);
        footerLine=footer.findViewById(R.id.footer_betpar_details_line_ll2);
        footer1=(TextView)footer.findViewById(R.id.footer_betpar_details_tv1);
        footer2=(TextView)footer.findViewById(R.id.footer_betpar_details_tv2);
        footer3=(TextView)footer.findViewById(R.id.footer_betpar_details_tv3);
        footerLine=footer.findViewById(R.id.footer_betpar_details_line_ll2);
        headLine=header.findViewById(R.id.footer_betpar_details_line_ll1);
        footerLine.setVisibility(View.GONE);
        headLine.setVisibility(View.GONE);
        listContentPtrlv.getRefreshableView().addFooterView(footer,null,false);
        listContentPtrlv.getRefreshableView().addHeaderView(header,null,false);

        contentAdapter=new AdapterViewContent<BetParDetalsBean.DetailBean>(mContext,listContentPtrlv.getRefreshableView());
        contentAdapter.setBaseAdapter(new QuickAdapterImp<BetParDetalsBean.DetailBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_betpar_details;
            }

            @Override
            public void convert(ViewHolder helper, BetParDetalsBean.DetailBean item, int position) {
                helper.setText(R.id.betpar_details_module_tv,item.getModuleTitle());
                helper.setText(R.id.betpar_details_vs_tv,item.getHome()+"  VS  "+item.getAway());
                helper.setText(R.id.betpar_details_date_time_tv,item.getMatchDate());
                helper.setText(R.id.betpar_details_result_tv,getString(R.string.jieguo)+":"+item.getHomeScore()+"-"+item.getAwayScore());
                helper.setText(R.id.betpar_details_type_odds_tv,getString(R.string.leixing)+item.getGameType()+"  "+getString(R.string.odds)+":"+item.getOdds());
                String firstHalf="";
                if(!item.getIsFH().equals("")){
                    firstHalf="("+item.getIsFH()+")";
                }
                helper.setText(R.id.betpar_details_bet_tv,item.getBetType()+firstHalf);
                String status="";
                if(!item.getStatus().equals("")){
                    status=" ("+item.getStatus()+")";
                }
                helper.setText(R.id.betpar_details_state_odds_tv,item.getHdpOdds()+"@"+item.getOdds()+status);
            }
        });
    }

}
