package gaming178.com.casinogame.base;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.base.BaseApplication;
import com.unkonw.testapp.libs.common.ActivityPageManager;
import com.unkonw.testapp.libs.utils.ThreadPoolUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cn.finalteam.toolsfinal.DeviceUtils;
import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.BaccaratActivity;
import gaming178.com.casinogame.Activity.ChangePasswordActivity;
import gaming178.com.casinogame.Activity.DragonTigerActivity;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Activity.LobbyBaccaratActivity;
import gaming178.com.casinogame.Activity.LobbyDragonTigerActivity;
import gaming178.com.casinogame.Activity.LobbyRouletteActivity;
import gaming178.com.casinogame.Activity.LobbySicboActivity;
import gaming178.com.casinogame.Activity.ReportFormActivity;
import gaming178.com.casinogame.Activity.RouletteActivity;
import gaming178.com.casinogame.Activity.SicboActivity;
import gaming178.com.casinogame.Activity.SlotsWebActivity;
import gaming178.com.casinogame.Bean.Baccarat;
import gaming178.com.casinogame.Bean.BaccaratOtherUserBetInfomation;
import gaming178.com.casinogame.Bean.BaccaratPlayer;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.DragonTiger;
import gaming178.com.casinogame.Bean.GameMenuItem;
import gaming178.com.casinogame.Bean.User;
import gaming178.com.casinogame.Popupwindow.DepositPop;
import gaming178.com.casinogame.Popupwindow.PopLiveChat;
import gaming178.com.casinogame.Popupwindow.PopReferralList;
import gaming178.com.casinogame.Popupwindow.PopReport;
import gaming178.com.casinogame.Popupwindow.WithdrawPop;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.ChangePasswordHelper;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.GameSlideChangeTableHelper;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.LogIntervalUtils;
import gaming178.com.casinogame.Util.PopChooseChip;
import gaming178.com.casinogame.Util.PopReferrer;
import gaming178.com.casinogame.Util.PopSlideHint;
import gaming178.com.casinogame.Util.TableChangePop;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.login.LanguageHelper;
import gaming178.com.casinogame.login.LoginActivity;
import gaming178.com.casinogame.login.MenuItemInfo;
import gaming178.com.casinogame.login.PopChoiceLanguage;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.lib.util.LogUtil;
import gaming178.com.mylibrary.popupwindow.AbsListPopupWindow;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Administrator on 2016/3/21.
 */
public abstract class BaseActivity extends gaming178.com.mylibrary.base.component.BaseActivity {
    private static final int THREAD_ALL_LOBBY = 1011;
    boolean isAllLobbyEnd = false;
    private UpdateGameStatus updateGameStatus = null;
    private Thread threadGameStatus = null;
    private UpdateGameTimer updateGameTimer = null;
    private Thread threadGameTimer = null;

    protected boolean bGetGameStatus = true;
    protected boolean bGetGameTimer = true;
    private String strResultsOld = "";
    private boolean updateRouletteRoad = true;
    protected ComponentName componentFront;
    protected ComponentName componentBack;
    protected LinearLayout llCenter;
    protected AbsListPopupWindow<String> popupGameChoose;
    protected List<String> selectableGames;
    public int tableId;
    protected String usName;
    public String currency;
    private String appUserName;
    //    private  AbsListPopupWindow<GameMenuItem> popupWindow;
    protected TableChangePop tablePop;
    private UpdateAllHallIdGameStatus updateAllLobby;
    protected ArrayList<GameMenuItem> games;
    public TextView toolbar_right_bottom_tv;
    public TextView toolbar_right_top_tv;

    protected TextView logoutTv;
    protected TextView rightTv;
    protected ImageView imgBack;
    protected TextView rightTableTv;
    protected TextView rightMusicTv;
    protected TextView rightBetTv;
    protected TextView rightWinLoseTv;
    protected TextView rightReportTv;
    protected TextView rightBalanceTv;
    protected TextView changeBetUiTv;
    protected TextView rouletteNumberTv;
    public LinearLayout girlLayout;

    public LinearLayout ll_more_info;
    public float poolSize = 12;
    public AppModel mAppViewModel;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void setOtherUserBetInformation(String areaId, String type, String money) {
        BaccaratOtherUserBetInfomation baccaratOtherUserBetInformation = new BaccaratOtherUserBetInfomation();
        baccaratOtherUserBetInformation.setAreaId(areaId);
        baccaratOtherUserBetInformation.setType(type);
        baccaratOtherUserBetInformation.setBetMoney(Integer.parseInt(money));
        Log.i(WebSiteUrl.Tag, "areaId=" + areaId + ",type=" + type + ",money=" + money);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation().add(baccaratOtherUserBetInformation);
    }

    public void getBaccaratStatus(String strRes, int tableId) {
        if (!strResultsOld.equals(strRes)) {
            strResultsOld = strRes;
           /* if(WebSiteUrl.isDomain&&WebSiteUrl.GameType==1){
                String[] tableStrs = strRes.split("\\^");
                if(tableStrs.length>2){
                    strRes=tableStrs[2];
                }
            }*/
            if (strRes.contains("&")) {
                String[] newSplitArr = strRes.split("\\|");
                String newData = newSplitArr[0];
                String[] splitData = newData.split("#");
                String newFiveResult = splitData[3];
                String[] split = newFiveResult.split("&");
                mAppViewModel.getBaccarat(tableId).getBaccaratResults().setLucky6(Integer.parseInt(split[1]));
                mAppViewModel.getBaccarat(tableId).getBaccaratResults().setAnyPairs(Integer.parseInt(split[2]));
                mAppViewModel.getBaccarat(tableId).getBaccaratResults().setPerfectPairs(Integer.parseInt(split[3]));
                mAppViewModel.getBaccarat(tableId).getBaccaratResults().setnBanker(Integer.parseInt(split[4]));
                mAppViewModel.getBaccarat(tableId).getBaccaratResults().setnPlayer(Integer.parseInt(split[5]));
                strRes = strRes.replace(splitData[3], split[0]);
            }
            String tableInfo[] = strRes.split("\\|");
            if (tableInfo.length >= 2) {

                String tableDetailTemp[] = tableInfo[0].split("#");
                String tableDetail[] = tableDetailTemp;
                if (WebSiteUrl.isDomain && tableDetailTemp.length == 13) {
                    tableDetail = new String[tableDetailTemp.length + 1];
                    tableDetail[0] = tableDetailTemp[0];
                    tableDetail[1] = "";
                    for (int i = 1; i < tableDetailTemp.length; i++) {
                        tableDetail[i + 1] = tableDetailTemp[i];
                    }
                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().Init();

                } else if (tableDetailTemp.length == 17) {
                    tableDetail = new String[tableDetail.length + 1];
                    tableDetail[0] = tableDetailTemp[0];
                    tableDetail[1] = "";
                    for (int i = 1; i < tableDetailTemp.length; i++) {
                        tableDetail[i + 1] = tableDetailTemp[i];
                    }

                }
                if (tableDetail.length >= 14) {
                    // 第0个数据还要根据^拆分得到用户的下注信息
                    String betDetail[] = tableDetail[0].split("\\^");
                    if (tableDetail[0].length() > 12 && !mAppViewModel.getBaccarat(tableId).getOtherUserBetString().equals(tableDetail[0]) && betDetail.length > 1) {
                        //     Log.i(WebSiteUrl.Tag,"User BetInfo = "+betDetail[1]);
                        mAppViewModel.getBaccarat(tableId).setOtherUserBetString(tableDetail[0]);

                        mAppViewModel.getBaccarat(tableId).getOtherUserBetInfomation().clear();

                        for (int j = 0; j < betDetail.length; j++) {
                            if (j == 0)
                                continue;
                            String detail[] = betDetail[j].split(":");
                            switch (detail[0]) {
                                case "Player1":
                                    setOtherUserBetInformation("1", "Player", detail[1]);
                                    break;
                                case "Player2":
                                    setOtherUserBetInformation("2", "Player", detail[1]);
                                    break;
                                case "Player3":
                                    setOtherUserBetInformation("3", "Player", detail[1]);
                                    break;
                                case "Player5":
                                    setOtherUserBetInformation("5", "Player", detail[1]);
                                    break;
                                case "Player6":
                                    setOtherUserBetInformation("6", "Player", detail[1]);
                                    break;
                                case "Player7":
                                    setOtherUserBetInformation("7", "Player", detail[1]);
                                    break;
                                case "Player8":
                                    setOtherUserBetInformation("8", "Player", detail[1]);
                                    break;
                                case "Banker1":
                                    setOtherUserBetInformation("1", "Banker", detail[1]);
                                    break;
                                case "Banker2":
                                    setOtherUserBetInformation("2", "Banker", detail[1]);
                                    break;
                                case "Banker3":
                                    setOtherUserBetInformation("3", "Banker", detail[1]);
                                    break;
                                case "Banker5":
                                    setOtherUserBetInformation("5", "Banker", detail[1]);
                                    break;
                                case "Banker6":
                                    setOtherUserBetInformation("6", "Banker", detail[1]);
                                    break;
                                case "Banker7":
                                    setOtherUserBetInformation("7", "Banker", detail[1]);
                                    break;
                                case "Banker8":
                                    setOtherUserBetInformation("8", "Banker", detail[1]);
                                    break;
                                case "Tie1":
                                    setOtherUserBetInformation("1", "Tie", detail[1]);
                                    break;
                                case "Tie2":
                                    setOtherUserBetInformation("2", "Tie", detail[1]);
                                    break;
                                case "Tie3":
                                    setOtherUserBetInformation("3", "Tie", detail[1]);
                                    break;
                                case "Tie5":
                                    setOtherUserBetInformation("5", "Tie", detail[1]);
                                    break;
                                case "Tie6":
                                    setOtherUserBetInformation("6", "Tie", detail[1]);
                                    break;
                                case "Tie7":
                                    setOtherUserBetInformation("7", "Tie", detail[1]);
                                    break;
                                case "Tie8":
                                    setOtherUserBetInformation("8", "Tie", detail[1]);
                                    break;
                                case "Pd1":
                                    setOtherUserBetInformation("1", "Pd", detail[1]);
                                    break;
                                case "Pd2":
                                    setOtherUserBetInformation("2", "Pd", detail[1]);
                                    break;
                                case "Pd3":
                                    setOtherUserBetInformation("3", "Pd", detail[1]);
                                    break;
                                case "Pd5":
                                    setOtherUserBetInformation("5", "Pd", detail[1]);
                                    break;
                                case "Pd6":
                                    setOtherUserBetInformation("6", "Pd", detail[1]);
                                    break;
                                case "Pd7":
                                    setOtherUserBetInformation("7", "Pd", detail[1]);
                                    break;
                                case "Pd8":
                                    setOtherUserBetInformation("8", "Pd", detail[1]);
                                    break;
                                case "Bd1":
                                    setOtherUserBetInformation("1", "Bd", detail[1]);
                                    break;
                                case "Bd2":
                                    setOtherUserBetInformation("2", "Bd", detail[1]);
                                    break;
                                case "Bd3":
                                    setOtherUserBetInformation("3", "Bd", detail[1]);
                                    break;
                                case "Bd5":
                                    setOtherUserBetInformation("5", "Bd", detail[1]);
                                    break;
                                case "Bd6":
                                    setOtherUserBetInformation("6", "Bd", detail[1]);
                                    break;
                                case "Bd7":
                                    setOtherUserBetInformation("7", "Bd", detail[1]);
                                    break;
                                case "Bd8":
                                    setOtherUserBetInformation("8", "Bd", detail[1]);
                                    break;
                            }
                        }

                    }
                    ///////////第1个数据是次桌会员信息
                    if (!mAppViewModel.getBaccarat(tableId).getBaccaratPlayerString().equals(tableDetail[1])) {
                        mAppViewModel.getBaccarat(tableId).setBaccaratPlayerString(tableDetail[1]);
                        String playerDetail[] = tableDetail[1].split("\\^");
                        mAppViewModel.getBaccarat(tableId).getBaccaratPlayer().clear();
                        for (int i = 0; i < playerDetail.length; i++) {
                            if (playerDetail[i] != null && !"".equals(playerDetail[i])) {
                                String detail[] = playerDetail[i].split(":");
                                if (detail.length == 2) {
                                    BaccaratPlayer baccaratPlayer = new BaccaratPlayer();
                                    baccaratPlayer.setName(detail[1]);
                                    baccaratPlayer.setNumber(detail[0]);
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPlayer().add(baccaratPlayer);
                                }

                            }
                        }
                    }

                    ///////////第2个数据是游戏状态
                    mAppViewModel.getBaccarat(tableId).setGameStatus(Integer.parseInt(tableDetail[2]));
                    ///////////第3个数据是倒计时
                    //   Log.i(WebSiteUrl.Tag,"baccaratTimer Base= "+tableDetail[3]);
                    mAppViewModel.getBaccarat(tableId).setTimer(Integer.parseInt(tableDetail[3]));
                    ///////////第4个数据是结果,将结果拆分好，放到路子信息里面
                    if (!"".equals(tableDetail[4]) && !"0".equals(tableDetail[4]) && tableDetail[4].length() == 4) {
                        mAppViewModel.getBaccarat(tableId).getBaccaratResults().setBanker_palyer_tie(Integer.parseInt(tableDetail[4].substring(0, 1)));
                        mAppViewModel.getBaccarat(tableId).getBaccaratResults().setBankerPair(Integer.parseInt(tableDetail[4].substring(1, 2)));
                        mAppViewModel.getBaccarat(tableId).getBaccaratResults().setPlayerPair(Integer.parseInt(tableDetail[4].substring(2, 3)));
                        mAppViewModel.getBaccarat(tableId).getBaccaratResults().setBig_small(Integer.parseInt(tableDetail[4].substring(3, 4)));
                        //   String road = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad();
                        //  mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoad(road+tableDetail[4]);
                    }
                    ///////////第5个数据是庄下注
                    //    Log.i(WebSiteUrl.Tag,"BankerPool = "+Integer.parseInt(tableDetail[5]));
                    if (!mAppViewModel.getBaccarat(tableId).getPoolString().equals(tableDetail[5] + tableDetail[6] + tableDetail[7]
                            + tableDetail[8] + tableDetail[9] + tableDetail[10] + tableDetail[11])) {
                        mAppViewModel.getBaccarat(tableId).setPoolString(tableDetail[5] + tableDetail[6] + tableDetail[7]
                                + tableDetail[8] + tableDetail[9] + tableDetail[10] + tableDetail[11]);
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setBanker(Integer.parseInt(tableDetail[5]));
                        ///////////第6个数据是闲下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setPlayer(Integer.parseInt(tableDetail[6]));
                        ///////////第7个数据是和下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setTie(Integer.parseInt(tableDetail[7]));
                        ///////////第8个数据是庄对下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setBankerPair(Integer.parseInt(tableDetail[8]));
                        ///////////第9个数据是闲对下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setPlayerPair(Integer.parseInt(tableDetail[9]));
                        ///////////第10个数据是大下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setBig(Integer.parseInt(tableDetail[10]));
                        ///////////第11个数据是小下注
                        mAppViewModel.getBaccarat(tableId).getBaccaratPool().setSmall(Integer.parseInt(tableDetail[11]));
                    }


                    ///////////第12个数据是系统时间
                    mAppViewModel.getBaccarat(tableId).setServerTime(tableDetail[12].substring(0, tableDetail[12].length() - 3));

                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().Init();
                    Log.d("GameStatus123", "Banker: " + tableDetail[15] + "-" + tableDetail[16] + "-" + tableDetail[17]);
                    if (tableDetail.length > 15) {
                        if (!"null".equals(tableDetail[15]))
                            mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker1(Integer.parseInt(tableDetail[15]));
                        if (!"null".equals(tableDetail[16]))
                            mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker2(Integer.parseInt(tableDetail[16]));
                        if (!"null".equals(tableDetail[17]))
                            mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker3(Integer.parseInt(tableDetail[17]));
                        //拆分扑克牌信息
                        if (tableInfo.length >= 2) {
                            String pokerDetailPlayer[] = tableInfo[1].split("#");
                            Log.d("GameStatus123", "Player: " + tableInfo[1]);
                            if (pokerDetailPlayer.length > 3) {
                                if (!"null".equals(pokerDetailPlayer[0]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer1(Integer.parseInt(pokerDetailPlayer[0]));
                                if (!"null".equals(pokerDetailPlayer[1]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer2(Integer.parseInt(pokerDetailPlayer[1]));
                                if (!"null".equals(pokerDetailPlayer[2]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer3(Integer.parseInt(pokerDetailPlayer[2]));

                                mAppViewModel.getBaccarat(tableId).setShoeNumber(pokerDetailPlayer[3]);
                                LogUtil.d("tv_baccarat_shoe_number", "getBaccaratStatus,setShoeNumber:" + pokerDetailPlayer[3] + "," + pokerDetailPlayer[4]);
                                mAppViewModel.getBaccarat(tableId).setGameNumber(pokerDetailPlayer[4]);

                            }

                        }
                    } else {
                        Log.d("GameStatus123", "banker: " + tableInfo[1]);
                        if (tableInfo.length > 2) {
                            String pokerDetailBanker[] = tableInfo[1].split("#");
                            if (pokerDetailBanker.length == 3) {
                                if (!"null".equals(pokerDetailBanker[0]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker1(Integer.parseInt(pokerDetailBanker[0]));
                                if (!"null".equals(pokerDetailBanker[1]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker2(Integer.parseInt(pokerDetailBanker[1]));
                                if (!"null".equals(pokerDetailBanker[2]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setBanker3(Integer.parseInt(pokerDetailBanker[2]));

                            }
                            Log.d("GameStatus123", "Player: " + tableInfo[2]);
                            String pokerDetailPlayer[] = tableInfo[2].split("#");
                            if (pokerDetailPlayer.length > 3) {
                                if (!"null".equals(pokerDetailPlayer[0]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer1(Integer.parseInt(pokerDetailPlayer[0]));
                                if (!"null".equals(pokerDetailPlayer[1]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer2(Integer.parseInt(pokerDetailPlayer[1]));
                                if (!"null".equals(pokerDetailPlayer[2]))
                                    mAppViewModel.getBaccarat(tableId).getBaccaratPoker().setPlayer3(Integer.parseInt(pokerDetailPlayer[2]));

                                mAppViewModel.getBaccarat(tableId).setShoeNumber(pokerDetailPlayer[3]);
                                LogUtil.d("tv_baccarat_shoe_number", "getBaccaratStatus,setShoeNumber:" + pokerDetailPlayer[3] + "," + pokerDetailPlayer[4]);
                                mAppViewModel.getBaccarat(tableId).setGameNumber(pokerDetailPlayer[4]);

                            }

                        }
                    }
                }


            } else {

            }
        }
    }

    public void getSicboStatus(String strRes) {
        if (!strResultsOld.equals(strRes)) {
            strResultsOld = strRes;
            String tableDetail[] = strRes.split("#");

            if (tableDetail.length >= 10) {


                ///////////第1个数据是游戏状态
                mAppViewModel.getSicbo01().setGameStatus(Integer.parseInt(tableDetail[1]));
                ///////////第2个数据是倒计时
                //  Log.i(WebSiteUrl.Tag,"baccaratTimer = "+tableDetail[3]);
                mAppViewModel.getSicbo01().setTimer(Integer.parseInt(tableDetail[2]));
                ///////////第3个数据是结果,将结果拆分好，放到路子信息里面
                if (!"".equals(tableDetail[3]) && !"0".equals(tableDetail[3]) && tableDetail[3].length() == 3) {
                    String luzi = tableDetail[3].substring(0, 1) + "-" + tableDetail[3].substring(1, 2) + "-" + tableDetail[3].substring(2, 3);
                    mAppViewModel.getSicbo01().setResult(tableDetail[3]);
                    String reslut[] = mAppViewModel.getSicbo01().getRoad().split("#");
                    String lastReslut = "";
                    if (!"".equals(reslut[reslut.length - 1]) && reslut[reslut.length - 1] != null)
                        lastReslut = reslut[reslut.length - 1];
                    else
                        lastReslut = reslut[reslut.length - 2];

                    if (!luzi.equals(lastReslut)) {
                        //    Log.i(WebSiteUrl.Tag,"last  road = "+lastReslut);
                        //    Log.i(WebSiteUrl.Tag,"getSicboStatus update road = "+luzi);
                        mAppViewModel.getSicbo01().setRoad(mAppViewModel.getSicbo01().getRoad().substring(6, mAppViewModel.getSicbo01().getRoad().length()) + luzi + "#");
                    }
                }
                ///////////第4个数据是庄下注
                //    Log.i(WebSiteUrl.Tag,"BankerPool = "+Integer.parseInt(tableDetail[5]));
                if (!mAppViewModel.getSicbo01().getPoolString().equals(tableDetail[4] + tableDetail[5] + tableDetail[6]
                        + tableDetail[7] + tableDetail[8] + tableDetail[9] + tableDetail[10])) {
                    mAppViewModel.getSicbo01().setPoolString(tableDetail[4] + tableDetail[5] + tableDetail[6]
                            + tableDetail[7] + tableDetail[8] + tableDetail[9] + tableDetail[10]);
                    mAppViewModel.getSicbo01().getSicboPool().setBigSmall(Integer.parseInt(tableDetail[4]));
                    ///////////第5个数据三军
                    mAppViewModel.getSicbo01().getSicboPool().setThreeforces(Integer.parseInt(tableDetail[5]));
                    ///////////第6个数据是短牌，（1，2）
                    mAppViewModel.getSicbo01().getSicboPool().setNineway(Integer.parseInt(tableDetail[6]));
                    ///////////第7个数据是对子，长牌
                    mAppViewModel.getSicbo01().getSicboPool().setPair(Integer.parseInt(tableDetail[7]));
                    ///////////第8个数据是围骰
                    mAppViewModel.getSicbo01().getSicboPool().setWaiDices(Integer.parseInt(tableDetail[8]));
                    ///////////第9个数据是全围
                    mAppViewModel.getSicbo01().getSicboPool().setAllDices(Integer.parseInt(tableDetail[9]));
                    ///////////第10个数据是点数
                    mAppViewModel.getSicbo01().getSicboPool().setCombination(Integer.parseInt(tableDetail[10]));
                }


                ///////////第12个数据是系统时间
                mAppViewModel.getSicbo01().setServerTime(tableDetail[13].substring(0, tableDetail[13].length() - 3));
                mAppViewModel.getSicbo01().setGameNumber(tableDetail[12]);
            }


        }
    }

    public void getRouletteStatus(String strRes) {
        if (!strResultsOld.equals(strRes)) {
            strResultsOld = strRes;
            String tableDetail[] = strRes.split("#");

            if (tableDetail.length >= 11) {


                ///////////第1个数据是游戏状态
                mAppViewModel.getRoulette01().setGameStatus(Integer.parseInt(tableDetail[1]));
                if (Integer.parseInt(tableDetail[1]) == 2)
                    updateRouletteRoad = true;
                ///////////第2个数据是倒计时
                //  Log.i(WebSiteUrl.Tag,"baccaratTimer = "+tableDetail[3]);
                mAppViewModel.getRoulette01().setTimer(Integer.parseInt(tableDetail[2]));
                ///////////第3个数据是结果,将结果拆分好，放到路子信息里面
                if (!"".equals(tableDetail[3]) && mAppViewModel.getRoulette01().getGameStatus() == 5 && updateRouletteRoad) {
                    updateRouletteRoad = false;
                    String luzi = "";
                    mAppViewModel.getRoulette01().setResult(tableDetail[3]);
                    String reslut[] = mAppViewModel.getRoulette01().getRoad().split("#");
                    for (int i = 1; i < reslut.length; i++) {
                        luzi += reslut[i] + "#";
                    }
                    luzi += tableDetail[3] + "#";

                    //      Log.i(WebSiteUrl.Tag,"last  road = "+mAppViewModel.getRoulette01().getRoad());

                    mAppViewModel.getRoulette01().setRoad(luzi);
                    //    Log.i(WebSiteUrl.Tag,"getSicboStatus update road = "+mAppViewModel.getRoulette01().getRoad());
                }
                ///////////第4个数据是庄下注
                //    Log.i(WebSiteUrl.Tag,"BankerPool = "+Integer.parseInt(tableDetail[5]));
                if (!mAppViewModel.getRoulette01().getPoolString().equals(tableDetail[4] + tableDetail[5] + tableDetail[6]
                        + tableDetail[7] + tableDetail[8] + tableDetail[9] + tableDetail[10] + tableDetail[11])) {
                    mAppViewModel.getRoulette01().setPoolString(tableDetail[4] + tableDetail[5] + tableDetail[6]
                            + tableDetail[7] + tableDetail[8] + tableDetail[9] + tableDetail[10] + tableDetail[11]);
                    ///////////第4个数是直注(35)DirectBet ,1-35个号码
                    mAppViewModel.getRoulette01().getRoulettePool().setNumber((int) Double.parseDouble(tableDetail[4]));
                    ///////////第5个数分注(18)	SeparateBet
                    mAppViewModel.getRoulette01().getRoulettePool().setSplit((int) Double.parseDouble(tableDetail[5]));
                    ///////////第6个数据3个号码(12)	StreetBet+ThreeBet
                    mAppViewModel.getRoulette01().getRoulettePool().setStreet((int) Double.parseDouble(tableDetail[6]));
                    ///////////第7个数据是四个号码(9)	AngleBet+FourBet
                    mAppViewModel.getRoulette01().getRoulettePool().setCorner((int) Double.parseDouble(tableDetail[7]));
                    ///////////第8个数据是6个号码(6)	LineBet
                    mAppViewModel.getRoulette01().getRoulettePool().setLine((int) Double.parseDouble(tableDetail[8]));
                    ///////////第9个数据是12个号码列注(2)	FristCol+SndCol+ThrCol
                    mAppViewModel.getRoulette01().getRoulettePool().setColumn((int) Double.parseDouble(tableDetail[9]));
                    ///////////第10个数据是12个号码行注(2)	FristRow+SndRow+ThrRow，行注，打注
                    mAppViewModel.getRoulette01().getRoulettePool().setDozen((int) Double.parseDouble(tableDetail[10]));
                    ///////////第11个数据是RedBet+BlackBet+OddBet+EvenBet+LowBet+HightBet
                    mAppViewModel.getRoulette01().getRoulettePool().setRed_black_odd_even_big_small((int) Double.parseDouble(tableDetail[11]));
                }


                ///////////第12个数据是系统时间
                if (WebSiteUrl.isDomain && tableDetail.length == 16) {
                    mAppViewModel.getRoulette01().setServerTime(tableDetail[15].substring(0, tableDetail[15].length() - 3));
                    mAppViewModel.getRoulette01().setGameNumber(tableDetail[14]);
                } else {
                    mAppViewModel.getRoulette01().setServerTime(tableDetail[14].substring(0, tableDetail[14].length() - 3));
                    mAppViewModel.getRoulette01().setGameNumber(tableDetail[13]);
                }
            }


        }
    }

    public void getDragonTigerStatus(String strRes) {
        if (!strResultsOld.equals(strRes)) {
            strResultsOld = strRes;
            String tableDetail[] = strRes.split("#");

            if (tableDetail.length >= 8) {


                ///////////第1个数据是游戏状态
                mAppViewModel.getDragonTiger01().setGameStatus(Integer.parseInt(tableDetail[1]));
                ///////////第2个数据是倒计时
                //  Log.i(WebSiteUrl.Tag,"baccaratTimer = "+tableDetail[3]);
                mAppViewModel.getDragonTiger01().setTimer(Integer.parseInt(tableDetail[2]));
                ///////////第3个数据是结果,将结果拆分好，放到路子信息里面

                if (!"".equals(tableDetail[3]) && !"0".equals(tableDetail[3]) && tableDetail[3].length() == 5) {
                    mAppViewModel.getDragonTiger01().getDragonTigerResults().setDragon_tiger_tie(Integer.parseInt(tableDetail[3].substring(0, 1)));
                    mAppViewModel.getDragonTiger01().getDragonTigerResults().setDragon_odd_even(Integer.parseInt(tableDetail[3].substring(1, 2)));
                    mAppViewModel.getDragonTiger01().getDragonTigerResults().setDragon_red_black(Integer.parseInt(tableDetail[3].substring(2, 3)));
                    mAppViewModel.getDragonTiger01().getDragonTigerResults().setTiger_odd_even(Integer.parseInt(tableDetail[3].substring(3, 4)));
                    mAppViewModel.getDragonTiger01().getDragonTigerResults().setTiger_red_black(Integer.parseInt(tableDetail[3].substring(4, 5)));
                    //   String road = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad();
                    //  mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoad(road+tableDetail[4]);
                }
                ///////////第4个数据是庄下注
                //    Log.i(WebSiteUrl.Tag,"BankerPool = "+Integer.parseInt(tableDetail[5]));
                if (!mAppViewModel.getDragonTiger01().getPoolString().equals(tableDetail[4] + tableDetail[5] + tableDetail[6]
                )) {
                    mAppViewModel.getDragonTiger01().setPoolString(tableDetail[4] + tableDetail[5] + tableDetail[6]
                    );

                    mAppViewModel.getDragonTiger01().getDragonTigerPool().setDragon((int) Double.parseDouble(tableDetail[4]));

                    mAppViewModel.getDragonTiger01().getDragonTigerPool().setTiger((int) Double.parseDouble(tableDetail[5]));

                    mAppViewModel.getDragonTiger01().getDragonTigerPool().setTie((int) Double.parseDouble(tableDetail[6]));

                }


                ///////////第12个数据是系统时间
                mAppViewModel.getDragonTiger01().setServerTime(tableDetail[7].substring(0, tableDetail[7].length() - 3));
                mAppViewModel.getDragonTiger01().setShoeNumber(tableDetail[9]);
                mAppViewModel.getDragonTiger01().setGameNumber(tableDetail[10]);
                String pokerDetail[] = tableDetail[8].split("\\|");
                if (pokerDetail.length == 3) {

                    if (!"null".equals(pokerDetail[1])) {
                        Log.d("shangpeisheng12121", "Dragon=" + Integer.parseInt(pokerDetail[1]));
                        mAppViewModel.getDragonTiger01().getDragonTigerPoker().setDragon(Integer.parseInt(pokerDetail[1]));
                    }
                    if (!"null".equals(pokerDetail[2])) {
                        Log.d("shangpeisheng12121", "Tiger=" + Integer.parseInt(pokerDetail[2]));
                        mAppViewModel.getDragonTiger01().getDragonTigerPoker().setTiger(Integer.parseInt(pokerDetail[2]));
                    }
                    //    Log.i(WebSiteUrl.Tag, "-------------- "+mAppViewModel.getTableId()+","+strRes);
                }


            }


        }
    }

    public void refreshUserBetMsg(String str) {
    }

    private String getParam(int tableId) {
        String param = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                + "&Serial=" + mAppViewModel.getSerialId() + "&Areaid=" + mAppViewModel.getAreaId();
        return param;
    }

    public class UpdateGameStatus implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetGameStatus) {
                try {
                    if (mAppViewModel.isbLogin() && mAppViewModel.isbLobby()) {
                        String statusUrl = "";
                        statusUrl = WebSiteUrl.TABLE_INFO_A_URL;
                        String strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                        Log.d("Afb88", strRes);
                        String tableInfo[] = strRes.split("\\^");
                        if (strRes.equals("netError") || strRes.equals("Results=no") || tableInfo.length < 9) {//连续5次拿不到数据就退出，返回到登录界面
                            Log.d("Afb88", "netError--------" + statusUrl);
                            iError++;
                        } else
                            iError = 0;

                        if (iError == 5) {
                            mAppViewModel.setbLogin(false);
                            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        }

                        if (iError == 0) {
                            mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
                            //拿公告信息
                            String language = AppTool.getAppLanguage(mContext);
                            switch (language) {
                                case "zh":
                                    language = "cn";
                                    break;
                                case "en":
                                    language = "en";
                                    break;
                                default:
                                    break;
                            }
                            String annoucementParams = "lng=" + language + "&Usid=" + mAppViewModel.getUser().getName();
                            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                            String ann[] = strRes.split("Results=ok\\|");
                            if (!strRes.equals("netError") && ann.length > 1) {
                                mAppViewModel.setAnnouncement(ann[1]);
                            }
                        }
                        //每隔10秒更新一下界面上的信息


                        Thread.sleep(10000);
                    } else if (mAppViewModel.isbLogin()) {
                        //    Log.i(WebSiteUrl.Tag, "-------------- UpdateSingleGameStatus");
                        String postUrl = "";
                        switch (mAppViewModel.getTableId()) {
                            case 1:
                            case 2:
                            case 3:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 71:
                                postUrl = WebSiteUrl.BJL_TABLE_STATUS_URL;
                                break;
                            case 31:
                                postUrl = WebSiteUrl.SICBO_TABLE_STATUS_URL;
                                break;
                            case 21:
                                postUrl = WebSiteUrl.LP_TABLE_STATUS_URL;
                                break;
                            case 5:
                                postUrl = WebSiteUrl.LH_TABLE_STATUS_URL;
                                break;
                        }
                        String param = getParam(mAppViewModel.getTableId());
                        String strRes = mAppViewModel.getHttpClient().sendPost(postUrl, param);
                        Log.d("shangpeisheng1212", "bjlRes=" + strRes);
                        if (mAppViewModel.isOpenChangeTable()) {
                            String bjlUrl = WebSiteUrl.BJL_TABLE_STATUS_URL;
                            String sicboUrl = WebSiteUrl.SICBO_TABLE_STATUS_URL;
                            String lpUrl = WebSiteUrl.LP_TABLE_STATUS_URL;
                            String lhUrl = WebSiteUrl.LH_TABLE_STATUS_URL;
                            if (postUrl.equals(bjlUrl)) {
                                if (mAppViewModel.isClickBaccarat1() && mAppViewModel.getTableId() != 1) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(1));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 1);
                                    }
                                }
                                if (mAppViewModel.isClickBaccarat2() && mAppViewModel.getTableId() != 2) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(2));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 2);
                                    }
                                }

                                if (mAppViewModel.isClickBaccarat3() && mAppViewModel.getTableId() != 3) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(3));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 3);
                                    }
                                }
                                if (mAppViewModel.isClickBaccarat5() && mAppViewModel.getTableId() != 61) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(61));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 61);
                                    }
                                }
                                if (mAppViewModel.isClickBaccarat6() && mAppViewModel.getTableId() != 62) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(62));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 62);
                                    }
                                }
                                if (mAppViewModel.isClickBaccarat7() && mAppViewModel.getTableId() != 63) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(63));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 63);
                                    }
                                }
                                if (mAppViewModel.isClickBaccaratMi() && mAppViewModel.getTableId() != 71) {
                                    String bjlRes = mAppViewModel.getHttpClient().sendPost(bjlUrl, getParam(71));
                                    Log.d("shangpeisheng1212", "bjlRes=" + bjlRes);
                                    if (bjlRes.startsWith("Results=ok")) {
                                        getBaccaratStatus(bjlRes, 71);
                                    }
                                }

                            }
                            if (mAppViewModel.isClickSicbo() && !postUrl.equals(sicboUrl)) {
                                String sicboRes = mAppViewModel.getHttpClient().sendPost(sicboUrl, getParam(31));
                                Log.d("shangpeisheng1212", "sicboRes=" + sicboRes);
                                if (sicboRes.startsWith("Results=ok")) {
                                    getSicboStatus(sicboRes);
                                }
                            }
                            if (mAppViewModel.isClickRoulette() && !postUrl.equals(lpUrl)) {
                                String lpRes = mAppViewModel.getHttpClient().sendPost(lpUrl, getParam(21));
                                Log.d("shangpeisheng1212", "lpRes=" + lpRes);
                                if (lpRes.startsWith("Results=ok")) {
                                    getRouletteStatus(lpRes);
                                }
                            }
                            if (mAppViewModel.isClickDragonTiger() && !postUrl.equals(lhUrl)) {
                                String lhRes = mAppViewModel.getHttpClient().sendPost(lhUrl, getParam(5));
                                Log.d("shangpeisheng12121", "lhRes=" + lhRes);
                                if (lhRes.startsWith("Results=ok")) {
                                    getDragonTigerStatus(lhRes);
                                }
                            }
                        }
                        Log.d("shangpeisheng", strRes);
                        refreshUserBetMsg(strRes);
                        Log.i(WebSiteUrl.Tag, strRes);
                        if (strRes.equals("netError") || !strRes.startsWith("Results=ok")) {//连续10次拿不到数据就退出，返回到登录界面
                            Log.d("Afb88", "netError--------" + postUrl);
                            iError++;
                        } else {
                            iError = 0;
                            //Results=ok^Player1:100^Tie1:10^Player3:30^Banker5:150^#^player1:DLDLDLYY14^player3:LK00AWYUNUS1234^player5:LK00AKMAKASSAR888^player8:LK00BKJIIN^#2#0#0#1338#2885#36#29#20#0#0#KH 2016-04-19 13:41:40#1|null#null#null|null#null#null

                            switch (mAppViewModel.getTableId()) {
                                case 1:
                                case 2:
                                case 3:
                                case 61:
                                case 62:
                                case 63:
                                case 64:
                                case 65:
                                case 66:
                                case 81:
                                case 82:
                                case 83:
                                case 84:
                                case 71:
                                    getBaccaratStatus(strRes, mAppViewModel.getTableId());
                                    break;
                                case 31:
                                    getSicboStatus(strRes);
                                    break;
                                case 21:
                                    getRouletteStatus(strRes);
                                    break;
                                case 5:
                                    getDragonTigerStatus(strRes);
                                    break;
                            }

                        }

                        if (iError == 10) {
                            mAppViewModel.setbLogin(false);
                            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        }

                        Thread.sleep(2000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(WebSiteUrl.Tag, "////////////  update status error");
                }
            }
            //  Log.i(WebSiteUrl.Tag, "-------------- end update status");
        }

    }

    public class UpdateAllHallIdGameStatus implements Runnable {

        public void run() {
            while (bGetGameStatus && !mAppViewModel.isbLobby()) {
                try {
                    if (mAppViewModel.isbLogin()) {
                        updateLobbyData(1);
                        //每隔10秒更新一下界面上的信息
//                        updateLobbyData(2);
                        handler.sendEmptyMessage(THREAD_ALL_LOBBY);
                        Thread.sleep(20000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        private void updateLobbyData(int hallId) {
            String statusUrl = "";
            statusUrl = WebSiteUrl.TABLE_INFO_A_URL;
            String strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
            Log.d("Afb88", strRes);
            String tableInfo[] = strRes.split("\\^");
            if (strRes.equals("netError") || strRes.equals("Results=no") || tableInfo.length < 9) {//连续5次拿不到数据就退出，返回到登录界面
                Log.d("Afb88", "netError--------" + statusUrl);
            } else
                mAppViewModel.splitTableInfo(strRes, hallId);

        }

    }

    public class UpdateGameTimer implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetGameTimer) {
                try {


                    if (mAppViewModel.isbLogin() && mAppViewModel.isbLobby()) {
                        String statusUrl = "";
                        switch (mAppViewModel.getHallId()) {
                            case 1:
                                statusUrl = WebSiteUrl.COUNTDOWN_URL_A;
                                break;
                            case 2:
                                statusUrl = WebSiteUrl.COUNTDOWN_URL_B;
                                break;
                            case 3:
                                statusUrl = WebSiteUrl.COUNTDOWN_URL_C;
                                break;
                        }
                        String strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                        Log.d("Afb8821", strRes);
                        if (strRes.equals("netError") || strRes.equals("Results=no")) {//连续10次拿不到数据就退出，返回到登录界面
                            iError++;
                            Log.d("Afb88", "netError:--------" + statusUrl);
                        } else
                            iError = 0;

                        if (iError == 10) {
                            mAppViewModel.setbLogin(false);
                            Log.d("Afb88", "netError:--------" + statusUrl);
                            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        }
                        //      Log.i(WebSiteUrl.Tag, "++++++++++++++ "+strRes);
                        if (iError == 0)
                            mAppViewModel.splitTimer(strRes);
                    }


                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public Handler getHandler() {
        return handler;
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {


            switch (msg.what) {
                case ErrorCode.LOGIN_ERROR_NETWORK:
                    Toast.makeText(mContext, R.string.server_network_error, Toast.LENGTH_LONG).show();
                    stopUpdateStatus();
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
                    skipAct(LoginActivity.class, bundle);
                    break;
                case HandlerCode.CHOOSE_SEAT_SUCESS:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
                    skipAct(BaccaratActivity.class, bundle2);
                    finish();
                    break;
                case HandlerCode.THREAD_ERROR:
                    Toast.makeText(mContext, R.string.server_network_error, Toast.LENGTH_SHORT).show();
                    dismissBlockDialog();
                    break;
                case THREAD_ALL_LOBBY:
                    if (!isAllLobbyEnd && games != null) {
//                        tablePop.setTablesData(afbApp, games);
                        isAllLobbyEnd = true;
                    }
                    break;
                case ErrorCode.LOGIN_SECCESS:
                    onAfbLoginSucceed();
                    break;

            }
            //

        }
    };

    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 跳转到对应activity
     */
    public void skipFullNameActivity(Bundle bundle, String fullName) {
        if (fullName != null && fullName.length() > 0) {
            try {
                skipAct(Class.forName(fullName), bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.setFlags(flags);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void onAfbLoginSucceed() {
        startUpdateStatus();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        llCenter = (LinearLayout) findViewById(R.id.toolbar_center_ll);
        toolbar_right_bottom_tv = (TextView) findViewById(R.id.toolbar_right_bottom_tv);
        toolbar_right_top_tv = (TextView) findViewById(R.id.toolbar_right_top_tv);
        logoutTv = (TextView) findViewById(R.id.toolbar_logout_tv);
        rightTv = (TextView) findViewById(R.id.toolbar_right_tv);
        imgBack = (ImageView) findViewById(R.id.img_back);
        rightTableTv = (TextView) findViewById(R.id.toolbar_right_table_tv);
        rightMusicTv = (TextView) findViewById(R.id.toolbar_right_music_tv);
        rightBetTv = (TextView) findViewById(R.id.toolbar_right_bet_tv);
        rightWinLoseTv = (TextView) findViewById(R.id.toolbar_right_win_lose_tv);
        rightReportTv = (TextView) findViewById(R.id.toolbar_right_report_tv);
        rightBalanceTv = (TextView) findViewById(R.id.toolbar_balance_tv);
        changeBetUiTv = (TextView) findViewById(R.id.toolbar_change_bet_ui_tv);
        rouletteNumberTv = (TextView) findViewById(R.id.toolbar_roulette_number_tv);

        componentFront = new ComponentName(this,
                FrontMuzicService.class);
        componentBack = new ComponentName(this,
                BackgroudMuzicService.class);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.mipmap.gd_arrow_left_back);
//            backTv.setText(getResources().getString(R.string.back));
            if (backTv != null)
                backTv.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
//            backTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    leftClick();
//                }
//            });
            toolbar.setSubtitle("");
           /* toolbar.setSubtitle(getResources().getString(R.string.back));

            toolbar.setSubtitleTextAppearance(mContext,R.style.text_match_bold_grey18);
            toolbar.setSubtitleTextColor(getResources().getColor(R.color.yellow_brown_white_word));*/
        }

        if (titleTv != null)
//            titleTv.setBackgroundResource(R.mipmap.gd_app_logo);
            if (rightTv != null) {

                rightTv.setGravity(Gravity.RIGHT);
                rightTv.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
                rightTv.setCompoundDrawablePadding(3);
                rightTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        clickRight(v);
                    }

                });
            }

        appUserName = mAppViewModel.getUser().getName();
        usName = appUserName;
        if (WebSiteUrl.isDomain) {
            try {
                usName = appUserName.substring(appUserName.indexOf("s") + 1);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                usName = appUserName;
            }
        }
        currency = mAppViewModel.getUser().getCurrency();
        popupGameChoose = new AbsListPopupWindow<String>(mContext, rightTv, ScreenUtil.dip2px(mContext, 180), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void popItemCLick(String gameMenuItem, int position) {


            }

            @Override
            protected void convertItem(ViewHolder helper, String item, int position) {
                helper.setText(R.id.gd__text_tv1, item);
                helper.setTextColorRes(R.id.gd__text_tv1, R.color.white);
                helper.setBackgroundRes(R.id.gd__text_tv1, R.drawable.gd_rectangle_green_corner2);
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_text;
            }

            @Override
            protected int getListViewId() {
                return R.id.gridview_content_gv;
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.include_gridview;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                GridView gv = (GridView) getAbListViewRes(view);
                gv.setNumColumns(3);
                gv.setBackgroundResource(R.drawable.gd_rectangle_trans_black_corner5);

            }
        };
        initOrientation();
    }

    protected void initOrientation() {

        int mCurrentOrientation = getResources().getConfiguration().orientation;
        handleOrientation(mCurrentOrientation);
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            mAppViewModel.setHeadSpeace(14);
            mAppViewModel.setHeadMargin(0);

//            rightTv.setTextSize(ScreenUtil.sp2px(mContext, 4));


        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mAppViewModel.setHeadSpeace(20);
            mAppViewModel.setHeadMargin(2);

            rightTv.setTextSize(ScreenUtil.sp2px(mContext, 4));

        }
//        if (tablePop != null)
//            tablePop.setTablesData(afbApp, games);
    }

    protected void handleOrientation(int mCurrentOrientation) {
        if (findViewById(R.id.gd__ll_layout_girl) != null && findViewById(R.id.gd__ll_more_info) != null)
            if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                findViewById(R.id.gd__ll_layout_girl).setVisibility(View.GONE);
                findViewById(R.id.gd__ll_more_info).setVisibility(View.GONE);
                poolSize = 12;
            } else {
                poolSize = 18;
                findViewById(R.id.gd__ll_layout_girl).setVisibility(View.VISIBLE);
                findViewById(R.id.gd__ll_more_info).setVisibility(View.VISIBLE);
            }
    }

    protected void clickRight(View v) {

    }

    private void startSeatThread(int tableId) {
        ChooseSeat seat = new ChooseSeat();
        seat.setTableId(tableId);
        showBlockDialog();
        new Thread(seat).start();
    }

    public class ChooseSeat implements Runnable {
        public void setTableId(int tableId) {
            this.tableId = tableId;
        }

        private int tableId;

        public void run() {

            try {
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL, "Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName());
                String strInfo[] = strRes.split("\\^");
              /*  if (strRes != null && strRes.startsWith("Results=ok") ) {
                    if (strInfo.length < 2) {
                        mAppViewModel.setTableId(tableId);
                        mAppViewModel.setSerialId(0);
                        mAppViewModel.setAreaId(0);
                        mAppViewModel.setbLobby(false);
                        handler.sendEmptyMessage(HandlerCode.CHOOSE_SEAT_SUCESS);
                        Log.i(WebSiteUrl.Tag, "T----------" + strRes);
                    } else {
                        String[] seatInfos = strInfo[1].split("#");

                        if (seatInfos != null && seatInfos.length > 1) {
                            mAppViewModel.setTableId(tableId);
                            mAppViewModel.setSerialId(Integer.valueOf(seatInfos[0]));
                            mAppViewModel.setAreaId(Integer.valueOf(seatInfos[1]));
                            mAppViewModel.setbLobby(false);

                            handler.sendEmptyMessage(HandlerCode.CHOOSE_SEAT_SUCESS);
                            Log.i(WebSiteUrl.Tag, "T----------" + strRes);
                        } else {
                            //   Log.i(WebSiteUrl.Tag,"----------"+tableId+",SerialId="+serialId+",AreaId="+areaId);
                            Log.i(WebSiteUrl.Tag, "F----------" + strRes);
                            handler.sendEmptyMessage(HandlerCode.THREAD_ERROR);
                        }
                    }
                }*/
            } catch (Exception e) {
                e.printStackTrace();
//                handler.sendEmptyMessage(HandlerCode.THREAD_ERROR);
            }
            mAppViewModel.setTableId(tableId);
            mAppViewModel.setSerialId(0);
            mAppViewModel.setAreaId(0);
            mAppViewModel.setbLobby(false);
            handler.sendEmptyMessage(HandlerCode.CHOOSE_SEAT_SUCESS);

        }
    }

    public void setMoreToolbar(boolean hasMoreToolbar) {
     /*   if (hasMoreToolbar) {

            llCenter.setVisibility(View.VISIBLE);
            leftTv.setText(getString(R.string.language));
            leftTv2.setText(getString(R.string.report));
            rightTv2.setText(getString(R.string.setting));
            rightTv3.setText(getString(R.string.logout));

//            leftTv.setTextAppearance(mContext, R.style.text_match_bold_black14);
            leftTv.setTextColor(getResources().getColor(R.color.white));
//            leftTv2.setTextAppearance(mContext, R.style.text_match_bold_black14);
            leftTv2.setTextColor(getResources().getColor(R.color.white));
//            rightTv2.setTextAppearance(mContext, R.style.text_match_bold_black14);
            rightTv2.setTextColor(getResources().getColor(R.color.white));
//            rightTv3.setTextAppearance(mContext, R.style.text_match_bold_black14);
            rightTv3.setTextColor(getResources().getColor(R.color.white));
           *//* Toolbar.LayoutParams params= (Toolbar.LayoutParams) leftTv.getLayoutParams();
            params.setMargins(ScreenUtil.getScreenWidthPix(mContext)/10,0,0,0);
            Toolbar.LayoutParams params2= (Toolbar.LayoutParams) leftTv2.getLayoutParams();

            Toolbar.LayoutParams params4= (Toolbar.LayoutParams) rightTv3.getLayoutParams();
            params4.setMargins(0,0,ScreenUtil.getScreenWidthPix(mContext)/10,0);*//*
            rightTv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BaseYseNoChoosePopupwindow pop = new BaseYseNoChoosePopupwindow(mContext, v) {
                        @Override
                        protected void clickSure(View v) {
                            logout(v);
                        }
                    };
                    pop.getChooseTitleTv().setText(getString(R.string.sure));
                    pop.getChooseMessage().setText(getString(R.string.sure_logout));
                    pop.getChooseCancelTv().setText(getString(R.string.no));
                    pop.getChooseSureTv().setText(getString(R.string.yes));
                    pop.showPopupCenterWindow();
                }
            });
            leftTv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showReport();
                }
            });
            leftTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLanguagePop(v);
                }
            });
            rightTv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSetPop(v);
                }
            });

        }*/
    }

    public void showReport() {
        skipAct(ReportFormActivity.class);
    }

    PopChoiceLanguage popLanguage;

    public void showLanguagePop(View v, final float weight) {

        if (popLanguage == null) {
            int width = ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                width = width / 2;
            }
            popLanguage = new PopChoiceLanguage<MenuItemInfo<String>>(mContext, v, width, ScreenUtil.dip2px(mContext, 200)) {
                @Override
                protected int onSetRcItemLayout() {
                    return R.layout.gd_item_language_selected;
                }

                @Override
                public void onConvert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                    ImageView ivFlag = holder.getView(R.id.gd__iv_flag_country);
                    TextView tvContent = holder.getView(R.id.gd__selectable_text_content_tv);
                    tvContent.setText(item.getText());
                    ivFlag.setImageResource(item.getRes());
                    boolean itemLanguageSelected = new LanguageHelper(mContext).isItemLanguageSelected(item.getType());
                    if (itemLanguageSelected) {
                        if (BuildConfig.FLAVOR.isEmpty() || BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
                            tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.oval_blue_point_12, 0);
                        } else {
                            if (BaseActivity.this instanceof LoginActivity) {
                                tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.oval_blue_point_12, 0);
                            } else {
                                tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.oval_blue_point_12_1, 0);
                            }
                        }
                    } else {
                        tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                }

                @Override
                public void onClickItem(MenuItemInfo item, int position) {
                    closePopupWindow();
                    if (BuildConfig.FLAVOR.isEmpty() || BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
                        AppTool.setAppLanguage(BaseActivity.this, item.getType());
                        recreate();
                    } else {
                        if (BaseActivity.this instanceof LoginActivity) {
                            AppTool.setAppLanguage(BaseActivity.this, item.getType());
                            recreate();
                        } else {
                            int screenWidth = WidgetUtil.getPopScreenWidth((Activity) context);
                            int width = screenWidth / 15 * 14;
                            String type = item.getType();
                            User u = mAppViewModel.getUser();
                            switch (type) {
                                case "deposit":
                                    DepositPop pop = new DepositPop(mContext, v, width, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    pop.setDialog(dialog);
                                    pop.setUser(u);
                                    pop.showPopupCenterWindow();
                                    break;
                                case "withdraw":
                                    WithdrawPop p = new WithdrawPop(mContext, v, width, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    p.setDialog(dialog);
                                    p.setUser(u);
                                    p.showPopupCenterWindow();
                                    break;
                                case "referrer":
                                    PopReferrer popReferrer = new PopReferrer(mContext, v, width, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    popReferrer.showPopupCenterWindow();
                                    break;
                                case "katasandi":
                                    startActivity(new Intent(mContext, ChangePasswordActivity.class));
                                    break;
                                case "Referral_List":
                                    PopReferralList popReferralList = new PopReferralList(mContext, v, width, width);
                                    popReferralList.showPopupCenterWindow();
                                    break;
                                case "setting":
                                    showSetPop(v, Gravity.TOP);
                                    break;
                                case "report":
                                    PopReport popReport = new PopReport(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    popReport.showPopupCenterWindow();
                                    break;
                                case "liveChat":
                                    PopLiveChat popLiveChat = new PopLiveChat(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    popLiveChat.showPopupCenterWindow();
                                    break;
                            }
                        }
                    }

                }

                @Override
                public void initData() {
                    super.initData();
                    recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));

                }

                @Override
                protected void initView(View view) {
                    super.initView(view);
                    View viewById = view.findViewById(R.id.gd__view_weight1);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewById.getLayoutParams();
                    params.weight = weight;
                    viewById.setLayoutParams(params);
                }

                @Override
                protected void onCloose() {
                    super.onCloose();
                    darkenBackground(1f);
                }

                @Override
                protected int getContentViewLayoutRes() {
                    return R.layout.gd_popupwindow_language_select;
                }
            };
            popLanguage.setData(new LanguageHelper(mContext).getLanguageItems());
        }
        darkenBackground(0.5f);
        if (BaseActivity.this instanceof LoginActivity) {
            popLanguage.showPopupDownWindow();
        } else {
            popLanguage.showPopupWindowBottom(v, ScreenUtil.dip2px(mContext, 200), ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20));
        }

    }

    private ArrayList<String> getSetLimitData(int tableId) {
        Baccarat baccarat;
        String limit1 = "0 - 0";
        String limit2 = "0 - 0";
        String limit3 = "0 - 0";
        String limit4 = "0 - 0";
        if (tableId == 1 || tableId == 2 || tableId == 3 || tableId == 61 || tableId == 62 || tableId == 63 || tableId == 71) {
            baccarat = mAppViewModel.getBaccarat(tableId);
            if (baccarat != null) {
                limit1 = "" + (int) baccarat.getBaccaratLimit1().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit1().getMaxTotalBet();
                limit2 = "" + (int) baccarat.getBaccaratLimit2().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit2().getMaxTotalBet();
                limit3 = "" + (int) baccarat.getBaccaratLimit3().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit3().getMaxTotalBet();
                limit4 = "" + (int) baccarat.getBaccaratLimit4().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit4().getMaxTotalBet();
            }
        } else if (tableId == 5) {
            DragonTiger dragonTiger = mAppViewModel.getDragonTiger01();
            if (dragonTiger != null) {
                limit1 = "" + (int) dragonTiger.getDragonTigerLimit1().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit1().getMaxTotalBet();
                limit2 = "" + (int) dragonTiger.getDragonTigerLimit2().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit2().getMaxTotalBet();
                limit3 = "" + (int) dragonTiger.getDragonTigerLimit3().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit3().getMaxTotalBet();
                limit4 = "" + (int) dragonTiger.getDragonTigerLimit4().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit4().getMaxTotalBet();
            }
        } else if (tableId == 21) {
            if (mAppViewModel.getRoulette01() != null) {
                limit1 = "" + (int) mAppViewModel.getRoulette01().getRouletteLimit1().getMinTotalBet() + " - " + (int) mAppViewModel.getRoulette01().getRouletteLimit1().getMaxTotalBet();
                limit2 = "" + (int) mAppViewModel.getRoulette01().getRouletteLimit2().getMinTotalBet() + " - " + (int) mAppViewModel.getRoulette01().getRouletteLimit2().getMaxTotalBet();
                limit3 = "" + (int) mAppViewModel.getRoulette01().getRouletteLimit3().getMinTotalBet() + " - " + (int) mAppViewModel.getRoulette01().getRouletteLimit3().getMaxTotalBet();
                limit4 = "" + (int) mAppViewModel.getRoulette01().getRouletteLimit4().getMinTotalBet() + " - " + (int) mAppViewModel.getRoulette01().getRouletteLimit4().getMaxTotalBet();
            }
        } else if (tableId == 31) {
            if (mAppViewModel.getSicbo01() != null) {
                limit1 = "" + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMaxTotalBet();
                limit2 = "" + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMaxTotalBet();
                limit3 = "" + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMaxTotalBet();
                limit4 = "" + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMaxTotalBet();
            }
        }
        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));
//        centerPop.setData());
    }

    public void onInGameChooseLanguage() {

    }

    CheckBox currentBox;
    private boolean isGameUi;

    public void showSetPop(final View v, int gravity) {
        View center = v;
        if (v == null) {
            center = new View(mContext);
        }
        int width = ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            width = width / 2;
        }
        BasePopupWindow pop = new BasePopupWindow(mContext, center, width, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.gd_popupwindow_voice_set_layout;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RadioGroup rg_switch = (RadioGroup) view.findViewById(R.id.gd__rg_switch);
                RadioButton rb_password = (RadioButton) view.findViewById(R.id.gd__rb_password);
                RadioButton rb_limit = (RadioButton) view.findViewById(R.id.gd__rb_limit);
                RadioButton rb_language = (RadioButton) view.findViewById(R.id.gd__rb_language);
                ImageView img_open_close = (ImageView) view.findViewById(R.id.gd_img_open_close);
                if (mAppViewModel.isMusicOpen()) {
                    img_open_close.setImageResource(R.mipmap.music_open);
                } else {
                    img_open_close.setImageResource(R.mipmap.music_close);
                }
                if (!BuildConfig.FLAVOR.isEmpty() && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
                    rb_limit.setBackgroundResource(R.drawable.gd_selector_music_choose_right);
                    rb_password.setVisibility(View.GONE);
                    rb_language.setVisibility(View.GONE);
                }
                TextView tv_music_title = (TextView) view.findViewById(R.id.gd__tv_music_title);
                if (BaseActivity.this instanceof LobbyActivity) {
                    rg_switch.setVisibility(View.GONE);
                    tv_music_title.setVisibility(View.VISIBLE);
                    isGameUi = false;
                } else {
                    isGameUi = true;
                }
                img_open_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAppViewModel.isMusicOpen()) {
                            mAppViewModel.setMusicOpen(false);
                            img_open_close.setImageResource(R.mipmap.music_close);
                            if (isGameUi) {
                                mAppViewModel.closeMuzicService(mContext, BackgroudMuzicService.class);
                            }
                        } else {
                            mAppViewModel.setMusicOpen(true);
                            img_open_close.setImageResource(R.mipmap.music_open);
                            if (isGameUi) {
                                mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
                            }
                        }
                    }
                });
                final LinearLayout ll_music = view.findViewById(R.id.gd__ll_music);
                final LinearLayout ll_password = view.findViewById(R.id.gd__ll_password);
                final RecyclerView recyclerView = view.findViewById(R.id.gd__base_rv);
                final RecyclerView rc_lg = view.findViewById(R.id.gd__rc_lg);
                recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mContext, getSetLimitData(mAppViewModel.getTableId()), R.layout.gd_item_popupwindow_text_select) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, String item) {
                        holder.setText(R.id.gd__pop_text_tv, item);
                    }

                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(View view, String s, int position) {
                        if ("0 - 0".endsWith(s)) {
                            return;
                        }
                        if (mAppViewModel.getTableId() == 1 || mAppViewModel.getTableId() == 2 || mAppViewModel.getTableId() == 3 || mAppViewModel.getTableId() == 61 || mAppViewModel.getTableId() == 62 || mAppViewModel.getTableId() == 63 || mAppViewModel.getTableId() == 71) {
                            mAppViewModel.getBaccarat(1).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(2).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(3).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(61).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(62).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(63).setLimitIndex(position + 1);
                            mAppViewModel.getBaccarat(71).setLimitIndex(position + 1);
                        } else if (mAppViewModel.getTableId() == 5) {
                            mAppViewModel.getDragonTiger01().setLimitIndex(position + 1);
                        } else if (mAppViewModel.getTableId() == 21) {
                            mAppViewModel.getRoulette01().setLimitIndex(position + 1);
                        } else if (mAppViewModel.getTableId() == 31) {
                            mAppViewModel.getSicbo01().setLimitIndex(position + 1);
                        }
                        closePopupWindow();
                    }
                });
                recyclerView.setAdapter(baseRecyclerAdapter);

                rc_lg.setLayoutManager(new GridLayoutManager(mContext, 2));
                BaseRecyclerAdapter<MenuItemInfo<String>> recyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(mContext, new LanguageHelper(mContext).getLanguageItems(), R.layout.gd_item_language_selected) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                        ImageView ivFlag = holder.getView(R.id.gd__iv_flag_country);
                        TextView tvContent = holder.getView(R.id.gd__selectable_text_content_tv);
                        tvContent.setText(item.getText());
                        ivFlag.setImageResource(item.getRes());
                        boolean itemLanguageSelected = new LanguageHelper(mContext).isItemLanguageSelected(item.getType());
                        if (itemLanguageSelected) {
                            tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.oval_blue_point_12, 0);
                        } else {
                            tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        }
                    }
                };
                recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                        AppTool.setAppLanguage(BaseActivity.this, item.getType());
                        closePopupWindow();
                        onInGameChooseLanguage();
                    }
                });
                rc_lg.setAdapter(recyclerAdapter);

                rg_switch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.gd__rb_music) {
                            ll_music.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            rc_lg.setVisibility(View.GONE);
                            ll_password.setVisibility(View.GONE);
                        } else if (checkedId == R.id.gd__rb_password) {
                            ll_music.setVisibility(View.INVISIBLE);
                            ll_password.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            rc_lg.setVisibility(View.GONE);
                        } else if (checkedId == R.id.gd__rb_limit) {
                            ll_music.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            rc_lg.setVisibility(View.GONE);
                            ll_password.setVisibility(View.GONE);
                        } else if (checkedId == R.id.gd__rb_language) {
                            ll_music.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            rc_lg.setVisibility(View.VISIBLE);
                            ll_password.setVisibility(View.GONE);
                        }
                    }
                });
                CheckBox checkBox1 = view.findViewById(R.id.gd__rb_set_muzic1);
                CheckBox checkBox2 = view.findViewById(R.id.gd__rb_set_muzic2);
                CheckBox checkBox3 = view.findViewById(R.id.gd__rb_set_muzic3);
                CheckBox checkBox4 = view.findViewById(R.id.gd__rb_set_muzic4);
                CheckBox checkBox5 = view.findViewById(R.id.gd__rb_set_muzic5);
                CheckBox checkBox6 = view.findViewById(R.id.gd__rb_set_muzic6);
                List<CheckBox> boxList = Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
                switch (mAppViewModel.getMuzicIndex()) {
                    case 1:
                        currentBox = checkBox1;
                        break;
                    case 2:
                        currentBox = checkBox2;
                        break;
                    case 3:
                        currentBox = checkBox3;
                        break;
                    case 4:
                        currentBox = checkBox4;
                        break;
                    case 5:
                        currentBox = checkBox5;
                        break;
                    case 6:
                        currentBox = checkBox6;
                        break;
                }
                currentBox.setChecked(true);
                checkBox1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                checkBox2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                checkBox3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                checkBox4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                checkBox5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                checkBox6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseMusic(boxList, (CheckBox) v);
                    }
                });
                SeekBar sb_front_voice = (SeekBar) view.findViewById(R.id.gd__sb_front_voice);
                SeekBar sb_background_voice = (SeekBar) view.findViewById(R.id.gd__sb_background_voice);
                sb_front_voice.setProgress(mAppViewModel.getFrontVolume());
                sb_background_voice.setProgress(mAppViewModel.getBackgroudVolume());
                sb_front_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mAppViewModel.setFrontVolume(seekBar.getProgress());
                        mAppViewModel.changeMuzicVolumeService(componentFront, mContext, seekBar.getProgress(), FrontMuzicService.PLAY_CHANGE_VOLUME);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                sb_background_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mAppViewModel.setBackgroudVolume(seekBar.getProgress());
                        mAppViewModel.changeMuzicVolumeService(componentBack, mContext, seekBar.getProgress(), BackgroudMuzicService.PLAY_CHANGE_VOLUME);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                EditText edt_old_passwrod = (EditText) view.findViewById(R.id.gd__edt_old_passwrod);
                EditText edt_new_passwrod = (EditText) view.findViewById(R.id.gd__edt_new_passwrod);
                EditText edt_confirm_passwrod = (EditText) view.findViewById(R.id.gd__edt_confirm_passwrod);
                TextView tv_ok = (TextView) view.findViewById(R.id.gd__tv_ok);
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String oldPassword = edt_old_passwrod.getText().toString();
                        String newPassword = edt_new_passwrod.getText().toString();
                        String confirmPassword = edt_confirm_passwrod.getText().toString();
                        ChangePasswordHelper changePasswordHelper = new ChangePasswordHelper(oldPassword, newPassword, confirmPassword, BaseActivity.this);
                        changePasswordHelper.changePassword();
                    }
                });
            }

            @Override
            protected void onCloose() {
                super.onCloose();
                darkenBackground(1f);
            }
        };
        darkenBackground(0.5f);
        View viewById = pop.getView().findViewById(R.id.gd__ll_triangle_white_bottom);
        if (gravity == Gravity.CENTER) {
            if (viewById != null)
                viewById.setVisibility(View.GONE);
            pop.showPopupCenterWindow();
        } else {
            if (viewById != null)
                viewById.setVisibility(View.VISIBLE);
            pop.showPopupWindowBottom(v, ScreenUtil.dip2px(mContext, 200), ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20));
        }
    }

    private void chooseMusic(List<CheckBox> boxList, CheckBox chooseCheckBox) {
        if (currentBox.equals(chooseCheckBox)) {
            chooseCheckBox.setChecked(true);
            return;
        } else {
            currentBox = chooseCheckBox;
        }
        for (int i = 0; i < boxList.size(); i++) {
            CheckBox checkBox = boxList.get(i);
            if (checkBox.equals(chooseCheckBox)) {
                checkBox.setChecked(true);
                mAppViewModel.setMuzicIndex(i + 1);
                if (mAppViewModel.isbLobby() == false) {
                    mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
                }
            } else {
                checkBox.setChecked(false);
            }
        }
    }

    public void showChooseChip(View v) {
        PopChooseChip popChooseChip = new PopChooseChip(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
            @Override
            public void onChooseChipFinish() {
                onSwitchChipFinish();
            }
        };
        popChooseChip.showPopupCenterWindow();
    }

    public void onSwitchChipFinish() {

    }

    protected void showChangeTable(View v) {
        initOldBigRoad();
        tablePop.setPopTopContent(getGameChooseChip());
        if (tablePop.getParentCount() > 0) {
            mAppViewModel.setOpenChangeTable(true);
            tablePop.showPopupGravityWindow(Gravity.RIGHT, 0, 0);
        }
    }

    protected void showMenuPop(final View v) {


        PopChoiceLanguage<GameMenuItem> popSet;
        popSet = new PopChoiceLanguage<GameMenuItem>(mContext, v, ScreenUtil.dip2px(mContext, 100), ScreenUtil.dip2px(mContext, 120)) {
            @Override
            protected int onSetRcItemLayout() {
                return R.layout.gd_item_limit_red_text;
            }

            @Override
            public void onConvert(MyRecyclerViewHolder helper, int position, GameMenuItem item) {
                TextView tv = helper.getTextView(R.id.gd__text_tv1);
                tv.setCompoundDrawablesWithIntrinsicBounds(item.getDrawableRes(), 0, 0, 0);
                tv.setText(item.getTitle());
                tv.setTextColor(getResources().getColor(R.color.black_grey));
                tv.setPadding(ScreenUtil.dip2px(mContext, 5), ScreenUtil.dip2px(mContext, 8), ScreenUtil.dip2px(mContext, 5), 0);
            }

            @Override
            public void onClickItem(GameMenuItem item, int position) {
                if (item.getTitle().equals(getString(R.string.POOL))) {
                    showPool();
                } else if (item.getTitle().equals(getString(R.string.MUSIC))) {
                    showSetPop(null, Gravity.CENTER);
                } else if (item.getDrawableRes() == R.mipmap.gd_set_limit_green) {
                    showLimit();
                }

            }

            @Override
            public void initData() {
                super.initData();

            }

            @Override
            protected void onCloose() {
                super.onCloose();
                darkenBackground(1f);
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.gd_popupwindow_top_set_menu;
            }
        };
        darkenBackground(0.5f);
        popSet.setData(new ArrayList<GameMenuItem>(Arrays.asList(
                new GameMenuItem(R.mipmap.gd_set_music_green, getString(R.string.MUSIC), ""),
                new GameMenuItem(R.mipmap.gd_set_dollar_green, getString(R.string.POOL), ""),
                new GameMenuItem(R.mipmap.gd_set_limit_green, getString(R.string.LIMIT), "")

        )));

        darkenBackground(0.5f);
        popSet.showPopupDownWindow();
    }


    protected void showLimit() {

    }

    private void goBaccarat(int tableId) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
        mAppViewModel.setTableId(tableId);
        bundle.putBoolean("baccaratA", true);
        mAppViewModel.setbLobby(false);
        skipAct(BaccaratActivity.class, bundle);
        finish();
    }

    public void initBaccarat() {

    }

    private void initChangeTable(View v, String removeStr) {
        games = new ArrayList<>();
        if (WebSiteUrl.HEADER.equals("http://202.178.114.15/")) {
            addGame(games, new GameMenuItem(1, "LB1", ""), removeStr);
            addGame(games, new GameMenuItem(2, "LB2", ""), removeStr);
            addGame(games, new GameMenuItem(3, "LB3", ""), removeStr);
            addGame(games, new GameMenuItem(61, "LB5", ""), removeStr);
            addGame(games, new GameMenuItem(62, "LB6", ""), removeStr);
            addGame(games, new GameMenuItem(63, "LB7", ""), removeStr);
            addGame(games, new GameMenuItem(71, "BM1", ""), removeStr);
        } else {
            if (WebSiteUrl.GameType == 0 || !WebSiteUrl.isDomain || WebSiteUrl.GameType == 3) {
                addGame(games, new GameMenuItem(1, "LB1", ""), removeStr);
                addGame(games, new GameMenuItem(2, "LB2", ""), removeStr);
                addGame(games, new GameMenuItem(3, "LB3", ""), removeStr);
                addGame(games, new GameMenuItem(61, "LB5", ""), removeStr);
                addGame(games, new GameMenuItem(62, "LB6", ""), removeStr);
                addGame(games, new GameMenuItem(63, "LB7", ""), removeStr);
                addGame(games, new GameMenuItem(5, "DT1", ""), removeStr);
                addGame(games, new GameMenuItem(71, "BM1", ""), removeStr);
            }

            if (WebSiteUrl.GameType == 0 || !WebSiteUrl.isDomain || WebSiteUrl.GameType == 3) {
                addGame(games, new GameMenuItem(21, "RL1", ""), removeStr);
                addGame(games, new GameMenuItem(31, "SB1", ""), removeStr);
            }

        }
        int with = ScreenUtil.dip2px(mContext, 250);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            with = with * 2;
        }
        tablePop = new TableChangePop(mContext, v, with, ViewGroup.LayoutParams.MATCH_PARENT);
        tablePop.setClickImp(new ItemCLickImp<GameMenuItem>() {
            @Override
            public void itemCLick(View view, GameMenuItem gameMenuItem, int position) {
                if (mAppViewModel.getTableId() == gameMenuItem.getDrawableRes()) {
                    Toast.makeText(mContext, getString(R.string.your_here), Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (gameMenuItem.getDrawableRes()) {
                    case 1:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat1(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 2:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat2(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 3:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat3(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 61:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat5(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 62:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat6(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 63:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccarat7(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 71:
                        mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickBaccaratMi(true);
                        tablePop.getBaccaratBetContentBean(gameMenuItem.getDrawableRes()).getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateBaccaratBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 5:
                        mAppViewModel.getDragonTiger(gameMenuItem.getDrawableRes()).setGameStatus(-1);
                        mAppViewModel.setClickDragonTiger(true);
                        tablePop.getDragonTigerContentBean().getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateDragonTigerBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 21:
                        mAppViewModel.getRoulette01().setGameStatus(-1);
                        mAppViewModel.setClickRoulette(true);
                        tablePop.getRouletteContentBean().getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateRouletteBetMoney(gameMenuItem.getDrawableRes());
                        break;
                    case 31:
                        mAppViewModel.getSicbo01().setGameStatus(-1);
                        mAppViewModel.setClickSicbo(true);
                        tablePop.getSicboContentBean().getContentView().setVisibility(View.VISIBLE);
                        tablePop.updateSicboBetMoney(gameMenuItem.getDrawableRes());
                        break;
                }

//                tablePop.closePopupWindow();
//                if (mAppViewModel.getTableId() == 1 || mAppViewModel.getTableId() == 2 || mAppViewModel.getTableId() == 3 ||
//                        mAppViewModel.getTableId() == 71 || mAppViewModel.getTableId() == 61 || mAppViewModel.getTableId() == 62 || mAppViewModel.getTableId() == 63) {
//                    if (gameMenuItem.getDrawableRes() == 1 || gameMenuItem.getDrawableRes() == 2 || gameMenuItem.getDrawableRes() == 3 ||
//                            gameMenuItem.getDrawableRes() == 71 || gameMenuItem.getDrawableRes() == 61 || gameMenuItem.getDrawableRes() == 62 ||
//                            gameMenuItem.getDrawableRes() == 63) {
//                        if (mAppViewModel.getBaccarat(gameMenuItem.getDrawableRes()).getStatus() != 1) {
//                            Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        } else {
//                            mAppViewModel.setTableId(gameMenuItem.getDrawableRes());
//                            tableId = gameMenuItem.getDrawableRes();
//                            initBaccarat();
//                        }
//                        return;
//                    }
//                }
//                String menuStr = gameMenuItem.getTitle();
//                if (menuStr.equals("LB1")) {
//                    if (mAppViewModel.getBaccarat(1).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    mAppViewModel.setTableId(1);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat01().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat01().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    tableId = 1;
//                    goBaccarat(1);
//                } else if (menuStr.equals("LB2")) {
//                    if (mAppViewModel.getBaccarat(2).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 2;
//                    mAppViewModel.setTableId(2);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat02().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat02().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(2);
//                } else if (menuStr.equals("LB3")) {
//                    if (mAppViewModel.getBaccarat(3).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 3;
//                    mAppViewModel.setTableId(3);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat03().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat03().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(3);
//                } else if (menuStr.equals("BM1")) {
//                    if (mAppViewModel.getBaccarat(71).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 71;
//                    mAppViewModel.setTableId(71);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat71().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat71().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(71);
//                } else if (menuStr.equals("RL1")) {
//                    if (mAppViewModel.getRoulette01().getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    showBlockDialog();
//                    tableId = 21;
//                    Bundle bundle = new Bundle();
//                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "21");
//                    mAppViewModel.setTableId(21);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getRoulette01().getRouletteLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getRoulette01().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    skipAct(RouletteActivity.class, bundle);
//                    finish();
//                } else if (menuStr.equals("SB1")) {
//                    if (mAppViewModel.getSicbo01().getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 31;
//                    showBlockDialog();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "31");
//                    mAppViewModel.setTableId(31);
//                    String s = "";
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getSicbo01().getSicboLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getSicbo01().setLimitIndex(i);
//                            if (i == 1) {
//                                s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMaxTotalBet();
//                            } else if (i == 2) {
//                                s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMaxTotalBet();
//                            } else if (i == 3) {
//                                s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMaxTotalBet();
//                            } else {
//                                s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMaxTotalBet();
//                            }
//                            break;
//                        }
//                    }
//                    bundle.putString("limit", s);
//                    skipAct(SicboActivity.class, bundle);
//                    finish();
//                } else if (menuStr.equals("DT1")) {
//                    if (mAppViewModel.getDragonTiger01().getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 5;
//                    showBlockDialog();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "5");
//                    mAppViewModel.setTableId(5);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getDragonTiger01().getDragonTigerLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getDragonTiger01().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    skipAct(DragonTigerActivity.class, bundle);
//                    finish();
//                } else if (menuStr.equals("LB5")) {
//                    if (mAppViewModel.getBaccarat(61).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 61;
//                    mAppViewModel.setTableId(61);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat61().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat61().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(61);
//                } else if (menuStr.equals("LB6")) {
//                    if (mAppViewModel.getBaccarat(62).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 62;
//                    mAppViewModel.setTableId(62);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat62().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat62().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(62);
//                } else if (menuStr.equals("LB7")) {
//                    if (mAppViewModel.getBaccarat(63).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    tableId = 63;
//                    mAppViewModel.setTableId(63);
//                    for (int i = 1; i <= 4; i++) {
//                        if (mAppViewModel.getBaccarat63().getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                            mAppViewModel.getBaccarat63().setLimitIndex(i);
//                            break;
//                        }
//                    }
//                    goBaccarat(63);
//                } else if (menuStr.equals("B04")) {
//                    if (mAppViewModel.getBaccarat(64).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    changeSeatGame(64);
//                } else if (menuStr.equals("B05")) {
//                    if (mAppViewModel.getBaccarat(65).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    changeSeatGame(65);
//                } else if (menuStr.equals("B06")) {
//                    if (mAppViewModel.getBaccarat(66).getStatus() != 1) {
//                        Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    changeSeatGame(66);
//                }
//
            }
        });
        tablePop.setTablesData(mAppViewModel, games);
    }

    protected void showPool() {

    }


    public BaseApplication getApp() {
        return (BaseApplication) getApplication();
    }

    public void startUpdateStatus() {
        //   Log.i(WebSiteUrl.Tag, "startUpdateStatus() ");
        if (mAppViewModel.isbLogin() && updateGameStatus == null) {
            //        Log.i(WebSiteUrl.Tag, "StartUpdateGameStatus() Start");
            bGetGameStatus = true;
            bGetGameTimer = true;
            updateGameStatus = new UpdateGameStatus();
            threadGameStatus = new Thread(updateGameStatus);
            threadGameStatus.start();

            updateGameTimer = new UpdateGameTimer();
            threadGameTimer = new Thread(updateGameTimer);
            threadGameTimer.start();

            updateAllLobby = new UpdateAllHallIdGameStatus();
            Thread threadLobby = new Thread(updateAllLobby);
            threadLobby.start();

        }
    }

    public void stopUpdateStatus() {
        //   Log.i(WebSiteUrl.Tag, "Base StopUpdateGameStatus() ");
        if (updateGameStatus != null) { //Log.i(WebSiteUrl.Tag, "stopUpdateStatus() Start");
            bGetGameStatus = false;
            bGetGameTimer = false;
            updateGameStatus = null;
            threadGameStatus = null;
            updateGameTimer = null;
            threadGameTimer = null;
            updateAllLobby = null;
        }


    }


    //判断PopupWindow是不是存在，存在就把它dismiss掉
    private void dismissPopupWindow() {
  /*      if(popupWindow != null){
            popupWindow.closePopupWindow();
        }*/
        if (tablePop != null) {
            tablePop.closePopupWindow();
        }
        if (popupGameChoose != null) {
            popupGameChoose.closePopupWindow();
        }
    }

    protected void initArcMenu(TextView tvMenu, String removeStr, int hallId) {

        initChangeTable(tvMenu, removeStr);
    }

    private void addGame(ArrayList<GameMenuItem> games1, GameMenuItem item, String removeStr) {
//        if (!item.getTitle().equals(removeStr))
        games1.add(item);
    }

    public void changeSeatGame(int tId) {
        mAppViewModel.setTableId(tId);
        for (int i = 1; i <= 4; i++) {
            if (mAppViewModel.getBaccarat(tId).getBaccaratLimit(i).getMaxTotalBet() > 0) {
                mAppViewModel.getBaccarat(tId).setLimitIndex(i);
                break;
            }
        }
        tableId = tId;
        startSeatThread(tId);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        leftClick();
    }

    public boolean isLogin() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        if (!isLogin()) {
            ActivityPageManager.getInstance().addGd88Activity(this);
        }
//        AutoLayoutConifg.getInstance().setSize(this);
        initBetImg();

        super.onCreate(savedInstanceState);
        if (mAppViewModel.isbLogin()) {
            startUpdateStatus();
        }
        getMethodName();
    }

    @Override
    protected void initView() {
        mAppViewModel = getAppViewModelProvider().get(AppModel.class);
        super.initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMethodName();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMethodName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bGetGameStatus && !bGetGameTimer && mAppViewModel.isbLogin()) {
            startUpdateStatus();
        }
        getMethodName();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        stopUpdateStatus();
        dismissBlockDialog();
        dismissPopupWindow();
        getMethodName();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!WidgetUtil.isRunBackground(this)) {
            stopUpdateStatus();
        }
        getMethodName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUpdateStatus();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getMethodName();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getMethodName();
    }

    String getMethodName() {

        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        StackTraceElement a = (StackTraceElement) temp[2];
        StackTraceElement b = (StackTraceElement) temp[3];
        LogUtil.d("Life", getClass().getSimpleName() + ":" + a.getMethodName() + "-" + b.getMethodName());
        return a.getMethodName() + "-" + b.getMethodName();

    }

    public List<ChipBean> getCurrentChip(boolean isNeedAddSure) {
        List<ChipBean> list = new ArrayList<>();
        String chipContent = Gd88Utils.getChipContent(this);
        String[] split = chipContent.split("-");
        for (int i = 0; i < split.length; i++) {
            String chipSize = split[i];
            A:
            for (int j = 0; j < chipListChoice.size(); j++) {
                ChipBean chipBean = chipListChoice.get(j);
                String name = chipBean.getName();
                if (name.equals(chipSize)) {
                    list.add(chipBean);
                    break A;
                }

            }
        }
        list.add(new ChipBean(R.mipmap.gd_chip_choose, "CHOOSE", -101));
        if (isNeedAddSure) {
            list.add(new ChipBean(R.mipmap.gd_noimg, "", -2));
            list.add(new ChipBean(R.mipmap.gd_replayimg, "", -3));
            list.add(new ChipBean(R.mipmap.gd_sureimg, "", -1));
        }
        if (this != null && this instanceof RouletteActivity && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            List<ChipBean> listNew = new ArrayList<>();
            for (int i = list.size() - 1; i >= 0; i--) {
                listNew.add(list.get(i));
            }
            return listNew;
        } else {
            return list;
        }
    }

    public void initBetImg() {
        chipList = new ArrayList<>();
        chipListChoice = new ArrayList<>();
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip1, "1", 1));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip10, "10", 10));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip50, "50", 50));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip100, "100", 100));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip500, "500", 500));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip1k, "1000", 1000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip5k, "5000", 5000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip10k, "10k", 10000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip50k, "50k", 50000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip100k, "100k", 100000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip500k, "500k", 500000));
        chipListChoice.add(new ChipBean(R.mipmap.gd_chip_max, "MAX", 1000000));
        chipListCanNotChoice = new ArrayList<>();
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip1_false, "1", 1));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip10_false, "10", 10));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip50_false, "50", 50));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip100_false, "100", 100));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip500_false, "500", 500));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip1k_false, "1000", 1000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip5k_false, "5000", 5000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip10k_false, "10k", 10000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip50k_false, "50k", 50000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip100k_false, "100k", 100000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip500k_false, "500k", 500000));
        chipListCanNotChoice.add(new ChipBean(R.mipmap.gd_chip_max_false, "MAX", 1000000));
        chipList.add(new ChipBean(R.mipmap.gd_chip1_show, "1", 1));
        chipList.add(new ChipBean(R.mipmap.gd_chip10_show, "10", 10));
        chipList.add(new ChipBean(R.mipmap.gd_chip50_show, "50", 50));
        chipList.add(new ChipBean(R.mipmap.gd_chip100_show, "100", 100));
        chipList.add(new ChipBean(R.mipmap.gd_chip500_show, "500", 500));
        chipList.add(new ChipBean(R.mipmap.gd_chip1k_show, "1000", 1000));
        chipList.add(new ChipBean(R.mipmap.gd_chip5k_show, "5000", 5000));
        chipList.add(new ChipBean(R.mipmap.gd_chip10k_show, "10k", 10000));
        chipList.add(new ChipBean(R.mipmap.gd_chip50k_show, "50k", 50000));
        chipList.add(new ChipBean(R.mipmap.gd_chip100k_show, "100k", 100000));
        chipList.add(new ChipBean(R.mipmap.gd_chip500k_show, "500k", 500000));
        chipList.add(new ChipBean(R.mipmap.gd_chip_max_show, "MAX", 1000000));
    }

    public ArrayList<ChipBean> chipList;
    public ArrayList<ChipBean> chipListChoice;
    public ArrayList<ChipBean> chipListCanNotChoice;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    protected void darkenBackground(float color) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = color;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    public void setToolbarNameAndBalance() {
        llCenter.setVisibility(View.VISIBLE);
        rightTv.setVisibility(View.GONE);
        toolbar_right_top_tv.setText(mAppViewModel.getUser().getName());
        toolbar_right_top_tv.setTextColor(Color.parseColor("#ffffff"));
        toolbar_right_bottom_tv.setText(mAppViewModel.getUser().getBalance() + "");
        toolbar_right_bottom_tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.gd_dollar_yellow, 0, 0, 0);
        toolbar_right_bottom_tv.setTextColor(getResources().getColor(R.color.yellow_gold));
    }

    public void setToolbarAndSet(String up, String down) {
        llCenter.setVisibility(View.VISIBLE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.LayoutParams tableParams = (LinearLayout.LayoutParams) rightTableTv.getLayoutParams();
            tableParams.width = ScreenUtil.dip2px(mContext, 27);
            tableParams.height = ScreenUtil.dip2px(mContext, 27);
            rightTableTv.setLayoutParams(tableParams);
            rightTableTv.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams musicParams = (LinearLayout.LayoutParams) rightMusicTv.getLayoutParams();
            musicParams.width = ScreenUtil.dip2px(mContext, 27);
            musicParams.height = ScreenUtil.dip2px(mContext, 27);
            rightMusicTv.setLayoutParams(musicParams);
            rightMusicTv.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rightTv.getLayoutParams();
            params.width = ScreenUtil.dip2px(mContext, 27);
            params.height = ScreenUtil.dip2px(mContext, 27);
            rightTv.setLayoutParams(params);
            rightTv.setVisibility(View.VISIBLE);
            rightBetTv.setVisibility(View.VISIBLE);
            rightWinLoseTv.setVisibility(View.VISIBLE);
            rightBalanceTv.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams reportParams = (LinearLayout.LayoutParams) rightReportTv.getLayoutParams();
            reportParams.width = ScreenUtil.dip2px(mContext, 27);
            reportParams.height = ScreenUtil.dip2px(mContext, 27);
            rightReportTv.setLayoutParams(reportParams);
        } else {
            LinearLayout.LayoutParams tableParams = (LinearLayout.LayoutParams) rightTableTv.getLayoutParams();
            tableParams.width = ScreenUtil.dip2px(mContext, 24);
            tableParams.height = ScreenUtil.dip2px(mContext, 24);
            rightTableTv.setLayoutParams(tableParams);
            LinearLayout.LayoutParams musicParams = (LinearLayout.LayoutParams) rightMusicTv.getLayoutParams();
            musicParams.width = ScreenUtil.dip2px(mContext, 24);
            musicParams.height = ScreenUtil.dip2px(mContext, 24);
            rightMusicTv.setLayoutParams(musicParams);
            rightMusicTv.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rightTv.getLayoutParams();
            params.width = ScreenUtil.dip2px(mContext, 24);
            params.height = ScreenUtil.dip2px(mContext, 24);
            rightTv.setLayoutParams(params);
            rightTv.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams reportParams = (LinearLayout.LayoutParams) rightReportTv.getLayoutParams();
            reportParams.width = ScreenUtil.dip2px(mContext, 24);
            reportParams.height = ScreenUtil.dip2px(mContext, 24);
            rightReportTv.setLayoutParams(reportParams);
            LinearLayout.LayoutParams logoutParams = (LinearLayout.LayoutParams) logoutTv.getLayoutParams();
            logoutParams.width = ScreenUtil.dip2px(mContext, 24);
            logoutParams.height = ScreenUtil.dip2px(mContext, 24);
            logoutTv.setLayoutParams(logoutParams);
            if (this != null) {
                if (this instanceof RouletteActivity || this instanceof SicboActivity) {
                    logoutTv.setVisibility(View.GONE);
                } else {
                    logoutTv.setVisibility(View.VISIBLE);
                }
            }
        }
        rightReportTv.setVisibility(View.VISIBLE);
        rightReportTv.setBackgroundResource(R.mipmap.gd_report_top);
        logoutTv.setBackgroundResource(R.mipmap.logout_gd);
        rightTv.setBackgroundResource(R.mipmap.gd_pool_top);
        rightMusicTv.setBackgroundResource(R.mipmap.gd_set_black);
        rightTableTv.setBackgroundResource(R.mipmap.gd_table_top);
        rightReportTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopReport popReport = new PopReport(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popReport.showPopupCenterWindow();
            }
        });
        logoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(BuildConfig.FLAVOR) || WebSiteUrl.PROJECT.contains("kgapi")) {
                    ActivityPageManager.getInstance().finishGd88AllActivity();
                } else {
                    stopUpdateStatus();
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
                    skipAct(LoginActivity.class, bundle);
                }
            }
        });
        rightTableTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeTable(v);
            }
        });
        rightMusicTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetPop(null, Gravity.CENTER);
            }
        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuPop(view);
            }
        });

    }

    public void logout() {
        if (mAppViewModel != null)
            mAppViewModel.setbInitLimit(false);
        stopUpdateStatus();
        if (BuildConfig.FLAVOR.isEmpty() || WebSiteUrl.PROJECT.contains("kgapi")) {
            finish();
            return;
        }
        if (WebSiteUrl.isDomain) {
            ActivityPageManager.getInstance().finishAllActivity();
            return;
        }
        new Thread(new Logout()).start();
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        skipAct(LoginActivity.class, bundle);

    }

    public class Logout implements Runnable {
        public void run() {
            try {
                mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGOUT_URL, "logout=out");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean requestPermission(String[] PERMISSIONS, int PERMISSIONS_CODE) {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            for (String s : PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(this, s) != PERMISSION_GRANTED) {
                    //如果没有写sd卡权限
                    isGranted = false;
                }
            }
            if (!isGranted) {
                ActivityCompat.requestPermissions(this,
                        PERMISSIONS,
                        PERMISSIONS_CODE);
            }
        }
        return isGranted;
    }

    public void initAutoSize() {

    }

    public void initOldBigRoad() {
        mAppViewModel.getBaccarat(1).setBigRoadOld("");
        mAppViewModel.getBaccarat(2).setBigRoadOld("");
        mAppViewModel.getBaccarat(3).setBigRoadOld("");
        mAppViewModel.getBaccarat(61).setBigRoadOld("");
        mAppViewModel.getBaccarat(62).setBigRoadOld("");
        mAppViewModel.getBaccarat(63).setBigRoadOld("");
        mAppViewModel.getBaccarat(71).setBigRoadOld("");
        mAppViewModel.getDragonTiger01().setBigRoadOld("");
        mAppViewModel.getRoulette01().setRoadOld("");
        mAppViewModel.getSicbo01().setRoadOld("");
    }

    public ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider(getApp().getViewModelStore(), getApp().getViewModelFactory());
    }

    long currentTime;

    protected void fromAfb1188(int type, Bundle extras) {
        //http://112-alias-api.gd88.org/cklogin.jsp?txtAcctid=TestSH20&txtPwd=12345678&txtLang=0&txtRandCode=qwtpbS8vRnYZedYdD1kWvnPwcAC1jijhVB90dQw1DpEJSG2Kvln
        //http://112-alias-api.gd88.org/select_tb_infoa.jsp
        //http://112-alias-api.gd88.org/GDWebService?wsdl
        WebSiteUrl.isDomain = true;
        LogIntervalUtils.logCustomTime(currentTime, "开始启动登录线程");
        if (type == 5)
            WebSiteUrl.setOther("http://112api.gd09.info/", "");
        else {
            WebSiteUrl.setOther("http://afb88.bpt88.net/", "OLTGames/");
        }
        //https://112api.gd88bet.net/cklogin.jsp?txtAcctid=Demoafba0310&txtPwd=12345678&txtLang=0&txtRandCode=Ma5qXnw1HuauTpIpzO5WLWkE7tBgduFHmtNCClCEx4tM1xHZloL
        mAppViewModel.setCookie("");
//http://112api.gd88bet.net/main.jsp?membername=DEMOAFBA0311&lang=1

        /*  intent.putString("username", info.getLoginName());
        intent.putString("password", info.getPassword());
        intent.putString("language", "en");*/

        String username = extras.getString("username").toUpperCase();//转成大写;
//        String password = extras.getString("password");
        String password = "12345678";
        String balance = extras.getString("balance");
        String curCode = extras.getString("curCode");


        postNewThread(new Runnable() {
            @Override
            public void run() {
                String strAgent = Gd88Utils.getCurCodeMap().get(curCode);
                String sbUrl = "http://112api.gd09.info/player/afb1188/GD88WebService?wsdl";
                String sbParam = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://gd88/\"><soapenv:Header/><soapenv:Body><maw:CreateAccount><!--Optional:--><strUsername>"
                        + username + "</strUsername><Api_key>"
                        + "j0h93zNB7VDGn4TJEMbnm8WfpOuLMDwl"
                        + "</Api_key><strPassword>"
                        + password + "</strPassword><strAgent>"
                        + strAgent + "</strAgent></maw:CreateAccount></soapenv:Body></soapenv:Envelope>";

                HttpClient httpClient = new HttpClient(sbUrl
                        , "");
                String depositStr = httpClient.sendPostSoap(sbUrl, sbParam);
                LogIntervalUtils.logCustomTime(currentTime, "sbUrl:" + sbUrl + ",depositStr:" + depositStr);
                LogIntervalUtils.logCustomTime(currentTime, ",sbParam:" + sbParam);

                String urlLogin = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + BuildConfig.loginjsp + "?txtAcctid=" + username + "&txtPwd=12345678&txtLang=0";
                mAppViewModel.setHttpClient(new HttpClient(urlLogin
                        , mAppViewModel.getCookie()));
                mAppViewModel.getUser().setName(username);
                if (balance != null) {
                    mAppViewModel.getUser().setBalance(Double.parseDouble(balance));
                }
                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                try {
                    String strRes = mAppViewModel.getHttpClient().getBodyString("UTF-8");
                    mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                    LogIntervalUtils.logCustomTime(currentTime, urlLogin + "初始化cookie完成:" + strRes);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                checkSucceed(0x0001);
            }
        });

//        postNewThread(postGameGG());
//        postNewThread(postTableInfoA());
//        postNewThread(postTimer());
    }

    private Runnable postTimer() {
        return new Runnable() {
            @Override
            public void run() {
                String strRes;
                LogIntervalUtils.logCustomTime(currentTime, "开始" + WebSiteUrl.COUNTDOWN_URL_A);
                strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                if (strRes.equals("netError")) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.COUNTDOWN_URL_A + "完成0x100");

                mAppViewModel.splitTimer(strRes);
                checkSucceed(0x1000);
            }
        };
    }

    private Runnable postTableInfoA() {
        return new Runnable() {
            @Override
            public void run() {
                String strRes;
                LogIntervalUtils.logCustomTime(currentTime, "开始Table数据" + WebSiteUrl.TABLE_INFO_A_URL);
                strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLE_INFO_A_URL, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());

                if (strRes.equals("netError")) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                String tableInfo[] = strRes.split("\\^");
                if (tableInfo.length < 12) {
                    handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                    return;
                }
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.TABLE_INFO_A_URL + "完成0x010");
                mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
                checkSucceed(0x0100);
            }
        };
    }

    private void postNewThread(Runnable runnable) {
        ThreadPoolUtils.execute(runnable);
    }

    private Runnable postGameGG() {
        return new Runnable() {
            @Override
            public void run() {
                String strRes;
                String language1 = "0";
                String annoucementParams = "lng=" + language1 + "&Usid=" + mAppViewModel.getUser().getName();
                LogIntervalUtils.logCustomTime(currentTime, "开始" + WebSiteUrl.GAME_GG_URL);
                strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                if (strRes.equals("netError")) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.GAME_GG_URL + "完成0x001");
                String ann[] = strRes.split("Results=ok\\|");
                if (ann.length > 1)
                    mAppViewModel.setAnnouncement(ann[1]);
                checkSucceed(0x0010);
            }
        };

    }

    private volatile int hasSucceed = 0x0000;

    private void checkSucceed(int type) {
        hasSucceed = hasSucceed | type;
        if ((hasSucceed & 0x0001) == 0x0001) {
            mAppViewModel.setbLogin(true);
            mAppViewModel.setbLobby(true);
            handler.sendEmptyMessage(ErrorCode.LOGIN_SECCESS);
            LogIntervalUtils.logCustomTime(currentTime, "LOGIN_SECCESS" + "完成进入游戏------");
        }
    }

    public void setTitleChangeGame(TextView title) {
        if (!BuildConfig.FLAVOR.isEmpty())
            return;
        title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.gd_arrow_down_grey_light, 0);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherGameChoose(title);
            }
        });

    }

    private void initGamePopList(TextView title) {
        popWindow = new BaseListPopupWindow<MenuItemInfo<Class>>(mContext, title, DeviceUtils.dip2px(this, 160), LinearLayout.LayoutParams.WRAP_CONTENT, title) {

            @Override
            public void onConvert(com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder holder, int position, MenuItemInfo<Class> item) {
                TextView tv = holder.getView(com.unkonw.testapp.R.id.item_regist_text_tv);
                tv.setAllCaps(true);
                tv.setText(item.getText());
                tv.setTextSize(16);
                tv.setSelected(true);
                tv.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.green_table_color));
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                tv.setTextColor(ContextCompat.getColor(BaseActivity.this, R.color.white));
            }

            @Override
            protected void clickItem(TextView tv, MenuItemInfo<Class> item) {
                super.clickItem(tv, item);
                skipAct(item.getParent());
                closePopupWindow();
                finish();
            }

            @Override
            protected int onSetLayoutRes() {
                return R.layout.gd_layout_base_recycler_choose_game;
            }

            public int getItemLayoutRes() {
                return R.layout.gd_choose_game_item;

            }
        };
        popWindow.setTrans(1f);
    }

    BaseListPopupWindow<MenuItemInfo<Class>> popWindow;

    public void showOtherGameChoose(TextView title) {
        Class aClass = this.getClass();
        ArrayList<MenuItemInfo<Class>> menuItemInfos = new ArrayList<>(Arrays.asList(
                new MenuItemInfo<Class>(R.mipmap.gd_ba1, getString(R.string.baccarat), "", LobbyBaccaratActivity.class),
                new MenuItemInfo<Class>(R.mipmap.gd_longhu, getString(R.string.dragon_tiger), "", LobbyDragonTigerActivity.class),
                new MenuItemInfo<Class>(R.mipmap.gd_lunpan, getString(R.string.roulette), "", LobbyRouletteActivity.class),
                new MenuItemInfo<Class>(R.mipmap.gd_toubao, getString(R.string.sicbo), "", LobbySicboActivity.class)
        ));
        Iterator<MenuItemInfo<Class>> iterator = menuItemInfos.iterator();
        while (iterator.hasNext()) {
            MenuItemInfo<Class> next = iterator.next();
            if (aClass.equals(next.getParent())) {
                iterator.remove();
                break;
            }
        }
        if (popWindow == null)
            initGamePopList(title);
        popWindow.setData(menuItemInfos);
        popWindow.showPopupDownWindow();
    }

    public boolean isCanSlideChangeTable() {
        return false;
    }

    int lastX, lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isCanSlideChangeTable()) {
            return super.dispatchTouchEvent(event);
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                if (Math.abs(offsetX) > 350 || Math.abs(offsetY) > 350) {
                    if (Math.abs(offsetX) > Math.abs(offsetY)) {
                        if (this instanceof SlotsWebActivity) {
                            //slots暂时没得左右滑
                        } else {
                            if (offsetX > 0) {
                                GameSlideChangeTableHelper.changeTable(BaseActivity.this, mAppViewModel.getTableId(), GameSlideChangeTableHelper.SlideRight);
                            } else {
                                GameSlideChangeTableHelper.changeTable(BaseActivity.this, mAppViewModel.getTableId(), GameSlideChangeTableHelper.SlideLeft);
                            }
                        }
                    } else {
                        if (this instanceof SlotsWebActivity) {
                            if (offsetY > 0) {
                                GameSlideChangeTableHelper.slotsChangeTable(BaseActivity.this, GameSlideChangeTableHelper.SlideDown);
                            } else {
                                GameSlideChangeTableHelper.slotsChangeTable(BaseActivity.this, GameSlideChangeTableHelper.SlideUp);
                            }
                        } else {
                            if (offsetY > 0) {
                                GameSlideChangeTableHelper.changeTable(BaseActivity.this, mAppViewModel.getTableId(), GameSlideChangeTableHelper.SlideDown);
                            } else {
                                GameSlideChangeTableHelper.changeTable(BaseActivity.this, mAppViewModel.getTableId(), GameSlideChangeTableHelper.SlideUp);
                            }
                        }
                    }
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public PopSlideHint popSlideHint;

    public void checkSlideHint(View view) {
        String tag = "";
        Object tagObject = view.getTag();
        if (tagObject != null) {
            tag = tagObject.toString();
        }
        int orientation = getResources().getConfiguration().orientation;
        Integer times;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            String key = AppConfig.ACTION_KEY_SLIDE_HINT_P;
            if (!TextUtils.isEmpty(tag)) {
                if (tag.equals("SLOTS")) {
                    key = AppConfig.ACTION_KEY_SLIDE_HINT_SLOTS;
                } else {
                    key = AppConfig.ACTION_KEY_SLIDE_HINT_CQ9;
                }
            }
            times = (Integer) AppTool.getObjectData(mContext, key);
        } else {
            String key = AppConfig.ACTION_KEY_SLIDE_HINT_l;
            if (!TextUtils.isEmpty(tag)) {
                if (tag.equals("SLOTS")) {
                    key = AppConfig.ACTION_KEY_SLIDE_HINT_SLOTS;
                } else {
                    key = AppConfig.ACTION_KEY_SLIDE_HINT_CQ9;
                }
            }
            times = (Integer) AppTool.getObjectData(mContext, key);
        }
        if (times == null || times < 3) {
            if (popSlideHint == null) {
                popSlideHint = new PopSlideHint(mContext, view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            popSlideHint.setTag(tag);
            if (!popSlideHint.isShowing()) {
                popSlideHint.initOrientation();
                popSlideHint.showPopupCenterWindow();
            }
        }
    }

    private int gameChooseChip;

    public int getGameChooseChip() {
        return gameChooseChip;
    }

    public void setGameChooseChip(int gameChooseChip) {
        this.gameChooseChip = gameChooseChip;
    }
}
