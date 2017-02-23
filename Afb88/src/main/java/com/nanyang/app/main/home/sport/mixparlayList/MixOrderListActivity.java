package com.nanyang.app.main.home.sport.mixparlayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.nanyang.app.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.BindString;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MixOrderListActivity extends BaseToolbarActivity{

    @Bind(R.id.slideCutListView)
    DelSlideListView slideCutListView;
    @Bind(R.id.clearance_bottom_fsvlst)
    FSVListView fsvlst;
    @BindString(R.string.loading)
    String loading;
    private AdapterViewContent<BettingInfoBean> contentList;
    private HashMap<LeagueBean, Boolean> selecteds = new HashMap<>();
    private boolean editable;
    private BettingParPromptBean datas;
    private Map<Boolean, Integer> selectedMap = new HashMap<>();
    AdapterViewContent<ClearanceBetAmountBean> bottomContent;
    private boolean shouldShow;
    private ClearanceBetAmountBean selectedBean;
    private TableHttpHelper<Object> betHttpHelper;
    private TextView headOddsEdt;
    private ImageView moreIv;
    private TextView headAmountTv;
    private String betUrl;
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> datasMap;
    List<BettingInfoBean> betInfos;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_clearance_bet;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        rightTv.setText(R.string.edit);
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectedModle();
            }
        });
        datas = getApp().getBetParList();
        datasMap=getApp().getBetDetail();
        initBottomListData();
        initListData();
    }

    private void delectedModle() {
        editable = !editable;
        contentList.notifyDataSetChanged();
        slideCutListView.reset(true);
    }

    private void initBottomListData() {
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_clearance_bet_bottom, null);
        View footer = LayoutInflater.from(mContext).inflate(R.layout.footer_clearance_bet_bottom, null);
        View selectLl = header.findViewById(R.id.header_clearance_select_ll);
        //header
        headAmountTv = (TextView) header.findViewById(R.id.header_clearance_amount_tv);
        headOddsEdt = (TextView) header.findViewById(R.id.header_clearance_odds_edt);
        moreIv = (ImageView) header.findViewById(R.id.header_clearance_more_mark_iv);
        //footer
        final TextView footerCountTv = (TextView) footer.findViewById(R.id.footer_clearance_count_tv);
        TextView footerContentTv = (TextView) footer.findViewById(R.id.footer_clearance_content_tv);
        Button footerBetTv = (Button) footer.findViewById(R.id.footer_clearance_bet_submit_btn);
        Button footerCancelbtn = (Button) footer.findViewById(R.id.footer_clearance_bet_cancel_btn);
        selectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectedList();
            }
        });
        footerBetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitParBet(v);
            }
        });
        footerCancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBet(v);
            }
        });
        fsvlst.addFooterView(footer, null, false);
        fsvlst.addHeaderView(header, null, false);

        footerCountTv.setText(getString(R.string.odds) + datas.getPayoutOdds() + "");
        footerContentTv.setText(getString(R.string.max_bet) + datas.getMaxLimit() + "    " + getString(R.string.min_bet) + datas.getMinLimit());
        selectedMap.put(true, 0);
        bottomContent = new AdapterViewContent<>(mContext, fsvlst);
        bottomContent.setBaseAdapter(new QuickAdapterImp<ClearanceBetAmountBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_selectable_text;
            }

            @Override
            public void convert(ViewHolder helper, ClearanceBetAmountBean item, int position) {
                helper.setText(R.id.selectable_text_content_tv, item.getTitle());
                TextView contentTv = helper.retrieveView(R.id.selectable_text_content_tv);
                if (position == selectedMap.get(true)) {
                    footerCountTv.setText(item.getTitle());
                    contentTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hook_red_small, 0);
                } else {
                    contentTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });
        bottomContent.setItemClick(new ItemCLickImp<ClearanceBetAmountBean>() {
            @Override
            public void itemCLick(View view, ClearanceBetAmountBean t, int position) {
                position = position - 1;
                selectedMap.put(true, position);
                headAmountTv.setText(t.getTitle());
                bottomContent.notifyDataSetChanged();
                selectedBean=t;
            }
        });
        showSelectedList();
    }

    private void cancelBet(View v) {
        if(betInfos!=null&& betInfos.size()>0) {
            int i =0;
            for (BettingInfoBean item : betInfos) {
                removeBetItem(v,i,item,false);
                i++;
            }
        }
    }

    private void submitParBet(View v) {
        setDialog(new BlockDialog(mContext, loading));
        String amt = headOddsEdt.getText().toString().trim();
        if (StringUtils.isNull(mContext, amt, getString(R.string.input_bet_amount_please)))
            return;
        if (datas == null || datas.getBetPar().size() < 3) {
            Toast.makeText(mContext, getString(R.string.clearance_should_be_more_than_three), Toast.LENGTH_SHORT).show();
            return;
        }
        int count=Integer.valueOf(amt);
        if(!datas.getMaxLimit().equals("")&&!datas.getMaxLimit().equals("0")&&!datas.getMinLimit().equals("")) {
            int max = Integer.valueOf(datas.getMaxLimit());
            int min = Integer.valueOf(datas.getMinLimit());
            int amount= Integer.valueOf(amt);
            if (amount<min||amount>max){
                Toast.makeText(mContext,getString(R.string.invalid_amount_bet),Toast.LENGTH_SHORT).show();
                headOddsEdt.setText("");
                return;
            }
        }
        betHttpHelper = new TableHttpHelper<>(mContext, null, new ThreadEndT<Object>(null) {

            @Override
            public void endT(Object result,int model) {
                if (result != null) {

                    CheckEnd(result.toString());
                }
                dismissBlockDialog();
            }

            @Override
            public void endString(String result,int model) {
                dismissBlockDialog();
            }

            @Override
            public void endError(String error) {
                dismissBlockDialog();
                CheckEnd(error);
            }
        });
        showBlockDialog();
        betUrl= WebSiteUrl.SportUrl + "_bet/" + datas.getBetUrl() + "&amt=" + amt + "&coupon=" + selectedBean.getAmount() + "&exRate=" + datas.getExRate();
        betHttpHelper.getData(betUrl, "", TableDataHelper.ModelType.Running);
    }

    private void CheckEnd(String result) {
        if(result==null){
            Toast.makeText(mContext,getString(R.string.xiazhucuowu), Toast.LENGTH_SHORT).show();
            return;
        }

        if ( result.startsWith("Parlay")||result.contains("r=")) {
            Toast.makeText(mContext, getString(R.string.xiazhusuccess), Toast.LENGTH_SHORT).show();
            getApp().setBetParList(null);
            getApp().setBetDetail(null);
            finish();
        } else if(result.startsWith("MULTIBET")){
            getApp().setBetParList(null);
            getApp().setBetDetail(null);
            finish();
        }else if(result.startsWith("PARCHG")) {
            BaseYseNoChoosePopupwindow pop=new BaseYseNoChoosePopupwindow(mContext,slideCutListView) {
                @Override
                protected void clickSure(View v) {
                    showBlockDialog();
                    betHttpHelper.getData(betUrl, "", TableDataHelper.ModelType.Running);
                }
            };
            pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
            pop.getChooseMessage().setText(result+" "+getString(R.string.do_you_bet_again));
            pop.getChooseSureTv().setText(getString(R.string.sure));
            pop.getChooseCancelTv().setText(getString(R.string.cancel));
            pop.showPopupCenterWindow();
        }else{
            Toast.makeText(mContext,result, Toast.LENGTH_SHORT).show();
        }
    }

    private void changeSelectedList() {
        shouldShow = !shouldShow;
        showSelectedList();
    }

    private void showSelectedList() {
        if(datas==null)
            return;
        moreIv.setVisibility(View.VISIBLE);
        if (datas!=null&&datas.getBetPar()!=null&&datas.getBetPar().size() < 8 && datas.getBetPar().size() > 2) {
            if(selectedBean==null||selectedBean.getTitle().equals(""))
                selectedBean = new ClearanceBetAmountBean(1, datas.getBetPar().size() + "  X  1");
        } else {
            selectedBean = new ClearanceBetAmountBean(1, "");
        }
        if (shouldShow) {
            moreIv.setImageResource(R.drawable.oval_blue_light_arrow_up);
            int size = datas.getBetPar().size();
            if (size == 3) {
                bottomContent.setData(Arrays.asList(new ClearanceBetAmountBean(1, "3  X  1"), new ClearanceBetAmountBean(3, "3  X  3"), new ClearanceBetAmountBean(4, "3  X  4")));
            } else if (size == 4) {
                bottomContent.setData(Arrays.asList(new ClearanceBetAmountBean(1, "4  X  1"), new ClearanceBetAmountBean(4, "4  X  4"), new ClearanceBetAmountBean(5, "4  X  5"), new ClearanceBetAmountBean(6, "4  X  6")));
            } else if (size == 5) {
                bottomContent.setData(Arrays.asList(new ClearanceBetAmountBean(1, "5  X  1"), new ClearanceBetAmountBean(5, "5  X  5"), new ClearanceBetAmountBean(6, "5  X  6"), new ClearanceBetAmountBean(10, "5  X  10")
                        , new ClearanceBetAmountBean(16, "5  X  16"), new ClearanceBetAmountBean(20, "5  X  20"), new ClearanceBetAmountBean(26, "5  X  26")));
            } else if (size == 6) {
                bottomContent.setData(Arrays.asList(new ClearanceBetAmountBean(1, "6  X  1"), new ClearanceBetAmountBean(6, "6  X  6"), new ClearanceBetAmountBean(7, "6  X  7"), new ClearanceBetAmountBean(15, "6  X  15"),
                        new ClearanceBetAmountBean(20, "6  X  20"), new ClearanceBetAmountBean(35, "6  X  35"), new ClearanceBetAmountBean(42, "6  X  42"), new ClearanceBetAmountBean(50, "6  X  50"), new ClearanceBetAmountBean(57, "6  X  57")));
            } else if (size == 7) {
                bottomContent.setData(Arrays.asList(new ClearanceBetAmountBean(1, "7  X  1"), new ClearanceBetAmountBean(7, "7  X  7"), new ClearanceBetAmountBean(8, "7  X  8"), new ClearanceBetAmountBean(21, "7  X  21"), new ClearanceBetAmountBean(28, "7  X  28"),
                        new ClearanceBetAmountBean(29, "7  X  29"), new ClearanceBetAmountBean(35, "7  X  35"), new ClearanceBetAmountBean(56, "7  X  56"), new ClearanceBetAmountBean(70, "7  X  70"), new ClearanceBetAmountBean(91, "7  X  91"),
                        new ClearanceBetAmountBean(98, "7  X  98"), new ClearanceBetAmountBean(99, "7  X  99"), new ClearanceBetAmountBean(112, "7  X  112"), new ClearanceBetAmountBean(119, "7  X  119"), new ClearanceBetAmountBean(120, "7  X  120")));
            } else {
                bottomContent.setData(new ArrayList<ClearanceBetAmountBean>());
                selectedBean = new ClearanceBetAmountBean(1, "");
                moreIv.setVisibility(View.GONE);
            }
        } else {
            bottomContent.setData(new ArrayList<ClearanceBetAmountBean>());
            moreIv.setImageResource(R.drawable.oval_blue_light_arrow_down);
            if (datas==null||datas.getBetPar()==null||datas.getBetPar().size() < 3) {
                moreIv.setVisibility(View.GONE);
            }
            headAmountTv.setText(selectedBean.getTitle());
        }

    }


    private void initListData() {
        if (datas != null) {
            contentList = new AdapterViewContent<BettingInfoBean>(mContext, slideCutListView);
            contentList.setBaseAdapter(new QuickAdapterImp<BettingInfoBean>() {
                @Override
                public int getBaseItemResource() {
                    return R.layout.item_clearance_content;
                }

                @Override
                public void convert(ViewHolder helper, final BettingInfoBean item, final int position) {
                    helper.setText(R.id.clearance_type_tv, getString(R.string.football));
                    if(item.getIsFH()==1){
                        helper.setText(R.id.clearance_type_tv, getString(R.string.football)+"("+getString(R.string.half_time)+")");
                    }
                    helper.setText(R.id.clearance_home_tv, item.getHome());
                    helper.setText(R.id.clearance_away_tv, item.getAway());
                    setOddsText(helper, item);
                    helper.setVisible(R.id.selectable_left_deleted_iv, editable);
                    helper.setClickLisenter(R.id.selectable_left_deleted_iv, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeBetItem(v, position, item,true);

                        }
                    });
                    helper.setClickLisenter(R.id.delete_action, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeBetItem(v, position, item,true);
                        }
                    });
                }
            });
            Iterator<Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>>> it = datasMap.entrySet().iterator();
            betInfos=new ArrayList<>();
            while (it.hasNext()){
                Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>> keyItem = it.next();
                Iterator<Map.Entry<String, Map<Integer, BettingInfoBean>>> itt = keyItem.getValue().entrySet().iterator();
                while (itt.hasNext()){
                    Iterator<Map.Entry<Integer, BettingInfoBean>> ittt = itt.next().getValue().entrySet().iterator();
                    while (ittt.hasNext()){
                        BettingInfoBean item = ittt.next().getValue();
                        if(item!=null){
                            betInfos.add(item);
                        }
                    }
                }
            }
            contentList.setData(betInfos);
        }
    }

    protected void removeBetItem(View v, final int position, final BettingInfoBean item, final boolean shouldRemove) {
        setDialog(new BlockDialog(mContext, getResources().getString(R.string.loading)));
        final TableHttpHelper<BettingParPromptBean> deleteHttpHelper = new TableHttpHelper<>(mContext,null, new ThreadEndT<BettingParPromptBean>(new TypeToken<BettingParPromptBean>() {
        }.getType()) {
            @Override
            public void endT(BettingParPromptBean result,int model) {
                dismissBlockDialog();
                datas = result;
                datasMap.remove(item.getHome() + "+" + item.getAway());
                getApp().setBetParList(result);
                if(shouldRemove) {
                    contentList.removeItem(position);
                    showSelectedList();
                }
                else if(result==null||result.getBetPar()==null||result.getBetPar().size()<1){
                    getApp().setBetDetail(null);
                    getApp().setBetParList(result);
                    finish();

                }
            }

            @Override
            public void endString(String result,int model) {

            }

            @Override
            public void endError(String error) {
                dismissBlockDialog();

            }
        });
        showBlockDialog();
        String ParUrl="";
        if(datas==null){
            dismissBlockDialog();
            return;
        }
        for(BettingParPromptBean.BetParBean aitem:datas.getBetPar()){
            if(item.getHome().equals(aitem.getHome())&&item.getAway().equals(aitem.getAway())){
                ParUrl=aitem.getParUrl();
                break;
            }
        }
        if(!ParUrl.equals("")) {
            if(item.getIsFH()==0)
                deleteHttpHelper.getData(ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId(), "", 0);
            else{
                deleteHttpHelper.getData(ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId_FH(), "", 0);
            }
        }
        else{
            dismissBlockDialog();
        }
    }

    public void setOddsText(ViewHolder helper, BettingInfoBean item) {
        String hdp = "";
        String b = item.getB();
        String sc = item.getSc();
        String state = "";
        if (b.equals("1"))
            state = item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if(b.equals("1_par"))
            state = "1X2:"+item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("2"))
            state = item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        else if(b.equals("2_par")){
            state ="1X2:"+ item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        }
        else if (b.equals("x") || b.equals("X"))
            state = item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        else if(b.equals("X_par")){
            state = "1X2:"+ item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        }
        else if (b.contains("odd")) {
            state = "OE:"+mContext.getString(R.string.odd);
        } else if (b.contains("even")) {
            state =  "OE:"+mContext.getString(R.string.even);

        } else if (b.equals("csr")) {
            if (sc != null) {
                if (sc.length() == 1) {
                    sc = "0" + sc;
                }
                char s1 = sc.charAt(0);
                char s2 = sc.charAt(1);
                hdp = s1 + "-" + s2;
            }
        } else if (b.equals("dc")) {
            state = getString(R.string.double_chance);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = mContext.getString(R.string.x1);
                else if (sc.equals("12"))
                    hdp = mContext.getString(R.string.x12);
                else if (sc.equals("2"))
                    hdp = mContext.getString(R.string.x2);
            }
        } else if (b.equals("htft")) {
            state = getString(R.string.half_full_time);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = "HD";
                else if (sc.equals("12"))
                    hdp = "HA";
                else if (sc.equals("2"))
                    hdp = "DA";
                else if (sc.equals("0"))
                    hdp = "DD";
                else if (sc.equals("1"))
                    hdp = "DH";
                else if (sc.equals("11"))
                    hdp = "HH";
                else if (sc.equals("20"))
                    hdp = "AD";
                else if (sc.equals("21"))
                    hdp = "AH";
                else if (sc.equals("22"))
                    hdp = "AA";
            }
        } else if (b.equals("fglg")) {
            state = getString(R.string.first_last_goal);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = item.getHome() + "(" + getString(R.string.first_goal) + ")";
                else if (sc.equals("1"))
                    hdp = item.getHome() + "(" + getString(R.string.last_goal) + ")";
                else if (sc.equals("20"))
                    hdp = item.getAway() + "(" + getString(R.string.first_goal) + ")";
                else if (sc.equals("2"))
                    hdp = item.getAway() + "(" + getString(R.string.last_goal) + ")";
                else if (sc.equals("0"))
                    hdp = getString(R.string.no_goal);
            }
        } else if (b.equals("tg")) {
            state = getString(R.string.total_goals);
            if (sc != null) {
                if (sc.equals("1"))
                    hdp = "0-1";
                else if (sc.equals("23"))
                    hdp = "2-3";
                else if (sc.equals("46"))
                    hdp = "4-6";
                else if (sc.equals("70"))
                    hdp = "7&Over";
            }
        } else if (b.equals("away")) {
            state = "HDP:"+item.getAway();
        } else if (b.equals("home")) {
            state = "HDP:"+item.getHome();
        } else if (b.equals("under")) {
            state = "OU:"+getString(R.string.under);
        } else if (b.equals("over")) {
            state = "OU:" + getString(R.string.over);
        }
        else {
            state=item.getB();
        }
        if (item.getHdp() != null) {
            if ((item.isHomeGiven() && b.equals("home")) || (!item.isHomeGiven() && b.equals("away")))
                hdp = "-" + item.getHdp();
            else
                hdp = item.getHdp();
        }
        if(item.getIsFH()==1){
            state=state+"("+getString(R.string.half_time)+")";
        }
        helper.setText(R.id.clearance_odds_content_tv, state + "  " + hdp + "@" + item.getOdds());
    }
}
