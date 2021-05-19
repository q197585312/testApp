package gaming178.com.casinogame.Util;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
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
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetBean;
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetContentBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableBetBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableContentBean;
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

    public static void addChip(FrameLayout fl, int money, int betMoney, Context context) {
        List<ChipBean> chipList = getChipList();
        fl.removeAllViews();
        int bottomMargin = 0;
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
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            params.rightMargin = AutoUtils.getPercentHeightSize(5);
            params.bottomMargin = AutoUtils.getPercentHeightSize(2);
            moneyTv.setLayoutParams(params);
            moneyTv.setText(betMoney + "");
            moneyTv.setTextSize(6);
            moneyTv.setGravity(Gravity.CENTER);
            moneyTv.setTextColor(Color.WHITE);
            moneyTv.setBackgroundResource(R.drawable.gd_rectangle_trans_chip_tips);
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
