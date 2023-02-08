package nanyang.com.dig88.Table;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindColor;
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
import nanyang.com.dig88.Table.entity.BetStatesecondBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/3.
 */
public class BetSettlementSecondActivity extends GameBaseActivity {

    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindColor(R.color.red_pink_word)
    int redWord;
    @BindColor(R.color.black_grey)
    int black_grey;
    private AdapterViewContent<BetStatesecondBean> listContent;
    private TableHttpHelper<List<BetStatesecondBean>> helper;
    private  int blueWord= 0Xff4285f4;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_list_content;
    }
/*Statement：
第一层:
http://mobilesport.dig88api.com/_norm/AccHistory_App.aspx
*/
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        String url=getIntent().getStringExtra(AppConfig.ACTION_KEY_INITENT_STRING);
        setTitle(getString(R.string.event));
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        initAdapter();
        helper = new TableHttpHelper<>(mContext, listContentPtrlv, new ThreadEndT<List<BetStatesecondBean>>(new TypeToken<List<BetStatesecondBean>>(){}.getType()) {
            @Override
            public void endT(List<BetStatesecondBean> result,int model) {
                if(result!=null) {
                   setsecondListData(result);
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
        helper.getData(WebSiteUrl.SportUrl+"_norm/"+url,"", TableDataHelper.ModelType.Running);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    private void initAdapter() {
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        listContent=new AdapterViewContent<BetStatesecondBean>(mContext,listContentPtrlv.getRefreshableView());
        listContent.setBaseAdapter(new QuickAdapterImp<BetStatesecondBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_statement_second_floor;
            }

            @Override
            public void convert(ViewHolder helper, final BetStatesecondBean item, int position) {
                if (position == 0) {
                    helper.setVisible(R.id.statement_second_key_ll, true);
                } else {
                    helper.setVisible(R.id.statement_second_key_ll, false);
                }
                if (position==listContent.getDataCount()-1){
                    helper.setVisible(R.id.footer_line1,true);
                    helper.setVisible(R.id.footer_line2,true);
                }else {
                    helper.setVisible(R.id.footer_line1,false);
                    helper.setVisible(R.id.footer_line2,false);
                }
                helper.setText(R.id.statement_second_value_data_tv, item.getDate());
                TextView tv = (TextView) helper.getView().findViewById(R.id.statement_second_value_betting_state_tv);
                tv.setText(handleStringColor(item.getEvent(), Color.BLUE));
                helper.setText(R.id.statement_second_value_effect_amount_tv, item.getAmount());
                if(item.getWinLose().startsWith("-")){
                    helper.setTextColor(R.id.statement_second_value_win_lose_tv,redWord);
                }else{
                    helper.setTextColor(R.id.statement_second_value_win_lose_tv,black_grey);
                }
                helper.setText(R.id.statement_second_value_win_lose_tv, item.getWinLose());
                helper.setText(R.id.statement_second_value_commission_tv, item.getCom());
                if (item.getParamsUrl()!=null&&!item.getParamsUrl().equals("")) {
                    helper.setClickLisenter(R.id.statement_second_value_betting_state_tv, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(AppConfig.ACTION_KEY_INITENT_STRING, item.getParamsUrl());
                            AppTool.activiyJump(mContext, BetSettlementThirdActivity.class, bundle);
                        }
                    });
                }
            }
        });
    }

    private void setsecondListData(List<BetStatesecondBean> result) {
        listContent.setData(result);
    }
    public  SpannableStringBuilder handleStringColor(String str, int color) {
        if (TextUtils.isEmpty(str)){
            return null;
        }
        int bstart = str.indexOf("VS");
        int bend = bstart + "VS".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), 0, bstart, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(color), bend, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }
}
