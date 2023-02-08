package nanyang.com.dig88.Table;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BetStateFirstBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2015/12/3.
 */
public class BetSettlementFirstActivity extends GameBaseActivity {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    private AdapterViewContent<BetStateFirstBean> listContent;
    private TableHttpHelper<List<BetStateFirstBean>> helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }
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
        setTitle(getString(R.string.statement));
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        helper = new TableHttpHelper<>(mContext, listContentPtrlv, new ThreadEndT<List<BetStateFirstBean>>(new TypeToken<List<BetStateFirstBean>>(){}.getType()) {
            @Override
            public void endT(List<BetStateFirstBean> result,int model) {
                dismissBlockDialog();
                if(result!=null) {
                   setFirstListData(result);
                }
            }

            @Override
            public void endString(String result,int model) {
                try {
                    Log.w("Test", result);
                    String url = result.substring(result.indexOf("\"") + 1, result.lastIndexOf("\""));
                    if (url != null && url.startsWith("http:")) {
                        url = url.replace("&amp;", "&");
                        helper.getData(url, "", TableDataHelper.ModelType.Running);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    endError(e.getMessage());
                }
            }

            @Override
            public void endError(String error) {
                dismissBlockDialog();
                Toast.makeText(mContext,R.string.load_fail,Toast.LENGTH_SHORT).show();
            }
        });
        setDialog(new BlockDialog(mContext,getString(R.string.loading)));
        showBlockDialog();
        helper.updateData(WebSiteUrl.SportUrl+"_norm/AccHistory_App.aspx","",true,false,0);
    }

    private void initAdapter() {
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        listContent=new AdapterViewContent<BetStateFirstBean>(mContext,listContentPtrlv.getRefreshableView());
        listContent.setBaseAdapter(new QuickAdapterImp<BetStateFirstBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_statement_first_floor;
            }

            @Override
            public void convert(ViewHolder helper, final BetStateFirstBean item, int position) {
                if(position==0){
                    helper.setVisible(R.id.statement_first_key_ll,true);
                }else {
                    helper.setVisible(R.id.statement_first_key_ll, false);
                }
                if (position==listContent.getDataCount()-1){
                    helper.setVisible(R.id.footer_line1,true);
                    helper.setVisible(R.id.footer_line2,true);
                }else {
                    helper.setVisible(R.id.footer_line1,false);
                    helper.setVisible(R.id.footer_line2,false);
                }
                helper.setText(R.id.statement_first_value_data_tv,item.getDate());
                helper.setText(R.id.statement_first_value_betting_state_tv,item.getStake());
                helper.setText(R.id.statement_first_value_effect_amount_tv,item.getValidAmount());
                helper.setText(R.id.statement_first_value_win_lose_tv,item.getWL());
                helper.setText(R.id.statement_first_value_commission_tv,item.getCom());
                if(item.getStake()!=null&&!item.getStake().equals("0")&&item.getParamsUrl()!=null&&!item.getParamsUrl().equals("")) {
                    helper.setTextColor(R.id.statement_first_value_betting_state_tv, Color.BLUE);
                    helper.setClickLisenter(R.id.statement_first_value_betting_state_tv, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(AppConfig.ACTION_KEY_INITENT_STRING, item.getParamsUrl());
                            AppTool.activiyJump(mContext, BetSettlementSecondActivity.class, bundle);
                        }
                    });
                }
                else{

                }
            }
        });
    }

    private void setFirstListData(List<BetStateFirstBean> result) {
        listContent.setData(result);
    }
}
