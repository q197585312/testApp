package gaming178.com.casinogame.Util;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.SicboActivity;
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetBean;
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetContentBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableBetBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableContentBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableBetBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableContentBean;
import gaming178.com.casinogame.Activity.entity.SicboTableBetBean;
import gaming178.com.casinogame.Activity.entity.SicboTableContentBean;
import gaming178.com.casinogame.Bean.BetDetail;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.base.AppModel;

public class TableBetUtils {
    private static Handler handler = new Handler();

    public static void baccaratBet(int tableId, BaccaratTableBetBean baccaratTableBetBean, BaccaratTableBetContentBean contentBean, AppModel mAppViewModel, Context context, int chooseChip, String betType, boolean isRepeat) {
        int currentBet = 0;
        int alreadyBet = 0;
        if (!isRepeat) {
            int min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinBankerPlayerBet();
            int max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxBankerPlayerBet();
            switch (betType) {
                case "P":
                    currentBet = baccaratTableBetBean.getPlayerCurrentBet();
                    alreadyBet = baccaratTableBetBean.getPlayerAlreadyBet();
                    min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinBankerPlayerBet();
                    max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxBankerPlayerBet();
                    break;
                case "B":
                    currentBet = baccaratTableBetBean.getBankerCurrentBet();
                    alreadyBet = baccaratTableBetBean.getBankerAlreadyBet();
                    min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinBankerPlayerBet();
                    max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxBankerPlayerBet();
                    break;
                case "T":
                    currentBet = baccaratTableBetBean.getTieCurrentBet();
                    alreadyBet = baccaratTableBetBean.getTieAlreadyBet();
                    min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinTieBet();
                    max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxTieBet();
                    break;
                case "PP":
                    currentBet = baccaratTableBetBean.getPpCurrentBet();
                    alreadyBet = baccaratTableBetBean.getPpAlreadyBet();
                    min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinPairBet();
                    max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxPairBet();
                    break;
                case "BP":
                    currentBet = baccaratTableBetBean.getBpCurrentBet();
                    alreadyBet = baccaratTableBetBean.getBpAlreadyBet();
                    min = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMinPairBet();
                    max = mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxPairBet();
                    break;
            }
            if (alreadyBet == 0) {
                if (currentBet == 0) {
                    if (chooseChip < min) {
                        currentBet = min;
                    } else if (chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (currentBet + chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            } else {
                if (currentBet == 0) {
                    if (chooseChip + alreadyBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (chooseChip + alreadyBet + currentBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            }
        } else {
            switch (betType) {
                case "P":
                    currentBet = baccaratTableBetBean.getPlayerRepeatBet();
                    break;
                case "B":
                    currentBet = baccaratTableBetBean.getBankerRepeatBet();
                    break;
                case "T":
                    currentBet = baccaratTableBetBean.getTieRepeatBet();
                    break;
                case "PP":
                    currentBet = baccaratTableBetBean.getPpRepeatBet();
                    break;
                case "BP":
                    currentBet = baccaratTableBetBean.getBpRepeatBet();
                    break;
            }
        }
        if (currentBet > 0) {
            FrameLayout fl = null;
            int betMoney = 0;
            int money = 0;
            switch (betType) {
                case "P":
                    baccaratTableBetBean.setPlayerCurrentBet(currentBet);
                    fl = contentBean.getFlTablePlayer();
                    betMoney = baccaratTableBetBean.getPlayerCurrentBet() + baccaratTableBetBean.getPlayerAlreadyBet();
                    money = baccaratTableBetBean.getPlayerCurrentBet() + baccaratTableBetBean.getPlayerAlreadyBet();
                    break;
                case "B":
                    baccaratTableBetBean.setBankerCurrentBet(currentBet);
                    fl = contentBean.getFlTableBanker();
                    betMoney = baccaratTableBetBean.getBankerCurrentBet() + baccaratTableBetBean.getBankerAlreadyBet();
                    money = baccaratTableBetBean.getBankerCurrentBet() + baccaratTableBetBean.getBankerAlreadyBet();
                    break;
                case "T":
                    baccaratTableBetBean.setTieCurrentBet(currentBet);
                    fl = contentBean.getFlTableTie();
                    betMoney = baccaratTableBetBean.getTieCurrentBet() + baccaratTableBetBean.getTieAlreadyBet();
                    money = baccaratTableBetBean.getTieCurrentBet() + baccaratTableBetBean.getTieAlreadyBet();
                    break;
                case "PP":
                    baccaratTableBetBean.setPpCurrentBet(currentBet);
                    fl = contentBean.getFlTablePP();
                    betMoney = baccaratTableBetBean.getPpCurrentBet() + baccaratTableBetBean.getPpAlreadyBet();
                    money = baccaratTableBetBean.getPpCurrentBet() + baccaratTableBetBean.getPpAlreadyBet();
                    break;
                case "BP":
                    baccaratTableBetBean.setBpCurrentBet(currentBet);
                    fl = contentBean.getFlTableBP();
                    betMoney = baccaratTableBetBean.getBpCurrentBet() + baccaratTableBetBean.getBpAlreadyBet();
                    money = baccaratTableBetBean.getBpCurrentBet() + baccaratTableBetBean.getBpAlreadyBet();
                    break;
            }
            if (fl != null) {
                addChip(fl, money, betMoney, context);
                List<FrameLayout> list = new ArrayList<>();
                list.add(contentBean.getFlTablePlayer());
                list.add(contentBean.getFlTableBanker());
                list.add(contentBean.getFlTablePP());
                list.add(contentBean.getFlTableTie());
                list.add(contentBean.getFlTableBP());
                for (int i = 0; i < list.size(); i++) {
                    FrameLayout frameLayout = list.get(i);
                    View viewBetButton = frameLayout.findViewById(R.id.ll_bet_button);
                    if (frameLayout.equals(fl)) {
                        if (viewBetButton == null) {
                            int layout = R.layout.include_bet_ui;
                            if (betType.equals("T") || betType.equals("PP") || betType.equals("BP")) {
                                layout = R.layout.include_bet_ui_small;
                            }
                            View betView = LayoutInflater.from(context).inflate(layout, null);
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.gravity = Gravity.CENTER;
                            betView.setLayoutParams(layoutParams);
                            ImageView imgCancel = betView.findViewById(R.id.gd__tv_table_bet_cancel);
                            ImageView imgRepeat = betView.findViewById(R.id.gd__tv_table_bet_replay);
                            ImageView imgSure = betView.findViewById(R.id.gd__tv_table_bet_sure);
                            imgCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clearNoBetChip(baccaratTableBetBean, contentBean, context);
                                }
                            });
                            imgRepeat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    RepeatBet(mAppViewModel, tableId, baccaratTableBetBean, context, contentBean, chooseChip);
                                }
                            });
                            imgSure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baccaratBetAll(mAppViewModel, tableId, baccaratTableBetBean, context, contentBean);
                                }
                            });
                            frameLayout.addView(betView);
                        }
                    } else {
                        if (viewBetButton != null) {
                            frameLayout.removeView(viewBetButton);
                        }
                    }
                }
            }


        }
    }

    public static void dragonTigerBet(int tableId, DragonTigerTableBetBean betBean, DragonTigerTableContentBean contentBean, AppModel mAppViewModel, Context context, int chooseChip, String betType, boolean isRepeat) {
        int currentBet = 0;
        int alreadyBet = 0;
        if (!isRepeat) {
            int min = 0;
            int max = 0;
            switch (betType) {
                case "D":
                    currentBet = betBean.getDragonCurrentBet();
                    alreadyBet = betBean.getDragonAlreadyBet();
                    min = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMinDragonTigerBet();
                    max = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMaxDragonTigerBet();
                    break;
                case "Tie":
                    currentBet = betBean.getTieCurrentBet();
                    alreadyBet = betBean.getTieAlreadyBet();
                    min = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMinTieBet();
                    max = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMaxTieBet();
                    break;
                case "T":
                    currentBet = betBean.getTigerCurrentBet();
                    alreadyBet = betBean.getTigerAlreadyBet();
                    min = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMinDragonTigerBet();
                    max = mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMaxDragonTigerBet();
                    break;
            }
            if (alreadyBet == 0) {
                if (currentBet == 0) {
                    if (chooseChip < min) {
                        currentBet = min;
                    } else if (chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (currentBet + chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            } else {
                if (currentBet == 0) {
                    if (chooseChip + alreadyBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (chooseChip + alreadyBet + currentBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            }
        } else {
            switch (betType) {
                case "D":
                    currentBet = betBean.getDragonRepeatBet();
                    break;
                case "Tie":
                    currentBet = betBean.getTieRepeatBet();
                    break;
                case "T":
                    currentBet = betBean.getTigerRepeatBet();
                    break;
            }
        }
        if (currentBet > 0) {
            FrameLayout fl = null;
            int betMoney = 0;
            int money = 0;
            switch (betType) {
                case "D":
                    betBean.setDragonCurrentBet(currentBet);
                    fl = contentBean.getFlTableDragon();
                    betMoney = betBean.getDragonCurrentBet() + betBean.getDragonAlreadyBet();
                    money = betBean.getDragonCurrentBet() + betBean.getDragonAlreadyBet();
                    break;
                case "Tie":
                    betBean.setTieCurrentBet(currentBet);
                    fl = contentBean.getFlTableTie();
                    betMoney = betBean.getTieCurrentBet() + betBean.getTieAlreadyBet();
                    money = betBean.getTieCurrentBet() + betBean.getTieAlreadyBet();
                    break;
                case "T":
                    betBean.setTigerCurrentBet(currentBet);
                    fl = contentBean.getFlTableTiger();
                    betMoney = betBean.getTigerCurrentBet() + betBean.getTigerAlreadyBet();
                    money = betBean.getTigerCurrentBet() + betBean.getTigerAlreadyBet();
                    break;
            }
            if (fl != null) {
                addChip(fl, money, betMoney, context);
                List<FrameLayout> list = new ArrayList<>();
                list.add(contentBean.getFlTableDragon());
                list.add(contentBean.getFlTableTie());
                list.add(contentBean.getFlTableTiger());
                for (int i = 0; i < list.size(); i++) {
                    FrameLayout frameLayout = list.get(i);
                    View viewBetButton = frameLayout.findViewById(R.id.ll_bet_button);
                    if (frameLayout.equals(fl)) {
                        if (viewBetButton == null) {
                            View betView = LayoutInflater.from(context).inflate(R.layout.include_bet_ui_small, null);
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.gravity = Gravity.TOP;
                            layoutParams.topMargin = fl.getHeight() / 4;
                            betView.setLayoutParams(layoutParams);
                            ImageView imgCancel = betView.findViewById(R.id.gd__tv_table_bet_cancel);
                            ImageView imgRepeat = betView.findViewById(R.id.gd__tv_table_bet_replay);
                            ImageView imgSure = betView.findViewById(R.id.gd__tv_table_bet_sure);
                            imgCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clearDragonTigerNoBetChip(betBean, contentBean, context);
                                }
                            });
                            imgRepeat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dragonTigerRepeatBet(mAppViewModel, tableId, betBean, context, contentBean, chooseChip);
                                }
                            });
                            imgSure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dragonTigerBetAll(mAppViewModel, tableId, betBean, context, contentBean);
                                }
                            });
                            frameLayout.addView(betView);
                        }
                    } else {
                        if (viewBetButton != null) {
                            frameLayout.removeView(viewBetButton);
                        }
                    }
                }
            }


        }
    }

    public static void sicboBet(int tableId, SicboTableBetBean betBean, SicboTableContentBean contentBean, AppModel mAppViewModel, Context context, int chooseChip, String betType, boolean isRepeat) {
        int currentBet = 0;
        int alreadyBet = 0;
        if (!isRepeat) {
            int min = 0;
            int max = 0;
            switch (betType) {
                case "B":
                    currentBet = betBean.getBigCurrentBet();
                    alreadyBet = betBean.getBigAlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet();
                    break;
                case "S":
                    currentBet = betBean.getSmallCurrentBet();
                    alreadyBet = betBean.getSmallAlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet();
                    break;
                case "A":
                    currentBet = betBean.getAnyCurrentBet();
                    alreadyBet = betBean.getAnyAlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinAlldiceBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxAlldiceBet();
                    break;
                case "1":
                    currentBet = betBean.getSingle1CurrentBet();
                    alreadyBet = betBean.getSingle1AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
                case "2":
                    currentBet = betBean.getSingle2CurrentBet();
                    alreadyBet = betBean.getSingle2AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
                case "3":
                    currentBet = betBean.getSingle3CurrentBet();
                    alreadyBet = betBean.getSingle3AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
                case "4":
                    currentBet = betBean.getSingle4CurrentBet();
                    alreadyBet = betBean.getSingle4AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
                case "5":
                    currentBet = betBean.getSingle5CurrentBet();
                    alreadyBet = betBean.getSingle5AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
                case "6":
                    currentBet = betBean.getSingle6CurrentBet();
                    alreadyBet = betBean.getSingle6AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet();
                    break;
            }
            if (alreadyBet == 0) {
                if (currentBet == 0) {
                    if (chooseChip < min) {
                        currentBet = min;
                    } else if (chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (currentBet + chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            } else {
                if (currentBet == 0) {
                    if (chooseChip + alreadyBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (chooseChip + alreadyBet + currentBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            }
        } else {
            switch (betType) {
                case "B":
                    currentBet = betBean.getBigRepeatBet();
                    break;
                case "S":
                    currentBet = betBean.getSmallRepeatBet();
                    break;
                case "A":
                    currentBet = betBean.getAnyRepeatBet();
                    break;
                case "1":
                    currentBet = betBean.getSingle1RepeatBet();
                    break;
                case "2":
                    currentBet = betBean.getSingle2RepeatBet();
                    break;
                case "3":
                    currentBet = betBean.getSingle3RepeatBet();
                    break;
                case "4":
                    currentBet = betBean.getSingle4RepeatBet();
                    break;
                case "5":
                    currentBet = betBean.getSingle5RepeatBet();
                    break;
                case "6":
                    currentBet = betBean.getSingle6RepeatBet();
                    break;
            }
        }
        if (currentBet > 0) {
            FrameLayout fl = null;
            int betMoney = 0;
            int money = 0;
            switch (betType) {
                case "B":
                    betBean.setBigCurrentBet(currentBet);
                    fl = contentBean.getFlBig();
                    betMoney = betBean.getBigCurrentBet() + betBean.getBigAlreadyBet();
                    money = betBean.getBigCurrentBet() + betBean.getBigAlreadyBet();
                    break;
                case "S":
                    betBean.setSmallCurrentBet(currentBet);
                    fl = contentBean.getFlSmall();
                    betMoney = betBean.getSmallCurrentBet() + betBean.getSmallAlreadyBet();
                    money = betBean.getSmallCurrentBet() + betBean.getSmallAlreadyBet();
                    break;
                case "A":
                    betBean.setAnyCurrentBet(currentBet);
                    fl = contentBean.getFlAny();
                    betMoney = betBean.getAnyCurrentBet() + betBean.getAnyAlreadyBet();
                    money = betBean.getAnyCurrentBet() + betBean.getAnyAlreadyBet();
                    break;
                case "1":
                    betBean.setSingle1CurrentBet(currentBet);
                    fl = contentBean.getFlSingle1();
                    betMoney = betBean.getSingle1CurrentBet() + betBean.getSingle1AlreadyBet();
                    money = betBean.getSingle1CurrentBet() + betBean.getSingle1AlreadyBet();
                    break;
                case "2":
                    betBean.setSingle2CurrentBet(currentBet);
                    fl = contentBean.getFlSingle2();
                    betMoney = betBean.getSingle2CurrentBet() + betBean.getSingle2AlreadyBet();
                    money = betBean.getSingle2CurrentBet() + betBean.getSingle2AlreadyBet();
                    break;
                case "3":
                    betBean.setSingle3CurrentBet(currentBet);
                    fl = contentBean.getFlSingle3();
                    betMoney = betBean.getSingle3CurrentBet() + betBean.getSingle3AlreadyBet();
                    money = betBean.getSingle3CurrentBet() + betBean.getSingle3AlreadyBet();
                    break;
                case "4":
                    betBean.setSingle4CurrentBet(currentBet);
                    fl = contentBean.getFlSingle4();
                    betMoney = betBean.getSingle4CurrentBet() + betBean.getSingle4AlreadyBet();
                    money = betBean.getSingle4CurrentBet() + betBean.getSingle4AlreadyBet();
                    break;
                case "5":
                    betBean.setSingle5CurrentBet(currentBet);
                    fl = contentBean.getFlSingle5();
                    betMoney = betBean.getSingle5CurrentBet() + betBean.getSingle5AlreadyBet();
                    money = betBean.getSingle5CurrentBet() + betBean.getSingle5AlreadyBet();
                    break;
                case "6":
                    betBean.setSingle6CurrentBet(currentBet);
                    fl = contentBean.getFlSingle6();
                    betMoney = betBean.getSingle6CurrentBet() + betBean.getSingle6AlreadyBet();
                    money = betBean.getSingle6CurrentBet() + betBean.getSingle6AlreadyBet();
                    break;
            }
            if (fl != null) {
                addChip(fl, money, betMoney, context);
                View viewBetButton = contentBean.getFlBetButton().findViewById(R.id.ll_bet_button);
                if (viewBetButton == null) {
                    View betView = LayoutInflater.from(context).inflate(R.layout.include_bet_ui, null);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.gravity = Gravity.CENTER;
                    betView.setLayoutParams(layoutParams);
                    ImageView imgCancel = betView.findViewById(R.id.gd__tv_table_bet_cancel);
                    ImageView imgRepeat = betView.findViewById(R.id.gd__tv_table_bet_replay);
                    ImageView imgSure = betView.findViewById(R.id.gd__tv_table_bet_sure);
                    imgCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearSicboNoBetChip(betBean, contentBean, context);
                        }
                    });
                    imgRepeat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sicboRepeatBet(mAppViewModel, tableId, betBean, context, contentBean, chooseChip);
                        }
                    });
                    imgSure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sicboBetAll(mAppViewModel, tableId, betBean, context, contentBean);
                        }
                    });
                    contentBean.getFlBetButton().addView(betView);
                }
            }
        }
    }

    public static void RouletteBet(int tableId, RouletteTableBetBean betBean, RouletteTableContentBean contentBean, AppModel mAppViewModel, Context context, int chooseChip, String betType, boolean isRepeat) {
        int currentBet = 0;
        int alreadyBet = 0;
        if (!isRepeat) {
            int min = 0;
            int max = 0;
            switch (betType) {
                case "Even":
                    currentBet = betBean.getEvenCurrentBet();
                    alreadyBet = betBean.getEvenAlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
                case "0":
                    currentBet = betBean.getZeroCurrentBet();
                    alreadyBet = betBean.getZeroAlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinNumberBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxNumberBet();
                    break;
                case "Odd":
                    currentBet = betBean.getOddCurrentBet();
                    alreadyBet = betBean.getOddAlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
                case "1_12":
                    currentBet = betBean.getSingle1_12CurrentBet();
                    alreadyBet = betBean.getSingle1_12AlreadyBet();
                    min = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinAlldiceBet();
                    max = mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxAlldiceBet();
                    break;
                case "13_24":
                    currentBet = betBean.getSingle13_24CurrentBet();
                    alreadyBet = betBean.getSingle13_24AlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet();
                    break;
                case "25_36":
                    currentBet = betBean.getSingle25_36CurrentBet();
                    alreadyBet = betBean.getSingle25_36AlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinColumnDozenBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxColumnDozenBet();
                    break;
                case "1_18":
                    currentBet = betBean.getSingle1_18CurrentBet();
                    alreadyBet = betBean.getSingle1_18AlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
                case "19_36":
                    currentBet = betBean.getSingle19_36CurrentBet();
                    alreadyBet = betBean.getSingle19_36AlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
                case "Red":
                    currentBet = betBean.getRedCurrentBet();
                    alreadyBet = betBean.getRedAlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
                case "Black":
                    currentBet = betBean.getBlackCurrentBet();
                    alreadyBet = betBean.getBlackAlreadyBet();
                    min = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMinEvenOddBet();
                    max = mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxEvenOddBet();
                    break;
            }
            if (alreadyBet == 0) {
                if (currentBet == 0) {
                    if (chooseChip < min) {
                        currentBet = min;
                    } else if (chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (currentBet + chooseChip > max) {
                        currentBet = max;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            } else {
                if (currentBet == 0) {
                    if (chooseChip + alreadyBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = chooseChip;
                    }
                } else {
                    if (chooseChip + alreadyBet + currentBet > max) {
                        currentBet = max - alreadyBet;
                    } else {
                        currentBet = currentBet + chooseChip;
                    }
                }
            }
        } else {
            switch (betType) {
                case "Even":
                    currentBet = betBean.getEvenRepeatBet();
                    break;
                case "0":
                    currentBet = betBean.getZeroRepeatBet();
                    break;
                case "Odd":
                    currentBet = betBean.getOddRepeatBet();
                    break;
                case "1_12":
                    currentBet = betBean.getSingle1_12RepeatBet();
                    break;
                case "13_24":
                    currentBet = betBean.getSingle13_24RepeatBet();
                    break;
                case "25_36":
                    currentBet = betBean.getSingle25_36RepeatBet();
                    break;
                case "1_18":
                    currentBet = betBean.getSingle1_18RepeatBet();
                    break;
                case "19_36":
                    currentBet = betBean.getSingle19_36RepeatBet();
                    break;
                case "Red":
                    currentBet = betBean.getRedRepeatBet();
                    break;
                case "Black":
                    currentBet = betBean.getBlackRepeatBet();
                    break;
            }
        }
        if (currentBet > 0) {
            FrameLayout fl = null;
            int betMoney = 0;
            int money = 0;
            switch (betType) {
                case "Even":
                    betBean.setEvenCurrentBet(currentBet);
                    fl = contentBean.getFlEven();
                    betMoney = betBean.getEvenCurrentBet() + betBean.getEvenAlreadyBet();
                    money = betBean.getEvenCurrentBet() + betBean.getEvenAlreadyBet();
                    break;
                case "0":
                    betBean.setZeroCurrentBet(currentBet);
                    fl = contentBean.getFlZero();
                    betMoney = betBean.getZeroCurrentBet() + betBean.getZeroAlreadyBet();
                    money = betBean.getZeroCurrentBet() + betBean.getZeroAlreadyBet();
                    break;
                case "Odd":
                    betBean.setOddCurrentBet(currentBet);
                    fl = contentBean.getFlOdd();
                    betMoney = betBean.getOddCurrentBet() + betBean.getOddAlreadyBet();
                    money = betBean.getOddCurrentBet() + betBean.getOddAlreadyBet();
                    break;
                case "1_12":
                    betBean.setSingle1_12CurrentBet(currentBet);
                    fl = contentBean.getFlSingle1_12();
                    betMoney = betBean.getSingle1_12CurrentBet() + betBean.getSingle1_12AlreadyBet();
                    money = betBean.getSingle1_12CurrentBet() + betBean.getSingle1_12AlreadyBet();
                    break;
                case "13_24":
                    betBean.setSingle13_24CurrentBet(currentBet);
                    fl = contentBean.getFlSingle13_24();
                    betMoney = betBean.getSingle13_24CurrentBet() + betBean.getSingle13_24AlreadyBet();
                    money = betBean.getSingle13_24CurrentBet() + betBean.getSingle13_24AlreadyBet();
                    break;
                case "25_36":
                    betBean.setSingle25_36CurrentBet(currentBet);
                    fl = contentBean.getFlSingle25_36();
                    betMoney = betBean.getSingle25_36CurrentBet() + betBean.getSingle25_36AlreadyBet();
                    money = betBean.getSingle25_36CurrentBet() + betBean.getSingle25_36AlreadyBet();
                    break;
                case "1_18":
                    betBean.setSingle1_18CurrentBet(currentBet);
                    fl = contentBean.getFlSingle1_18();
                    betMoney = betBean.getSingle1_18CurrentBet() + betBean.getSingle1_18AlreadyBet();
                    money = betBean.getSingle1_18CurrentBet() + betBean.getSingle1_18AlreadyBet();
                    break;
                case "19_36":
                    betBean.setSingle19_36CurrentBet(currentBet);
                    fl = contentBean.getFlSingle19_36();
                    betMoney = betBean.getSingle19_36CurrentBet() + betBean.getSingle19_36AlreadyBet();
                    money = betBean.getSingle19_36CurrentBet() + betBean.getSingle19_36AlreadyBet();
                    break;
                case "Red":
                    betBean.setRedCurrentBet(currentBet);
                    fl = contentBean.getFlRed();
                    betMoney = betBean.getRedCurrentBet() + betBean.getRedAlreadyBet();
                    money = betBean.getRedCurrentBet() + betBean.getRedAlreadyBet();
                    break;
                case "Black":
                    betBean.setBlackCurrentBet(currentBet);
                    fl = contentBean.getFlBlack();
                    betMoney = betBean.getBlackCurrentBet() + betBean.getBlackAlreadyBet();
                    money = betBean.getBlackCurrentBet() + betBean.getBlackAlreadyBet();
                    break;
            }
            if (fl != null) {
                addChip(fl, money, betMoney, context);
                View viewBetButton = contentBean.getFlBetButton().findViewById(R.id.ll_bet_button);
                if (viewBetButton == null) {
                    View betView = LayoutInflater.from(context).inflate(R.layout.include_bet_ui, null);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.gravity = Gravity.CENTER;
                    betView.setLayoutParams(layoutParams);
                    ImageView imgCancel = betView.findViewById(R.id.gd__tv_table_bet_cancel);
                    ImageView imgRepeat = betView.findViewById(R.id.gd__tv_table_bet_replay);
                    ImageView imgSure = betView.findViewById(R.id.gd__tv_table_bet_sure);
                    imgCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearRouletteNoBetChip(betBean, contentBean, context);
                        }
                    });
                    imgRepeat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rouletteRepeatBet(mAppViewModel, tableId, betBean, context, contentBean, chooseChip);
                        }
                    });
                    imgSure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rouletteBetAll(mAppViewModel, tableId, betBean, context, contentBean);
                        }
                    });
                    contentBean.getFlBetButton().addView(betView);
                }
            }
        }
    }

    private static void baccaratBetAll(AppModel mAppViewModel, int tableId, BaccaratTableBetBean baccaratTableBetBean, Context context, BaccaratTableBetContentBean contentBean) {
        new Thread() {
            @Override
            public void run() {
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_BET_URL, getBaccaratBetParam(tableId, baccaratTableBetBean, mAppViewModel));
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));

                        double resMoney = Double.parseDouble(strInfo[4]);
                        baccaratTableBetBean.setBankerAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setBankerRepeatBet((int) resMoney);
                        baccaratTableBetBean.setBankerCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[3]);
                        baccaratTableBetBean.setPlayerAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setPlayerRepeatBet((int) resMoney);
                        baccaratTableBetBean.setPlayerCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[5]);
                        baccaratTableBetBean.setTieAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setTieRepeatBet((int) resMoney);
                        baccaratTableBetBean.setTieCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[6]);
                        baccaratTableBetBean.setBpAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setBpRepeatBet((int) resMoney);
                        baccaratTableBetBean.setBpCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[7]);
                        baccaratTableBetBean.setPpAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setPpAlreadyBet((int) resMoney);
                        baccaratTableBetBean.setPpCurrentBet(0);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
                            clearNoBetChip(baccaratTableBetBean, contentBean, context);
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();
    }

    private static void dragonTigerBetAll(AppModel mAppViewModel, int tableId, DragonTigerTableBetBean betBean, Context context, DragonTigerTableContentBean contentBean) {
        new Thread() {
            @Override
            public void run() {
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_BET_URL, getDragonTigerBetParam(tableId, betBean, mAppViewModel));
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));

                        double resMoney = Double.parseDouble(strInfo[4]);
                        betBean.setDragonAlreadyBet((int) resMoney);
                        betBean.setDragonRepeatBet((int) resMoney);
                        betBean.setDragonCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[3]);
                        betBean.setTigerAlreadyBet((int) resMoney);
                        betBean.setTigerRepeatBet((int) resMoney);
                        betBean.setTigerCurrentBet(0);

                        resMoney = Double.parseDouble(strInfo[5]);
                        betBean.setTieAlreadyBet((int) resMoney);
                        betBean.setTieRepeatBet((int) resMoney);
                        betBean.setTieCurrentBet(0);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
                                clearDragonTigerNoBetChip(betBean, contentBean, context);
                            }
                        });
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();

    }

    private static void sicboBetAll(AppModel mAppViewModel, int tableId, SicboTableBetBean betBean, Context context, SicboTableContentBean contentBean) {
        new Thread() {
            @Override
            public void run() {
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_BET_URL, getSicboBetParam(tableId, betBean, mAppViewModel));
                Log.d("getSicboBetParam", getSicboBetParam(tableId, betBean, mAppViewModel));
                Log.d("getSicboBetParam", strRes);
                String strInfo[] = strRes.split("\\^");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        double resMoney = Double.parseDouble(strInfo[3]);
                        betBean.setBigAlreadyBet((int) resMoney);
                        betBean.setBigRepeatBet((int) resMoney);
                        betBean.setBigCurrentBet(0);
                        resMoney = Double.parseDouble(strInfo[4]);
                        betBean.setSmallAlreadyBet((int) resMoney);
                        betBean.setSmallRepeatBet((int) resMoney);
                        betBean.setSmallCurrentBet(0);
                        resMoney = Double.parseDouble(strInfo[7]);
                        betBean.setAnyAlreadyBet((int) resMoney);
                        betBean.setAnyRepeatBet((int) resMoney);
                        betBean.setAnyCurrentBet(0);
                        if (!"0".equals(strInfo[8])) {
                            String strThree[] = strInfo[8].split("\\|");
                            for (int i = 0; i < strThree.length; i++) {
                                String strThreeDetail[] = strThree[i].split("#");
                                if (strThreeDetail != null && strThreeDetail.length == 2) {
                                    String point = strThreeDetail[0];
                                    int betMoney = (int) Double.parseDouble(strThreeDetail[1]);
                                    if (point.equals("1")) {
                                        betBean.setSingle1AlreadyBet(betMoney);
                                        betBean.setSingle1RepeatBet(betMoney);
                                        betBean.setSingle1CurrentBet(0);
                                    } else if (point.equals("2")) {
                                        betBean.setSingle2AlreadyBet(betMoney);
                                        betBean.setSingle2RepeatBet(betMoney);
                                        betBean.setSingle2CurrentBet(0);
                                    } else if (point.equals("3")) {
                                        betBean.setSingle3AlreadyBet(betMoney);
                                        betBean.setSingle3RepeatBet(betMoney);
                                        betBean.setSingle3CurrentBet(0);
                                    } else if (point.equals("4")) {
                                        betBean.setSingle4AlreadyBet(betMoney);
                                        betBean.setSingle4RepeatBet(betMoney);
                                        betBean.setSingle4CurrentBet(0);
                                    } else if (point.equals("5")) {
                                        betBean.setSingle5AlreadyBet(betMoney);
                                        betBean.setSingle5RepeatBet(betMoney);
                                        betBean.setSingle5CurrentBet(0);
                                    } else if (point.equals("6")) {
                                        betBean.setSingle6AlreadyBet(betMoney);
                                        betBean.setSingle6RepeatBet(betMoney);
                                        betBean.setSingle6CurrentBet(0);
                                    }
                                }
                            }
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
                                clearSicboNoBetChip(betBean, contentBean, context);
                            }
                        });
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();
    }

    private static void rouletteBetAll(AppModel mAppViewModel, int tableId, RouletteTableBetBean betBean, Context context, RouletteTableContentBean contentBean) {
        new Thread() {
            @Override
            public void run() {
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_BET_URL, getRouletteBetParam(tableId, betBean, mAppViewModel));
                String strInfo[] = strRes.split("\\^");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        if (!"0".equals(strInfo[3])) {
                            String direct[] = strInfo[3].split("\\|");
                            for (int i = 0; i < direct.length; i++) {
                                String strDirectDetail[] = direct[i].split("#");
                                if (strDirectDetail != null && strDirectDetail.length == 2) {
                                    String point = strDirectDetail[0];
                                    int betMoney = (int) Double.parseDouble(strDirectDetail[1]);
                                    if (point.equals("00")) {
                                        betBean.setZeroAlreadyBet(betMoney);
                                        betBean.setZeroRepeatBet(betMoney);
                                        betBean.setZeroCurrentBet(0);
                                        break;
                                    }
                                }

                            }
                        }
                        betBean.setSingle1_12AlreadyBet(Integer.parseInt(strInfo[13]));
                        betBean.setSingle13_24AlreadyBet(Integer.parseInt(strInfo[14]));
                        betBean.setSingle25_36AlreadyBet(Integer.parseInt(strInfo[15]));
                        betBean.setRedAlreadyBet(Integer.parseInt(strInfo[16]));
                        betBean.setBlackAlreadyBet(Integer.parseInt(strInfo[17]));
                        betBean.setOddAlreadyBet(Integer.parseInt(strInfo[18]));
                        betBean.setEvenAlreadyBet(Integer.parseInt(strInfo[19]));
                        betBean.setSingle1_18AlreadyBet(Integer.parseInt(strInfo[20]));
                        betBean.setSingle19_36AlreadyBet(Integer.parseInt(strInfo[21]));
                        betBean.setSingle1_12RepeatBet(Integer.parseInt(strInfo[13]));
                        betBean.setSingle13_24RepeatBet(Integer.parseInt(strInfo[14]));
                        betBean.setSingle25_36RepeatBet(Integer.parseInt(strInfo[15]));
                        betBean.setRedRepeatBet(Integer.parseInt(strInfo[16]));
                        betBean.setBlackRepeatBet(Integer.parseInt(strInfo[17]));
                        betBean.setOddRepeatBet(Integer.parseInt(strInfo[18]));
                        betBean.setEvenRepeatBet(Integer.parseInt(strInfo[19]));
                        betBean.setSingle1_18RepeatBet(Integer.parseInt(strInfo[20]));
                        betBean.setSingle19_36RepeatBet(Integer.parseInt(strInfo[21]));
                        betBean.setSingle1_12CurrentBet(0);
                        betBean.setSingle13_24CurrentBet(0);
                        betBean.setSingle25_36CurrentBet(0);
                        betBean.setRedCurrentBet(0);
                        betBean.setBlackCurrentBet(0);
                        betBean.setOddCurrentBet(0);
                        betBean.setEvenCurrentBet(0);
                        betBean.setSingle1_18CurrentBet(0);
                        betBean.setSingle19_36CurrentBet(0);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
                            clearRouletteNoBetChip(betBean, contentBean, context);
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();
    }

    private static void RepeatBet(AppModel mAppViewModel, int tableId, BaccaratTableBetBean baccaratTableBetBean, Context context, BaccaratTableBetContentBean contentBean, int chooseChip) {
        int playerRepeatBet = baccaratTableBetBean.getPlayerRepeatBet();
        int bankerRepeatBet = baccaratTableBetBean.getBankerRepeatBet();
        int tieRepeatBet = baccaratTableBetBean.getTieRepeatBet();
        int ppRepeatBet = baccaratTableBetBean.getPpRepeatBet();
        int bpRepeatBet = baccaratTableBetBean.getBpRepeatBet();
        int playerAlreadyBet = baccaratTableBetBean.getPlayerAlreadyBet();
        int bankerAlreadyBet = baccaratTableBetBean.getBankerAlreadyBet();
        int tieAlreadyBet = baccaratTableBetBean.getTieAlreadyBet();
        int ppAlreadyBet = baccaratTableBetBean.getPpAlreadyBet();
        int bpAlreadyBet = baccaratTableBetBean.getBpAlreadyBet();
        if (playerAlreadyBet == 0 && bankerAlreadyBet == 0 && tieAlreadyBet == 0 && ppAlreadyBet == 0 && bpAlreadyBet == 0) {
            clearAllChip(baccaratTableBetBean, contentBean);
            if (playerRepeatBet > 0) {
                baccaratBet(tableId, baccaratTableBetBean, contentBean, mAppViewModel, context, chooseChip, "P", true);
            }
            if (bankerRepeatBet > 0) {
                baccaratBet(tableId, baccaratTableBetBean, contentBean, mAppViewModel, context, chooseChip, "B", true);
            }
            if (tieRepeatBet > 0) {
                baccaratBet(tableId, baccaratTableBetBean, contentBean, mAppViewModel, context, chooseChip, "T", true);
            }
            if (ppRepeatBet > 0) {
                baccaratBet(tableId, baccaratTableBetBean, contentBean, mAppViewModel, context, chooseChip, "PP", true);
            }
            if (bpRepeatBet > 0) {
                baccaratBet(tableId, baccaratTableBetBean, contentBean, mAppViewModel, context, chooseChip, "BP ", true);
            }
        }
    }

    private static void dragonTigerRepeatBet(AppModel mAppViewModel, int tableId, DragonTigerTableBetBean betBean, Context context, DragonTigerTableContentBean contentBean, int chooseChip) {
        int dragonRepeatBet = betBean.getDragonRepeatBet();
        int tigerRepeatBet = betBean.getTigerRepeatBet();
        int tieRepeatBet = betBean.getTieRepeatBet();
        int dragonAlreadyBet = betBean.getDragonAlreadyBet();
        int tigerAlreadyBet = betBean.getTigerAlreadyBet();
        int tieAlreadyBet = betBean.getTieAlreadyBet();
        if (dragonAlreadyBet == 0 && tigerAlreadyBet == 0 && tieAlreadyBet == 0) {
            clearDragonTigerAllChip(betBean, contentBean);
            if (dragonRepeatBet > 0) {
                dragonTigerBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "D", true);
            }
            if (tieRepeatBet > 0) {
                dragonTigerBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "Tie", true);
            }
            if (tigerRepeatBet > 0) {
                dragonTigerBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "T", true);
            }
        }
    }

    private static void sicboRepeatBet(AppModel mAppViewModel, int tableId, SicboTableBetBean betBean, Context context, SicboTableContentBean contentBean, int chooseChip) {
        int bigRepeatBet = betBean.getBigRepeatBet();
        int smallRepeatBet = betBean.getSmallRepeatBet();
        int anyRepeatBet = betBean.getAnyRepeatBet();
        int single1RepeatBet = betBean.getSingle1RepeatBet();
        int single2RepeatBet = betBean.getSingle2RepeatBet();
        int single3RepeatBet = betBean.getSingle3RepeatBet();
        int single4RepeatBet = betBean.getSingle4RepeatBet();
        int single5RepeatBet = betBean.getSingle5RepeatBet();
        int single6RepeatBet = betBean.getSingle6RepeatBet();
        int bigAlreadyBet = betBean.getBigAlreadyBet();
        int smallAlreadyBet = betBean.getSmallAlreadyBet();
        int anyAlreadyBet = betBean.getAnyAlreadyBet();
        int single1AlreadyBet = betBean.getSingle1AlreadyBet();
        int single2AlreadyBet = betBean.getSingle2AlreadyBet();
        int single3AlreadyBet = betBean.getSingle3AlreadyBet();
        int single4AlreadyBet = betBean.getSingle4AlreadyBet();
        int single5AlreadyBet = betBean.getSingle5AlreadyBet();
        int single6AlreadyBet = betBean.getSingle6AlreadyBet();
        if (bigAlreadyBet == 0 && smallAlreadyBet == 0 && anyAlreadyBet == 0 && single1AlreadyBet == 0 && single2AlreadyBet == 0 && single3AlreadyBet == 0 && single4AlreadyBet == 0 && single5AlreadyBet == 0 && single6AlreadyBet == 0) {
            clearSicboAllChip(betBean, contentBean);
            if (bigRepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "B", true);
            }
            if (smallRepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "S", true);
            }
            if (anyRepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "A", true);
            }
            if (single1RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "1", true);
            }
            if (single2RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "2", true);
            }
            if (single3RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "3", true);
            }
            if (single4RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "4", true);
            }
            if (single5RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "5", true);
            }
            if (single6RepeatBet > 0) {
                sicboBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "6", true);
            }
        }
    }

    private static void rouletteRepeatBet(AppModel mAppViewModel, int tableId, RouletteTableBetBean betBean, Context context, RouletteTableContentBean contentBean, int chooseChip) {
        int evenRepeatBet = betBean.getEvenRepeatBet();
        int zeroRepeatBet = betBean.getZeroRepeatBet();
        int oddRepeatBet = betBean.getOddRepeatBet();
        int single1_12RepeatBet = betBean.getSingle1_12RepeatBet();
        int single13_24RepeatBet = betBean.getSingle13_24RepeatBet();
        int single25_36RepeatBet = betBean.getSingle25_36RepeatBet();
        int single1_18RepeatBet = betBean.getSingle1_18RepeatBet();
        int single19_36RepeatBet = betBean.getSingle19_36RepeatBet();
        int redRepeatBet = betBean.getRedRepeatBet();
        int blackRepeatBet = betBean.getBlackRepeatBet();
        int evenAlreadyBet = betBean.getEvenAlreadyBet();
        int zeroAlreadyBet = betBean.getZeroAlreadyBet();
        int oddAlreadyBet = betBean.getOddAlreadyBet();
        int single1_12AlreadyBet = betBean.getSingle1_12AlreadyBet();
        int single13_24AlreadyBet = betBean.getSingle13_24AlreadyBet();
        int single25_36AlreadyBet = betBean.getSingle25_36AlreadyBet();
        int single1_18AlreadyBet = betBean.getSingle1_18AlreadyBet();
        int single19_36AlreadyBet = betBean.getSingle19_36AlreadyBet();
        int redAlreadyBet = betBean.getRedAlreadyBet();
        int blackAlreadyBet = betBean.getBlackAlreadyBet();
        if (evenAlreadyBet == 0 && zeroAlreadyBet == 0 && oddAlreadyBet == 0 && single1_12AlreadyBet == 0 && single13_24AlreadyBet == 0 && single25_36AlreadyBet == 0
                && single1_18AlreadyBet == 0 && single19_36AlreadyBet == 0 && redAlreadyBet == 0 && blackAlreadyBet == 0) {
            clearRouletteAllChip(betBean, contentBean);
            if (evenRepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "Even", true);
            }
            if (zeroRepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "0", true);
            }
            if (oddRepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "Odd", true);
            }
            if (single1_12RepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "1_12", true);
            }
            if (single13_24RepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "13_24", true);
            }
            if (single25_36RepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "25_36", true);
            }
            if (single1_18RepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "1_18", true);
            }
            if (single19_36RepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "19_36", true);
            }
            if (redRepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "Red", true);
            }
            if (blackRepeatBet > 0) {
                RouletteBet(tableId, betBean, contentBean, mAppViewModel, context, chooseChip, "Black", true);
            }
        }
    }

    public static void clearNoBetChip(BaccaratTableBetBean baccaratTableBetBean, BaccaratTableBetContentBean contentBean, Context context) {
        int playerAlreadyBet = baccaratTableBetBean.getPlayerAlreadyBet();
        int bankerAlreadyBet = baccaratTableBetBean.getBankerAlreadyBet();
        int tieAlreadyBet = baccaratTableBetBean.getTieAlreadyBet();
        int ppAlreadyBet = baccaratTableBetBean.getPpAlreadyBet();
        int bpAlreadyBet = baccaratTableBetBean.getBpAlreadyBet();
        contentBean.getFlTablePlayer().removeAllViews();
        if (playerAlreadyBet > 0) {
            addChip(contentBean.getFlTablePlayer(), playerAlreadyBet, playerAlreadyBet, context);
        }
        contentBean.getFlTableBanker().removeAllViews();
        if (bankerAlreadyBet > 0) {
            addChip(contentBean.getFlTableBanker(), bankerAlreadyBet, bankerAlreadyBet, context);
        }
        contentBean.getFlTableTie().removeAllViews();
        if (tieAlreadyBet > 0) {
            addChip(contentBean.getFlTableTie(), tieAlreadyBet, tieAlreadyBet, context);
        }
        contentBean.getFlTablePP().removeAllViews();
        if (ppAlreadyBet > 0) {
            addChip(contentBean.getFlTablePP(), ppAlreadyBet, ppAlreadyBet, context);
        }
        contentBean.getFlTableBP().removeAllViews();
        if (bpAlreadyBet > 0) {
            addChip(contentBean.getFlTableBP(), bpAlreadyBet, bpAlreadyBet, context);
        }
        baccaratTableBetBean.setPlayerCurrentBet(0);
        baccaratTableBetBean.setBankerCurrentBet(0);
        baccaratTableBetBean.setTieCurrentBet(0);
        baccaratTableBetBean.setPpCurrentBet(0);
        baccaratTableBetBean.setBpCurrentBet(0);
    }

    public static void clearDragonTigerNoBetChip(DragonTigerTableBetBean betBean, DragonTigerTableContentBean contentBean, Context context) {
        int dragonAlreadyBet = betBean.getDragonAlreadyBet();
        int tigerAlreadyBet = betBean.getTigerAlreadyBet();
        int tieAlreadyBet = betBean.getTieAlreadyBet();
        contentBean.getFlTableDragon().removeAllViews();
        if (dragonAlreadyBet > 0) {
            addChip(contentBean.getFlTableDragon(), dragonAlreadyBet, dragonAlreadyBet, context);
        }
        contentBean.getFlTableTiger().removeAllViews();
        if (tigerAlreadyBet > 0) {
            addChip(contentBean.getFlTableTiger(), tigerAlreadyBet, tigerAlreadyBet, context);
        }
        contentBean.getFlTableTie().removeAllViews();
        if (tieAlreadyBet > 0) {
            addChip(contentBean.getFlTableTie(), tieAlreadyBet, tieAlreadyBet, context);
        }
        betBean.setDragonCurrentBet(0);
        betBean.setTigerCurrentBet(0);
        betBean.setTieCurrentBet(0);
    }

    public static void clearSicboNoBetChip(SicboTableBetBean betBean, SicboTableContentBean contentBean, Context context) {
        int bigAlreadyBet = betBean.getBigAlreadyBet();
        int smallAlreadyBet = betBean.getSmallAlreadyBet();
        int anyAlreadyBet = betBean.getAnyAlreadyBet();
        int single1AlreadyBet = betBean.getSingle1AlreadyBet();
        int single2AlreadyBet = betBean.getSingle2AlreadyBet();
        int single3AlreadyBet = betBean.getSingle3AlreadyBet();
        int single4AlreadyBet = betBean.getSingle4AlreadyBet();
        int single5AlreadyBet = betBean.getSingle5AlreadyBet();
        int single6AlreadyBet = betBean.getSingle6AlreadyBet();
        contentBean.getFlBig().removeAllViews();
        if (bigAlreadyBet > 0) {
            addChip(contentBean.getFlBig(), bigAlreadyBet, bigAlreadyBet, context);
        }
        contentBean.getFlSmall().removeAllViews();
        if (smallAlreadyBet > 0) {
            addChip(contentBean.getFlSmall(), smallAlreadyBet, smallAlreadyBet, context);
        }
        contentBean.getFlAny().removeAllViews();
        if (anyAlreadyBet > 0) {
            addChip(contentBean.getFlAny(), anyAlreadyBet, anyAlreadyBet, context);
        }
        contentBean.getFlSingle1().removeAllViews();
        if (single1AlreadyBet > 0) {
            addChip(contentBean.getFlSingle1(), single1AlreadyBet, single1AlreadyBet, context);
        }
        contentBean.getFlSingle2().removeAllViews();
        if (single2AlreadyBet > 0) {
            addChip(contentBean.getFlSingle2(), single2AlreadyBet, single2AlreadyBet, context);
        }
        contentBean.getFlSingle3().removeAllViews();
        if (single3AlreadyBet > 0) {
            addChip(contentBean.getFlSingle3(), single3AlreadyBet, single3AlreadyBet, context);
        }
        contentBean.getFlSingle4().removeAllViews();
        if (single4AlreadyBet > 0) {
            addChip(contentBean.getFlSingle4(), single4AlreadyBet, single4AlreadyBet, context);
        }
        contentBean.getFlSingle5().removeAllViews();
        if (single5AlreadyBet > 0) {
            addChip(contentBean.getFlSingle5(), single5AlreadyBet, single5AlreadyBet, context);
        }
        contentBean.getFlSingle6().removeAllViews();
        if (single6AlreadyBet > 0) {
            addChip(contentBean.getFlSingle6(), single6AlreadyBet, single6AlreadyBet, context);
        }
        betBean.setBigCurrentBet(0);
        betBean.setSmallCurrentBet(0);
        betBean.setAnyCurrentBet(0);
        betBean.setSingle1CurrentBet(0);
        betBean.setSingle2CurrentBet(0);
        betBean.setSingle3CurrentBet(0);
        betBean.setSingle4CurrentBet(0);
        betBean.setSingle5CurrentBet(0);
        betBean.setSingle6CurrentBet(0);
        contentBean.getFlBetButton().removeAllViews();
    }

    public static void clearRouletteNoBetChip(RouletteTableBetBean betBean, RouletteTableContentBean contentBean, Context context) {
        int evenAlreadyBet = betBean.getEvenAlreadyBet();
        int zeroAlreadyBet = betBean.getZeroAlreadyBet();
        int oddAlreadyBet = betBean.getOddAlreadyBet();
        int single1_12AlreadyBet = betBean.getSingle1_12AlreadyBet();
        int single13_24AlreadyBet = betBean.getSingle13_24AlreadyBet();
        int single25_36AlreadyBet = betBean.getSingle25_36AlreadyBet();
        int single1_18AlreadyBet = betBean.getSingle1_18AlreadyBet();
        int single19_36AlreadyBet = betBean.getSingle19_36AlreadyBet();
        int redAlreadyBet = betBean.getRedAlreadyBet();
        int blackAlreadyBet = betBean.getBlackAlreadyBet();
        contentBean.getFlEven().removeAllViews();
        if (evenAlreadyBet > 0) {
            addChip(contentBean.getFlEven(), evenAlreadyBet, evenAlreadyBet, context);
        }
        contentBean.getFlZero().removeAllViews();
        if (zeroAlreadyBet > 0) {
            addChip(contentBean.getFlZero(), zeroAlreadyBet, zeroAlreadyBet, context);
        }
        contentBean.getFlOdd().removeAllViews();
        if (oddAlreadyBet > 0) {
            addChip(contentBean.getFlOdd(), oddAlreadyBet, oddAlreadyBet, context);
        }
        contentBean.getFlSingle1_12().removeAllViews();
        if (single1_12AlreadyBet > 0) {
            addChip(contentBean.getFlSingle1_12(), single1_12AlreadyBet, single1_12AlreadyBet, context);
        }
        contentBean.getFlSingle13_24().removeAllViews();
        if (single13_24AlreadyBet > 0) {
            addChip(contentBean.getFlSingle13_24(), single13_24AlreadyBet, single13_24AlreadyBet, context);
        }
        contentBean.getFlSingle25_36().removeAllViews();
        if (single25_36AlreadyBet > 0) {
            addChip(contentBean.getFlSingle25_36(), single25_36AlreadyBet, single25_36AlreadyBet, context);
        }
        contentBean.getFlSingle1_18().removeAllViews();
        if (single1_18AlreadyBet > 0) {
            addChip(contentBean.getFlSingle1_18(), single1_18AlreadyBet, single1_18AlreadyBet, context);
        }
        contentBean.getFlSingle19_36().removeAllViews();
        if (single19_36AlreadyBet > 0) {
            addChip(contentBean.getFlSingle19_36(), single19_36AlreadyBet, single19_36AlreadyBet, context);
        }
        contentBean.getFlRed().removeAllViews();
        if (redAlreadyBet > 0) {
            addChip(contentBean.getFlRed(), redAlreadyBet, redAlreadyBet, context);
        }
        contentBean.getFlBlack().removeAllViews();
        if (blackAlreadyBet > 0) {
            addChip(contentBean.getFlBlack(), blackAlreadyBet, blackAlreadyBet, context);
        }
        betBean.setEvenCurrentBet(0);
        betBean.setZeroCurrentBet(0);
        betBean.setOddCurrentBet(0);
        betBean.setSingle1_12CurrentBet(0);
        betBean.setSingle13_24CurrentBet(0);
        betBean.setSingle25_36CurrentBet(0);
        betBean.setSingle1_18CurrentBet(0);
        betBean.setSingle19_36CurrentBet(0);
        betBean.setRedCurrentBet(0);
        betBean.setBlackCurrentBet(0);
        contentBean.getFlBetButton().removeAllViews();
    }

    public static void clearAllChip(BaccaratTableBetBean baccaratTableBetBean, BaccaratTableBetContentBean contentBean) {
        contentBean.getFlTablePlayer().removeAllViews();
        contentBean.getFlTableBanker().removeAllViews();
        contentBean.getFlTableTie().removeAllViews();
        contentBean.getFlTablePP().removeAllViews();
        contentBean.getFlTableBP().removeAllViews();
        baccaratTableBetBean.setPlayerCurrentBet(0);
        baccaratTableBetBean.setBankerCurrentBet(0);
        baccaratTableBetBean.setTieCurrentBet(0);
        baccaratTableBetBean.setPpCurrentBet(0);
        baccaratTableBetBean.setBpCurrentBet(0);
        baccaratTableBetBean.setPlayerAlreadyBet(0);
        baccaratTableBetBean.setBankerAlreadyBet(0);
        baccaratTableBetBean.setTieAlreadyBet(0);
        baccaratTableBetBean.setPpAlreadyBet(0);
        baccaratTableBetBean.setBpAlreadyBet(0);
    }

    public static void clearDragonTigerAllChip(DragonTigerTableBetBean betBean, DragonTigerTableContentBean contentBean) {
        contentBean.getFlTableDragon().removeAllViews();
        contentBean.getFlTableTiger().removeAllViews();
        contentBean.getFlTableTie().removeAllViews();
        betBean.setDragonCurrentBet(0);
        betBean.setTigerCurrentBet(0);
        betBean.setTieCurrentBet(0);
        betBean.setDragonAlreadyBet(0);
        betBean.setTigerAlreadyBet(0);
        betBean.setTieAlreadyBet(0);
    }

    public static void clearSicboAllChip(SicboTableBetBean betBean, SicboTableContentBean contentBean) {
        contentBean.getFlBig().removeAllViews();
        contentBean.getFlSmall().removeAllViews();
        contentBean.getFlAny().removeAllViews();
        contentBean.getFlSingle1().removeAllViews();
        contentBean.getFlSingle2().removeAllViews();
        contentBean.getFlSingle3().removeAllViews();
        contentBean.getFlSingle4().removeAllViews();
        contentBean.getFlSingle5().removeAllViews();
        contentBean.getFlSingle6().removeAllViews();
        betBean.setBigCurrentBet(0);
        betBean.setSmallCurrentBet(0);
        betBean.setAnyCurrentBet(0);
        betBean.setSingle1CurrentBet(0);
        betBean.setSingle2CurrentBet(0);
        betBean.setSingle3CurrentBet(0);
        betBean.setSingle4CurrentBet(0);
        betBean.setSingle5CurrentBet(0);
        betBean.setSingle6CurrentBet(0);
        betBean.setBigAlreadyBet(0);
        betBean.setSmallAlreadyBet(0);
        betBean.setAnyAlreadyBet(0);
        betBean.setSingle1AlreadyBet(0);
        betBean.setSingle2AlreadyBet(0);
        betBean.setSingle3AlreadyBet(0);
        betBean.setSingle4AlreadyBet(0);
        betBean.setSingle5AlreadyBet(0);
        betBean.setSingle6AlreadyBet(0);
        contentBean.getFlBetButton().removeAllViews();
    }

    public static void clearRouletteAllChip(RouletteTableBetBean betBean, RouletteTableContentBean contentBean) {
        contentBean.getFlEven().removeAllViews();
        contentBean.getFlZero().removeAllViews();
        contentBean.getFlOdd().removeAllViews();
        contentBean.getFlSingle1_12().removeAllViews();
        contentBean.getFlSingle13_24().removeAllViews();
        contentBean.getFlSingle25_36().removeAllViews();
        contentBean.getFlSingle1_18().removeAllViews();
        contentBean.getFlSingle19_36().removeAllViews();
        contentBean.getFlRed().removeAllViews();
        contentBean.getFlBlack().removeAllViews();
        betBean.setEvenCurrentBet(0);
        betBean.setZeroCurrentBet(0);
        betBean.setOddCurrentBet(0);
        betBean.setSingle1_12CurrentBet(0);
        betBean.setSingle13_24CurrentBet(0);
        betBean.setSingle25_36CurrentBet(0);
        betBean.setSingle1_18CurrentBet(0);
        betBean.setSingle19_36CurrentBet(0);
        betBean.setRedCurrentBet(0);
        betBean.setBlackCurrentBet(0);
        betBean.setEvenAlreadyBet(0);
        betBean.setZeroAlreadyBet(0);
        betBean.setOddAlreadyBet(0);
        betBean.setSingle1_12AlreadyBet(0);
        betBean.setSingle13_24AlreadyBet(0);
        betBean.setSingle25_36AlreadyBet(0);
        betBean.setSingle1_18AlreadyBet(0);
        betBean.setSingle19_36AlreadyBet(0);
        betBean.setRedAlreadyBet(0);
        betBean.setBlackAlreadyBet(0);
        contentBean.getFlBetButton().removeAllViews();
    }

    public static void addChip(FrameLayout fl, int money, int betMoney, Context context) {
        List<ChipBean> chipList = getChipList();
        fl.removeAllViews();
        String tag = (String) fl.getTag();
        int bottomMargin = 0;
        if (!TextUtils.isEmpty(tag) && tag.equals("DT")) {
            bottomMargin = fl.getHeight() / 4;
        }
        while (money > 0) {
            A:
            for (int i = chipList.size() - 1; i >= 0; i--) {
                int index = money / chipList.get(i).getValue();
                if (index > 0) {
                    FrameLayout.LayoutParams params = new AutoFrameLayout.LayoutParams(AutoUtils.getPercentHeightSize(28), AutoUtils.getPercentHeightSize(15));
                    params.gravity = Gravity.BOTTOM | Gravity.CENTER;
                    params.bottomMargin = bottomMargin;
                    ImageView img = new ImageView(context);
                    img.setBackgroundResource(chipList.get(i).getDrawableRes());
                    img.setLayoutParams(params);
                    fl.addView(img);
                    money = money - chipList.get(i).getValue();
                    bottomMargin = bottomMargin + AutoUtils.getPercentHeightSize(4);
                    break A;
                }
            }
        }
        if (betMoney > 0) {
            TextView moneyTv = new TextView(context);
            FrameLayout.LayoutParams params = new AutoFrameLayout.LayoutParams(AutoUtils.getPercentHeightSize(22), AutoUtils.getPercentHeightSize(12));
            if (!TextUtils.isEmpty(tag)) {
                if (tag.equals("Top")) {
                    params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                    bottomMargin = bottomMargin + AutoUtils.getPercentHeightSize(12);
                    params.bottomMargin = bottomMargin;
                } else if (tag.equals("DT")) {
                    params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                    params.leftMargin = AutoUtils.getPercentHeightSize(27);
                    params.bottomMargin = fl.getHeight() / 4 + AutoUtils.getPercentHeightSize(4);
                }
            } else {
                params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                params.leftMargin = AutoUtils.getPercentHeightSize(27);
                params.bottomMargin = AutoUtils.getPercentHeightSize(2);
            }
            moneyTv.setLayoutParams(params);
            moneyTv.setText(betMoney + "");
            moneyTv.setTextSize(6);
            moneyTv.setGravity(Gravity.CENTER);
            moneyTv.setTextColor(Color.WHITE);
            moneyTv.setBackgroundResource(R.mipmap.gd_show_chip_bg);
            fl.addView(moneyTv);
        }

    }

    private static String getBaccaratBetParam(int tableId, BaccaratTableBetBean baccaratTableBetBean, AppModel mAppViewModel) {
        String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                + "&Xhid=" + mAppViewModel.getBaccarat(tableId).getShoeNumber() + "&Blid=" + mAppViewModel.getBaccarat(tableId).getGameNumber()
                + "&Xh=" + mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxTotalBet()
                + "&Areaid=" + mAppViewModel.getAreaId() + "&Serial=" + mAppViewModel.getSerialId() + "&Hl=1"
                + "&Player=" + baccaratTableBetBean.getPlayerCurrentBet() + "&Banker=" + baccaratTableBetBean.getBankerCurrentBet() + "&Tie=" + baccaratTableBetBean.getTieCurrentBet() + "&Bd=" + baccaratTableBetBean.getBpCurrentBet() + "&Pd=" + baccaratTableBetBean.getPpCurrentBet()
                + "&Big=0&Small=0";

        params += "&Lucky6=" + 0 + "&AnyPairs=" + 0 + "&NPlayer=" + 0 + "&PerfectPairs=" + 0 + "&NBanker=" + 0 +
                "&CowPlayer=" + 0 + "&CowTie=" + 0 + "&CowBanker=" + 0;
        return params;
    }

    private static String getDragonTigerBetParam(int tableId, DragonTigerTableBetBean betBean, AppModel mAppViewModel) {
        String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                + "&Xhid=" + mAppViewModel.getDragonTiger(tableId).getShoeNumber() + "&Blid=" + mAppViewModel.getDragonTiger(tableId).getGameNumber()
                + "&Xh=" + mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMaxTotalBet()
                + "&Areaid=" + mAppViewModel.getAreaId() + "&Serial=" + mAppViewModel.getSerialId() + "&Hl=1"
                + "&Dragon=" + betBean.getDragonCurrentBet() + "&Tiger=" + betBean.getTigerCurrentBet() + "&Tie=" + betBean.getTieCurrentBet() + "&DragonOdd=" + 0 + "&DragonEven=" + 0
                + "&DragonRed=" + 0 + "&DragonBlack=" + 0
                + "&TigerRed=" + 0 + "&TigerBlack=" + 0
                + "&TigerOdd=" + 0 + "&TigerEven=" + 0;
        return params;
    }

    private static String getSicboBetParam(int tableId, SicboTableBetBean betBean, AppModel mAppViewModel) {
        String odd = "0";
        String even = "0";
        String threeForces = "";
        String nineWayGards = "0";
        String pairs = "0";
        String waiDices = "0";
        String points = "0";

        if (betBean.getSingle1CurrentBet() > 0) {
            threeForces += "1#" + betBean.getSingle1CurrentBet() + "|";
        }
        if (betBean.getSingle2CurrentBet() > 0) {
            threeForces += "2#" + betBean.getSingle2CurrentBet() + "|";
        }
        if (betBean.getSingle3CurrentBet() > 0) {
            threeForces += "3#" + betBean.getSingle3CurrentBet() + "|";
        }
        if (betBean.getSingle4CurrentBet() > 0) {
            threeForces += "4#" + betBean.getSingle4CurrentBet() + "|";
        }
        if (betBean.getSingle5CurrentBet() > 0) {
            threeForces += "5#" + betBean.getSingle5CurrentBet() + "|";
        }
        if (betBean.getSingle6CurrentBet() > 0) {
            threeForces += "6#" + betBean.getSingle6CurrentBet() + "|";
        }
        if (threeForces.equals("")) {
            threeForces = "0";
        }
        String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                + "&Bl=" + mAppViewModel.getSicbo01().getGameNumber()
                + "&Xh=" + mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet()
                + "&Hl=1"
                + "&Big=" + betBean.getBigCurrentBet() + "&Small=" + betBean.getSmallCurrentBet() + "&Odd=" + odd + "&Even=" + even + "&AllDices=" + betBean.getAnyCurrentBet()
                + "&ThreeForces=" + threeForces + "&NineWayGards=" + nineWayGards + "&Pairs=" + pairs + "&SurroundDices=" + waiDices + "&Points=" + points;
        return params;
    }

    private static String getRouletteBetParam(int tableId, RouletteTableBetBean betBean, AppModel mAppViewModel) {
        String zero = "";
        if (betBean.getZeroCurrentBet() > 0) {
            zero += "00#" + betBean.getZeroCurrentBet() + "|";
        }
        if (zero.equals("")) {
            zero = "0";
        }
        String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                + "&Blid=" + mAppViewModel.getRoulette01().getGameNumber()
                + "&Xh=" + mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet()
                + "&Hl=1"
                + "&RedBet=" + betBean.getRedCurrentBet() + "&BlackBet=" + betBean.getBlackCurrentBet() + "&OddBet=" + betBean.getOddCurrentBet() + "&EvenBet=" + betBean.getEvenCurrentBet() + "&LowBet=" + betBean.getSingle1_18CurrentBet() + "&HightBet=" + betBean.getSingle19_36CurrentBet()
                + "&FristRow=" + 0 + "&SndRow=" + 0 + "&ThrRow=" + 0 + "&FristCol=" + betBean.getSingle1_12CurrentBet() + "&SndCol=" + betBean.getSingle13_24CurrentBet() + "&ThrCol=" + betBean.getSingle25_36CurrentBet()
                + "&FourBet=" + 0
                + "&DirectBet=" + zero + "&SeparateBet=" + 0 + "&StreetBet=" + 0 + "&AngleBet=" + 0 + "&LineBet=" + 0 + "&ThreeBet=" + 0;
        return params;
    }

    public static List<ChipBean> getChipList() {
        ArrayList<ChipBean> chipList = new ArrayList<>();
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
        return chipList;
    }
}
