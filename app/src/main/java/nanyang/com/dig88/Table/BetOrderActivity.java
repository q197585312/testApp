package nanyang.com.dig88.Table;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import butterknife.Bind;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BetOrderBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.ItemCLickImp;
import xs.com.mylibrary.base.QuickAdapterImp;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Administrator on 2015/12/3.
 */
public class BetOrderActivity extends GameBaseActivity {
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_list_content;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        TableHttpHelper<BetOrderBean> helper = new TableHttpHelper<>(mContext, listContentPtrlv, new ThreadEndT<BetOrderBean>(new TypeToken<BetOrderBean>(){}.getType()) {
            @Override
            public void endT(BetOrderBean result,int model) {
                if(result!=null) {
                    Log.w("HtttpVolley", result.toString());
                    result.getDicAll();
                    parseList(result);
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
        String langParams="&lang=EN-US";

        String lang=AppTool.getAppLanguage(mContext);
        if(lang!=null&&lang.equals("zh")){
            langParams="&lang=ZH-CN";
        }
        //"http://mobilesport.dig88api.com/_bet/PanelStake_App.aspx?&t=1448608200999"
        helper.getData(WebSiteUrl.BetOrderPanelStake+langParams,"", TableDataHelper.ModelType.Running);
    }

    private void parseList(BetOrderBean result) {
        AdapterViewContent<BetOrderBean.DicAllEntity> listContent=new AdapterViewContent<BetOrderBean.DicAllEntity>(mContext,listContentPtrlv.getRefreshableView());
        listContent.setBaseAdapter(new QuickAdapterImp<BetOrderBean.DicAllEntity>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_order_content;
            }

            @Override
            public void convert(ViewHolder helper, BetOrderBean.DicAllEntity item, int position) {
                helper.setText(R.id.order_item_tv1,item.getRefNo()+"("+item.getTransDate()+")");

                if(item.getGameType().equals("PARLAY")||item.getGameType().equals("过关")){
                    helper.setText(R.id.order_item_tv2,item.getHome()+item.getPar_sign());
                }else{
                    helper.setText(R.id.order_item_tv2,item.getHome()+"  vs  "+item.getAway());
                }
                String odds=item.getOdds();
                if(item.getBetType()!=null) {
//                    obj.oBetType stringByReplacingOccurrencesOfString:@"&nbsp" withString:@" "]
                    if (item.getTransType().equals("OU") || item.getTransType().equals("HDP")) {
                        odds = item.getBetType().replace("&nbsp"," ") + "(" + item.getHdp() + "@" + item.getRunHomeScore() + "-" + item.getRunAwayScore() + ")  " + item.getOdds();
                    } else {
                        odds = item.getBetType().replace("&nbsp"," ") + " " + item.getOdds();
                    }
                }
                helper.setText(R.id.order_item_tv3,odds);
                helper.setText(R.id.order_item_tv4,item.getModuleTitle());
                String half="";
                if(item.getFullTimeId()>0)
                    half="(First Half)";
                helper.setText(R.id.order_item_tv5,item.getGameType()+half);
                String n=mContext.getString(R.string.accepted);
                if(item.getDangerStatus().equals("D")) {
                    n = mContext.getString(R.string.waiting);
                }
                else if(item.getDangerStatus().equals("R")) {
                    n = mContext.getString(R.string.rejected)+" " + item.getR_DateTime();
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }
                else if(item.getDangerStatus().equals("RR")){
                    n=mContext.getString(R.string.rejected)+" (Red Card "+item.getR_DateTime()+")";
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }else if(item.getDangerStatus().equals("RP")){
                    n=mContext.getString(R.string.rejected)+" (Goal Disallowed "+item.getR_DateTime()+")";
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }else if(item.getDangerStatus().equals("RA")){
                    n=mContext.getString(R.string.rejected)+" (Abnormal Bet "+item.getR_DateTime()+")";
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }else if(item.getDangerStatus().equals("RG")){
                    n=mContext.getString(R.string.rejected)+" (Goal "+item.getR_DateTime()+")";
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }
                else if(item.getDangerStatus().equals("0")){
                    n="Oddschange";
                    helper.setBackgroundRes(R.id.order_item_tv51, R.drawable.rectangle_red);
                }
                helper.setText(R.id.order_item_tv51, n);
                helper.setText(R.id.order_item_tv52, item.getAmt());
            }
        });
        listContent.setData(result.getDicAll());
        listContent.setItemClick(new ItemCLickImp<BetOrderBean.DicAllEntity>() {
            @Override
            public void itemCLick(View view, BetOrderBean.DicAllEntity dicAllEntity, int position) {
                if (dicAllEntity.getTransType().equals("PAR")) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, dicAllEntity);
                    AppTool.activiyJump(mContext, BetParDetailsActivity.class, bundle);
                }
            }
        });
    }
}
