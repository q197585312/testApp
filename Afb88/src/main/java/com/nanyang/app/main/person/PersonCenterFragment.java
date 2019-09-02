package com.nanyang.app.main.person;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BetCenter.Bean.PersonCenter;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 47184 on 2019/3/18.
 */

public class PersonCenterFragment extends BaseMoreFragment<PersonPresenter> {

    @Bind(R.id.edt_text)
    EditText edt_text;
    @Bind(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;
    BaseRecyclerAdapter<PersonCenter> adapter;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            adapter.setData(getCurrentData());
        }
    }

    private List<PersonCenter> getCurrentData() {
        List<PersonCenter> list = new ArrayList<>();
        PersonalInfo person = aty.getApp().getUser();
        SettingAllDataBean data = aty.getApp().getSettingAllDataBean();

        PersonCenter pc = new PersonCenter(getString(R.string.login_name), person.getLoginName());
        PersonCenter pc8 = new PersonCenter(getString(R.string.nike_name), data == null ? "" : data.getNickNameshow());
        PersonCenter pc1 = new PersonCenter(getString(R.string.currency), person.getCurCode2());
        PersonCenter pc2 = new PersonCenter(getString(R.string.cash_balance), AfbUtils.addComma(person.getBalances(), edt_text));
        PersonCenter pc3 = new PersonCenter(getString(R.string.outstanding_txn), AfbUtils.addComma(person.getEtotalstanding(), edt_text));
        PersonCenter pc4 = new PersonCenter(getString(R.string.min_bet_m), person.getMinLimit());
        PersonCenter pc5 = new PersonCenter(getString(R.string.bet_credit), AfbUtils.addComma(person.getCredit2(), edt_text));
        PersonCenter pc6 = new PersonCenter(getString(R.string.given_credit), AfbUtils.addComma(person.getTotalCredit(), edt_text));
        PersonCenter pc7 = new PersonCenter(getString(R.string.last_login_date), person.getLastLoginDate());
        list.add(pc);
        list.add(pc8);
        list.add(pc1);
        if (!AppConstant.IS_AGENT)
            list.add(pc2);
        list.add(pc3);
        list.add(pc4);
        if (!AppConstant.IS_AGENT)
            list.add(pc5);
        if (!AppConstant.IS_AGENT)
            list.add(pc6);
        list.add(pc7);
        return list;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.my_account));
        createPresenter(new PersonPresenter(this));
        aty = (BaseToolbarActivity) getActivity();
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rcContent.setLayoutManager(llm);
        adapter = new BaseRecyclerAdapter<PersonCenter>(mContext, getCurrentData(), R.layout.item_person_center) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PersonCenter item) {
                final View view = holder.getView(R.id.person_view_lin);
                if (item.getName().equals(getString(R.string.nike_name)) || item.getName().equals(getString(R.string.given_credit))) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
                TextView name = holder.getTextView(R.id.person_name);
                name.setText(item.getName());
                final TextView value = holder.getTextView(R.id.person_value);
                value.setText(item.getValue());
                if (item.getName().equals(getString(R.string.cash_balance)) && !StringUtils.isNull(item.getValue())) {
                    value.setText(AfbUtils.scientificCountingToString(item.getValue()));
                }
                if (item.getValue().startsWith("-")) {
                    value.setTextColor(Color.RED);
                } else {
                    value.setTextColor(Color.BLACK);
                }

                ImageView iv = holder.getImageView(R.id.person_img);
             /*   if (item.getName().equals(getString(R.string.nike_name))) {
                    value.setVisibility(View.GONE);
                    iv.setImageResource(R.mipmap.myacount);
                    iv.setVisibility(View.VISIBLE);
                } else {

                }*/
                iv.setVisibility(View.GONE);
                value.setVisibility(View.VISIBLE);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<PersonCenter>() {
            @Override
            public void onItemClick(View view, PersonCenter item, int position) {
                if (position == 1) {
                    final TextView value = view.findViewById(R.id.person_value);
                    BaseYseNoChoosePopupWindow baseYseNoChoosePopupWindow = new BaseYseNoChoosePopupWindow(mContext, value) {
                        TextView viewById;

                        @Override
                        protected void initView(View view) {
                            super.initView(view);
                            viewById = view.findViewById(R.id.tv_nick_name_tips);
                        }

                        @Override
                        protected void onClickSure(View v) {
                            final String nickName = getChooseMessage().getText().toString().trim();
                            if (nickName.length() < 5 || nickName.length() > 15) {
                                viewById.setText(R.string.nick_name_check_tips);
                                viewById.setVisibility(View.VISIBLE);
                            } else {
                                viewById.setVisibility(View.GONE);
                                presenter.saveNickName(nickName, new MainPresenter.CallBack<String>() {
                                    @Override
                                    public void onBack(String data) throws JSONException {
                                        if (data.contains("ok")) {
                                            ((BaseToolbarActivity) getBaseActivity()).getApp().getSettingAllDataBean().setNickNameshow(nickName);
                                            closePopupWindow();
                                            value.setText(nickName);
                                        } else {
                                            viewById.setVisibility(View.VISIBLE);
                                            JSONObject jsonObject = new JSONObject(data);
                                            viewById.setText(jsonObject.optString("message"));
                                        }
                                    }
                                });
                            }


                        }

                        @Override
                        protected int onSetLayoutRes() {
                            return R.layout.popupwindow_nick_name_edit_yes_no;
                        }
                    };
                    baseYseNoChoosePopupWindow.getChooseMessage().setText(value.getText());
                    ((BaseToolbarActivity) getBaseActivity()).onPopupWindowCreated(baseYseNoChoosePopupWindow, Gravity.CENTER);
                }

            }
        });

        rcContent.setAdapter(adapter);
    }
}
