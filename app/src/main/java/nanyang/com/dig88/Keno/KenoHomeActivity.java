package nanyang.com.dig88.Keno;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.TimeUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import gaming178.com.mylibrary.popupwindow.AbsListPopupWindow;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.Entity.ResultListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2016/6/7.
 */
public class KenoHomeActivity extends GameBaseActivity {
    public List<String[]> totalListStrings;
    protected NyVolleyJsonThreadHandler<List<DigGameOddsBean>> oddThread;
    KenoHomeFragment fHome=new KenoHomeFragment();
    KenoDetailsFragment fDetail=new KenoDetailsFragment();
    KenoBaseFragment indexFragment;
    private AbsListPopupWindow<LotteryStateGameBean> rightMenuPop;
    private FragmentManager manager;
    private NyVolleyJsonThreadHandler<List<LotteryStateGameBean>> stateThread;
    private NyVolleyJsonThreadHandler<List<ResultListBean>> resultListThread;
    private List<DigGameOddsBean> oddslist;
    private LotteryStateGameBean selectedStateBean;
    private ArrayList<DigGameOddsBean> selectedOddsList;
    private CountDownTimer timer;
    private ArrayList<DigGameOddsBean> pearlBallList;
    private DigGameOddsBean bigSmallBean;
    private DigGameOddsBean evenOddBean;
    private DigGameOddsBean combinationBean;
    private DigGameOddsBean elementBean;
    private DigGameOddsBean upDownBean;
    private DigGameOddsBean evensOddsBean;
    private DigGameOddsBean mixParlayBean;
    private String[] resultStrings;
    private ArrayList<KenoHeaderResultBean> headerList;

    public List<String[]> getTotalListStrings() {
        return totalListStrings;
    }

    public String[] getResultStrings() {
        return resultStrings;
    }

    public ArrayList<KenoHeaderResultBean> getHeaderList() {
        return headerList;
    }

    public List<DigGameOddsBean> getOddslist() {
        return oddslist;
    }

    public ArrayList<DigGameOddsBean> getSelectedOddsList() {
        return selectedOddsList;
    }

    public LotteryStateGameBean getSelectedStateBean() {
        return selectedStateBean;
    }

    public DigGameOddsBean getMixParlayBean() {
        return mixParlayBean;
    }

    public DigGameOddsBean getEvensOddsBean() {
        return evensOddsBean;
    }

    public DigGameOddsBean getUpDownBean() {
        return upDownBean;
    }

    public DigGameOddsBean getElementBean() {
        return elementBean;
    }

    public DigGameOddsBean getCombinationBean() {
        return combinationBean;
    }

    public DigGameOddsBean getEvenOddBean() {
        return evenOddBean;
    }

    public DigGameOddsBean getBigSmallBean() {
        return bigSmallBean;
    }

    public ArrayList<DigGameOddsBean> getPearlBallList() {
        return pearlBallList;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        AppTool.setAppLanguage(mContext,"");
        super.initData(savedInstanceState);
        initRightMenuPop();
        initFragment();
        initOddsList();
        initResultThread();
        initStateThread();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    /**
     * 首先获取赔率列表
     * 然后获取状态
     */
    protected void initOddsList() {
        oddThread = new NyVolleyJsonThreadHandler<List<DigGameOddsBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                //游戏状态appi01.sgtest.dig88api.net/index.php?page=game_set_submitter
                return new QuickRequestBean(WebSiteUrl.GameSetSubmitter, params
                        , new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<DigGameOddsBean> data) {
                if (data == null || data.size() < 1) {
                } else {
                    oddslist = data;
                    stateThread.startThread(null);
                }
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                dismissBlockDialog();
            }

            @Override
            public void startThread(Integer obj) {
                super.startThread(obj);
            }
        };
        showBlockDialog();
        oddThread.startThread(null);

    }
    protected void initResultThread() {
        resultListThread = new NyVolleyJsonThreadHandler<List<ResultListBean>>(mContext) {


            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                params.put("type1", "6");
                params.put("web_id", "0");
                params.put("type2", selectedStateBean.getType2());
                //游戏状态
                return new QuickRequestBean(WebSiteUrl.GetNumberGameResults, params
                        , new TypeToken<NyBaseResponse<List<ResultListBean>>>() {
                }.getType());
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                dismissBlockDialog();
            }

            @Override
            protected void successEndT(int total, List<ResultListBean> data) {
                if (data.size() < 1) {
                    dismissBlockDialog();
                    return;
                }
 /*849|1#1#2#2#3#1#|2#5#7#11#18#29#34#41#42#44#46#51#52#56#57#60#63#73#78#80#
                    Total|bs#oe#oes#ud#5e#co|2#5#7#11#18#29#34#41#42#44#46#51#52#56#57#60#63#73#78#80#*/
                String res = data.get(0).getResult();
                String[] strings = res.split("\\|");
                if (strings != null && strings.length > 2) {
                    List<String[]> totalListStrings = new ArrayList<>();
                    headerList=new ArrayList<>();
                    for (ResultListBean bean : data) {
                        String[] str = bean.getResult().split("\\|");
                        String[] content = str[1].split("\\#");
                        Log.d("Error",str[1]);
                        if(content.length<5) {
                            Log.d("Error",str[1]+"--->"+content.toString());
                            return;
                        }
                        String[] rs=new String[6];
                        if(content[0].equals("1")){
                            rs[0]="B";
                        }else if(content[0].equals("2")){
                            rs[0]="S";
                        }else {
                            rs[0]="T";
                        }
                        if(content[3].equals("1")){
                            rs[1]="U";
                        }else if(content[3].equals("2")){
                            rs[1]="D";
                        }else {
                            rs[1]="T";
                        }
                        if(content[2].equals("1")){
                            rs[2]="O";
                        }else if(content[2].equals("2")){
                            rs[2]="E";
                        }else {
                            rs[2]="T";
                        }
                        if(content[1].equals("1")){
                            rs[3]="O";
                        }else {
                            rs[3]="E";
                        }
                        if(content.length>5) {
                            if (content[5].equals("1")) {
                                rs[4] = "BO";
                            } else if (content[5].equals("2")) {
                                rs[4] = "SO";
                            } else if (content[5].equals("3")) {
                                rs[4] = "BE";
                            } else {
                                rs[4] = "SE";
                            }
                            if (content[4].equals("1")) {
                                rs[5] = "G";
                            } else if (content[4].equals("2") || content[4].equals("3")) {
                                rs[5] = "W";
                            } else if (content[4].equals("4")) {
                                rs[5] = "F";
                            } else {
                                rs[5] = "E";
                            }
                        }else {
                            rs[4] = " ";
                            if (content[4].equals("1")) {
                                rs[5] = "G";
                            } else if (content[4].equals("2") || content[4].equals("3")) {
                                rs[5] = "W";
                            } else if (content[4].equals("4")) {
                                rs[5] = "F";
                            } else {
                                rs[5] = "E";
                            }
                        }
                        String[] split = str[2].split("\\#");
                        totalListStrings.add(rs);

                        KenoHeaderResultBean rb=new KenoHeaderResultBean(bean,str[0],new ArrayList<String>(Arrays.asList(rs[0],rs[1],rs[2],rs[3],rs[5])),new ArrayList<String>(Arrays.asList(split)));
                        headerList.add(rb);
                    }
                    String[] resultStrings = strings[2].split("\\#");
                    if (resultStrings != null && resultStrings.length > 0) {
                        KenoHomeActivity.this.resultStrings=resultStrings;
                        KenoHomeActivity.this.totalListStrings=totalListStrings;
                       indexFragment.updateResultUI();
                    }
                    dismissBlockDialog();
                }
            }
        };
    }
    private void initStateThread() {
        stateThread = new NyVolleyJsonThreadHandler<List<LotteryStateGameBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                return new QuickRequestBean(WebSiteUrl.KenoStatusSubmitter, params
                        , new TypeToken<NyBaseResponse<List<LotteryStateGameBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<LotteryStateGameBean> data) {
                handleStateData(data);
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                if (obj != null)
                    Toast.makeText(mContext, obj, Toast.LENGTH_SHORT).show();
                dismissBlockDialog();
            }

            @Override
            public void startThread(Integer obj) {
                super.startThread(obj);
                showBlockDialog();
            }
        };
    }

    private void initFragment() {
        if (manager == null) {
            manager = getSupportFragmentManager();
        }
        changeFragment(fHome);

    }
    public void changeFragment(KenoBaseFragment fragment){
        this.indexFragment=fragment;
        AppTool.setFragment(manager, fragment, R.id.fragment);
    }

    public void changeFragment(){
        if(indexFragment==null)
            changeFragment(fHome);
        else if(indexFragment.equals(fHome)){
            changeFragment(fDetail);
        }else{
            changeFragment(fHome);
        }
    }


    @Override
    public void initView() {
        super.initView();

    }
    public void setRightViewClickable(boolean able){
        rightTv.setVisibility(View.VISIBLE);
      if(able){
          rightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.caipiaocaidan, 0);
          rightTv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  rightMenuPop.showPopupDownWindow();
              }
          });
      }else{
          rightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
          rightTv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

              }
          });
      }
    }
    protected void setActTitle(String name) {
        setTitle(getString(R.string.keno) + "(" + name + ")");
    }
    private void initRightMenuPop() {
        rightMenuPop = new AbsListPopupWindow<LotteryStateGameBean>(mContext, rightTv, 400, 600) {
            @Override
            protected void popItemCLick(LotteryStateGameBean bean, int position) {
                String name = bean.getGame_name();
                setActTitle(name);
                selectedStateBean = bean;
                stateThread.startThread(null);//每次选择 之后重新刷新状态
            }

            @Override
            protected void convertItem(ViewHolder helper, LotteryStateGameBean item, int position) {
                String name = item.getGame_name();
                helper.setText(R.id.item_tv, name);
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_popwindow;
            }

            @Override
            protected int getListViewId() {
                return R.id.lv_dialog;
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.area_popwindow;
            }
        };
    }


    protected void handleStateData(List<LotteryStateGameBean> data) {
        initRightMenuData(data);
    }

    protected void initRightMenuData(List<LotteryStateGameBean> data) {
        int i = 0;
        for (LotteryStateGameBean lotteryStateGameBean : data) {
            String name = lotteryStateGameBean.getGame_name();
            if (name.equals("SINGAPORE")) {
                name = getString(R.string.SINGAPORE);
            } else if (name.equals("TAIPEI")) {
                name = getString(R.string.TAIPEI);
            } else if (name.equals("KUALA_LUMPUR")) {
                name = getString(R.string.KUALA_LUMPUR);
            } else if (name.equals("HONGKONG")) {
                name = getString(R.string.HONGKONG);
            } else if (name.equals("MALAYSIA")) {
                name = getString(R.string.MALAYSIA);
            } else if (name.equals("CHINA")) {
                name = getString(R.string.CHINA);
            } else if (name.equals("HK4D")) {
                name = getString(R.string.HK4D);
            } else if (name.equals("CAMBODIA")) {
                name = getString(R.string.CAMBODIA);
            } else if (name.equals("MACAU")) {
                name = getString(R.string.MACAU);
            } else if (name.equals("CANADA4D")) {
                name = getString(R.string.CANADA4D);
            } else if (name.equals("KOREA")) {
                name = getString(R.string.KOREA);
            } else if (name.equals("JAPAN")) {
                name = getString(R.string.JAPAN);
            } else if (name.equals("VIETNAM")) {
                name = getString(R.string.VIETNAM);
            } else if (name.equals("THAILAND")) {
                name = getString(R.string.THAILAND);
            } else if (name.equals("JAKARTA")) {
                name = getString(R.string.JAKARTA);
            }
            data.get(i).setGame_name(name);
            i++;
        }
        if (selectedStateBean != null) {
            for (LotteryStateGameBean lotteryStateGameBean : data) {
                if (selectedStateBean.getGame_name().equals(lotteryStateGameBean.getGame_name())) {
                    selectedStateBean = lotteryStateGameBean;
                    break;
                }
            }
        } else {
            selectedStateBean = data.get(0);
        }
        rightMenuPop.setData(data);
        updateGameOddsSate();
    }

    protected void updateGameOddsSate() {
        if (selectedStateBean == null || oddslist == null)
            return;
        gameTimer(selectedStateBean);
        selectedOddsList = new ArrayList<>();
        for (DigGameOddsBean oddsBean : oddslist) {
            if (oddsBean.getType1().equals("6")) {
                selectedOddsList.add(oddsBean);
            }
        }
        updateOdds(selectedOddsList, selectedStateBean);

    }
    protected void updateOdds(ArrayList<DigGameOddsBean> selectedOddsList, LotteryStateGameBean selectedStateBean) {
        resultListThread.startThread(null);
        String name = selectedStateBean.getGame_name();
        setActTitle(name);

        pearlBallList=new ArrayList<>();
        for (DigGameOddsBean oddsBean : selectedOddsList) {
            if (oddsBean.getType3().equals("28")) {
                pearlBallList.add(oddsBean);
            } else if (oddsBean.getType3().equals("29")) {
                bigSmallBean=oddsBean;

            } else if (oddsBean.getType3().equals("30")) {
                evenOddBean =oddsBean;

            }else if (oddsBean.getType3().equals("31")) {
                combinationBean=oddsBean;

            }
            else if (oddsBean.getType3().equals("32")) {
                elementBean=oddsBean;

            }
            else if (oddsBean.getType3().equals("33")) {
                upDownBean=oddsBean;
            }
            else if (oddsBean.getType3().equals("34")) {
                evensOddsBean=oddsBean;
            }
            else if (oddsBean.getType3().equals("35")) {
                mixParlayBean=oddsBean;
            }
        }
        indexFragment.updateOddsUI();
    }
    protected void gameTimer(LotteryStateGameBean selectedStateBean) {
        String openrule = selectedStateBean.getOpen_rule();
        String nowTime = selectedStateBean.getNow_time();
        String openTime = selectedStateBean.getOpen_time();
        if(openrule==null)
            return;
        int openR = Integer.valueOf(openrule);
        long diff = TimeUtils.diffTime(nowTime, openTime, "yyyy-MM-dd HH:mm:ss");
        long waitTime = openR * 60 * 1000;
        indexFragment.setTimerText("00:00");
        if (diff > waitTime) {
          indexFragment.progressVisibility(true);
            updateStateDataDelayed();

            if(timer!=null) {
                timer.cancel();
                timer=null;
            }
        } else {
            indexFragment.progressVisibility(false);
            timeCount(waitTime - diff);
        }

    }

    public void updateStateDataDelayed() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                stateThread.startThread(null);
            }
        }, 50000);
    }

    protected void timeCount(long l) {
        if (timer != null) {
            timer.cancel();
            timer=null;
        }
        timer = new CountDownTimer(l, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minute = (int) (millisUntilFinished / 1000 / 60);
                int second = (int) (millisUntilFinished / 1000 % 60);
                String strMinute = minute + "";
                String strSecond = "" + second;
                if (minute < 10) {
                    strMinute = "0" + strMinute;
                }
                if (second < 10) {
                    strSecond = "0" + strSecond;
                }
                if(minute>=5){
                    indexFragment.progressVisibility(true);
                }else {
                    indexFragment.progressVisibility(false);
                    indexFragment.setTimerText(strMinute + ":" + strSecond);

                }
            }

            @Override
            public void onFinish() {
                indexFragment.setTimerText("00:00");
                indexFragment.progressVisibility(true);
                stateThread.startThread(null);//倒计时完成
                dismissBlockDialog();
                if(isAttached&&indexFragment.pop!=null&&indexFragment.pop.isShowing()){
                    indexFragment.pop.closePopupWindow();
                }

            }
        };
        timer.start();
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_keno;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null) {
            timer.cancel();
            timer=null;
        }
        dismissBlockDialog();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppTool.setAppLanguage(mContext, "");
    }

    public void clickBetProgress(View view) {
    }
}
