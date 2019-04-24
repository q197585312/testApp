package com.nanyang.app.main.person;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BetCenter.Bean.PersonCenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 47184 on 2019/3/18.
 */

public class PersonCenterFragment extends BaseMoreFragment {

    @Bind(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.my_account));
        aty = (BaseToolbarActivity) getActivity();
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rcContent.setLayoutManager(llm);
        List<PersonCenter> list = new ArrayList<>();
        PersonalInfo person = aty.getApp().getUser();
        PersonCenter pc = new PersonCenter(getString(R.string.login_name), person.getLoginName());
        PersonCenter pc8 = new PersonCenter(getString(R.string.nike_name), "");
        PersonCenter pc1 = new PersonCenter(getString(R.string.currency), person.getCurCode2());
        PersonCenter pc2 = new PersonCenter(getString(R.string.cash_balance), person.getBalances());
        PersonCenter pc3 = new PersonCenter(getString(R.string.outstanding_txn), person.getEtotalstanding());
        PersonCenter pc4 = new PersonCenter(getString(R.string.min_bet_m), person.getMinLimit());
        PersonCenter pc5 = new PersonCenter(getString(R.string.bet_credit), person.getCredit2());
        PersonCenter pc6 = new PersonCenter(getString(R.string.given_credit), person.getTotalCredit());
        PersonCenter pc7 = new PersonCenter(getString(R.string.last_login_date), person.getLastLoginDate());
        list.add(pc);
        list.add(pc8);
        list.add(pc1);
        list.add(pc2);
        list.add(pc3);
        list.add(pc4);
        list.add(pc5);
        list.add(pc6);
        list.add(pc7);
        BaseRecyclerAdapter<PersonCenter> adapter = new BaseRecyclerAdapter<PersonCenter>(mContext, list, R.layout.item_person_center) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PersonCenter item) {
                if (item.getName().equals(getString(R.string.nike_name)) || item.getName().equals(getString(R.string.given_credit))) {
                    View view = holder.getView(R.id.person_view_lin);
                    view.setVisibility(View.VISIBLE);
                }
                TextView name = holder.getTextView(R.id.person_name);
                name.setText(item.getName());
                TextView value = holder.getTextView(R.id.person_value);
                if (item.getName().equals(getString(R.string.nike_name))) {
                    value.setVisibility(View.GONE);
                    ImageView iv = holder.getImageView(R.id.person_img);
                    iv.setImageResource(R.mipmap.myacount);
                    iv.setVisibility(View.VISIBLE);
                } else {
                    value.setText(item.getValue());
                }
            }
        };
        rcContent.setAdapter(adapter);
    }
}
