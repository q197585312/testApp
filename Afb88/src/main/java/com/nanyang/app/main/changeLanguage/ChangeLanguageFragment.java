package com.nanyang.app.main.changeLanguage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ChangeLanguageFragment extends BaseMoreFragment<MainPresenter> implements ILanguageView<String> {
    @Bind(R.id.rc)
    RecyclerView rc;

    @Override
    public int onSetLayoutId() {
        return R.layout.frgment_change_language;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.setting));
        initRc();
        createPresenter(new MainPresenter(this));
    }

    private void initRc() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rc.setLayoutManager(mLayoutManager);
        List<MenuItemInfo<String>> dataList = new LanguageHelper(getBaseActivity()).getLanguageItems();
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(mContext, dataList, R.layout.item_change_language) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                String lag = AfbUtils.getLanguage(getActivity());
                TextView tv = holder.getView(R.id.selectable_text_content_tv);
                tv.setText(item.getText());
                if (lag.equals(item.getType())) {
                    tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, R.mipmap.oval_red_hook_red_choice, 0);
                } else {
                    tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, 0, 0);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
            @Override
            public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                AfbUtils.switchLanguage(item.getType(), getActivity());
                presenter.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
                    @Override
                    public void onBack(SettingAllDataBean data) throws JSONException {
                        onLanguageSwitchSucceed("");
                    }
                });
            }
        });
        rc.setAdapter(adapter);
    }

    @Override
    public void onGetData(String data) {

    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
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
}
