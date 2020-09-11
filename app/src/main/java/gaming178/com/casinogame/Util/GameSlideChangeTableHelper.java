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
            portraitList.add(5);
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
                    if (index > 0) {
                        int toTableId = slideList.get(index - 1);
                        goGame(baseActivity, toTableId, R.anim.slide_in_top, R.anim.slide_out_bottom);
                    }else {
                        int toTableId = slideList.get(6);
                        goGame(baseActivity, toTableId, R.anim.slide_in_top, R.anim.slide_out_bottom);
                    }
                } else {
                    if (index < 6) {
                        int toTableId = slideList.get(index + 1);
                        goGame(baseActivity, toTableId, R.anim.slide_in_bottom, R.anim.slide_out_top);
                    }else {
                        int toTableId = slideList.get(0);
                        goGame(baseActivity, toTableId, R.anim.slide_in_bottom, R.anim.slide_out_top);
                    }
                }
            }
        } else {
            if (slideType == SlideLeft || slideType == SlideRight) {
                if (slideType == SlideRight) {
                    if (index > 0) {
                        int toTableId = slideList.get(index - 1);
                        goGame(baseActivity, toTableId, R.anim.slide_in_left, R.anim.slide_out_right);
                    }else {
                        int toTableId = slideList.get(9);
                        goGame(baseActivity, toTableId, R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                } else {
                    if (index < 9) {
                        int toTableId = slideList.get(index + 1);
                        goGame(baseActivity, toTableId, R.anim.slide_in_right, R.anim.slide_out_left);
                    }else {
                        int toTableId = slideList.get(0);
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
//                for (int i = 1; i <= 4; i++) {
//                    if (baseActivity.mAppViewModel.getBaccarat(tableId).getBaccaratLimit(i).getMaxTotalBet() > 0) {
//                        baseActivity.mAppViewModel.getBaccarat(tableId).setLimitIndex(i);
//                        break;
//                    }
//                }
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

}