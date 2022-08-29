package com.nanyang.app.main.Setting;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.Pop.IString;
import com.nanyang.app.main.Setting.Pop.PopSwitch;
import com.nanyang.app.main.Setting.Pop.SoundBean;
import com.nanyang.app.main.home.GameChooseBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class SettingFragment extends BaseMoreFragment<MainPresenter> implements ILanguageView<String> {
    @BindView(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;
    BaseRecyclerAdapter<SettingBean> adapter;

    private String quickAmount;
    private String mixPar;
    private String parAmt;
    private String hideChip;
    Map<String, Boolean> gameMap = new HashMap<>();
    private boolean hasModified = false;
    private String BetterOdds = "1";

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_setting;
    }


    @Override
    public void initData() {
        super.initData();
        aty = (BaseToolbarActivity) getBaseActivity();
        createPresenter(new MainPresenter(this));

        adapter = new BaseRecyclerAdapter<SettingBean>(mContext, new ArrayList<SettingBean>(), R.layout.item_setting) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, SettingBean item) {
                TextView tvName = holder.getView(R.id.tv_name);
                TextView tvChoiceType = holder.getView(R.id.tv_choice_type);
                CheckBox cbChoice = holder.getView(R.id.cb_choice);
                LinearLayout llChip = holder.getView(R.id.ll_chip);
                RecyclerView rv_chips = holder.getView(R.id.rv_chips);

                View vLine = holder.getView(R.id.v_line);
                tvName.setText(item.getName());
                tvChoiceType.setText(item.getChoiceType());
                cbChoice.setChecked(true);
                String type = item.getType();
                if (type.equals("1")) {
                    tvChoiceType.setVisibility(View.VISIBLE);
                    cbChoice.setVisibility(View.GONE);
                    llChip.setVisibility(View.GONE);
                } else if (type.equals("2")) {
                    tvChoiceType.setVisibility(View.GONE);
                    cbChoice.setVisibility(View.VISIBLE);
                    llChip.setVisibility(View.GONE);
                } else if (type.equals("3")) {
                    tvChoiceType.setVisibility(View.GONE);
                    cbChoice.setVisibility(View.GONE);
                    if (item.chipBeans != null && item.chipBeans.size() > 0) {
                        llChip.setVisibility(View.VISIBLE);
                        BaseRecyclerAdapter<ChipBean> chipBeanBaseRecyclerAdapter = initChipAdapter();
                        rv_chips.setAdapter(chipBeanBaseRecyclerAdapter);
                        rv_chips.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                        chipBeanBaseRecyclerAdapter.addAllAndClear(item.chipBeans);
                    }
                } else if (type.equals("4")) {
                    tvChoiceType.setVisibility(View.GONE);
                    cbChoice.setVisibility(View.GONE);
                    if (item.getGameChooseBeans() != null && item.getGameChooseBeans().size() > 0) {
                        llChip.setVisibility(View.VISIBLE);
                        BaseRecyclerAdapter<GameChooseBean> chipBeanBaseRecyclerAdapter = initGameChooseAdapter();
                        rv_chips.setAdapter(chipBeanBaseRecyclerAdapter);
                        rv_chips.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                        chipBeanBaseRecyclerAdapter.addAllAndClear(item.getGameChooseBeans());
                    }
                }

                if (position == 2 || position == 3 || position == 4 || position == 7) {
                    vLine.setVisibility(View.VISIBLE);
                } else {
                    vLine.setVisibility(View.GONE);
                }

                switch (position) {

                    case 2:
                        MenuItemInfo<String> language = new LanguageHelper(getBaseActivity()).getLanguageItem();
                        tvChoiceType.setText(language.getText());
                        break;
                    case 3:
                        MenuItemInfo oddsType = ((BaseToolbarActivity) getBaseActivity()).getApp().getOddsType();
                        tvChoiceType.setText(oddsType.getText());
                        break;
                    case 4:
                        if (BetterOdds.equals("1")) {
                            cbChoice.setChecked(true);
                        } else {
                            cbChoice.setChecked(false);
                        }
                        break;
                    case 5:

                        tvChoiceType.setText(quickAmount);
                        break;
                    case 6:
                        tvChoiceType.setText(mixPar);
                        break;
                    case 7:
                        tvChoiceType.setText(parAmt);
                        break;
                    case 10:
                        MenuItemInfo allOdds = ((BaseToolbarActivity) getBaseActivity()).getApp().getMarketType();
                        tvChoiceType.setText(allOdds.getText());
                        break;
                    case 11:

                        if (SoundPlayUtils.getSoundIndex().getType().equals("0"))
                            tvChoiceType.setText(SoundPlayUtils.getSoundIndex().getText());
                        else {
                            tvChoiceType.setText(getString(SoundPlayUtils.getSoundIndex().getText()) + SoundPlayUtils.getSoundIndex().getType());
                        }
                        break;
                    case 12:
                        if (((BaseToolbarActivity) getBaseActivity()).getApp().getHideChip().equals("0"))
                            tvChoiceType.setText(getString(R.string.chip_enable));
                        else {
                            tvChoiceType.setText(getString(R.string.chip_disable));
                        }
                        break;
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rcContent.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SettingBean>() {
            @Override
            public void onItemClick(View view, SettingBean item, int position) {
                final TextView tv = view.findViewById(R.id.tv_choice_type);
                CheckBox cbChoice = view.findViewById(R.id.cb_choice);
                switch (position) {
                    case 1:
                        BaseYseNoChoosePopupWindow passwordPopupWindow = new BaseYseNoChoosePopupWindow(mContext, tv) {
                            @Override
                            protected void onClickSure(View v) {
                                EditText newPassword = contentView.findViewById(R.id.choose_new_password_tv);
                                EditText repeatPassword = (EditText) chooseMessage;
                                TextView tip_new_password_tv = contentView.findViewById(R.id.tip_new_password_tv);
                                TextView tip_repeat_password_tv = contentView.findViewById(R.id.tip_repeat_password_tv);
                                String strNew = newPassword.getText().toString();
                                String strRepeat = repeatPassword.getText().toString();
                                if (presenter.verifyPassword(strNew, tip_new_password_tv, strRepeat, tip_repeat_password_tv)) {
                                    presenter.doChangePassword(strNew, data -> {
                                        if (data.contains("OK")) {
                                            ToastUtils.showShort("Your password has been changed successfully");
                                            closePopupWindow();
                                        } else {
                                            tip_repeat_password_tv.setVisibility(View.VISIBLE);
                                            tip_repeat_password_tv.setText(data);
                                        }
                                    });
                                }
                            }

                            @Override
                            public int onSetLayoutRes() {
                                return R.layout.popupwindow_password_edit_yes_no;
                            }
                        };
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreatedAndShow(passwordPopupWindow, Gravity.CENTER);
                        break;

                    case 2:

                        PopSwitch popLg = new PopSwitch<MenuItemInfo<String>>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo<String> item, int position) {
                                tv.setText(item.getText());
                                AfbUtils.switchLanguage(item.getType(), getActivity());

                                presenter.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
                                    @Override
                                    public void onBack(SettingAllDataBean data) throws JSONException {
                                        onLanguageSwitchSucceed("");
                                    }
                                });
                            }
                        };
                        List<MenuItemInfo<String>> menuItemInfos = new LanguageHelper(getBaseActivity()).getLanguageItems();
                        popLg.setData(menuItemInfos, tv.getText().toString());
                        popLg.showPopupDownWindow();
                        break;
                    case 3:
                        PopSwitch<MenuItemInfo> popOddType = new PopSwitch<MenuItemInfo>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo item, int position) {
                                tv.setText(item.getText());
                                if (!item.getType().equals(((BaseToolbarActivity) getBaseActivity()).getApp().getOddsType().getType())) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().setOddsType(item);
                                }


                            }
                        };
                        List<MenuItemInfo> oddsTypeList = AfbUtils.getOddsTypeList(mContext, ((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode());
                        popOddType.setData(oddsTypeList, tv.getText().toString());
                        popOddType.showPopupDownWindow();
                        break;
                    case 4:
                        if (cbChoice.isChecked()) {
                            BetterOdds = "0";
                            cbChoice.setChecked(false);

                        } else {
                            BetterOdds = "1";
                            cbChoice.setChecked(true);

                        }
                        hasModified = true;
                        break;
                    case 8:
                        if (cbChoice.isChecked()) {
                            cbChoice.setChecked(false);
                        } else {
                            cbChoice.setChecked(true);
                        }
                        break;
                    case 5:
                        BaseYseNoChoosePopupWindow baseYseNoChoosePopupWindow2 = new BaseYseNoChoosePopupWindow(mContext, tv) {
                            @Override
                            protected void clickSure(View v) {
                                quickAmount = getChooseMessage().getText().toString().trim();
                                if (!quickAmount.equals(((BaseToolbarActivity) getBaseActivity()).getApp().getQuickAmount())) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().setQuickAmount(quickAmount);
                                }


                                tv.setText(quickAmount);
                            }

                            @Override
                            public int onSetLayoutRes() {
                                return R.layout.popupwindow_content_edit_yes_no;
                            }
                        };
                        baseYseNoChoosePopupWindow2.getChooseMessage().setText(quickAmount);
                        baseYseNoChoosePopupWindow2.getChooseTitleTv().setText(R.string.quick_bet_amount);
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreatedAndShow(baseYseNoChoosePopupWindow2, Gravity.CENTER);
                        break;
                    case 6:
                        BaseYseNoChoosePopupWindow baseYseNoChoosePopupWindow = new BaseYseNoChoosePopupWindow(mContext, tv) {
                            @Override
                            protected void clickSure(View v) {
                                mixPar = getChooseMessage().getText().toString().trim();

                                if (!mixPar.equals(((BaseToolbarActivity) getBaseActivity()).getApp().mixParAmount)) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().mixParAmount = (mixPar);
                                }
                                tv.setText(mixPar);
                            }

                            @Override
                            public int onSetLayoutRes() {
                                return R.layout.popupwindow_content_edit_yes_no;
                            }
                        };
                        baseYseNoChoosePopupWindow.getChooseMessage().setText(mixPar);
                        baseYseNoChoosePopupWindow.getChooseTitleTv().setText(R.string.quick_mix_par_amount);
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreatedAndShow(baseYseNoChoosePopupWindow, Gravity.CENTER);
                        break;
                    case 7:
                        BaseYseNoChoosePopupWindow baseYseNoChoosePopupWindow1 = new BaseYseNoChoosePopupWindow(mContext, tv) {
                            @Override
                            protected void clickSure(View v) {
                                parAmt = getChooseMessage().getText().toString().trim();

                                if (!parAmt.equals(((BaseToolbarActivity) getBaseActivity()).getApp().parAmtAmount)) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().parAmtAmount = (parAmt);
                                }
                                tv.setText(parAmt);
                            }

                            @Override
                            public int onSetLayoutRes() {
                                return R.layout.popupwindow_content_edit_yes_no;
                            }
                        };
                        baseYseNoChoosePopupWindow1.getChooseMessage().setText(parAmt);
                        baseYseNoChoosePopupWindow1.getChooseTitleTv().setText(R.string.quick_par_single_amount);
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreatedAndShow(baseYseNoChoosePopupWindow1, Gravity.CENTER);
                        break;

                    case 9:
                        PopSwitch<IString> popSort = new PopSwitch<IString>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(IString item, int position) {
                                tv.setText(item.getText());

                                if (position != ((BaseToolbarActivity) getBaseActivity()).getApp().getSort()) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().setSort(position);
                                }
                            }
                        };
                        List<IString> strings = new ArrayList<>();
                        strings.add(new IString() {
                            @Override
                            public int getText() {
                                return (R.string.hot_sort);
                            }
                        });
                        strings.add(new IString() {
                            @Override
                            public int getText() {
                                return (R.string.sort_by_time);
                            }
                        });
                        popSort.setData(strings, tv.getText().toString());
                        popSort.showPopupDownWindow();
                        break;
                    case 10:
                        PopSwitch<MenuItemInfo<String>> popMarket = new PopSwitch<MenuItemInfo<String>>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo<String> item, int position) {
                                tv.setText(item.getText());
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setMarketType(item);
                                if (!item.getType().equals(((BaseToolbarActivity) getBaseActivity()).getApp().getMarketType().getType())) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().setMarketType(item);
                                }
                            }
                        };
                        List<MenuItemInfo<String>> markets = AfbUtils.getMarketsList(mContext);
                        popMarket.setData(markets, tv.getText().toString());
                        popMarket.showPopupDownWindow();
                        break;
                    case 11:
                        PopSwitch<SoundBean> popSound = new PopSwitch<SoundBean>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(SoundBean item, int position) {
                                if (item.getType().equals("0"))
                                    tv.setText(item.getText());
                                else {
                                    tv.setText(getString(item.getText()) + item.getType());
                                }
                                hasModified = true;
                                SoundPlayUtils.setSound(item);
                            }

                            @Override
                            public void onConvert(MyRecyclerViewHolder holder, int position, SoundBean item) {

                                TextView tv = holder.getView(R.id.item_text_tv);
                                if (item.getType().equals("0"))
                                    tv.setText(item.getText());
                                else {
                                    tv.setText(getString(item.getText()) + item.getType());
                                }
                                if (getChoiceStr().equals(tv.getText())) {
                                    tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                                    tv.setTextColor(ContextCompat.getColor(context, R.color.blue));
                                } else {
                                    tv.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                                    tv.setTextColor(ContextCompat.getColor(context, R.color.black));
                                }
                            }
                        };
                        List<SoundBean> sounds = SoundPlayUtils.getSoundList();
                        popSound.setData(sounds, tv.getText().toString());
                        popSound.showPopupDownWindow();
                        break;
                    case 12:
                        PopSwitch<IString> popHideChip = new PopSwitch<IString>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(IString item, int position) {
                                tv.setText(item.getText());
                                hideChip = position + "";
                                if (!hideChip.equals(((BaseToolbarActivity) getBaseActivity()).getApp().getHideChip())) {
                                    hasModified = true;
                                    ((BaseToolbarActivity) getBaseActivity()).getApp().setHideChip(hideChip);
                                }
                            }
                        };
                        List<IString> hideChipStrings = new ArrayList<>();
                        hideChipStrings.add(new IString() {
                            @Override
                            public int getText() {
                                return (R.string.chip_enable);
                            }
                        });
                        hideChipStrings.add(new IString() {
                            @Override
                            public int getText() {
                                return (R.string.chip_disable);
                            }
                        });
                        popHideChip.setData(hideChipStrings, tv.getText().toString());
                        popHideChip.showPopupDownWindow();
                        break;
                }
            }
        });


        initSetData();

    }

    private BaseRecyclerAdapter<ChipBean> initChipAdapter() {
        BaseRecyclerAdapter<ChipBean> adapterChip = new BaseRecyclerAdapter<ChipBean>(mContext, new ArrayList<ChipBean>(), R.layout.item_setting_chip) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, ChipBean item) {
                ImageView view = holder.getView(R.id.img_chip);
                view.setImageResource(item.getDrawableRes());
                Boolean finalStatus = AfbUtils.getChipStatusMap().get(item.getName());
                if (finalStatus != null && finalStatus) {
                    view.setBackgroundResource(R.drawable.shape_chip);
                } else {
                    view.setBackgroundResource(0);
                }
            }
        };
        adapterChip.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ChipBean>() {
            @Override
            public void onItemClick(View view, ChipBean item, int position) {
                chipClick(item, adapterChip);
                hasModified = true;

            }
        });
        return adapterChip;
    }

    private BaseRecyclerAdapter<GameChooseBean> initGameChooseAdapter() {
        BaseRecyclerAdapter<GameChooseBean> adapterGameChoose = new BaseRecyclerAdapter<GameChooseBean>(mContext, new ArrayList<GameChooseBean>(), R.layout.item_game_choose) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GameChooseBean item) {
                TextView item_name = holder.getView(R.id.item_name);
                item_name.setText(item.getGameName());
                CheckBox view = holder.getView(R.id.cb_selected);
                Boolean aBoolean = gameMap.get(item.getGameType());
                if (aBoolean == null)
                    aBoolean = true;
                view.setChecked(aBoolean);
            }
        };
        adapterGameChoose.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GameChooseBean>() {
            @Override
            public void onItemClick(View view, GameChooseBean item, int position) {
                gameSelectClick(item, adapterGameChoose);
            }
        });
        return adapterGameChoose;
    }

    private void gameSelectClick(GameChooseBean item, BaseRecyclerAdapter<GameChooseBean> adapterGameChoose) {
        Boolean aBoolean = gameMap.get(item.getGameType());
        if (aBoolean == null)
            aBoolean = true;
        gameMap.put(item.getGameType(), !aBoolean);
        hasModified = true;
        adapterGameChoose.notifyDataSetChanged();
    }

    @Override
    public void showContent() {
        super.showContent();
//        initSetData();
        setBackTitle(getString(R.string.setting));
    }

    private void initSetData() {
        presenter.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
            @Override
            public void onBack(SettingAllDataBean data) throws JSONException {
                onGetSettingContentData(handleSettingData(data));
            }
        });
    }


    private List<SettingBean> handleSettingData(SettingAllDataBean data) {
        LanguageHelper helper = new LanguageHelper(getBaseActivity());
        quickAmount = data.getQuerBetAmt() + "";
        mixPar = data.getQuerMixParBetAmt() + "";
        parAmt = data.getQuerParBetAmt();
        hideChip = data.getIsHideChipSet();
        BetterOdds = data.getBetterOdds();
        String h5MainChoose = ((BaseToolbarActivity) getBaseActivity()).getApp().H5MainChoose;
        List<SettingBean> beanList = new ArrayList<>();
        SettingBean infoBean1 = new SettingBean("1", getBaseActivity().getString(R.string.home_user_name), ((BaseToolbarActivity) getBaseActivity()).getApp().getUser().getLoginName());
        SettingBean infoBean2 = new SettingBean("1", getBaseActivity().getString(R.string.Password), "**********");
        SettingBean infoBean3 = new SettingBean("1", getBaseActivity().getBaseActivity().getString(R.string.choose_language), getString(helper.getLanguageItem().getText()));
        SettingBean infoBean4 = new SettingBean("1", getBaseActivity().getString(R.string.Odds_Type), getString(AfbUtils.getOddsTypeByType(mContext, data.getAccType(), ((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode()).getText()));
        SettingBean infoBean5 = new SettingBean("2", getBaseActivity().getString(R.string.better_odds), BetterOdds);

        SettingBean infoBean6 = new SettingBean("1", getBaseActivity().getString(R.string.quick_bet_amount), quickAmount);
        SettingBean infoBeanMixPar = new SettingBean("1", getBaseActivity().getString(R.string.quick_mix_par_amount), mixPar);
        SettingBean infoBeanParAmt = new SettingBean("1", getBaseActivity().getString(R.string.quick_par_single_amount), parAmt);

        SettingBean infoBean7 = new SettingBean("2", getBaseActivity().getString(R.string.auto_refresh), "1");
        String sort = data.getAccDefaultSorting();
        if (sort == "0") {
            sort = getString(R.string.hot_sort);
        } else {
            sort = getString(R.string.sort_by_time);
        }
        SettingBean infoBean8 = new SettingBean("1", getBaseActivity().getString(R.string.default_sort), sort);
        SettingBean infoBean9 = new SettingBean("1", getBaseActivity().getString(R.string.market_type), data.getAccMarketType());
        SettingBean infoBean10 = new SettingBean("1", getBaseActivity().getString(R.string.score_sound), mContext.getString(R.string.sound) + data.getScoreSound());
        SettingBean infoBeanChip = new SettingBean("1", getBaseActivity().getString(R.string.hide_chip), data.getIsHideChipSet().equals("0") ? getBaseActivity().getString(R.string.chip_enable) : getBaseActivity().getString(R.string.chip_disable));
        List<ChipBean> chipList1 = new ArrayList<>();
        List<ChipBean> chipList2 = new ArrayList<>();
        chipList1.add(new ChipBean(R.mipmap.chips5000, "5000", 5000));
        chipList1.add(new ChipBean(R.mipmap.chips10000, "10000", 10000));
        chipList1.add(new ChipBean(R.mipmap.chips30000, "30000", 30000));
        chipList1.add(new ChipBean(R.mipmap.chips50000, "50000", 50000));
        chipList1.add(new ChipBean(R.mipmap.chips100000, "100000", 100000));
        chipList1.add(new ChipBean(R.mipmap.chips_max, "max", 0));

        chipList2.add(new ChipBean(R.mipmap.chips_min, "min", -1));
        chipList2.add(new ChipBean(R.mipmap.chips1, "1", 1));
        chipList2.add(new ChipBean(R.mipmap.chips10, "10", 10));
        chipList2.add(new ChipBean(R.mipmap.chips50, "50", 50));
        chipList2.add(new ChipBean(R.mipmap.chips100, "100", 100));
        chipList2.add(new ChipBean(R.mipmap.chips500, "500", 500));
        chipList2.add(new ChipBean(R.mipmap.chips1000, "1000", 1000));


        SettingBean infoBean11 = new SettingBean("3", getBaseActivity().getString(R.string.chip_set), "", chipList1);
        SettingBean infoBean12 = new SettingBean("3", "", "", chipList2);
        SettingBean infoBean13 = new SettingBean("1", "MAIN_FAVORITE", "");


        List<String> stringList = new ArrayList<>();
        if (h5MainChoose == null || h5MainChoose.length() < 2) {
            gameMap = AfbUtils.initSelectedGameMap(true);
        } else {
            gameMap = AfbUtils.initSelectedGameMap(false);
            String[] split = h5MainChoose.split(",");
            stringList = Arrays.asList(split);
            for (String item : stringList) {
                gameMap.put(item, true);
            }
        }

        List<GameChooseBean> gameChooseBeans1 = new ArrayList<>();
        List<GameChooseBean> gameChooseBeans2 = new ArrayList<>();
        List<GameChooseBean> gameChooseBeans3 = new ArrayList<>();
        List<GameChooseBean> gameChooseBeans4 = new ArrayList<>();
        gameChooseBeans1.add(new GameChooseBean("GD88", "Cashio"));
        gameChooseBeans1.add(new GameChooseBean("AFB CASINO", "AFBCashio"));
        gameChooseBeans1.add(new GameChooseBean("LG", "LGCashio"));
        gameChooseBeans1.add(new GameChooseBean("DG", "DGCashio"));
        gameChooseBeans1.add(new GameChooseBean("WM", "WMCashio"));
        gameChooseBeans1.add(new GameChooseBean("SG", "SGCashio"));

        gameChooseBeans2.add(new GameChooseBean("SV388", "SV388Cashio"));
        gameChooseBeans2.add(new GameChooseBean("NL", "NLCashio"));
        gameChooseBeans2.add(new GameChooseBean("SA", "SACashio"));
        gameChooseBeans2.add(new GameChooseBean("MK", "MKCashio"));
        gameChooseBeans2.add(new GameChooseBean("LUCKY361", "LUCKY361Cashio"));
        gameChooseBeans2.add(new GameChooseBean("JILI", "JILICashio"));

        gameChooseBeans3.add(new GameChooseBean("AFB Slots", "AFBSlotsCashio"));
        gameChooseBeans3.add(new GameChooseBean("PS", "PSCashio"));
        gameChooseBeans3.add(new GameChooseBean("PRG", "PRGCashio"));
        gameChooseBeans3.add(new GameChooseBean("PG", "PGCashio"));
        gameChooseBeans3.add(new GameChooseBean("EV", "EVCashio"));
        gameChooseBeans3.add(new GameChooseBean("CQ", "CQCashio"));

        gameChooseBeans4.add(new GameChooseBean("JKR", "JKRCashio"));
        gameChooseBeans4.add(new GameChooseBean("E-SPORTS", "TFGCashio"));
        gameChooseBeans4.add(new GameChooseBean("COCK FIGHT", "Cockfigh"));
        gameChooseBeans4.add(new GameChooseBean("KENO", "Keno"));
        gameChooseBeans4.add(new GameChooseBean("LOTTERY", "Lottery"));


        SettingBean infoBean14 = new SettingBean("4", "", gameChooseBeans1);
        SettingBean infoBean15 = new SettingBean("4", "", gameChooseBeans2);
        SettingBean infoBean16 = new SettingBean("4", "", gameChooseBeans3);
        SettingBean infoBean17 = new SettingBean("4", "", gameChooseBeans4);

        beanList.add(infoBean1);
        beanList.add(infoBean2);
        beanList.add(infoBean3);
        beanList.add(infoBean4);
        beanList.add(infoBean5);
        beanList.add(infoBean6);
        beanList.add(infoBeanMixPar);
        beanList.add(infoBeanParAmt);
        beanList.add(infoBean7);
        beanList.add(infoBean8);
        beanList.add(infoBean9);
        beanList.add(infoBean10);
        beanList.add(infoBeanChip);
        beanList.add(infoBean11);
        beanList.add(infoBean12);
        beanList.add(infoBean13);
        beanList.add(infoBean14);
        beanList.add(infoBean15);
        beanList.add(infoBean16);
        beanList.add(infoBean17);


        return beanList;
    }

    private void chipClick(ChipBean bean, BaseRecyclerAdapter<ChipBean> adapterChip) {
        String chip = bean.getName();
        boolean status = AfbUtils.getChipStatusMap().get(chip);
        AfbUtils.getChipStatusMap().put(chip, !status);
        adapterChip.notifyDataSetChanged();
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {
        if (!AppConstant.IS_AGENT) {
            Intent intent = new Intent(getActivity(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getBaseActivity().finish();
        } else {
            getBaseActivity().recreate();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
//            saveSettingData();
        } else {
            adapter.notifyDataSetChanged();
//            initSetData();
        }
    }

    public void saveSettingData() {
        /*"H5MainChoose":"1,SACashio,MKCashio,LUCKY361Cashio,TFGCashio"*/
        LoginInfo.SettingWfBean settingWfBean = new LoginInfo.SettingWfBean("Savesort", new LanguageHelper(getBaseActivity()).getLanguage(), "wfSettingH50");
        settingWfBean.setMarketTyped(((BaseToolbarActivity) getBaseActivity()).getApp().getMarketType().getType());
        settingWfBean.setDefaultSortingd(((BaseToolbarActivity) getBaseActivity()).getApp().getSort() + "");
        settingWfBean.setScoreSoundd(SoundPlayUtils.getSoundIndex().getType());
        settingWfBean.setAcc(((BaseToolbarActivity) getBaseActivity()).getApp().getOddsType().getType());
        settingWfBean.setAmtS(quickAmount);
        settingWfBean.setMixParAmt(mixPar);
        settingWfBean.setParAmt(parAmt);
        settingWfBean.setBetterOdds(BetterOdds);
        settingWfBean.setHideChip(hideChip);
        String selectedGameStr = AfbUtils.getSelectedGameStr(gameMap);
        settingWfBean.setH5MainChoose(selectedGameStr);
        String ChipsList = getChooseChips();
        //"ChipsList":"50000,30000,10000,5000,1000,500,10,1"
        settingWfBean.setChipsList(ChipsList);
        presenter.loadAllMainData(settingWfBean, new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                Log.d(TAG, "onBack: " + data);
                ((BaseToolbarActivity) getBaseActivity()).getApp().H5MainChoose = selectedGameStr;
            }
        });
    }


    String TAG = "SettingFragment";

    private String getChooseChips() {
        Iterator<Map.Entry<String, Boolean>> iterator = AfbUtils.getChipStatusMap().entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, Boolean> next = iterator.next();
            if (next.getValue()) {
                stringBuilder.append(next.getKey());
                stringBuilder.append(",");
            }
        }
        String string = stringBuilder.toString();
        if (string.length() > 1)
            return string.substring(0, string.length() - 1);
        return "";
    }

    public void onGetSettingContentData(List<SettingBean> beanList) {
        adapter.setData(beanList);
    }

    @Override
    public void onGetData(String data) {

    }

    @Override
    public void onFailed(String error) {

    }

    public boolean checkCanBack() {
        if (AfbUtils.getChipStatusMap() != null && !AfbUtils.getChipStatusMap().isEmpty()) {
            Collection<Boolean> values = AfbUtils.getChipStatusMap().values();
            int n = 0;
            for (Boolean value : values) {
                if (value)
                    n++;
            }
            if (n < 5 || n > 8) {
                ToastUtils.showShort(R.string.no_less_5_no_more_8);
                return false;
            }
        }
        if (hasModified)
            saveSettingData();
        return true;
    }
}
