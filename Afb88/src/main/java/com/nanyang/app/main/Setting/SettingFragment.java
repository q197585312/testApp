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
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Administrator on 2019/4/25.
 */

public class SettingFragment extends BaseMoreFragment<MainPresenter> implements ILanguageView<String> {
    @BindView(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;
    BaseRecyclerAdapter<SettingInfoBean> adapter;

    private String quickAmount;
    private String hideChip;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_setting;
    }


    @Override
    public void initData() {
        super.initData();
        aty = (BaseToolbarActivity) getBaseActivity();
        createPresenter(new MainPresenter(this));

        adapter = new BaseRecyclerAdapter<SettingInfoBean>(mContext, new ArrayList<SettingInfoBean>(), R.layout.item_setting) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, SettingInfoBean item) {
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
                } else {
                    tvChoiceType.setVisibility(View.GONE);
                    cbChoice.setVisibility(View.GONE);
                    if (item.chipBeans != null && item.chipBeans.size() > 0) {
                        llChip.setVisibility(View.VISIBLE);
                        BaseRecyclerAdapter<ChipBean> chipBeanBaseRecyclerAdapter = initChipAdapter();
                        rv_chips.setAdapter(chipBeanBaseRecyclerAdapter);
                        rv_chips.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                        chipBeanBaseRecyclerAdapter.addAllAndClear(item.chipBeans);
                    }
                }
                if (position == 2 || position == 3 || position == 4 || position == 5) {
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
                    case 5:
//                        tvChoiceType.setText(quickAmount);
                        break;
                    case 7:
                        int sort = ((BaseToolbarActivity) getBaseActivity()).getApp().getSort();
                        if (sort == 0) {
                            tvChoiceType.setText(R.string.hot_sort);
                        } else {
                            tvChoiceType.setText(R.string.sort_by_time);
                        }
                        break;
                    case 8:
                        MenuItemInfo allOdds = ((BaseToolbarActivity) getBaseActivity()).getApp().getMarketType();
                        tvChoiceType.setText(allOdds.getText());
                        break;
                    case 9:

                        if (SoundPlayUtils.getSoundIndex().getType().equals("0"))
                            tvChoiceType.setText(SoundPlayUtils.getSoundIndex().getText());
                        else {
                            tvChoiceType.setText(getString(SoundPlayUtils.getSoundIndex().getText()) + SoundPlayUtils.getSoundIndex().getType());
                        }
                        break;
                    case 10:
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
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SettingInfoBean>() {
            @Override
            public void onItemClick(View view, SettingInfoBean item, int position) {
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
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setOddsType(item);
                            }
                        };
                        List<MenuItemInfo> oddsTypeList = AfbUtils.getOddsTypeList(mContext, ((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode());
                        popOddType.setData(oddsTypeList, tv.getText().toString());
                        popOddType.showPopupDownWindow();
                        break;
                    case 4:
                    case 6:
                        if (cbChoice.isChecked()) {
                            cbChoice.setChecked(false);
                        } else {
                            cbChoice.setChecked(true);
                        }
                        break;
                    case 5:
                        BaseYseNoChoosePopupWindow baseYseNoChoosePopupWindow = new BaseYseNoChoosePopupWindow(mContext, tv) {
                            @Override
                            protected void clickSure(View v) {
                                quickAmount = getChooseMessage().getText().toString().trim();
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setQuickAmount(quickAmount);

//                                tv.setText(quickAmount);
                            }

                            @Override
                            public int onSetLayoutRes() {
                                return R.layout.popupwindow_content_edit_yes_no;
                            }
                        };
                        baseYseNoChoosePopupWindow.getChooseMessage().setText(quickAmount);
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreatedAndShow(baseYseNoChoosePopupWindow, Gravity.CENTER);
                        break;
                    case 7:
                        PopSwitch<IString> popSort = new PopSwitch<IString>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(IString item, int position) {
                                tv.setText(item.getText());
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setSort(position);
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
                    case 8:
                        PopSwitch<MenuItemInfo<String>> popMarket = new PopSwitch<MenuItemInfo<String>>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo<String> item, int position) {
                                tv.setText(item.getText());
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setMarketType(item);
                            }
                        };
                        List<MenuItemInfo<String>> markets = AfbUtils.getMarketsList(mContext);
                        popMarket.setData(markets, tv.getText().toString());
                        popMarket.showPopupDownWindow();
                        break;
                    case 9:
                        PopSwitch<SoundBean> popSound = new PopSwitch<SoundBean>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(SoundBean item, int position) {
                                if (item.getType().equals("0"))
                                    tv.setText(item.getText());
                                else {
                                    tv.setText(getString(item.getText()) + item.getType());
                                }

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
                    case 10:
                        PopSwitch<IString> popHideChip = new PopSwitch<IString>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(IString item, int position) {
                                tv.setText(item.getText());
                                hideChip = position + "";
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setHideChip(hideChip);
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
            }
        });
        return adapterChip;
    }

    @Override
    public void showContent() {
        super.showContent();
        initSetData();
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


    private List<SettingInfoBean> handleSettingData(SettingAllDataBean data) {
        LanguageHelper helper = new LanguageHelper(getBaseActivity());
        quickAmount = data.getAccamount() + "";
        hideChip = data.getIsHideChipSet();
        List<SettingInfoBean> beanList = new ArrayList<>();
        SettingInfoBean infoBean1 = new SettingInfoBean("1", getBaseActivity().getString(R.string.home_user_name), ((BaseToolbarActivity) getBaseActivity()).getApp().getUser().getLoginName());
        SettingInfoBean infoBean2 = new SettingInfoBean("1", getBaseActivity().getString(R.string.Password), "**********");
        SettingInfoBean infoBean3 = new SettingInfoBean("1", getBaseActivity().getBaseActivity().getString(R.string.choose_language), getString(helper.getLanguageItem().getText()));
        SettingInfoBean infoBean4 = new SettingInfoBean("1", getBaseActivity().getString(R.string.Odds_Type), getString(AfbUtils.getOddsTypeByType(mContext, data.getAccType(), ((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode()).getText()));
        SettingInfoBean infoBean5 = new SettingInfoBean("2", getBaseActivity().getString(R.string.better_odds), "1");
        SettingInfoBean infoBean6 = new SettingInfoBean("1", getBaseActivity().getString(R.string.quick_bet_amount), getString(R.string.customise));
        SettingInfoBean infoBean7 = new SettingInfoBean("2", getBaseActivity().getString(R.string.auto_refresh), "1");
        SettingInfoBean infoBean8 = new SettingInfoBean("1", getBaseActivity().getString(R.string.default_sort), data.getAccDefaultSorting()/*((BaseToolbarActivity) getBaseActivity()).getApp().getSort()*/ + "");
        SettingInfoBean infoBean9 = new SettingInfoBean("1", getBaseActivity().getString(R.string.market_type), data.getAccMarketType());
        SettingInfoBean infoBean10 = new SettingInfoBean("1", getBaseActivity().getString(R.string.score_sound), mContext.getString(R.string.sound) + data.getScoreSound());
        SettingInfoBean infoBeanChip = new SettingInfoBean("1", getBaseActivity().getString(R.string.hide_chip), data.getIsHideChipSet().equals("0") ? getBaseActivity().getString(R.string.chip_enable) : getBaseActivity().getString(R.string.chip_disable));
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


        SettingInfoBean infoBean11 = new SettingInfoBean("3", getBaseActivity().getString(R.string.chip_set), "", chipList1);
        SettingInfoBean infoBean12 = new SettingInfoBean("3", "", "", chipList2);


        beanList.add(infoBean1);
        beanList.add(infoBean2);
        beanList.add(infoBean3);
        beanList.add(infoBean4);
        beanList.add(infoBean5);
        beanList.add(infoBean6);
        beanList.add(infoBean7);
        beanList.add(infoBean8);
        beanList.add(infoBean9);
        beanList.add(infoBean10);
        beanList.add(infoBeanChip);
        beanList.add(infoBean11);
        beanList.add(infoBean12);

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
            LoginInfo.SettingWfBean settingWfBean = new LoginInfo.SettingWfBean("Savesort", new LanguageHelper(getBaseActivity()).getLanguage(), "wfSettingH50");
            settingWfBean.setMarketTyped(((BaseToolbarActivity) getBaseActivity()).getApp().getMarketType().getType());
            settingWfBean.setDefaultSortingd(((BaseToolbarActivity) getBaseActivity()).getApp().getSort() + "");
            settingWfBean.setScoreSoundd(SoundPlayUtils.getSoundIndex().getType());
            settingWfBean.setAccType(((BaseToolbarActivity) getBaseActivity()).getApp().getOddsType().getType());
            settingWfBean.setAmtS(quickAmount);
            settingWfBean.setHideChip(hideChip);
            String ChipsList = getChooseChips();
            //"ChipsList":"50000,30000,10000,5000,1000,500,10,1"
            settingWfBean.setChipsList(ChipsList);
            presenter.loadAllMainData(settingWfBean, new MainPresenter.CallBack<String>() {
                @Override
                public void onBack(String data) throws JSONException {
                    Log.d(TAG, "onBack: " + data);
                }
            });
        } else {
            adapter.notifyDataSetChanged();
//            initSetData();
        }
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

    public void onGetSettingContentData(List<SettingInfoBean> beanList) {
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
        return true;
    }
}
