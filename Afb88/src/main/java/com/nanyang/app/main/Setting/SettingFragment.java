package com.nanyang.app.main.Setting;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/25.
 */

public class SettingFragment extends BaseMoreFragment<MainPresenter> implements ILanguageView<String> {
    @Bind(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;
    BaseRecyclerAdapter<SettingInfoBean> adapter;
    private String quickAmount;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_setting;
    }


    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.setting));
        aty = (BaseToolbarActivity) getBaseActivity();
        createPresenter(new MainPresenter(this));

        adapter = new BaseRecyclerAdapter<SettingInfoBean>(mContext, new ArrayList<SettingInfoBean>(), R.layout.item_setting) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, SettingInfoBean item) {
                TextView tvName = holder.getView(R.id.tv_name);
                TextView tvChoiceType = holder.getView(R.id.tv_choice_type);
                CheckBox cbChoice = holder.getView(R.id.cb_choice);
                LinearLayout llChip = holder.getView(R.id.ll_chip);
                ImageView imgChip1 = holder.getView(R.id.img_chip_1);
                ImageView imgChip2 = holder.getView(R.id.img_chip_2);
                ImageView imgChip3 = holder.getView(R.id.img_chip_3);
                ImageView imgChip4 = holder.getView(R.id.img_chip_4);
                ImageView imgChip5 = holder.getView(R.id.img_chip_5);
                ImageView imgChip6 = holder.getView(R.id.img_chip_6);
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
                    llChip.setVisibility(View.VISIBLE);
                    int chip1 = item.getchip1();
                    if (chip1 == 0) {
                        imgChip1.setVisibility(View.GONE);
                    } else {
                        imgChip1.setVisibility(View.VISIBLE);
                        imgChip1.setImageResource(item.getchip1());
                        imgChip1.setTag(item.getChipSize1());
                        setChipsBg(imgChip1, item.getChipSize1());
                    }
                    imgChip2.setImageResource(item.getchip2());
                    imgChip2.setTag(item.getChipSize2());
                    imgChip3.setImageResource(item.getchip3());
                    imgChip3.setTag(item.getChipSize3());
                    imgChip4.setImageResource(item.getchip4());
                    imgChip4.setTag(item.getChipSize4());
                    imgChip5.setImageResource(item.getchip5());
                    imgChip5.setTag(item.getChipSize5());
                    imgChip6.setImageResource(item.getChip6());
                    imgChip6.setTag(item.getChipSize6());

                    setChipsBg(imgChip2, item.getChipSize2());
                    setChipsBg(imgChip3, item.getChipSize3());
                    setChipsBg(imgChip4, item.getChipSize4());
                    setChipsBg(imgChip5, item.getChipSize5());
                    setChipsBg(imgChip6, item.getChipSize6());
                    imgChip1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                    imgChip2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                    imgChip3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                    imgChip4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                    imgChip5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                    imgChip6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipClick(v);
                        }
                    });
                }
                if (position == 2 || position == 3 || position == 4 || position == 5) {
                    vLine.setVisibility(View.VISIBLE);
                } else {
                    vLine.setVisibility(View.GONE);
                }
                String choiceType = item.getChoiceType();
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
                        tvChoiceType.setText(quickAmount);
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
                        tvChoiceType.setText(SoundPlayUtils.getSoundIndex().getText());
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
                        break;
                    case 2:
                        if (AppConstant.IS_AGENT)
                            return;
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
                        List<MenuItemInfo> oddsTypeList = AfbUtils.getOddsTypeList(mContext,((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode());
                        popOddType.setData(oddsTypeList, tv.getText().toString());
                        popOddType.showPopupDownWindow();
                        break;
                    case 4:
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

                                tv.setText(quickAmount);
                            }

                            @Override
                            protected int onSetLayoutRes() {
                                return R.layout.popupwindow_content_edit_yes_no;
                            }
                        };
                        baseYseNoChoosePopupWindow.getChooseMessage().setText(tv.getText());
                        ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreated(baseYseNoChoosePopupWindow, Gravity.CENTER);
                        break;
                    case 6:
                        if (cbChoice.isChecked()) {
                            cbChoice.setChecked(false);
                        } else {
                            cbChoice.setChecked(true);
                        }
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
                            public String getText() {
                                return getString(R.string.hot_sort);
                            }
                        });
                        strings.add(new IString() {
                            @Override
                            public String getText() {
                                return getString(R.string.sort_by_time);
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
                                tv.setText(item.getText());
                                SoundPlayUtils.setSound(item);
                            }
                        };
                        List<SoundBean> sounds = SoundPlayUtils.getSoundList();
                        popSound.setData(sounds, tv.getText().toString());
                        popSound.showPopupDownWindow();
                        break;
                }
            }
        });
        initSetData();

    }

    @Override
    public void showContent() {
        super.showContent();
        initSetData();
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
        List<SettingInfoBean> beanList = new ArrayList<>();
        SettingInfoBean infoBean1 = new SettingInfoBean("1", getBaseActivity().getString(R.string.home_user_name), ((BaseToolbarActivity) getBaseActivity()).getApp().getUser().getLoginName(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean2 = new SettingInfoBean("1", getBaseActivity().getString(R.string.Password), "**********", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean3 = new SettingInfoBean("1", getBaseActivity().getBaseActivity().getString(R.string.choose_language), helper.getLanguageItem().getText(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean4 = new SettingInfoBean("1", getBaseActivity().getString(R.string.Odds_Type), AfbUtils.getOddsTypeByType(mContext, data.getAccType(),((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().getCurCode()).getText(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean5 = new SettingInfoBean("2", getBaseActivity().getString(R.string.better_odds), "1", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean6 = new SettingInfoBean("1", getBaseActivity().getString(R.string.quick_bet_amount), data.getAccamount() + "", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean7 = new SettingInfoBean("2", getBaseActivity().getString(R.string.auto_refresh), "1", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean8 = new SettingInfoBean("1", getBaseActivity().getString(R.string.default_sort), data.getAccDefaultSorting()/*((BaseToolbarActivity) getBaseActivity()).getApp().getSort()*/ + "", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean9 = new SettingInfoBean("1", getBaseActivity().getString(R.string.market_type), data.getAccMarketType(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean10 = new SettingInfoBean("1", getBaseActivity().getString(R.string.score_sound), mContext.getString(R.string.sound) + data.getScoreSound(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean11 = new SettingInfoBean("3", getBaseActivity().getString(R.string.chip_set), "", R.mipmap.chips5000, R.mipmap.chips10000, R.mipmap.chips30000, R.mipmap.chips50000, R.mipmap.chips100000, R.mipmap.chips_max);
        SettingInfoBean infoBean12 = new SettingInfoBean("3", "", "", R.mipmap.chips1, R.mipmap.chips10, R.mipmap.chips50, R.mipmap.chips100, R.mipmap.chips500, R.mipmap.chips1000);
        infoBean11.setChipSize1(5000);
        infoBean11.setChipSize2(10000);
        infoBean11.setChipSize3(30000);
        infoBean11.setChipSize4(50000);
        infoBean11.setChipSize5(100000);
        infoBean11.setChipSize6(0);

        infoBean12.setChipSize1(1);
        infoBean12.setChipSize2(10);
        infoBean12.setChipSize3(50);
        infoBean12.setChipSize4(100);
        infoBean12.setChipSize5(500);
        infoBean12.setChipSize6(1000);

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
        beanList.add(infoBean11);
        beanList.add(infoBean12);

        return beanList;
    }

    private void chipClick(View v) {
        ImageView imageView = (ImageView) v;
        int chipSize = (int) imageView.getTag();
        String chip = chipSize + "";
        if (chipSize == 0) {
            chip = "max";
        }
        boolean status = AfbUtils.getChipStatusMap().get(chip);
        AfbUtils.getChipStatusMap().put(chip, !status);
        setChipsBg(imageView, chipSize);
    }

    private void setChipsBg(ImageView imageView, int chipSize) {
        String chip = chipSize + "";
        if (chipSize == 0) {
            chip = "max";
        }
        Boolean finalStatus = AfbUtils.getChipStatusMap().get(chip);
        if (finalStatus != null && finalStatus) {
            imageView.setBackgroundResource(R.drawable.shape_chip);
        } else {
            imageView.setBackgroundResource(0);
        }
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {
        Intent intent = new Intent(getActivity(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getBaseActivity().finish();
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
            if (n > 0 && (n < 5 || n > 8)) {
                ToastUtils.showShort(R.string.no_less_5_no_more_8);
                return false;
            }
        }
        return true;
    }
}
