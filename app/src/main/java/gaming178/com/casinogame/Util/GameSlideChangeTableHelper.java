package gaming178.com.casinogame.Util;


import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.BaccaratActivity;
import gaming178.com.casinogame.Activity.DragonTigerActivity;
import gaming178.com.casinogame.Activity.RouletteActivity;
import gaming178.com.casinogame.Activity.SicboActivity;
import gaming178.com.casinogame.Activity.SlotsWebActivity;
import gaming178.com.casinogame.Activity.entity.CQSlotsGameInfoBean;
import gaming178.com.casinogame.Bean.SlotsBean;
import gaming178.com.casinogame.base.BaseActivity;

public class GameSlideChangeTableHelper {

    private static List<Integer> portraitList;
    private static List<Integer> landscapeList;
    public static int SlideUp = 1;
    public static int SlideDown = 2;
    public static int SlideLeft = 3;
    public static int SlideRight = 4;

    private static List<Integer> getSlideList(int orientation) {
        if (portraitList == null) {
            portraitList = new ArrayList<>();
            portraitList.add(1);
            portraitList.add(2);
            portraitList.add(3);
            portraitList.add(61);
            portraitList.add(62);
            portraitList.add(63);
            portraitList.add(71);
            portraitList.add(5);
            portraitList.add(21);
            portraitList.add(31);
        }
        if (landscapeList == null) {
            landscapeList = new ArrayList<>();
            landscapeList.add(1);
            landscapeList.add(2);
            landscapeList.add(3);
            landscapeList.add(61);
            landscapeList.add(62);
            landscapeList.add(63);
            landscapeList.add(71);
            landscapeList.add(5);
            landscapeList.add(21);
            landscapeList.add(31);
        }
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return portraitList;
        } else {
            return landscapeList;
        }
    }

    public static void changeTable(BaseActivity baseActivity, int tableId, int slideType) {
        int orientation = baseActivity.getResources().getConfiguration().orientation;
        List<Integer> slideList = getSlideList(orientation);
        int index = -1;
        for (int i = 0; i < slideList.size(); i++) {
            int listTableId = slideList.get(i);
            if (listTableId == tableId) {
                index = i;
                break;
            }
        }
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (slideType == SlideUp || slideType == SlideDown) {
                if (slideType == SlideDown) {
                    int toTableIdIndex = getToTableIdIndex(index, slideList, Configuration.ORIENTATION_PORTRAIT, SlideDown, baseActivity);
                    int toTableId = slideList.get(toTableIdIndex);
                    if (index != toTableIdIndex) {
                        goGame(baseActivity, toTableId, R.anim.slide_in_top, R.anim.slide_out_bottom);
                    }
                } else {
                    int toTableIdIndex = getToTableIdIndex(index, slideList, Configuration.ORIENTATION_PORTRAIT, SlideUp, baseActivity);
                    int toTableId = slideList.get(toTableIdIndex);
                    if (index != toTableIdIndex) {
                        goGame(baseActivity, toTableId, R.anim.slide_in_bottom, R.anim.slide_out_top);
                    }
                }
            }
        } else {
            if (slideType == SlideLeft || slideType == SlideRight) {
                if (slideType == SlideRight) {
                    int toTableIdIndex = getToTableIdIndex(index, slideList, Configuration.ORIENTATION_LANDSCAPE, SlideRight, baseActivity);
                    int toTableId = slideList.get(toTableIdIndex);
                    if (index != toTableIdIndex) {
                        goGame(baseActivity, toTableId, R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                } else {
                    int toTableIdIndex = getToTableIdIndex(index, slideList, Configuration.ORIENTATION_LANDSCAPE, SlideLeft, baseActivity);
                    int toTableId = slideList.get(toTableIdIndex);
                    if (index != toTableIdIndex) {
                        goGame(baseActivity, toTableId, R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }
        }
    }

    private static void goGame(BaseActivity baseActivity, int tableId, int enter, int exit) {
        baseActivity.mAppViewModel.setTableId(tableId);
        baseActivity.tableId = tableId;
        switch (tableId) {
            case 1:
            case 2:
            case 3:
            case 61:
            case 62:
            case 63:
            case 71:
                Bundle bundle = new Bundle();
                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
                baseActivity.mAppViewModel.setTableId(tableId);
                bundle.putBoolean("baccaratA", true);
                baseActivity.mAppViewModel.setbLobby(false);
                baseActivity.skipAct(BaccaratActivity.class, bundle);
                break;
            case 5:
                Bundle bundle1 = new Bundle();
                bundle1.putString(AppConfig.ACTION_KEY_INITENT_DATA, "5");
                baseActivity.mAppViewModel.setTableId(tableId);
                for (int i = 1; i <= 4; i++) {
                    if (baseActivity.mAppViewModel.getDragonTiger01().getDragonTigerLimit(i).getMaxTotalBet() > 0) {
                        baseActivity.mAppViewModel.getDragonTiger01().setLimitIndex(i);
                        break;
                    }
                }
                baseActivity.skipAct(DragonTigerActivity.class, bundle1);
                break;
            case 21:
                Bundle bundle2 = new Bundle();
                bundle2.putString(AppConfig.ACTION_KEY_INITENT_DATA, "21");
                baseActivity.mAppViewModel.setTableId(tableId);
                for (int i = 1; i <= 4; i++) {
                    if (baseActivity.mAppViewModel.getRoulette01().getRouletteLimit(i).getMaxTotalBet() > 0) {
                        baseActivity.mAppViewModel.getRoulette01().setLimitIndex(i);
                        break;
                    }
                }
                baseActivity.skipAct(RouletteActivity.class, bundle2);
                break;
            case 31:
                Bundle bundle3 = new Bundle();
                bundle3.putString(AppConfig.ACTION_KEY_INITENT_DATA, "31");
                baseActivity.mAppViewModel.setTableId(tableId);
                String s = "";
                for (int i = 1; i <= 4; i++) {
                    if (baseActivity.mAppViewModel.getSicbo01().getSicboLimit(i).getMaxTotalBet() > 0) {
                        baseActivity.mAppViewModel.getSicbo01().setLimitIndex(i);
                        if (i == 1) {
                            s = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit1().getMaxTotalBet();
                        } else if (i == 2) {
                            s = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit2().getMaxTotalBet();
                        } else if (i == 3) {
                            s = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit3().getMaxTotalBet();
                        } else {
                            s = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit4().getMaxTotalBet();
                        }
                        break;
                    }
                }
                bundle3.putString("limit", s);
                baseActivity.skipAct(SicboActivity.class, bundle3);
                break;
        }
        baseActivity.overridePendingTransition(enter, exit);
        baseActivity.finish();
    }

    private static int getToTableIdIndex(int index, List<Integer> slideList, int orientation, int slideType, BaseActivity baseActivity) {
        int gameStatus = -1;
        int myIndex = index;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (slideType == SlideDown) {
                while (gameStatus == -1) {
                    myIndex--;
                    if (myIndex < 0) {
                        myIndex = 9;
                    }
                    gameStatus = getGameStatus(slideList.get(myIndex), baseActivity);
                    if (gameStatus != 1) {
                        gameStatus = -1;
                    }
                }
            } else {
                while (gameStatus == -1) {
                    myIndex++;
                    if (myIndex > 9) {
                        myIndex = 0;
                    }
                    gameStatus = getGameStatus(slideList.get(myIndex), baseActivity);
                    if (gameStatus != 1) {
                        gameStatus = -1;
                    }
                }
            }
        } else {
            if (slideType == SlideRight) {
                while (gameStatus == -1) {
                    myIndex--;
                    if (myIndex < 0) {
                        myIndex = 9;
                    }
                    gameStatus = getGameStatus(slideList.get(myIndex), baseActivity);
                    if (gameStatus != 1) {
                        gameStatus = -1;
                    }
                }
            } else {
                while (gameStatus == -1) {
                    myIndex++;
                    if (myIndex > 9) {
                        myIndex = 0;
                    }
                    gameStatus = getGameStatus(slideList.get(myIndex), baseActivity);
                    if (gameStatus != 1) {
                        gameStatus = -1;
                    }
                }
            }
        }
        return myIndex;
    }

    private static int getGameStatus(int tableId, BaseActivity baseActivity) {
        if (tableId == 5) {
            return baseActivity.mAppViewModel.getDragonTiger01().getStatus();
        } else if (tableId == 21) {
            return baseActivity.mAppViewModel.getRoulette01().getStatus();
        } else if (tableId == 31) {
            return baseActivity.mAppViewModel.getSicbo01().getStatus();
        } else {
            return baseActivity.mAppViewModel.getBaccarat(tableId).getStatus();
        }
    }

    public static void slotsChangeTable(BaseActivity baseActivity, int slideType) {
        String slideGameType = baseActivity.mAppViewModel.getSlideGameType();
        if (slideGameType.equals("SLOTS")) {
            SlotsBean slotsBean = baseActivity.mAppViewModel.getSlotsBean();
            List<SlotsBean.DataBean> dataList = slotsBean.getData();
            if (slotsBean != null && slotsBean.getData() != null && slotsBean.getData().size() > 0) {
                int from = baseActivity.mAppViewModel.getSlotsCurrentIndex();
                int to = -1;
                if (slideType == SlideDown) {
                    to = from - 1;
                    if (to < 0) {
                        to = dataList.size() - 1;
                    }
                    SlotsBean.DataBean item = dataList.get(to);
                    baseActivity.mAppViewModel.setSlideGameType("SLOTS");
                    baseActivity.mAppViewModel.setSlotsCurrentIndex(to);
                    Bundle bundle = new Bundle();
                    String url = item.getUrl() + "?" + "platform=" + item.getPlatform() + "&language=" + item.getLanguage() + "&game=" + item.getGame() +
                            "&id=" + item.getId() + "&username=" + item.getUsername() + "&token=" + item.getToken();
                    bundle.putString("url", url);
                    bundle.putString("gameType", "SLOTS");
                    baseActivity.skipAct(SlotsWebActivity.class, bundle);
                    baseActivity.overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                    baseActivity.finish();
                } else {
                    to = from + 1;
                    if (to > dataList.size() - 1) {
                        to = 0;
                    }
                    SlotsBean.DataBean item = dataList.get(to);
                    baseActivity.mAppViewModel.setSlideGameType("SLOTS");
                    baseActivity.mAppViewModel.setSlotsCurrentIndex(to);
                    Bundle bundle = new Bundle();
                    String url = item.getUrl() + "?" + "platform=" + item.getPlatform() + "&language=" + item.getLanguage() + "&game=" + item.getGame() +
                            "&id=" + item.getId() + "&username=" + item.getUsername() + "&token=" + item.getToken();
                    bundle.putString("url", url);
                    bundle.putString("gameType", "SLOTS");
                    baseActivity.skipAct(SlotsWebActivity.class, bundle);
                    baseActivity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
                    baseActivity.finish();
                }
            }
        } else {
            CQSlotsGameInfoBean cqSlotsBean = baseActivity.mAppViewModel.getCqSlotsBean();
            List<CQSlotsGameInfoBean.DataBean> dataList = cqSlotsBean.getData();
            if (cqSlotsBean != null && cqSlotsBean.getData() != null && cqSlotsBean.getData().size() > 0) {
                int from = baseActivity.mAppViewModel.getCqSlotsCurrentIndex();
                int to = -1;
                if (slideType == SlideDown) {
                    to = from - 1;
                    if (to < 0) {
                        to = dataList.size() - 1;
                    }
                    CQSlotsGameInfoBean.DataBean item = dataList.get(to);
                    baseActivity.mAppViewModel.setSlideGameType("CQ9");
                    baseActivity.mAppViewModel.setCqSlotsCurrentIndex(to);
                    new Thread() {
                        @Override
                        public void run() {
                            String gameUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "cq9path.jsp";
                            String param = "game_usid=" + item.getId();
                            String result = baseActivity.mAppViewModel.getHttpClient().sendPostCQ(gameUrl, param);
                            if (result.startsWith("Results=ok")) {
                                String[] split = result.split("#");
                                String loadUrl = split[1] + "&" + split[2];
                                Bundle bundle = new Bundle();
                                bundle.putString("url", loadUrl);
                                bundle.putString("gameType", "CQ9");
                                baseActivity.skipAct(SlotsWebActivity.class, bundle);
                                baseActivity.overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                                baseActivity.finish();
                            }
                        }
                    }.start();
                } else {
                    to = from + 1;
                    if (to > dataList.size() - 1) {
                        to = 0;
                    }
                    CQSlotsGameInfoBean.DataBean item = dataList.get(to);
                    baseActivity.mAppViewModel.setSlideGameType("CQ9");
                    baseActivity.mAppViewModel.setCqSlotsCurrentIndex(to);
                    new Thread() {
                        @Override
                        public void run() {
                            String gameUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "cq9path.jsp";
                            String param = "game_usid=" + item.getId();
                            String result = baseActivity.mAppViewModel.getHttpClient().sendPostCQ(gameUrl, param);
                            if (result.startsWith("Results=ok")) {
                                String[] split = result.split("#");
                                String loadUrl = split[1] + "&" + split[2];
                                Bundle bundle = new Bundle();
                                bundle.putString("url", loadUrl);
                                bundle.putString("gameType", "CQ9");
                                baseActivity.skipAct(SlotsWebActivity.class, bundle);
                                baseActivity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
                                baseActivity.finish();
                            }
                        }
                    }.start();
                }
            }
        }
    }
}
