package com.nanyang.app.main.Setting;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.Pop.IString;
import com.nanyang.app.main.Setting.Pop.PopSwitch;
import com.nanyang.app.main.Setting.Pop.SoundBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/25.
 */

public class SettingFragment extends BaseMoreFragment<LanguagePresenter> implements ILanguageView<String> {
    @Bind(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;
    BaseRecyclerAdapter<SettingInfoBean> adapter;
    Map<Integer, Boolean> chipStatusMap;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.setting));
        aty = (BaseToolbarActivity) getBaseActivity();
        createPresenter(new LanguagePresenter(this));
        chipStatusMap = new HashMap<>();
        chipStatusMap.put(1, false);
        chipStatusMap.put(10, false);
        chipStatusMap.put(50, false);
        chipStatusMap.put(100, false);
        chipStatusMap.put(500, false);
        chipStatusMap.put(1000, false);
        chipStatusMap.put(5000, false);
        chipStatusMap.put(10000, false);
        chipStatusMap.put(30000, false);
        chipStatusMap.put(50000, false);
        chipStatusMap.put(100000, false);
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
                        String language = AfbUtils.getLanguage(mContext);
                        if (language.equals("en")) {
                            tvChoiceType.setText("ENGLISH");
                        } else if (language.equals("zh")) {
                            tvChoiceType.setText("简体中文");
                        } else if (language.equals("th")) {
                            tvChoiceType.setText("ภาษาไทย");
                        } else if (language.equals("vi")) {
                            tvChoiceType.setText("Tiếng Việt");
                        } else if (language.equals("KOREAN")) {
                            tvChoiceType.setText("ko");
                        } else if (language.equals("tr")) {
                            tvChoiceType.setText("TURKISH");
                        }
                        break;
                    case 7:
                        if (choiceType.equals("0")) {
                            tvChoiceType.setText("Normal Sorting");
                        } else {
                            tvChoiceType.setText("Sort by Time");
                        }
                        break;
                    case 8:
                        if (choiceType.equals("0")) {
                            tvChoiceType.setText(mContext.getString(R.string.All_Markets));
                        } else if (choiceType.equals("1")) {
                            tvChoiceType.setText(mContext.getString(R.string.Main_Markets));
                        } else {
                            tvChoiceType.setText(mContext.getString(R.string.Other_Bet_Markets));
                        }
                        break;
                    case 9:
                        if (choiceType.equals("1")) {
                            tvChoiceType.setText("Sound1");
                        } else if (choiceType.equals("2")) {
                            tvChoiceType.setText("Sound2");
                        } else if (choiceType.equals("3")) {
                            tvChoiceType.setText("Sound3");
                        } else if (choiceType.equals("4")) {
                            tvChoiceType.setText("Sound4");
                        } else if (choiceType.equals("5")) {
                            tvChoiceType.setText("Sound5");
                        } else if (choiceType.equals("6")) {
                            tvChoiceType.setText("Sound6");
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
                        break;
                    case 2:
                        PopSwitch popLg = new PopSwitch<MenuItemInfo<String>>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo<String> item, int position) {
                                tv.setText(item.getText());
                                AfbUtils.switchLanguage(item.getType(), getActivity());
                                presenter.switchLanguage(item.getParent());
                            }
                        };
                        List<MenuItemInfo<String>> menuItemInfos = new ArrayList<MenuItemInfo<String>>(Arrays.asList(
                                new MenuItemInfo<String>(R.mipmap.lang_zh_flag, "简体中文", "zh", "ZH-CN"),
                                new MenuItemInfo<String>(R.mipmap.lang_en_flag, "English", "en", "EN-US"),
                                new MenuItemInfo<String>(R.mipmap.lang_th_flag, "ไทย", "th", "TH-TH"),
                                new MenuItemInfo<String>(R.mipmap.lang_ko_flag, "한국의", "ko", "EN-TT"),
                                new MenuItemInfo<String>(R.mipmap.lang_vi_flag, "tiếng việt", "vi", "EN-IE"),
                                new MenuItemInfo<String>(R.mipmap.lang_tr_flag, "Türk dili", "tr", "UR-PK")
                        )
                        );
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
                        List<MenuItemInfo> list = new ArrayList<>();
                        list.add(new MenuItemInfo(0, getString(R.string.HK_ODDS), "HK"));//accType=
                        list.add(new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY"));
                        list.add(new MenuItemInfo(0, getString(R.string.ID_ODDS), "ID"));
                        list.add(new MenuItemInfo(0, getString(R.string.EU_ODDS), "EU"));

                        popOddType.setData(list, tv.getText().toString());
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
                        PopSwitch<MenuItemInfo> popMarket = new PopSwitch<MenuItemInfo>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(MenuItemInfo item, int position) {
                                tv.setText(item.getText());
                                ((BaseToolbarActivity) getBaseActivity()).getApp().setAllOdds(item);
                            }
                        };
                        List<MenuItemInfo> markets = new ArrayList<>();
                        markets.add(new MenuItemInfo(0, mContext.getString(R.string.All_Markets), "0"));//accType=
                        markets.add(new MenuItemInfo(0, mContext.getString(R.string.Main_Markets), "1"));
                        markets.add(new MenuItemInfo(0, mContext.getString(R.string.Other_Bet_Markets), "2"));
                        popMarket.setData(markets, tv.getText().toString());
                        popMarket.showPopupDownWindow();
                        break;
                    case 9:
                        PopSwitch<SoundBean> popSound = new PopSwitch<SoundBean>(mContext, tv, AfbUtils.dp2px(mContext, 130), ViewGroup.LayoutParams.WRAP_CONTENT) {
                            @Override
                            public void onClickItem(SoundBean item, int position) {
                                tv.setText(item.getText());
                                SoundPlayUtils.setSound(item.getSound());
                            }
                        };
                        List<SoundBean> sounds = new ArrayList<>();
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "1", SoundPlayUtils.sound1));//accType=
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "2", SoundPlayUtils.sound2));
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "3", SoundPlayUtils.sound3));
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "4", SoundPlayUtils.sound4));
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "5", SoundPlayUtils.sound5));
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "6", SoundPlayUtils.sound6));
                        sounds.add(new SoundBean(mContext.getString(R.string.sound) + "7", SoundPlayUtils.sound7));

                        popSound.setData(sounds, tv.getText().toString());
                        popSound.showPopupDownWindow();
                        break;
                }
            }
        });
        presenter.getSettingContentData();
    }

    private void chipClick(View v) {
        ImageView imageView = (ImageView) v;
        int chipSize = (int) imageView.getTag();
        boolean status = chipStatusMap.get(chipSize);
        chipStatusMap.put(chipSize, !status);
        boolean finalStatus = chipStatusMap.get(chipSize);
        if (finalStatus) {
            imageView.setBackgroundResource(R.drawable.shape_chip);
        } else {
            imageView.setBackgroundResource(0);
        }
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {
        Intent intent = new Intent(getActivity(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onGetTransferMoneyData(int type, String getBackStr, String data) {

    }

    @Override
    public void onLoginAgainFinish(String gameType) {

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
}
